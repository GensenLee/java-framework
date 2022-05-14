package org.devops.tool.autocode.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.devops.tool.autocode.data.ItemProperty;
import org.devops.tool.autocode.data.JavaType;
import org.devops.tool.autocode.data.TableProperty;

public abstract class BaseAutoCoder {


	public static List<TableProperty> initTable(String driver, String url, String username, String password, String tableName, String project)throws Exception{
		List<TableProperty> liTableProperty = new ArrayList<TableProperty>();
		Connection conn = DriverManager.getConnection(url, username, password);
		List<String> tables = new ArrayList<String>();
		ResultSet rs;
		
		if(!tableName.matches("^[a-zA-Z0-9_-]+$")){
			rs = conn.prepareStatement("show tables").executeQuery();
			
			while(rs.next()){
				if(rs.getString(1).matches(tableName)){
					tables.add(rs.getString(1));
				}
			}
		}else{
			tables.add(tableName);
		}
		
		for(String table : tables){
			TableProperty tableProperty = new TableProperty();
			String comm = "";
			rs = conn.prepareStatement("show table STATUS").executeQuery();
			while(rs.next()) {
				if(rs.getString("Name").equals(table)) {
					comm = rs.getString("Comment");
					break;
				}
			}
			
			List<ItemProperty> items = new ArrayList<ItemProperty>(),
					search = new ArrayList<ItemProperty>();
			
			rs = conn.prepareStatement("show full fields from " + table).executeQuery();
			while(rs.next()) {
				ItemProperty item = new ItemProperty();
				String name = rs.getString("Field");
				String type = rs.getString("Type");
				String comment = rs.getString("Comment");
				String key = rs.getString("Key");
				item.setName(name);
				item.setType(type.split("\\(")[0]);
				item.setJtype(JavaType.get(item.getType()));
				if(JavaType.getImport(item.getType()) != null){
					tableProperty.setImport(JavaType.getImport(item.getType()));
				}
				item.setComment(comment);
				items.add(item);
				
			}
			if(search.size() == 0) {
				search.addAll(items);
			}
			tableProperty.setName(tableName);
			tableProperty.setComment(comm.split(";")[0]);
			tableProperty.setItems(items);
			tableProperty.setProject(project);
			liTableProperty.add(tableProperty);
		}
		
		return liTableProperty;
	}

//	public static TableProperty  initTable(String driver,String url,String username,String password,String tableName)throws Exception{
//		TableProperty table = new TableProperty();
//		Class.forName(driver);
//		Connection conn = DriverManager.getConnection(url, username, password);
//		String comm = "";
//		ResultSet rs = conn.prepareStatement("show table STATUS").executeQuery();
//		while(rs.next()) {
//			if(rs.getString("Name").equals(tableName)) {
//				comm = rs.getString("Comment");
//				break;
//			}
//		}
//		List<ItemProperty> items = new ArrayList<ItemProperty>(), 
//				search = new ArrayList<ItemProperty>();
//		ItemProperty prikey = new ItemProperty();
//		rs = conn.prepareStatement("show full fields from " + tableName).executeQuery();
//		while(rs.next()) {
//			ItemProperty item = new ItemProperty();
//			String name = rs.getString("Field");
//			String type = rs.getString("Type");
//			String comment = rs.getString("Comment");
//			String key = rs.getString("Key");
//			item.setName(name);
//			item.setType(type.split("\\(")[0]);
//			item.setJtype(JavaType.get(item.getType()));
//			item.setComment(comment);
//			if("PRI".equals(key)) {
//				prikey = item;
//				item.setPriKey(true);
//			} else {
//				item.setPriKey(false);
//			}
//			items.add(item);
//			
//			if(searchParams == null || searchParams.length == 0) continue;
//			for(String s : searchParams) {
//				if(name.equals(s)) {
//					search.add(item);
//					break;
//				}
//			}
//			
//			if(bSort && group != null && !group.equals("") && name.equals(group)){
//				ItemProperty groupItem = new ItemProperty();
//				groupItem.setName(name);
//				groupItem.setType(type.split("\\(")[0]);
//				groupItem.setJtype(JavaType.get(groupItem.getType()));
//				groupItem.setComment(comment);
//				table.setGroup(groupItem);
//			}
//		}
//		if(search.size() == 0) {
//			search.addAll(items);
//		}
//		table.setName(tableName);
//		table.setComment(comm.split(";")[0]);
//		table.setItems(items);
//		table.setSearch(search);
//		table.setPrikey(prikey);
//		
//		table.setProject(project);
//		return table;
//	}
}
