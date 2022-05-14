package org.devops.tool.autocode.main.hep;

import org.devops.tool.autocode.data.CreatorFile;
import org.devops.tool.autocode.data.TableProperty;
import org.devops.tool.autocode.model.CreatorExt;
import org.devops.tool.autocode.model.coder.ModelAutoCoder;
import org.devops.tool.autocode.vo.ProjectInfoVO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class HepAutoCoder extends ModelAutoCoder {

	public static final String url       = "jdbc:mysql://47.93.100.3:3306/polyv?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	public static final String  username = "root";
	public static final String  password = "mysql!@#root";
	public static final String  driver   = "com.mysql.jdbc.Driver";
	
	public static final String projectDir = "D:\\code\\git_polyv\\hep\\higher-education-backend\\";
	
	public static final String suffix = "\\src\\main\\java";

	public static final String module = "hep-biz";

	public static final String exclude = "";
	
	public static final List<ProjectInfoVO> projectList = new ArrayList<ProjectInfoVO>(){{
		add(new ProjectInfoVO(projectDir + module + suffix,
				"org.devops.hep.biz.model.polyv",
				"vm/template/Model.vm",
				"",
				null,
				true)); // model
		
		add(new ProjectInfoVO(projectDir + module + suffix,
				"org.devops.hep.biz.repository.polyv",
				"vm/template/ModelRepository.vm",
				"Repository",
				new HashSet<String>(){{
					add("org.devops.hep.biz.model.polyv.${className}");
				}})); // repository
		
//		add(new ProjectInfoVO(projectDir + module + suffix,
//				"org.devops.tool.main.vo.svo",
//				"vm/template/SearchVO.vm",
//				"SearchVO")); // SearchVO
//
//		add(new ProjectInfoVO(projectDir + module + suffix,
//				"org.devops.tool.main.service",
//				"vm/template/Service.vm",
//				"Service",
//				new HashSet<String>(){{p
//					add("org.devops.tool.main.model.${className}");
//					add("org.devops.tool.main.vo.svo.${className}SearchVO");
//				}})); // service
	}};
	
	public static void main(String[] args) {
		try {
			String tableName  = "hep_tag";

			List<TableProperty> liTable = initTable(driver,url,username,password,tableName,null,null);
			for(TableProperty table : liTable)
			{
				//table.setName(table.getClassName().replaceAll("^"+exclude, ""));
				Map<String, CreatorFile> modelToFile =  createBaseJavaFile(table,projectList);
				for(String key : modelToFile.keySet()){
					System.out.println(key+" *** "+modelToFile.get(key).toString());
				}
				CreatorExt.create(table,modelToFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
