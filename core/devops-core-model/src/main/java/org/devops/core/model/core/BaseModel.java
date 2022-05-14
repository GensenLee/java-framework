package org.devops.core.model.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.devops.core.model.annotation.Table;
import org.devops.core.model.dto.ModelJoin;
import org.devops.core.model.dto.ModelSet;
import org.devops.core.model.dto.ModelWhere;
import org.devops.core.model.dto.ModelWhereExt;
import org.devops.core.model.dto.TableField;
import org.devops.core.model.emun.ModelParamType;
import org.devops.core.utils.util.ListUtil;
import org.devops.core.utils.util.StringUtil;

public class BaseModel {
	
	private String table;
	
	private String prefix;
	
	private Table annotationTable;

	private String priKey;
	
	private Boolean autoIncrement;
	
	
	private ThreadLocal<ModelWhereExt> modelWhereExtHolder = new ThreadLocal<ModelWhereExt>();
	
	private ThreadLocal<String> fieldsHolder = new ThreadLocal<String>();
	private ThreadLocal<String> excludeFieldsHolder = new ThreadLocal<String>();
	
	private ThreadLocal<ModelWhere> modelWhereHolder = new ThreadLocal<ModelWhere>();
	
	private ThreadLocal<ModelSet> updateSetModelHolder = new ThreadLocal<ModelSet>();
	
	private ThreadLocal<Boolean> distinctHolder = new ThreadLocal<Boolean>();
	
	private ThreadLocal<Boolean> ignoreOnceHolder = new ThreadLocal<Boolean>();
	
	private ThreadLocal<Boolean> closeLogHolder = new ThreadLocal<Boolean>();
	
	private ThreadLocal<Boolean> closeCreateTableHolder = new ThreadLocal<Boolean>();
	
	private ThreadLocal<ModelJoin> modelJoinHolder = new ThreadLocal<ModelJoin>();
	
	private ThreadLocal<String> tableSuffixHolder = new ThreadLocal<String>();
	
	private ThreadLocal<Map<String,Object>> parameterHolder = new ThreadLocal<Map<String,Object>>(); 
	
	private ThreadLocal<Map<String,Object>> targetParameterHolder = new ThreadLocal<Map<String,Object>>(); 
	
	protected List<TableField> liTableField;
	
	
	public BaseModel(String table,String prefix){
		this.table = table;
		this.prefix = prefix;
	}
	
	public ModelSet getUpdateModel(){
		if(updateSetModelHolder.get() == null){
			updateSetModelHolder.set(new ModelSet());
		}
		return updateSetModelHolder.get();
	}
	
	public String getRealTable(){
		return table;
	}

	public String getTable() {
		if(StringUtil.isNotEmpty(tableSuffixHolder.get())){
			return table + tableSuffixHolder.get();
		}
		return table;
	}

	protected void setTable(String table) {
		this.table = table;
	}
	
	

	protected Table getAnnotationTable() {
		return annotationTable;
	}

	protected void setAnnotationTable(Table annotationTable) {
		this.annotationTable = annotationTable;
	}

	protected String getPrefix() {
		return prefix;
	}

	protected void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	protected String getResource()
	{
		if(this.prefix == null || "".equals(this.prefix)){
			return "modelDao";
		}
		return this.prefix+"ModelDao";
	}

	public String getPriKey() {
		return priKey;
	}

	protected void setPriKey(String priKey) {
		this.priKey = priKey;
	}

	protected Boolean getAutoIncrement() {
		return autoIncrement == null?false:autoIncrement;
	}

	protected void setAutoIncrement(Boolean autoIncrement) {
		this.autoIncrement = autoIncrement;
	}
	
	protected ModelWhereExt getModelWhereExt() {
		if(modelWhereExtHolder.get() == null){
			modelWhereExtHolder.set(new ModelWhereExt());
		}
		return modelWhereExtHolder.get();
	}
	
	protected String getModelWhereExtString() {
		if(this.getAnnotationTable() != null && this.getAnnotationTable().parametric()){
			return this.getModelWhereExt().toStringParametric(getOrNewParameterWhere());
		}
		else{
			return this.getModelWhereExt().toString();
		}
		
	}
	
	protected ModelWhere getModelWhere() {
		if(modelWhereHolder.get() == null){
			modelWhereHolder.set(new ModelWhere());
		}
		return modelWhereHolder.get();
	}
	
	public boolean hasWhere(){
		return this.getModelWhere().hasWhere();
	}
	
	protected String getModelWhereString() {
		return getModelWhereString(getOrNewParameterWhere());
	}
	
	protected String getModelWhereString(List<Object> listParameterWhere) {
		String where = "";
		if(this.getAnnotationTable() != null && this.getAnnotationTable().parametric()){
			where = this.getModelWhere().toStringParametric(listParameterWhere);
		}else{
			where = this.getModelWhere().toString();
		}
		
		if(StringUtil.isEmpty(where)){
			return "";
		}
		return "WHERE "+where;
	}
	
	protected void setDistinct(Boolean distinct){
		distinctHolder.set(distinct);
	}
	
	protected String getDistinct(){
		 Boolean bDistinct = this.distinctHolder.get();
         if(bDistinct != null && bDistinct){
                 return " distinct ";
         }
         return "";
	}
	
	
	protected void setFields(String fields) {
		fieldsHolder.set(fields);
	}
	
	protected void setExcludeFields(String fields) {
		excludeFieldsHolder.set(fields);
	}
	
	protected String getExcludeFields() {
		if(excludeFieldsHolder.get() == null){
			return null;
		}
		return excludeFieldsHolder.get();
	}
	
	protected String getFields() {
		if(fieldsHolder.get() == null && excludeFieldsHolder.get() == null){
			return "*";
		}
		
		
		
		if(fieldsHolder.get() != null){
			return fieldsHolder.get();
		}
		
		List<String> fields = new ArrayList<String>();
		if(excludeFieldsHolder.get() != null && liTableField != null){
			for(TableField tableField : liTableField){
				fields.add("`"+tableField.getField()+"`");
			}
			for(String tmp : excludeFieldsHolder.get().split(",")){
				tmp = "`"+tmp.replaceAll("`", "").trim()+"`";
				fields.remove(tmp);
			}
		}
		String result = ListUtil.join(fields,",");
		if(StringUtil.isEmpty(result)){
			result = "*";
		}
		return result;
	}
	
	protected TableField getTableFiled(String field){
		if(liTableField != null){
			for(TableField tableField : liTableField){
				if(tableField.getField().equalsIgnoreCase(field)){
					return tableField;
				}
			}
		}
		return null;
	}
	
	protected void setIgnoreOnce(boolean ignoreOnce){
		ignoreOnceHolder.set(ignoreOnce);
	}
	
	protected boolean getIgnoreOnce(){
		return ignoreOnceHolder.get() == null?false:ignoreOnceHolder.get();
	}
	
	protected void setCloseLog(boolean closeLog){
		closeLogHolder.set(closeLog);
	}
	
	protected boolean isOpenLog(){
		return closeLogHolder.get() == null?true:!closeLogHolder.get();
	}
	
	
	protected void setCloseCreateTable(boolean closeLog){
		closeCreateTableHolder.set(closeLog);
	}
	
	protected boolean isOpenCreateTable(){
		return closeCreateTableHolder.get() == null?true:!closeCreateTableHolder.get();
	}
	
	protected ModelJoin getModelJoin() {
		return modelJoinHolder.get();
	}

	protected void setModelJoin(ModelJoin modelJoin) {
		this.modelJoinHolder.set(modelJoin);
	}
	
	protected void setTableSuffix(String tableSuffix){
		tableSuffixHolder.set(tableSuffix);
	}
	
	protected String getTableSuffix(){
		return tableSuffixHolder.get() == null? "" : tableSuffixHolder.get();
	}
	
	protected void addParameter(ModelParamType modelParamType,Object value){
		Map<String,Object> mapParameter = getOrNewParameter();
		mapParameter.put(modelParamType.getCode(),value);
	}
	
	protected Map<String,Object> getOrNewParameter(){
		Map<String,Object> mapParameter = parameterHolder.get();
		if(mapParameter == null){
			mapParameter = new HashMap<String,Object>();
			parameterHolder.set(mapParameter);
		}
		return mapParameter;
	}
	
	@SuppressWarnings("unchecked")
	protected List<Object> getOrNewParameterWhere(){
		Map<String,Object> mapParameter = getOrNewParameter();
		Object list = mapParameter.get(ModelParamType.WHERE.getCode());
		if(list == null){
			list = new ArrayList<Object>();
			mapParameter.put(ModelParamType.WHERE.getCode(), list);
		}
		return (List<Object>)list;
	}
	
	@SuppressWarnings("unchecked")
	protected List<List<Object>> getOrNewParameterValues(){
		Map<String,Object> mapParameter = getOrNewParameter();
		Object list = mapParameter.get(ModelParamType.VALUES.getCode());
		if(list == null){
			list = new ArrayList<List<Object>>();
			mapParameter.put(ModelParamType.VALUES.getCode(), list);
		}
		return (List<List<Object>>)list;
	}
	
	protected Map<String,Object> getParameter(){
		return targetParameterHolder.get();
	}

	protected void clear() {
		clear(true);
	}
	
	protected void clear(boolean clearParameter) {
		if(clearParameter){
			targetParameterHolder.set(parameterHolder.get());
			parameterHolder.remove();
		}
		if(this.getIgnoreOnce()){
			ignoreOnceHolder.remove();
			return;
		}
		ignoreOnceHolder.remove();
		modelWhereExtHolder.remove();
		fieldsHolder.remove();
		excludeFieldsHolder.remove();
		modelWhereHolder.remove();
		updateSetModelHolder.remove();
		distinctHolder.remove();
		modelJoinHolder.remove();
		tableSuffixHolder.remove();
		closeCreateTableHolder.remove();
	}
	
	protected void clearParameter(){
		targetParameterHolder.remove();
	}
	
	public String listSQL(){
		String sql = String.format("SELECT %s %s FROM `%s` %s %s",
				this.getDistinct(),
				this.getFields(),this.getTable(),
				this.getModelWhereString(),
				this.getModelWhereExtString());
		this.clear();
		return sql;
	}
	
	

}
