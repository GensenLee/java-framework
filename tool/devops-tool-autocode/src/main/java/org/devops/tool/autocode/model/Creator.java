package org.devops.tool.autocode.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.devops.tool.autocode.data.TableProperty;
import org.devops.tool.autocode.util.DataService;
import org.devops.tool.autocode.util.FileUtil;

public class Creator  {
	protected TableProperty table;
	
	protected String targetDir;
	
	protected String fileName = "temp";
	
	protected String tmpl;
	
	
	
	private static List<Creator> creators = new ArrayList<Creator>();
	private static  Map<String, Object> map = new HashMap<String, Object>();
	
	public Creator(){
		
	}
	
	public static List<Creator>  allCreateor(TableProperty table ,Map<String,String> modelToFile) throws Exception{
		
		
		//vo生成
		List<Creator> creators = new ArrayList<Creator>();
		map.put("table", table);
		for(String k : modelToFile.keySet()){
			Creator cc = new Creator();
			cc.setTable(table);
			String target  = modelToFile.get(k);
			cc.setTmpl(k);
			int i  =  target.lastIndexOf("/");
			String filename = target.substring(i+1);
			String targetDir = target.substring(0, i+1);
			cc.setTargetDir(targetDir);
			cc.setFileName(filename);
			creators.add(cc);
			String content = DataService.buildData(cc.tmpl, map);
			//System.out.println("----转换后的内容----"+content);
			
			//特殊处理{\}"\\{\\}
			content = content.replace("ZZ", "");
			//写到临时目录
			FileUtil.writIn(targetDir + File.separator + cc.getFileName(), content);
			
			
		}
		
		return creators;
		
		
		
		
		
	}
	
	
	public static List<Creator> getCreators() {
		return creators;
	}
	
	public static boolean scan(List<Creator> creators) throws Exception {
		
		System.out.println("Scaning project directories...");
		boolean exist = false;
		//获得
		for(Creator cc : creators) {
			String f = cc.getTargetDir() + File.separator + cc.getFileName();
			f = f.replace('\\', '/');
			if(new File(f).exists()) {
				System.out.println("[exist] " + f);
				exist = true;
			}
		}
		//判断文件是否存在，存在就终止
		if(exist) {
			System.out.println("Abort!");
			return false;
		}
		return true;
	}
	/**
	 * 删除覆盖的文件
	 * @param tmpDir
	 * @param ccs
	 * @throws Exception
	 */
	public static void cleanFile(String tmpDir,List<Creator> ccs) throws Exception {
		System.out.println("clean files...");
		
		for(Creator cc : ccs) {
			String targetDir = cc.targetDir;
			String s3 = cc.getFileName();
			FileUtil.deleteFile( targetDir, s3);
			System.out.println("-------------目标路径----------"+targetDir+File.separator+s3+"已删除.");
		}	
	}
	public static void copy(String tmpDir,List<Creator> ccs) throws Exception {
		System.out.println("Copying files...");
		
		boolean existFile = false;
		for(Creator cc : ccs) {
			String s1 = tmpDir + "/" + cc.table.getName() + "/" + cc.getFileName();
			String targetDir = cc.targetDir;
			String s3 = cc.getFileName();
			if(FileUtil.fileIsExit(targetDir, s3)){
				System.out.println("-------------目标路径----------"+targetDir+"已存在.");
				existFile = true;
			}else{
				FileUtil.copyFile(s1, targetDir, s3);
			}
		}
		
		if(existFile){
			new Exception("存在被覆盖的文件，请小心检查文件.");
			System.exit(-1);
		}
		
		for(Creator cc : ccs) {
			String s1 = tmpDir + "/" + cc.table.getName() + "/" + cc.getFileName();
			String targetDir = cc.targetDir;
			String s3 = cc.getFileName();
			System.out.println("-------------目标路径----------"+targetDir+"已生成.");
			FileUtil.copyFile(s1, targetDir, s3);
		}		
		System.out.println("Done!");
	}

	public TableProperty getTable() {
		return table;
	}

	public void setTable(TableProperty table) {
		this.table = table;
	}


	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTmpl() {
		return tmpl;
	}

	public void setTmpl(String tmpl) {
		this.tmpl = tmpl;
	}

	public String getTargetDir() {
		return targetDir;
	}

	public void setTargetDir(String targetDir) {
		this.targetDir = targetDir;
	}

}
