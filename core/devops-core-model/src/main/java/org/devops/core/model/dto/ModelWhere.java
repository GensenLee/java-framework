package org.devops.core.model.dto;

import java.util.ArrayList;
import java.util.List;

import org.devops.core.model.core.ModelHelper;
import org.devops.core.model.emun.ModelCondition;
import org.devops.core.model.emun.ModelOperator;
import org.devops.core.utils.util.FastJsonUtils;
import org.devops.core.utils.util.StringUtil;
import org.springframework.util.CollectionUtils;

public class ModelWhere {

	private String column;
	private Object value;
	private ModelCondition mc;
	private ModelOperator mo;
	private String whereString;
	private Boolean _not = false;
	private List<ModelWhere> listSubWhere;
	
	public ModelWhere(){
	}
	
	public ModelWhere(String column,Object value,ModelOperator mo){
		if(value instanceof List && mo == null){
			mo = ModelOperator.IN;
		}
		this.column = column;
		this.value = value;
		this.mo = mo;
		this.mc = ModelCondition.AND;
	}
	
	public ModelWhere(String column,Object value,ModelOperator mo,ModelCondition mc){
		if(value instanceof List && mo == null){
			mo = ModelOperator.IN;
		}
		this.column = column;
		this.value = value;
		this.mo = mo;
		this.mc = mc;
	}
	
	public ModelWhere where(String column,Object value,ModelOperator mo,ModelCondition mc){
		if(value instanceof List && mo == null){
			mo = ModelOperator.IN;
		}
		this.column = column;
		this.value = value;
		this.mo = mo;
		this.mc = mc;
		return this;
	}
	
	public ModelWhere add(ModelWhere mw){
		if(mw == null || !mw.hasWhere()){
			return this;
		}
		if(listSubWhere == null){
			listSubWhere = new ArrayList<ModelWhere>();
		}
		listSubWhere.add(mw);
		return this;
	}
	
	public ModelWhere add(ModelWhere mw,ModelCondition mc){
		if(mw == null || !mw.hasWhere()){
			return this;
		}
		if(listSubWhere == null){
			listSubWhere = new ArrayList<ModelWhere>();
		}
		mw.mc = mc;
		listSubWhere.add(mw);
		return this;
	}
	
	public ModelWhere add(String column,Object value){
		return this.add(column,value,null,null);
	}

	public ModelWhere add(String column,ModelOperator mo){
		return this.add(column,null, mo,null);
	}
	
	public ModelWhere add(String column,Object value,ModelOperator mo){
		return this.add(column,value,mo,null);
	}

	public ModelWhere add(String column,ModelOperator mo,ModelCondition mc){
		return this.add(column,null,mo,mc);
	}

	public ModelWhere add(String column,Object value,ModelCondition mc){
		return this.add(column,value,null,mc);
	}
	
	public ModelWhere add(String column,Object value,ModelOperator mo,ModelCondition mc){
		if(listSubWhere == null){
			listSubWhere = new ArrayList<ModelWhere>();
		}
		listSubWhere.add(new ModelWhere(column,value,mo,mc));
		return this;
	}
	
	public ModelWhere addWhereSQL(String whereString){
		if(listSubWhere == null){
			listSubWhere = new ArrayList<ModelWhere>();
		}
		listSubWhere.add(new ModelWhere().setWhereString(whereString));
		return this;
	}
	
	public ModelWhere or(String column,Object value) {
		return this.add(column,value,ModelCondition.OR);
		
	}
	
	public ModelWhere or(String column,Object value,ModelOperator mo) {
		return this.add(column,value,mo,ModelCondition.OR);
		
	}
	
	public ModelWhere or(ModelWhere mw) {
		return this.add(mw,ModelCondition.OR);
		
	}
	
	public ModelWhere not(){
		_not = true;
		return this;
	}
	
	public ModelWhere setWhereString(String whereString) {
		this.whereString = whereString;
		return this;
	}
	
	public ModelWhere addFormat(ModelCondition mc,String whereStr,Object... args){
		if(listSubWhere == null){
			listSubWhere = new ArrayList<ModelWhere>();
		}
		ModelWhere mw = new ModelWhere();
		mw.mc = mc;
		mw.whereString = String.format(whereStr, args);
		listSubWhere.add(mw);
		return this;
	}
	
	public ModelWhere addFormat(String whereStr,Object... args){
		return this.addFormat(ModelCondition.AND,whereStr,args);
	}
	
	public boolean hasWhere(){
		if(StringUtil.isEmpty(this.toString())){
			return false;
		}
		return true;
	}
	
	public List<ModelWhere> getSubWhere() {
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
		whereString = null;
		_not = false;
		if(listSubWhere != null) {
			listSubWhere.clear();
		}
	}
	
	public String toStringParametric(List<Object> listParameter){
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
					localValue = FastJsonUtils.toJsonString(localValue).replaceAll("^\\[\"", "").replaceAll("\"\\]$", "").replaceAll("\",\"", ",");
				}
				localValue = localValue.toString().replaceAll("^\\[", "(").replaceAll("\\]$", ")");
				if(localValue.toString().equals("()")){
					localValue = "(0)";
				}
				noMarks = true;
				
				String[] values = localValue.toString().replaceAll("^\\(", "").replaceAll("\\)$", "").split(",");
				localValue = "?";
				for(String v : values){
					listParameter.add(v);
					localValue = localValue.toString().replaceFirst("\\?", "?,?");
				}
				localValue = localValue.toString().replaceFirst("\\?,", "");
				localValue = "("+localValue+")";
			}else if(mo.equals(ModelOperator.BETWEEN) || mo.equals(ModelOperator.NOT_BETWEEN)){

				if(!(localValue instanceof String)){
					localValue = FastJsonUtils.toJsonString(localValue).replaceAll("^\\[\"", "").replaceAll("\"\\]$", "").replaceAll("\",\"", ",");
				}
				
				String[] values = localValue.toString().replaceAll("^\\(", "").replaceAll("\\)$", "")
								  .replaceAll("^\\[", "").replaceAll("\\]$", "")
								  .split(",");
				localValue = "?";
				for(String v : values){
					listParameter.add(v);
					localValue = localValue.toString().replaceFirst("\\?", "?,?");
				}
				localValue = localValue.toString().replaceFirst("\\?,", "");
				localValue = localValue.toString().replaceAll(",", " AND ");
				
				noMarks = true;
			}else if(mo.equals(ModelOperator.PLAIN)){
				noMarks = true;
			}
			
			if(noMarks){
				where += "`"+localColumn+"` "+mo.getOp() + ModelHelper.filter(localValue,noMarks);
			}else{
				listParameter.add(localValue);
				where += "`"+localColumn+"` "+mo.getOp() + " ? ";
			}
			
		}else if(StringUtil.isNotEmpty(localColumn) && StringUtil.isEmpty(localValue) && mo.equals(ModelOperator.PLAIN)){
			where += " "+localColumn+" ";
		}else if(!CollectionUtils.isEmpty(listSubWhere)){
			for(ModelWhere modelWhere : listSubWhere){
				if(StringUtil.isNotEmpty(where)){
					if(modelWhere.mc == null){
						modelWhere.mc = ModelCondition.AND;
					}
					where += modelWhere.mc.getCondition();
				}
				where += modelWhere.toStringParametric(listParameter);
			}
			where = "(" + where + ")";
		}
		
		return where;
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
			for(ModelWhere modelWhere : listSubWhere){
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
}
