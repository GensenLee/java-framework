package org.devops.core.model.core;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.devops.core.model.annotation.Table;
import org.devops.core.model.autoid.AutoIdGenerator;
import org.devops.core.model.dto.ModelJoin;
import org.devops.core.model.dto.ModelSet;
import org.devops.core.model.dto.ModelWhere;
import org.devops.core.model.dto.TableField;
import org.devops.core.model.emun.ModelCondition;
import org.devops.core.model.emun.ModelOperator;
import org.devops.core.model.exception.ModelNoSuchTableException;
import org.devops.core.model.exception.ModelNoWhereException;
import org.devops.core.model.executor.SqlExecutor;
import org.devops.core.model.interfaces.BaseModelDao;
import org.devops.core.model.interfaces.IModel;
import org.devops.core.model.interfaces.ModelAware;
import org.devops.core.utils.datasource.DataSourceMapper;
import org.devops.core.utils.exception.CommonRuntimeException;
import org.devops.core.utils.util.DateUtil;
import org.devops.core.utils.util.FastJsonUtils;
import org.devops.core.utils.util.IntUtil;
import org.devops.core.utils.util.ListUtil;
import org.devops.core.utils.util.LongUtil;
import org.devops.core.utils.util.MapUtil;
import org.devops.core.utils.util.StringUtil;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.util.CollectionUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Model<T, K> extends BaseModel implements IModel<T, K>{
	
	private Class<T> clazz;
	
	private Object initLock = new Object();
	
	private Map<String,Boolean> mapInit = new HashMap<String,Boolean>();
	
	protected Map<String,AutoIdGenerator<?>> mapAutoIdGenerator = new HashMap<>();
	
	private BaseModelDao modelDao;
	
	private SqlSessionFactory sqlSessionFactory;
	
	public Model(String table,String prefix,Class<T> clazz){
		super(table,prefix);
		this.clazz = clazz;
	}
	
	public Model(String table,String prefix,Table annotationTable,Class<T> clazz,BaseModelDao modelDao){
		super(table,prefix);
		this.clazz = clazz;
		this.modelDao = modelDao;
		this.setAnnotationTable(annotationTable);
	}
	
	public String getDataSourceName(){
		return DataSourceMapper.getName(clazz);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public synchronized void init(){
		boolean bInit = mapInit.get(this.getTable()) == null? false:mapInit.get(this.getTable()).booleanValue();
		if(bInit){
			return;
		}
		synchronized (initLock) {
			if(bInit){
				return;
			}
			
			final String tableSuffix = this.getTableSuffix();
			
			List<ModelAware> liModelAware = ModelHelper.getBeanList(ModelAware.class);
			
			List<Map<String,Object>> mapFields = null;
			try{
				mapFields = this.query("SHOW FULL COLUMNS FROM `"+this.getTable()+"`");
			}catch(BadSqlGrammarException e){
				if(e.getCause() instanceof SQLSyntaxErrorException){
					SQLSyntaxErrorException ee = (SQLSyntaxErrorException)e.getCause();
					if(ee.getErrorCode() == 1146){
						if(!this.isOpenCreateTable()) {
							// 关闭创建，适合那种分表查询时候关闭自动创建的场景
							this.clear();
							throw new ModelNoSuchTableException("已经关闭自动查询，找不到表:" + this.getTable());
						}
						//table 不存在
						if(this.getAnnotationTable() != null && this.getAnnotationTable().create()){
							if(this.isOpenLog()){
								log.debug("[Model.init] 表`{}`不存在，创建表",this.getTable());
							}
							//如果该表为自动创建的话
							String createSQL = ModelHelper.getCreateSQL(this.getClazz(),this.getTable());
							if(this.isOpenLog()){
								log.debug("[Model.init] SQL 语句:{}",createSQL);
							}
							query(createSQL);
							if(this.isOpenLog()){
								log.debug("[Model.init] SQL 成功创建表:`{}`",this.getTable());
							}
							
							ModelHelper.invokeWithThread(liModelAware, "onCreated", Model.this,tableSuffix);
							
							
							//判断是否有初始化数据
							List initData = ModelHelper.getInitData(clazz);
							String initSQL = ModelHelper.getInitSQL(clazz);
							
							if((initData != null && initData.size() > 0) || StringUtil.isNotEmpty(initSQL)) {
								if(StringUtil.isNotEmpty(initSQL)) {
									this.ignoreOnce(true).query(initSQL);
								}else if(initData != null && initData.size() > 0){
									this.ignoreOnce(true).insert(initData);
								}
							}else{
								init();
							}
							
							ModelHelper.invokeWithThread(liModelAware, "onDataInitialized", Model.this,tableSuffix);
							return;
						}
					}
				} else {
					if(this.isOpenLog()){
						log.error("model error:", e);
					}
					return;
				}
				
			}
			
			// 获取当前class的autoId
			mapAutoIdGenerator = ModelHelper.getMapAutoId(this.getClazz(), this.getTable());
			
			liTableField = ModelHelper.convert(mapFields, TableField.class);
			for(TableField tableField : liTableField){
				if("PRI".equalsIgnoreCase(tableField.getKey())){
					this.setPriKey(tableField.getField());
				}
				if("auto_increment".equalsIgnoreCase(tableField.getExtra())){
					this.setAutoIncrement(true);
				}
			}
			//判断列是否相等，找出不存在的列
			if(this.getAnnotationTable() != null && this.getAnnotationTable().create()){
				List<TableField> currentTableField = ModelHelper.getTableFields(this.getClazz());
				Map<String,TableField> mapTableField = MapUtil.toMapString(currentTableField, "field");
				for(TableField tableField : liTableField){
					mapTableField.remove(tableField.getField());
				}
				if(mapTableField.size() != 0){
					//设置新的field
					String sql = ModelHelper.getAddColumnSQL(this.getClazz(),mapTableField,this.getTable());
					if(this.isOpenLog()){
						log.debug("[Model.init] SQL 语句:{}",sql);
					}
					query(sql);
					init();
					ModelHelper.invokeWithThread(liModelAware, "onColumnAdded", Model.this,tableSuffix,mapTableField);
					return;
				}
			}
			mapInit.put(this.getTable(),true);
			ModelHelper.invokeWithThread(liModelAware, "onInitialized", Model.this,tableSuffix);
		}
	}
	
	@Override
	public List<T> list(){
		init();
		return list(this.getClazz());
	}
	
	@Override
	public List<T> list(List<K> keys){
		init();
		if(keys != null){
			this.where(this.getPriKey(),keys, ModelOperator.IN);
		}else{
			this.where(this.getPriKey(),"-999999",ModelOperator.IN);
		}
		return list(this.getClazz());
	}
	
	@Override
	public <T2> List<T2> list(Class<T2> clazz){
		init();
		if(this.getModelJoin() != null){
			return listJoin(clazz);
		}
		return ModelHelper.convert(query(listSQL()), clazz);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes"})
	private <T2> List<T2> listJoin(Class<T2> clazz){
		init();
		ModelJoin modelJoin = this.getModelJoin();
		
		List<Object> whereCondition = new ArrayList<Object>();
		
		List<Object> listSource = new ArrayList<Object>();
		if(!(modelJoin.getSource() instanceof List)){
			listSource.add(modelJoin.getSource());
		}else{
			listSource = (List)modelJoin.getSource();
		}
		
		for(Object o : listSource){
			whereCondition.add(ModelHelper.getValue(o, modelJoin.getSourceColumn()));
		}
		
		if(whereCondition.isEmpty()){
			this.clear();
			return new ArrayList<T2>();
		}
		
		this.include(modelJoin.getTargetColumn().keySet().toArray(new String[0]),modelJoin.getSourceColumnAlias())
		.where(modelJoin.getSourceColumnAlias(),whereCondition,ModelOperator.IN);
		
		List<Map<String,Object>> list = query(listSQL());
		
		Map<Object,Map<String,Object>> listMap = new HashMap<Object,Map<String,Object>>();
		
		for(Map<String,Object> l : list){
			listMap.put(l.get(modelJoin.getSourceColumnAlias()).toString(), l);
		}
		
		List<Map> mapSource = ModelHelper.convert(listSource, Map.class);
		
		for(Map s : mapSource){
			//获取realname
			String realName = ModelHelper.getAliasNameByAnnotation(listSource.get(0).getClass(), modelJoin.getSourceColumn());
			Object o = s.get(realName);
			if(o == null){
				continue;
			}
			Object tmp = listMap.get(o.toString());
//			if(tmp == null){
//				continue;
//			}
			for(String key : modelJoin.getTargetColumn().keySet()){
				Object v = ModelHelper.getValue(tmp, key);
				s.put(modelJoin.getTargetColumn().get(key), v == null? "": v);
			}
		}
		
		return ModelHelper.convert(mapSource, clazz);
	}
	
	@Override
	public T get(){
		init();
		return get(this.getClazz());
	}
	
	@Override
	public T get(Object key){
		init();
		if(key != null){
			this.where(this.getPriKey(),key);
		}
		return this.get();
	}
	
	@Override
	public <T2> T2 get(Class<T2> clazz){
		init();
		if(this.getModelJoin() != null){
			List<T2> result = listJoin(clazz);
			if(result == null || result.isEmpty()){
				return null;
			}
			return result.get(0);
		}
		this.limit(1);
		
		List<T2> list = null;
		
		if(!clazz.isAssignableFrom(Map.class)){
			list = ModelHelper.convert(query(listSQL()), clazz);
		}else{
			List<T> tmpList = ModelHelper.convert(query(listSQL()), this.getClazz());
			list = FastJsonUtils.getBeanList(FastJsonUtils.toJsonString(tmpList), clazz);
			tmpList.clear();
		}
		
		if(list == null || list.size() == 0){
			return null;
		}
		return list.get(0);
	}
	
	@Override
	public boolean isExists(){
		return this.count() != 0;
	}
	
	@Override
	public boolean isNotExists(){
		return this.count() == 0;
	}
	
	@Override
	public boolean isExists(K value){
		return this.count(value) != 0;
	}
	
	@Override
	public boolean isNotExists(K value){
		return this.count(value) == 0;
	}
	
	@Override
	public long count(K key){
		init();
		if(key != null){
			this.where(this.getPriKey(),key);
		}
		return count();
	}
	
	@Override
	public long count(){
		this.fields("count(1)");
		return this.getLong();
	}
	
	@Override
	public <T2> List<T2> listSingle(Class<T2> clazz){
		init();
		List<Map<String,Object>> list = query(this.listSQL());
		List<T2> result = new ArrayList<T2>();
		for(Map<String,Object> m : list){
			for(String key : m.keySet()){
				result.add(ModelHelper.convertSingle(m.get(key), clazz));
				break;
			}
		}
		return result;
	}
	
	@Override
	public <T2> T2 getSingle(Class<T2> clazz){
		this.limit(1);
		List<T2> list = listSingle(clazz);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		return list.get(0);
	}
	
	@Override
	public String getString(Object value){
		this.where(this.getPriKey(),value);
		return getSingle(String.class);
	}
	
	@Override
	public String getString(){
		return getSingle(String.class);
	}
	
	@Override
	public Integer getInteger(Object value){
		this.where(this.getPriKey(),value);
		return getSingle(Integer.class);
	}
	
	@Override
	public Integer getInteger(){
		return getSingle(Integer.class);
	}
	
	@Override
	public Long getLong(Object value){
		this.where(this.getPriKey(),value);
		return getSingle(Long.class);
	}
	
	@Override
	public Long getLong(){
		return getSingle(Long.class);
	}
	
	@Override
	public List<String> listString(){
		return listSingle(String.class);
	}
	
	@Override
	public List<Integer> listInteger(){
		return listSingle(Integer.class);
	}
	
	@Override
	public List<Long> listLong(){
		return listSingle(Long.class);
	}
	
	@Override
	public Model<T, K> ignoreOnce(boolean ignoreOnce){
		this.setIgnoreOnce(ignoreOnce);
		return this;
	}
	
	@Override
	public Model<T, K> tableSuffix(String suffix){
		return tableSuffix("_",suffix);
	}
	@Override
	public Model<T, K> tableSuffix(String suffixPrefix,String suffix){
		if(StringUtil.isEmpty(suffix)) {
			return this;
		}
		if(StringUtil.isEmpty(suffixPrefix)){
			suffixPrefix = "";
		}
		this.setTableSuffix(suffixPrefix+suffix);
		return this;
	}
	
	@Override
	public String getTableSuffix() {
		return super.getTableSuffix();
	}
	////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Model<T, K> limit(int size){
		this.getModelWhereExt().limit(size);
		return this;
	}
	
	@Override
	public Model<T, K> limit(int start,int size){
		this.getModelWhereExt().limit(start,size);
		return this;
	}
	
	@Override
	public Model<T, K> distinct(){
		this.setDistinct(true);
		return this;
	}
	
	@Override
	public Model<T, K> orderBy(String orderBy){
		this.getModelWhereExt().orderBy(orderBy);
		return this;
	}
	
	@Override
	public Model<T, K> orderBy(String...args){
		List<String> orderBy = new ArrayList<String>();
		String key = null;
		for(String arg : args){
			if(key == null){
				key = arg;
				if(key.indexOf("`") == -1){
					key = "`"+key+"`";
				}
				continue;
			}
			orderBy.add(key+" "+arg);
			key = null;
		}
		if(key != null){
			orderBy.add(key+" ASC");
		}
		this.getModelWhereExt().orderBy(ListUtil.join(orderBy));
		return this;
	}
	
	@Override
	public Model<T, K> groupBy(String groupBy){
		this.getModelWhereExt().groupBy(groupBy);
		return this;
	}
	
	@Override
	public Model<T, K> fields(String fields){
		this.setFields(fields);
		return this;
	}
	
	@Override
	public Model<T, K> include(String fields){
		this.setFields(fields);
		return this;
	}
	
	@Override
	public Model<T, K> include(String ...args){
		this.setFields(ListUtil.join(args,","));
		return this;
	}
	
	@Override
	public Model<T, K> include(String[] arrArgs,String ...args){
		String fields = ListUtil.join(arrArgs,",");
		if(args.length != 0){
			fields+=","+ListUtil.join(args,",");
		}
		this.setFields(fields);
		return this;
	}
	
	@Override
	public Model<T, K> exclude(String fields){
		this.setExcludeFields(fields);
		return this;
	}
	
	@Override
	public Model<T, K> exclude(String ...args){
		this.setExcludeFields(ListUtil.join(args,","));
		return this;
	}
	
	@Override
	public Model<T, K> exclude(String[] arrArgs,String ...args){
		String excludeFields = ListUtil.join(arrArgs,",");
		if(args.length != 0){
			excludeFields+=","+ListUtil.join(args,",");
		}
		this.setExcludeFields(excludeFields);
		return this;
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	// where start
	//////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public Model<T, K> or(String column,Object value){
		return this.where(column, value,ModelCondition.OR);
	}
	
	@Override
	public Model<T, K> and(String column,Object value){
		return this.where(column, value,ModelCondition.AND);
	}
	
	@Override
	public Model<T, K> where(String column,Object value){
		return this.where(column, value,null,null);
	}
	
	@Override
	public Model<T, K> where(String column,Object value,ModelOperator mo){
		return this.where(column, value,mo,null);
	}

	@Override
	public Model<T, K> where(String column,Object value,ModelCondition mc){
		return this.where(column, value,null,mc);
	}

	@Override
	public Model<T, K> where(ModelWhere mw){
		this.getModelWhere().add(mw);
		return this;
	}

	@Override
	public Model<T, K> where(Object value){
		this.init();
		return this.where(this.getPriKey(),value);
	}

	@Override
	public Model<T, K> where(ModelWhere mw,ModelCondition mc){
		this.getModelWhere().add(mw,mc);
		return this;
	}

	@Override
	public Model<T, K> where(String column,ModelOperator mo){
		return this.where(column, null,mo,null);
	}

	@Override
	public Model<T, K> where(String column,Object value,ModelOperator mo,ModelCondition mc){
		if(value == null && !ModelOperator.IS_NULL.equals(mo) && !ModelOperator.IS_NOT_NULL.equals(mo) && !ModelOperator.PLAIN.equals(mo)){
			return this;
		}
		this.getModelWhere().add(column,value,mo,mc);
		return this;
	}

	@Override
	public Model<T, K> whereFormat(String whereStr,Object ...args){
		this.getModelWhere().addFormat(whereStr,args);
		return this;
	}

	@Override
	public Model<T, K> whereFormat(ModelCondition mc,String whereStr,Object ...args){
		this.getModelWhere().addFormat(mc,whereStr,args);
		return this;
	}

	@Override
	public Model<T, K> whereSQL(String sql){
		this.getModelWhere().setWhereString(sql);
		return this;
	}
	
	/////////////////////////////////////////////////////////////////////
	// where end
	/////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////
	// join start
	/////////////////////////////////////////////////////////////////////

	@Override
	public Model<T, K> join(Object source,String sourceColumn,String sourceColumnAlias,String ...targetColumn){
		ModelJoin modelJoin = new ModelJoin(); 
		modelJoin.setSource(source);
		modelJoin.setSourceColumn(sourceColumn);
		modelJoin.setSourceColumnAlias(sourceColumnAlias);
		Map<String,String> targetColumnMap = new HashMap<String,String>();
		String key = null;
		for(String strTargetColumn : targetColumn){
			if(key == null){
				key = strTargetColumn;
			}else{
				targetColumnMap.put(key, strTargetColumn);
				key = null;
			}
		}
		if(targetColumn.length % 2 == 1){
			targetColumnMap.put(key, key);
		}
		modelJoin.setTargetColumn(targetColumnMap);
		this.setModelJoin(modelJoin);
		return this;
	}
	
	/////////////////////////////////////////////////////////////////////
	// join end
	/////////////////////////////////////////////////////////////////////
	
	
	/////////////////////////////////////////////////////////////////////
	// insert
	/////////////////////////////////////////////////////////////////////

	protected String insertSQL(Object insertModel,boolean ignorePriKey){
		
		String fields = getFields().trim();
		if(fields.equals("*")){
			fields = "";
		}
		List<String> includeFields = new ArrayList<String>();
		for(String field : fields.split(",")){
			if(StringUtil.isEmpty(field)){
				continue;
			}
			includeFields.add(field.replaceAll("`", ""));
		}
		
		String valueSql = "";
		
		if(this.getAnnotationTable() != null && this.getAnnotationTable().parametric()){
			valueSql = ModelHelper.getInsertValueParametric(insertModel,includeFields, liTableField, ignorePriKey,this.getOrNewParameterValues());
		}else{
			valueSql = ModelHelper.getInsertValue(insertModel,includeFields, liTableField, ignorePriKey);
		}
		
		String sql = String.format("INSERT INTO `%s` %s VALUES %s",this.getTable(),
				ModelHelper.getInsertColumn(insertModel,includeFields, liTableField, ignorePriKey),
				valueSql
				);
		this.clear();
		return sql;
	}
	
	@Override
	public long insertByObject(Object insertModel,boolean ignorePriKey){
		init();

		insertModel = preprocessInsert(insertModel);

		boolean isOpenLog = this.isOpenLog();
		long result =  LongUtil.toLong(query(insertSQL(insertModel,ignorePriKey)).get(0).get("affactRow"));
		result = 1;
		if(result > 0 && this.getAutoIncrement()){
			if(!isOpenLog) {
				this.closeLog();
			}
			//获取lastInsertId
			long lastInsertId = LongUtil.toLong(query("SELECT LAST_INSERT_ID() as id").get(0).get("id"));
			ModelHelper.setLastInsertId(insertModel, this.getPriKey(), lastInsertId);
		}
		
		return result;
	}
	
	@Override
	public long insertByObject(Object insertModel){
		return insertByObject(insertModel ,this.getAutoIncrement());
	}
	
	
	@Override
	public long insert(T insertModel,boolean ignorePriKey){
		return insertByObject(insertModel, ignorePriKey);
	}
	
	@Override
	public long insert(T insertModel){
		return insertByObject(insertModel,this.getAutoIncrement());
	}
	
	@Override
	public long insert(List<T> insertModel, boolean ignorePriKey) {
		return insertByObject(insertModel, ignorePriKey);
	}

	@Override
	public long insert(List<T> insertModel) {
		return insertByObject(insertModel,this.getAutoIncrement());
	}

	@Override
	public Object preprocessInsert(Object insertModel) {
		init();
		// 设置ID
		ModelHelper.setAutoId(insertModel, mapAutoIdGenerator);
		return insertModel;
	}

	protected String updateSQL(Object updateModel){
		return updateSQL(updateModel,true);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	protected String updateSQL(Object updateModel,String fields,boolean cleanWhere,boolean ignorePriKey){
		String sql = "";
		if(fields.equals("*")){
			fields = "";
		}
		if(!this.hasWhere()){
			Object priValue = ModelHelper.getValue(updateModel, this.getPriKey());
			if(priValue == null){
				priValue = ModelHelper.getValue(updateModel, "_value");
				if(priValue == null){
					throw new ModelNoWhereException("请传入更新条件");
				}else{
					this.where(ModelHelper.getValue(updateModel, "_column").toString(), priValue);
					cleanWhere = true;
				}
			}else{
				this.where(this.getPriKey(), priValue);
				cleanWhere = true;
			}
		}
		List<String> includeField = new ArrayList<String>();
		for(String field : fields.split(",")){
			if(StringUtil.isEmpty(field)){
				continue;
			}
			includeField.add(field.replaceAll("`", ""));
		}
		
		String valueSql = "";
		String whereSql = "";
		if(this.getAnnotationTable() != null && this.getAnnotationTable().parametric()){
			valueSql = ModelHelper.getSetSQLParametric(updateModel,includeField,this.liTableField,ignorePriKey,this.getOrNewParameterValues());
			whereSql = this.getModelWhereString(this.getOrNewParameterValues().get(this.getOrNewParameterValues().size() - 1));
		}else{
			valueSql = ModelHelper.getSetSQL(updateModel,includeField,this.liTableField,ignorePriKey);
			whereSql = this.getModelWhereString();
		}
		
		sql = String.format("UPDATE `%s` SET %s %s", this.getTable(),
				valueSql,
				whereSql);
		if(cleanWhere){
			this.clear(false);
		}
		return sql;
	}

	
	@SuppressWarnings("rawtypes")
	protected String updateSQL(Object updateModel,boolean ignorePriKey){
		String fields = getFields().trim();
		String resultSQL = "";
		if(updateModel instanceof List){
			List<String> liSQL = new ArrayList<String>();
      String tableSuffix = this.getTableSuffix();
			for(Object o : (List)updateModel){
				if(o instanceof ModelSet){
					o = ((ModelSet)o).get();
				}
				String sql = this.updateSQL(o,fields,false,ignorePriKey);
				this.setTableSuffix(tableSuffix);
				liSQL.add(sql);
			}
			resultSQL = ListUtil.join(liSQL,";");
		}else{
			resultSQL = this.updateSQL(updateModel,fields,true,ignorePriKey);
		}
		this.clear();
		return resultSQL;
	}

	@Override
	public Model<T, K> add(String column,Object value){
		this.getUpdateModel().add(column, value,ModelOperator.ADD);
		return this;
	}
	
	@Override
	public Model<T, K> add(String column,String valueColumn,Object value){
		this.getUpdateModel().add(column, valueColumn,value,ModelOperator.ADD);
		return this;
	}
	
	@Override
	public Model<T, K> sub(String column,Object value){
		this.getUpdateModel().add(column, value,ModelOperator.SUB);
		return this;
	}
	
	@Override
	public Model<T, K> sub(String column,String valueColumn,Object value){
		this.getUpdateModel().add(column, valueColumn,value,ModelOperator.SUB);
		return this;
	}
	
	@Override
	public Model<T, K> mcl(String column,Object value){
		this.getUpdateModel().add(column, value,ModelOperator.MCL);
		return this;
	}
	
	@Override
	public Model<T, K> mcl(String column,String valueColumn,Object value){
		this.getUpdateModel().add(column, valueColumn,value,ModelOperator.MCL);
		return this;
	}
	
	@Override
	public Model<T, K> divide(String column,Object value){
		this.getUpdateModel().add(column, value,ModelOperator.DIVIDE);
		return this;
	}
	
	@Override
	public Model<T, K> divide(String column,String valueColumn,Object value){
		this.getUpdateModel().add(column, valueColumn,value,ModelOperator.DIVIDE);
		return this;
	}

	@Override
	public long update(){
		if(this.getUpdateModel().isEmpty()){
			this.clear();
			return 0;
		}
		return this.update(this.getUpdateModel());
	}

	@Override
	public long update(String column,Object value){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(column, value);
		return updateByObject(map);
	}

	@Override
	public long update(ModelSet updateModel){
		return updateByObject(updateModel.get());
	}

	@Override
	public long updateByObject(Object updateModel){
		init();
		long result =  LongUtil.toLong(query(updateSQL(updateModel)).get(0).get("affactRow"));
		return result;
	}
	
	@Override
	public long update(T updateModel){
		return updateByObject(updateModel);
	}
	
	@Override
	public long update(List<T> updateModel){
		return updateByObject(updateModel);
	}
	
	@Override
	public String deleteSQL(){
		String sql = String.format("DELETE FROM `%s` %s", this.getTable(),
				this.getModelWhereString());
		this.clear();
		return sql;
	}
	
	//保存
	@Override
	public long saveByObject(Object saveModel){
		this.init();
		if(StringUtil.isEmpty(this.getPriKey())){
			return this.insertByObject(saveModel);
		}
		Object key = ModelHelper.getValue(saveModel, this.getPriKey());
		if(key == null){
			return this.insertByObject(saveModel);
		}else{
			return this.updateByObject(saveModel);
		}
	}
	
	//保存
	@Override
	public long save(T saveModel){
		return saveByObject(saveModel);
	}
	
	@Override
	public long save(List<T> saveModel){
		return saveByObject(saveModel);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public long delete(Object value){
		this.init();
		if(value instanceof List){
			if(ModelHelper.isInstance((List)value, this.getClazz())) {
				this.where(this.getPriKey(),ModelHelper.getValueList((List)value, this.getPriKey()),ModelOperator.IN);
			} else {
				this.where(this.getPriKey(),value,ModelOperator.IN);
			}
		}else{
			if(this.getClazz().isInstance(value)) {
				this.where(this.getPriKey(),ModelHelper.getValue(value, this.getPriKey()));
			} else {
				this.where(this.getPriKey(),value);
			}
			
		}
		return delete();
	}
	
	@Override
	public long delete(){
		init();
		if(!this.hasWhere()){
			throw new ModelNoWhereException("请传入删除条件，防止误删");
		}
		int result =  IntUtil.toInt(query(deleteSQL()).get(0).get("affactRow"));
		return result;
	}

	
	////////////////////////////////////////////////////////
	@Override
	public List<Map<String,Object>> query(String sql) 
	{
		if(this.getAnnotationTable() != null && this.getAnnotationTable().parametric()){
			return parametricQuery(sql,null);
		}
		return xmlQuery(sql);
	}
	
	@Override
	public List<Map<String,Object>> query(String sql,Map<String,Object> parameter) {
		return parametricQuery(sql,parameter);
	}
	
	private String logParameterToString(Object o){
		if(o == null){
			return "null";
		}
		if(o instanceof Date){
			return DateUtil.getDateTimeFormat((Date)o);
		}
		return o.toString();
	}
	@SuppressWarnings("rawtypes")
	private void logParameter(Map<String,Object> parameter){
		if(CollectionUtils.isEmpty(parameter)){
			return;
		}
		String msg = "Parameter -- ";
		List<String> listMsg = new ArrayList<String>();
		for(String key : parameter.keySet() ){
			listMsg.clear();
			List listParam = (List)parameter.get(key);
			List<String> listValueMsg = new ArrayList<String>();
			for(int i = 0;i<listParam.size();i++){
				Object param = listParam.get(i);
				if(param instanceof List){
					listValueMsg.clear();
					for(Object p : (List)param){
						listValueMsg.add("'"+logParameterToString(p)+"'");
					}
					
					listMsg.add("["+ListUtil.join(listValueMsg)+"]");
				}else{
					listMsg.add("(index:" + (i+1) + ";type:"+ (param == null?"null":param.getClass().getSimpleName()) + ";value:" + logParameterToString(param)+")");
				}
				
			}
			msg += key +":{" + ListUtil.join(listMsg) + "}";
		}
		if(this.isOpenLog()){
			log.info(msg);
		}
	}
	
	private List<Map<String,Object>> parametricQuery(String sql,Map<String,Object> parameter)
	{
	
		if(sqlSessionFactory == null){
			if (getDataSourceName() != null && getDataSourceName().equalsIgnoreCase("default")) {
				sqlSessionFactory = ModelHelper.getBean(SqlSessionFactory.class);
			} else {
				sqlSessionFactory = ModelHelper.getBean(getDataSourceName(), SqlSessionFactory.class);
			}
		}
		
		if(sqlSessionFactory == null) {
			throw new CommonRuntimeException(this.getClazz().getName() + " 请先配置数据源:" + (StringUtil.isEmpty(getDataSourceName())?"null":getDataSourceName()));
		}
			
		long start = System.currentTimeMillis();
		if(CollectionUtils.isEmpty(parameter)){
			parameter = this.getParameter();
		}
		
		SqlSession sqlSession = SqlSessionUtils.getSqlSession(sqlSessionFactory);
		
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		
		String logMsg = "";
		long logSize = 0;
		
		try{
			SqlExecutor sqlExecutor = new SqlExecutor(sqlSession.getConnection());
			if (sql.toLowerCase().indexOf("select ") == 0) {
				result = sqlExecutor.select(sql,parameter);
				logMsg = "Total Row -- ";
				logSize = result.size();
			} else if (sql.toLowerCase().indexOf("insert ") == 0 || sql.toLowerCase().indexOf("create ") == 0 || sql.toLowerCase().indexOf("alter ") == 0) {
			    Map<String,Object> affactRow = new HashMap<String,Object>();
			    long effectRow = sqlExecutor.insert(sql,parameter);
			    affactRow.put("affactRow", effectRow);
			    result.add(affactRow);
			    
			    logMsg = "Affected Row -- ";
				logSize = effectRow;
			} else if (sql.toLowerCase().indexOf("update ") == 0) {
			    Map<String,Object> affactRow = new HashMap<String,Object>();
			    long effectRow = sqlExecutor.update(sql,parameter);

	            affactRow.put("affactRow", effectRow);
	            result.add(affactRow);
	            
	            logMsg = "Affected Row -- ";
				logSize = effectRow;
			} else if (sql.toLowerCase().indexOf("delete ") == 0) {
			    Map<String,Object> affactRow = new HashMap<String,Object>();
			    long effectRow = sqlExecutor.delete(sql,parameter);

	            affactRow.put("affactRow", effectRow);
	            result.add(affactRow);
	            
	            logMsg = "Affected Row -- ";
				logSize = effectRow;
			}else{
				result = sqlExecutor.select(sql,parameter);
				logMsg = "Total Row -- ";
				logSize = result.size();
			}
		}catch(SQLException me){
			throw new BadSqlGrammarException("parametricQuery",sql,me);
		} catch(RuntimeException cre){
			throw cre;
		} catch(Exception e){
			throw new CommonRuntimeException(e);
		}finally{
			if(this.isOpenLog()){
				log.info("SQL -- " + sql);
			}
			logParameter(parameter);
			if(this.isOpenLog()){
				log.info("{}{} time used:{}ms", logMsg,logSize,System.currentTimeMillis() - start);
			}
			
			SqlSessionUtils.closeSqlSession(sqlSession, sqlSessionFactory);
			this.clearParameter();
			this.setCloseLog(false);
		}
		return result;
	}
	
	
	private List<Map<String,Object>> xmlQuery(String sql){
		
		if(this.isOpenLog()){
			log.debug(sql);
		}
		
		try {
			if(modelDao == null){
				modelDao = ModelHelper.getBaseModelDao(this.getResource());
			}
			
			List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
			if (sql.toLowerCase().indexOf("select ") == 0) {
				return modelDao.select(sql);
			} else if (sql.toLowerCase().indexOf("insert ") == 0) {
			    Map<String,Object> affactRow = new HashMap<String,Object>();
			    affactRow.put("affactRow", modelDao.insert(sql));
			    result.add(affactRow);
			} else if (sql.toLowerCase().indexOf("update ") == 0) {
			    Map<String,Object> affactRow = new HashMap<String,Object>();
	            affactRow.put("affactRow", modelDao.update(sql));
	            result.add(affactRow);
			} else if (sql.toLowerCase().indexOf("delete ") == 0) {
			    Map<String,Object> affactRow = new HashMap<String,Object>();
	            affactRow.put("affactRow", modelDao.delete(sql));
	            result.add(affactRow);
			}else{
				return modelDao.select(sql);
			}
			return result;
		} catch(RuntimeException ce){
			throw ce;
		}catch(Exception e) {
			throw new CommonRuntimeException(e);
		} finally {
			this.setCloseLog(false);
		}
	}

	public Class<T> getClazz() {
		return clazz;
	}

	protected void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	@Override
	public ModelSelect createSelect(){
		return new ModelSelect(this.clazz);
	}

	@Override
	public void setModelDao(BaseModelDao modelDao) {
		this.modelDao = modelDao;
	}
	
	public Model<T, K> clearAll(){
		super.clear();
		return this;
	}

	@Override
	public IModel<T, K> closeLog() {
		this.setCloseLog(true);
		return this;
	}
	
	@Override
	public IModel<T, K> closeCreateTable() {
		this.setCloseCreateTable(true);
		return this;
	}
	
}
