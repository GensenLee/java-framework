package org.devops.core.utils.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

@Slf4j
public class FileUtil {
    /**
     * UTF8编码写入文件
     *
     * @param filename
     * @param str
     * @throws IOException
     */
    public static void writeIn(String filename, String str) throws IOException {
        writeIn(filename, str, StandardCharsets.UTF_8.name());
    }

    /**
     * 创建一个目录
     */
    public static boolean mkDir(String DirectoryName) {
        boolean bRet = false;
        File file = new File(DirectoryName);
        if (!file.exists() && file.mkdirs()) {
            bRet = true;
        }
        return bRet;
    }

    /**
     * 往一个指定文件里全新写入指定编码字符串
     *
     * @param filename
     * @param str
     * @param enc      编码，如GBK
     * @throws IOException
     */
    public static void writeIn(String filename, String str, String enc)
            throws IOException {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            mkDir(filename.substring(0, filename.lastIndexOf(File.separator)));
            // 建立FileWrite变量,并设定由fw变量变数引用
            fw = new FileWriter(filename);
            bw = new BufferedWriter(fw);
            // 建立BufferedWriter变量,并设定由bw变量变数引用
            // 将字串写入文件
            bw.write(new String(str.getBytes(enc)));
            bw.flush(); // 将资料更新至文件
        } catch (IOException ex) {
            log.error("[Exception 出错啦]", ex);
        } finally {
            if (fw != null) {
                fw.close(); // 关闭档案
            }
            if (bw != null) {
                bw.close();
            }
        }
    }

    /**
     * @param filename
     * @return
     */
    private static String read(String filename, char split) {
        StringBuffer readfile = new StringBuffer();
        InputStreamReader read = null;
        BufferedReader reader = null;
        String line = "";
        try {
            File file = new File(filename);
            read = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            reader = new BufferedReader(read);
            while ((line = reader.readLine()) != null) {
                readfile.append(line + split);
            }
        } catch (Exception e) {
            log.error("[Exception 出错啦]", e);
            return "";
        } finally {
            try {
                if (read != null) {
                    read.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e1) {
                log.error("[Exception 出错啦]", e1);
            }
        }
        return readfile.toString();
    }

    /**
     * 读取文件内容到一行
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static String readOnLine(String filename) {
        return read(filename, '\r');
    }

    /**
     * 读取文件内容到多行
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static String readOnLines(String filename) {
        return read(filename, '\n');
    }

    /**
     * 删除一个指定的文件
     */
    public static boolean deleteFile(String FileName) {
        boolean bRet = false;
        if (FileName != null && FileName.length() > 0) {
            File filename = new File(FileName);
            if (filename.delete()) {
                bRet = true;
            }
        }
        return bRet;
    }

    /**
     * 拷贝文件
     *
     * @param sourceFile
     * @param destDir
     * @param newFileName
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String copyFile(String sourceFile, String destDir,
                                  String newFileName) throws FileNotFoundException, IOException {
        return copyFile(new FileInputStream(sourceFile), destDir, newFileName);
    }

    /**
     * 拷贝文件
     *
     * @param source
     * @param destDir
     * @param newFileName
     * @return
     * @throws IOException
     */
    public static String copyFile(InputStream source, String destDir,
                                  String newFileName) throws IOException {
        File dir = new File(destDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!dir.isDirectory()) {
            throw new IOException("dest dir (" + destDir + ") is not a folder");
        }
        String destFileFullName = null;
        BufferedOutputStream out = null;
        try {
            destFileFullName = destDir + File.separator + newFileName;
            out = new BufferedOutputStream(new FileOutputStream(
                    destFileFullName));
            byte[] buffer = new byte[8192];
            int bytesRead = 0;
            while ((bytesRead = source.read(buffer, 0, 8192)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

        } finally {
            if (out != null) {
                out.close();
            }
        }
        return destFileFullName;
    }

    /**
     * 获取文件的后缀，没有后缀的直接返回空字符
     *
     * @param fileName
     * @return
     */
    public static String getFileSuffix(String fileName) {
        if (StringUtil.isEmpty(fileName)) {
            return "";
        }
        int index = fileName.lastIndexOf(".");
        if (index == -1)
            return "";
        return fileName.substring(index);
    }

    /**
     * 获取文件的后缀，没有后缀的直接返回空字符
     *
     * @param fileName
     * @return
     */
    public static String getFileSuffixNoDian(String fileName) {
        if (StringUtil.isEmpty(fileName)) {
            return "";
        }
        int index = fileName.lastIndexOf(".");
        if (index == -1)
            return "";
        return fileName.substring(index + 1);
    }


    /**
     * 删除目录，先递归删除里面得文件
     *
     * @param directory
     * @return
     */
    public static void deleteDir(String directory) throws IOException {
        Path path = Paths.get(directory);
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
                Files.delete(file); // 有效，因为它始终是一个文件
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir); //这将起作用，因为目录中的文件已被删除
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
