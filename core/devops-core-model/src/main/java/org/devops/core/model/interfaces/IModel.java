package org.devops.core.model.interfaces;

import java.util.List;
import java.util.Map;

import org.devops.core.model.core.ModelSelect;
import org.devops.core.model.dto.ModelSet;
import org.devops.core.model.dto.ModelWhere;
import org.devops.core.model.emun.ModelCondition;
import org.devops.core.model.emun.ModelOperator;

public interface IModel<T, K> {

	void init();
	
	List<T> list();
	
	List<T> list(List<K> keys);
	
	<T2> List<T2> list(Class<T2> clazz);
	
	T get();
	
	T get(K key);
	
	<T2> T2 get(Class<T2> clazz);
	
	boolean isExists();
	
	boolean isNotExists();
	
	boolean isExists(K value);
	
	boolean isNotExists(K value);
	
	long count(K key);
	
	long count();
	
	<T2> List<T2> listSingle(Class<T2> clazz);
	
	<T2> T2 getSingle(Class<T2> clazz);
	
	String getString(K value);
	
	String getString();
	
	Integer getInteger(K value);
	
	Integer getInteger();
	
	Long getLong(K value);
	
	Long getLong();
	
	List<String> listString();
	
	List<Integer> listInteger();
	
	List<Long> listLong();
	
	IModel<T, K> ignoreOnce(boolean ignoreOnce);
	
	IModel<T, K> closeLog();
	
	IModel<T, K> closeCreateTable();
	
	IModel<T, K> tableSuffix(String suffix);
	
	IModel<T, K> tableSuffix(String suffixPrefix,String suffix);
	
	String getTableSuffix();
	
	////////////////////////////////////////////////////////////////////////////////////////
	IModel<T, K> limit(int size);
	
	IModel<T, K> limit(int start,int size);
	
	IModel<T, K> distinct();
	
	IModel<T, K> orderBy(String orderBy);
	
	IModel<T, K> orderBy(String...args);
	
	IModel<T, K> groupBy(String groupBy);
	
	IModel<T, K> fields(String fields);
	
	IModel<T, K> include(String fields);
	
	IModel<T, K> include(String ...args);
	
	IModel<T, K> include(String[] arrArgs,String ...args);
	
	IModel<T, K> exclude(String fields);
	
	IModel<T, K> exclude(String ...args);
	
	IModel<T, K> exclude(String[] arrArgs,String ...args);
	
	//////////////////////////////////////////////////////////////////////////////////
	// where start
	//////////////////////////////////////////////////////////////////////////////////
	
	IModel<T, K> or(String column,Object value);
	
	IModel<T, K> and(String column,Object value);
	
	
	IModel<T, K> where(String column,Object value);
	
	IModel<T, K> where(String column,Object value,ModelOperator mo);
	
	IModel<T, K> where(String column,Object value,ModelCondition mc);
	
	IModel<T, K> where(ModelWhere mw);
	
	IModel<T, K> where(Object value);
	
	IModel<T, K> where(ModelWhere mw,ModelCondition mc);
	
	IModel<T, K> where(String column,ModelOperator mo);
	
	IModel<T, K> where(String column,Object value,ModelOperator mo,ModelCondition mc);
	
	IModel<T, K> whereFormat(String whereStr,Object ...args);
	
	IModel<T, K> whereFormat(ModelCondition mc,String whereStr,Object ...args);
	
	IModel<T, K> whereSQL(String sql);
	
	/////////////////////////////////////////////////////////////////////
	// where end
	/////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////
	// join start
	/////////////////////////////////////////////////////////////////////
	
	IModel<T, K> join(Object source,String sourceColumn,String sourceColumnAlias,String ...targetColumn);
	
	/////////////////////////////////////////////////////////////////////
	// join end
	/////////////////////////////////////////////////////////////////////
	
	
	/////////////////////////////////////////////////////////////////////
	// insert
	/////////////////////////////////////////////////////////////////////
	
	long insertByObject(Object insertModel,boolean ignorePriKey);
	
	long insertByObject(Object insertModel);
	
	long insert(T insertModel,boolean ignorePriKey);
	
	long insert(T insertModel);
	
	long insert(List<T> insertModel,boolean ignorePriKey);
	
	long insert(List<T> insertModel);

	default Object preprocessInsert(Object insertModel){
		return insertModel;
	};


	IModel<T, K> add(String column,Object value);
	
	IModel<T, K> add(String column,String valueColumn,Object value);
	
	IModel<T, K> sub(String column,Object value);
	
	IModel<T, K> sub(String column,String valueColumn,Object value);
	
	IModel<T, K> mcl(String column,Object value);
	
	IModel<T, K> mcl(String column,String valueColumn,Object value);
	
	IModel<T, K> divide(String column,Object value);
	
	IModel<T, K> divide(String column,String valueColumn,Object value);
	
	long update();
	
	long update(String column,Object value);
	
	long update(ModelSet updateModel);
	
	long updateByObject(Object updateModel);
	
	long update(T updateModel);
	
	long update(List<T> updateModel);
	
	//保存
	long saveByObject(Object saveModel);
	
	long save(T saveModel);
	
	long save(List<T> saveModel);
	
	String deleteSQL();
	
	long delete(K value);
	
	long delete();

	
	////////////////////////////////////////////////////////
	List<Map<String,Object>> query(String sql);

	List<Map<String,Object>> query(String sql,Map<String,Object> parameter);
	
	ModelSelect createSelect();

	void setModelDao(BaseModelDao modelDao);
}
