package org.devops.tool.autocode.model;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.devops.tool.autocode.data.CreatorFile;
import org.devops.tool.autocode.data.TableProperty;
import org.devops.tool.autocode.util.DataService;
import org.devops.tool.autocode.util.FileUtil;

public class CreatorExt  {
	
	protected String targetDir;
	
	protected String fileName = "temp";
	
	protected String tmpl;
	
	public CreatorExt(){
		
	}
	
	public static void create(TableProperty table ,Map<String, CreatorFile> modelToFile) throws Exception{
		
		Set<String> importList = new HashSet<String>(table.getImportList());
		
		for(String k : modelToFile.keySet()){
			CreatorExt cc = new CreatorExt();
			CreatorFile creatorFile  = modelToFile.get(k);
			
			Map<String,String> mapTemplate = new HashMap<String,String>();
			
			mapTemplate.put("${table}", table.getName());
			mapTemplate.put("${className}", table.getClassName());
			mapTemplate.put("${classNameFirstLower}", table.getClassNameFirstLower());
			mapTemplate.put("${project}", table.getProject());
			mapTemplate.put("${packages}", table.getPackages());
			mapTemplate.put("${firstServiceLetterAddPoint}", table.getFirstServiceLetterAddPoint());
			mapTemplate.put("${firstLetter}", table.getFirstLetter());
			mapTemplate.put("${firstRepositoryLetterAddPoint}", table.getFirstRepositoryLetterAddPoint());
			table.resetImportList();
			if(creatorFile.isImportExt() && !CollectionUtils.isEmpty(importList)){
				table.addImport(importList);
			}
			
			if(!CollectionUtils.isEmpty(creatorFile.getImportList())){
				for(String importStr : creatorFile.getImportList()){
					if(!importStr.matches("^import\\s.*")){
						importStr = "import " + importStr;
					}
					if(!importStr.matches(".*;$")){
						importStr = importStr + ";";
					}
					for(String key : mapTemplate.keySet()){
						importStr = importStr.replaceAll(key.replace("$", "\\$").replace("{", "\\{").replace("}", "\\}"), mapTemplate.get(key));
					}
					table.addImport(importStr);
				}
			}

			table.setPackages(creatorFile.getPackages());
			String target = creatorFile.getTargetFile();
			
			int i  =  target.lastIndexOf("/");
			String filename = target.substring(i+1);
			String targetDir = target.substring(0, i+1);
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("table", table);
			
			String content = DataService.buildData(k, map);
			
			//写到目标目录
			FileUtil.writIn(targetDir + File.separator + filename, content);
			
			
		}
		
	}

}
