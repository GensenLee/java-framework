package org.devops.web.test.test.ie.importd;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.lang.StringUtils;
import org.devops.core.ie.util.ExcelImportUtil;
import org.devops.core.ie.util.ExcelUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * @author GENSEN
 * @version 1.00
 * @time 2021/12/30 16:05
 * @description
 */
public class ImportTest {

//    public static void main(String[] args) {
//
//        List<LinkedHashMap> linkedHashMaps = ExcelImportUtil.parseList("D:\\data\\tmp\\20211230\\export-1640849589926.xls", LinkedHashMap.class, 0, 2);
//        System.out.println(linkedHashMaps);
//
//
//    }


    public static void main(String[] args) throws IOException {
//		System.out.println(Long.toUnsignedString(45645646546458L, 55));
//		System.out.println(Long.toUnsignedString(45645646546458L, 505));


//        doExecuteCommand(Arrays.asList("D:"));

//        ExcelImportUtil.parseList("D:\\code\\ali_codeup\\product\\backend\\ent-user-center\\ent-user-center-webapi\\src\\test\\resources\\template\\department.xlsx", )



    }

    private static void doExecuteCommand(final List<String>command)throws IOException {

//		OutputStream infoOutputStream=new LoggerPrintStream(false);
//		PumpStreamHandler streamHandler=new PumpStreamHandler(infoOutputStream);

        CommandLine commandLine= CommandLine.parse(StringUtils.join(command," "));
        DefaultExecutor executor=new DefaultExecutor();
        executor.setExitValues(new int[]{0,1});
//		executor.setStreamHandler(streamHandler);

        int exitValue=executor.execute(commandLine);

        System.out.println(exitValue);

    }
}
