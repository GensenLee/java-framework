package org.devops.tool.autocode.main._template;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.devops.tool.autocode.model.CreatorExt;
import org.devops.tool.autocode.model.coder.ModelAutoCoder;
import org.devops.tool.autocode.data.CreatorFile;
import org.devops.tool.autocode.data.TableProperty;
import org.devops.tool.autocode.vo.ProjectInfoVO;

public class TemplateAutoCoder extends ModelAutoCoder {

	public static final String url       = "jdbc:mysql://mysql.sinmn.cn:3306/devops-test?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	public static final String  username = "root";
	public static final String  password = "mysql!@#root";
	public static final String  driver   = "com.mysql.jdbc.Driver";
	
	public static final String projectDir = "D:\\code\\git_sinmn\\template\\java\\mjar\\";
	
	public static final String suffix = "\\src\\main\\java";

	public static final String module = "devops-mjar-message";

	public static final String exclude = "";
	
	public static final List<ProjectInfoVO> projectList = new ArrayList<ProjectInfoVO>(){{
		add(new ProjectInfoVO(projectDir + module + suffix,
				"org.devops.mjar.message.model",
				"vm/default/Model.vm",
				"",
				null,
				true)); // model
		
//		add(new ProjectInfoVO(projectDir + module + suffix,
//				"org.devops.mjar.message.repository",
//				"vm/default/ModelRepository.vm",
//				"Repository",
//				new HashSet<String>(){{
//					add("org.devops.mjar.message.model.${className}");
//				}})); // repository
		
//		add(new ProjectInfoVO(projectDir + module + suffix,
//				"org.devops.tool.main.vo.svo",
//				"vm/default/SearchVO.vm",
//				"SearchVO")); // SearchVO
//
//		add(new ProjectInfoVO(projectDir + module + suffix,
//				"org.devops.tool.main.service",
//				"vm/default/Service.vm",
//				"Service",
//				new HashSet<String>(){{p
//					add("org.devops.tool.main.model.${className}");
//					add("org.devops.tool.main.vo.svo.${className}SearchVO");
//				}})); // service
	}};
	
	public static void main(String[] args) {
		try {
			String tableName  = "auth_user_test2";

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
