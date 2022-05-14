package org.devops.core.utils.util.sql.vo;

import java.util.ArrayList;
import java.util.List;

import org.devops.core.utils.exception.CommonRuntimeException;
import org.devops.core.utils.vo.BaseBean;

public class WhereVO extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 字段名
	 */
	private String field;
	
	/**
	 * 字段值
	 */
	private Object value;
	
	
	/**
	 * 表
	 */
	private String table;
	
	/**
	 * 条件 or and
	 */
	private String condition = "AND";
	
	/**
	 * 条件符号 = > < <> 等
	 */
	private String operator = "=";
	
	private List<String> operatorEmun; 
	
	public WhereVO() {
		operatorEmun = new ArrayList<>();
		operatorEmun.add("=");
		operatorEmun.add("<>");
		operatorEmun.add("!=");
		operatorEmun.add(">");
		operatorEmun.add(">=");
		operatorEmun.add("<");
		operatorEmun.add("<=");
		operatorEmun.add("IN");
		operatorEmun.add("NOT IN");
		operatorEmun.add("LIKE");
		operatorEmun.add("%");
		operatorEmun.add("%_%");
		operatorEmun.add("_%");
		operatorEmun.add("%_");
		operatorEmun.add("BETWEEN");
		operatorEmun.add("NOT BETWEEN");
		operatorEmun.add("IS NOT NULL");
		operatorEmun.add("IS NULL");
	} 
	
	/**
	 * 子查询条件
	 */
	private List<WhereVO> listWhere;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	
	
	
	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public List<WhereVO> getListWhere() {
		return listWhere;
	}

	public void setListWhere(List<WhereVO> listWhere) {
		this.listWhere = listWhere;
	}
	
	/** 替换特殊字符 防止sql语句注入*/
	public String filter(Object value)
	{
		if(value == null){
			return "null";
		}
		if(value instanceof String){
			return "'"+value.toString().trim().replace("\\", "\\\\").replace("'", "\\'").replace("\r", "").replace("\n", "")+"'";
		}else{
			return value.toString();
		}
		
	}

	@Override
	public String toString(){
		String sql = "";
		if(listWhere != null && !listWhere.isEmpty()){
			//如果是列表
			for(WhereVO whereVO : listWhere){
				if(!sql.equals("")){
					sql+=" "+whereVO.getCondition().toUpperCase()+" ";
				}
				sql+=whereVO.toString();
			}
			return "("+sql+")";
		}
		sql = "`"+this.getField()+"`";
		//sql = this.getField();
		String value = filter(this.getValue());
		String operator = this.getOperator();
		if(this.operatorEmun.indexOf(operator) == -1){
			throw new CommonRuntimeException("不合法的操作符");
		}
		//IN
		if("IN".equalsIgnoreCase(this.getOperator()) || "NOT IN".equalsIgnoreCase(this.getOperator())){
			value = value.replaceAll("^'\\[", "(").replaceAll("^\"\\[", "(").replaceAll("^\\[", "(")
					.replaceAll("\\]'$", ")").replaceAll("\\]\"$", ")").replaceAll("\\]$", ")");
			if(value.indexOf('(') == -1){
				value = '(' + value.replaceAll("\"", "'").replaceAll(",", "','") + ')';
			}
		}else if("BETWEEN".equalsIgnoreCase(this.getOperator()) || "NOT BETWEEN".equalsIgnoreCase(this.getOperator())){
			value = value.replaceAll("^'\\[", "").replaceAll("^\"\\[", "")
					.replaceAll("\\]'$", "").replaceAll("\\]\"$", "").replace(",", " AND ");
		}else if(this.getOperator().indexOf("%") >= 0){
			if(operator.equals("%")){
				operator = "%_%";
			}
			value = "'"+operator.replace("_",value.replaceAll("^'", "").replaceAll("'$", ""))+"'";
			operator = "LIKE";
		}
		sql += " "+operator.toUpperCase()+" "+value;
		return sql;
	}
}
