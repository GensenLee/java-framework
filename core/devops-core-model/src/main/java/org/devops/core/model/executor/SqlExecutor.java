package org.devops.core.model.executor;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.sql.*;
import java.util.Date;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import org.devops.core.model.emun.ModelParamType;

@Slf4j
public class SqlExecutor{
	
	private Connection conn;
	
	public SqlExecutor(Connection conn) {
		this.conn = conn;
	}
	protected Class<?> getBaseType(Class<?> clazz){
		if(clazz.equals(Integer.class)){
			return int.class;
		}else if(clazz.equals(Long.class)){
			return long.class;
		}else if(clazz.equals(Double.class)){
			return double.class;
		}else if(clazz.equals(Byte.class)){
			return byte.class;
		}else if(clazz.equals(Float.class)){
			return float.class;
		}else if(clazz.equals(Boolean.class)){
			return boolean.class;
		}else if(clazz.equals(Short.class)){
			return short.class;
		}
		return clazz;
	}
	protected void setParameter(PreparedStatement ps,List<Object> parameter,AtomicInteger index)
			throws SQLException
	{
		if(parameter == null){
			return;
		}
		for(Object param : parameter){
			if(param == null){
				ps.setNull(index.intValue(), Types.DATE);
			}else if(param instanceof Date){
				ps.setTimestamp(index.intValue(), new Timestamp(((Date)param).getTime()));
			}else{
				String simpleName = param.getClass().getSimpleName();
				if(simpleName.equalsIgnoreCase("Integer")){
					simpleName = "Int";
				}
				Class<?> clazz = ps.getClass();
				try{
					Method method = clazz.getMethod("set"+simpleName, int.class,getBaseType(param.getClass()));
					method.invoke(ps, index.intValue(),param);
				}catch(Exception e){
					log.error("[Exception 出错啦!]",e);
				}
			}
			index.set(index.incrementAndGet());
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void setParameter(PreparedStatement ps,Map<String,Object> parameter)
			throws SQLException
	{
		if(parameter == null){
			return;
		}
		AtomicInteger index = new AtomicInteger(1);
		Object valueParam = parameter.get(ModelParamType.VALUES.getCode());
		Object whereParam = parameter.get(ModelParamType.WHERE.getCode());
		
		if(valueParam != null){
			for(Object o : (List<Object>)valueParam){
				setParameter(ps,(List<Object>)o,index);
			}
		}
		
		setParameter(ps,(List<Object>)whereParam,index);
		
	}
	
	public List<Map<String, Object>> select(String sql,Map<String,Object> parameter)
		throws SQLException
	{
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		PreparedStatement ps = conn.prepareStatement(sql);
		setParameter(ps,parameter);
		ResultSet rs = null;
		try{
			rs = ps.executeQuery();
			ResultSetMetaData md = rs.getMetaData();
			int count = md.getColumnCount();
			Set<String> labels = new HashSet<String>();
			for(int i=1;i<=count;i++) {
				labels.add(md.getColumnLabel(i));
			}
			while(rs.next()) {
				Map<String, Object> row = new HashMap<String, Object>();
				for(String label : labels) {
					row.put(label, rs.getObject(label));
				}
				result.add(row);
			}
		}catch(SQLException e) {
			throw e;
		}finally {
			if( rs != null) {
				rs.close();
			}
			if(ps != null) {
				ps.close();
			}
		}
		
		return result;
	}

	public long insert(String sql,Map<String,Object> parameter)
			throws SQLException
	{
		long result = 0;
		PreparedStatement ps = conn.prepareStatement(sql);
		setParameter(ps,parameter);
		result = ps.executeUpdate();
		ps.close();
		return result;
	}

	public long update(String sql,Map<String,Object> parameter)
			throws SQLException
	{
		
		long result = 0;
		PreparedStatement ps = conn.prepareStatement(sql);
		setParameter(ps,parameter);
		result = ps.executeUpdate();
		ps.close();
		return result;
	}

	public long delete(String sql,Map<String,Object> parameter)
			throws SQLException
	{
		long result = 0;
		PreparedStatement ps = conn.prepareStatement(sql);
		setParameter(ps,parameter);
		result = ps.executeUpdate();
		ps.close();
		return result;
	}
}
