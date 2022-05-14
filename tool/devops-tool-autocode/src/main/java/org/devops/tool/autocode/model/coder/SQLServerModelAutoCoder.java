package org.devops.tool.autocode.model.coder;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.devops.tool.autocode.data.CreatorFile;
import org.devops.tool.autocode.data.ItemProperty;
import org.devops.tool.autocode.data.JavaType;
import org.devops.tool.autocode.data.TableProperty;
import org.devops.tool.autocode.vo.ProjectInfoVO;

public class SQLServerModelAutoCoder {

	public static List<TableProperty> initTable(String driver,String url,String username,String password,String tableName,String project,String packages)throws Exception{
		List<TableProperty> liTableProperty = new ArrayList<TableProperty>();
		Connection conn = DriverManager.getConnection(url, username, password);
		List<String> tables = new ArrayList<String>();
		ResultSet rs;
		
		if(!tableName.matches("^[a-zA-Z0-9_-]+$")){
			rs = conn.prepareStatement("SELECT Name FROM SysObjects Where XType='U' ORDER BY Name").executeQuery();
			
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
			rs = conn.prepareStatement("select cast(name as varchar(500))  AS name,cast(value as varchar(500)) from sys.extended_properties where major_id = object_id ('"+table+"' )").executeQuery();
			while(rs.next()) {
				if(rs.getString("name").equals(table)) {
					comm = rs.getString("value");
					break;
				}
			}
			
			List<ItemProperty> items = new ArrayList<ItemProperty>(), 
					search = new ArrayList<ItemProperty>();
			
			rs = conn.prepareStatement("SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '"+table+"' ORDER BY ORDINAL_POSITION").executeQuery();
			
			ResultSet priKeySet = conn.prepareStatement("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE TABLE_NAME='"+table+"'").executeQuery();

			String keyName = "";
			if(priKeySet.next()){
				keyName = priKeySet.getString("COLUMN_NAME");
			}
			while(rs.next()) {
				
				ItemProperty item = new ItemProperty();
				String name = rs.getString("COLUMN_NAME");
				String type = rs.getString("DATA_TYPE");
				if(type.equalsIgnoreCase("numeric")){
					type="decimal("+rs.getInt("NUMERIC_PRECISION")+","+rs.getInt("NUMERIC_SCALE")+")";
				}else if(type.equalsIgnoreCase("char") || type.equalsIgnoreCase("varchar") || type.equalsIgnoreCase("nvarchar")){
					type="varchar("+rs.getInt("CHARACTER_MAXIMUM_LENGTH")+")";
				}else if(type.equalsIgnoreCase("datetime2")){
					type = "datetime";
				}
				//String comment = rs.getString("COMMENT");
				//String key = rs.getString("Key");
				String comment = "";
				item.setName(name);
				item.setDef(rs.getString("COLUMN_DEFAULT"));
				if(keyName.equals(name)){
					item.setPriKey("PRI");
				}
				//item.setAutoIncrement(rs.getString("Extra"));
				item.setIsNull(rs.getString("IS_NULLABLE"));
				item.setOrigType(type);
				item.setType(type.split("\\(")[0]);
				item.setJtype(JavaType.get(item.getType()));
				if(JavaType.getImport(item.getType()) != null){
					tableProperty.setImport(JavaType.getImport(item.getType()));
				}
				item.setComment(comment);
				items.add(item);
				
				if(item.isPriKey()){
					tableProperty.setPrikey(item);
				}
				
			}
			if(search.size() == 0) {
				search.addAll(items);
			}
			tableProperty.setName(table);
			tableProperty.setTableName(table);
			tableProperty.setComment(comm.split(";")[0]);
			tableProperty.setItems(items);
			tableProperty.setProject(project);
			tableProperty.setPackages(packages);
			liTableProperty.add(tableProperty);
		}
		
		return liTableProperty;
	}
	
	public static Map<String, CreatorFile>  createBaseJavaFile(TableProperty table, List<ProjectInfoVO> projectList){
		Map<String,CreatorFile> modelToFile = new HashMap<String,CreatorFile>();
		
		for(ProjectInfoVO projectInfoVO : projectList) {
			modelToFile.put(projectInfoVO.getVm(),  
					new CreatorFile(projectInfoVO.getProjectDir().replace("/", File.separatorChar+"")+File.separatorChar+projectInfoVO.getPackageDir().replace(".", File.separatorChar+"")+File.separatorChar+table.getClassName()+projectInfoVO.getNameSuffix()+".java",
							projectInfoVO.getPackageDir(),
							projectInfoVO.getImportList(),
							projectInfoVO.getImportExt())
			);
		}
		return modelToFile;
	}
}
