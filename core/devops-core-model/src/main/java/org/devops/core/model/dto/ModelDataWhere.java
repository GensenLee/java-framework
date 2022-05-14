package org.devops.core.model.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.devops.core.model.core.ModelHelper;
import org.devops.core.model.emun.ModelCondition;
import org.devops.core.model.emun.ModelOperator;
import org.devops.core.utils.util.FastJsonUtils;
import org.devops.core.utils.util.StringUtil;
import org.springframework.util.CollectionUtils;

public class ModelDataWhere<T>{
	
	private String column;
	private Object value;
	private ModelCondition mc;
	private ModelOperator mo;
	private String whereString;
	private Boolean _not = false;
	private List<ModelDataWhere<T>> listSubWhere;
	
	private List<T> result = Collections.synchronizedList(new ArrayList<T>()); 
	
	private Set<String> setValue;
	
	public ModelDataWhere(){
	}
	
	public ModelDataWhere(ModelWhere mw){
		this.copy(mw);
	}
	
	public ModelDataWhere(List<ModelWhere> list){
		copyList(list);
	}
	
	private void copy(ModelWhere mw) {
		this.column = mw.getColumn();
		this.value = mw.getValue();
		this.mc = mw.getModelCondition();
		this.mo = mw.getModelOperator();
		copyList(mw.getSubWhere());
	}
	private void copyList(List<ModelWhere> list) {
		if(list == null || list.size() == 0) {
			return;
		}
		listSubWhere = new ArrayList<>();
		for(ModelWhere modelWhere : list) {
			listSubWhere.add(new ModelDataWhere<T>(modelWhere));
		}
	}
	
	public ModelDataWhere(String column,Object value,ModelOperator mo,ModelCondition mc){
		if(value instanceof List && mo == null){
			mo = ModelOperator.IN;
		}
		this.column = column;
		this.value = value;
		this.mo = mo;
		this.mc = mc;
	}
	
	public ModelDataWhere<T> where(String column,Object value,ModelOperator mo,ModelCondition mc){
		if(value instanceof List && mo == null){
			mo = ModelOperator.IN;
		}
		this.column = column;
		this.value = value;
		this.mo = mo;
		this.mc = mc;
		return this;
	}
	
	public ModelDataWhere<T> add(ModelDataWhere<T> mw){
		if(mw == null || !mw.hasWhere()){
			return this;
		}
		if(listSubWhere == null){
			listSubWhere = new ArrayList<ModelDataWhere<T>>();
		}
		listSubWhere.add(mw);
		return this;
	}
	
	public ModelDataWhere<T> add(ModelDataWhere<T> mw,ModelCondition mc){
		if(mw == null || !mw.hasWhere()){
			return this;
		}
		if(listSubWhere == null){
			listSubWhere = new ArrayList<ModelDataWhere<T>>();
		}
		mw.mc = mc;
		listSubWhere.add(mw);
		return this;
	}
	
	public ModelDataWhere<T> add(String column,Object value){
		return this.add(column,value,null,null);
	}

	public ModelDataWhere<T> add(String column,ModelOperator mo){
		return this.add(column,null, mo,null);
	}
	
	public ModelDataWhere<T> add(String column,Object value,ModelOperator mo){
		return this.add(column,value,mo,null);
	}

	public ModelDataWhere<T> add(String column,ModelOperator mo,ModelCondition mc){
		return this.add(column,null,mo,mc);
	}

	public ModelDataWhere<T> add(String column,Object value,ModelCondition mc){
		return this.add(column,value,null,mc);
	}
	
	public ModelDataWhere<T> add(String column,Object value,ModelOperator mo,ModelCondition mc){
		if(listSubWhere == null){
			listSubWhere = new ArrayList<ModelDataWhere<T>>();
		}
		listSubWhere.add(new ModelDataWhere<T>(column,value,mo,mc));
		return this;
	}
	
	public ModelDataWhere<T> or(String column,Object value) {
		return this.add(column,value,ModelCondition.OR);
		
	}
	
	public ModelDataWhere<T> or(String column,Object value,ModelOperator mo) {
		return this.add(column,value,mo,ModelCondition.OR);
		
	}
	
	public ModelDataWhere<T> or(ModelDataWhere<T> mw) {
		return this.add(mw,ModelCondition.OR);
		
	}
	
	public ModelDataWhere<T> not(){
		_not = true;
		return this;
	}
	
	public boolean hasWhere(){
		if(StringUtil.isEmpty(this.toString())){
			return false;
		}
		return true;
	}
	
	public List<ModelDataWhere<T>> getSubWhere() {
		return listSubWhere;
	}
	
	public String getColumn() {
		return column;
	}
	
	public Object getValue() {
		return value;
	}
	
	public ModelCondition getModelCondition() {
		return mc;
	}
	
	public ModelOperator getModelOperator() {
		return mo;
	}
	
	public void clear() {
		column = null;
		value = null;
		mc = null;
		mo = null;
		setValue = null;
		whereString = null;
		_not = false;
		if(listSubWhere != null) {
			listSubWhere.clear();
		}
	}
	
	@Override
	public String toString(){
		String localColumn = column;
		Object localValue = value;
		String where = "";
		if(StringUtil.isEmpty(localColumn) && CollectionUtils.isEmpty(listSubWhere) && StringUtil.isEmpty(whereString)){
			return where;
		}
		
		if(mo == null){
			mo = ModelOperator.EQ;
		}
		if(StringUtil.isNotEmpty(whereString)){
			return whereString;
		}else if(StringUtil.isNotEmpty(localColumn) && (mo.equals(ModelOperator.IS_NULL) || mo.equals(ModelOperator.IS_NOT_NULL))){
			where += "`"+localColumn+"` "+mo.getOp();
		}else if(StringUtil.isNotEmpty(localColumn) && localValue != null){
			boolean noMarks = false;
			if(mo.equals(ModelOperator.LIKE)){
				if(localValue.toString().indexOf("%") == -1){
					localValue = "%"+localValue.toString()+"%";
				}
			}else if(mo.equals(ModelOperator.LIKE_LEFT)){
				if(localValue.toString().indexOf("%") == -1){
					localValue = localValue.toString()+"%";
				}
			}else if(mo.equals(ModelOperator.LIKE_RIGHT)){
				if(localValue.toString().indexOf("%") == -1){
					localValue = "%"+localValue.toString();
				}
			}else if(mo.equals(ModelOperator.IN) || mo.equals(ModelOperator.NOT_IN)){
				if(localValue instanceof String){
					if(!localValue.toString().matches("\\(.*?\\)")){
						localValue = "(" +localValue+ ")";
					}
				}else{
					localValue = FastJsonUtils.toJsonString(localValue);
				}
				localValue = localValue.toString().replaceAll("^\\[", "(").replaceAll("\\]$", ")");
				if(localValue.toString().equals("()")){
					localValue = "(-99999999)";
				}
				noMarks = true;
			}else if(mo.equals(ModelOperator.BETWEEN) || mo.equals(ModelOperator.NOT_BETWEEN)){
				if(!(localValue instanceof String)){
					localValue = FastJsonUtils.toJsonString(localValue);
				}
				localValue = localValue.toString()
						.replaceAll("^\\(", "").replaceAll("\\)$", "")
						.replaceAll("^\\[", "").replaceAll("\\]$", "").replaceAll(",", " AND ");
				noMarks = true;
			}else if(mo.equals(ModelOperator.PLAIN)){
				noMarks = true;
			}
			where += "`"+localColumn+"` "+mo.getOp() + ModelHelper.filter(localValue,noMarks);
		}else if(StringUtil.isNotEmpty(localColumn) && StringUtil.isEmpty(localValue) && mo.equals(ModelOperator.PLAIN)){
			where += " "+localColumn+" ";
		}else if(!CollectionUtils.isEmpty(listSubWhere)){
			for(ModelDataWhere<T> modelWhere : listSubWhere){
				if(StringUtil.isNotEmpty(where)){
					if(modelWhere.mc == null){
						modelWhere.mc = ModelCondition.AND;
					}
					where += modelWhere.mc.getCondition();
				}
				where += modelWhere.toString();
			}
			where = "(" + where + ")";
		}
		if(this._not){
			if(StringUtil.isNotEmpty(where)){
				where = "!"+where;
			}
		}
		return where;
	}
	
	
	@SuppressWarnings("rawtypes")
	public synchronized Set<String> getSetValue() {
		if(!this.getModelOperator().equals(ModelOperator.IN) && !this.getModelOperator().equals(ModelOperator.NOT_IN)) {
			return null;
		}
		if(setValue != null) {
			return setValue;
		}
		setValue = new HashSet<>();
		
		if(getValue() instanceof List) {
			for(Object o : (List)getValue()) {
				if(o != null) {
					setValue.add(o.toString());
				}
			}
		}
		
		return setValue;
	}
	
	public List<T> getResult() {
		return result;
	}
	
	public void addResult(T o) {
		result.add(o);
	}
	
}
