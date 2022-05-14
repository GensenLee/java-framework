package org.devops.core.utils.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

@Slf4j
public class ZipUtil {
    private static final int BUFFER_SIZE = 2 * 1024;

    /**
     * 解压到对应目录下
     *
     * @param zipFileFullPath
     * @param unzipfilePath
     */
    public static void unPack(String zipFileFullPath, String unzipfilePath) throws IOException {
        FileUtil.mkDir(unzipfilePath);
        ZipEntry zipEntry = null;
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        int count = 0;
        byte[] buffer = new byte[1024];
        //解出压缩包文件
        try {
            ZipFile zip = new ZipFile(zipFileFullPath);
            Enumeration<ZipEntry> entrys = (Enumeration<ZipEntry>) zip.entries();
            while(entrys.hasMoreElements()) {
                zipEntry = entrys.nextElement();

                String entryFilePath = unzipfilePath + "/" + zipEntry.getName();
                File unfile = new File(entryFilePath);

                bos = new BufferedOutputStream(new FileOutputStream(unfile));
                bis = new BufferedInputStream(zip.getInputStream(zipEntry));

                while((count = bis.read(buffer, 0, 1024))!=-1) {
                    bos.write(buffer, 0, count);
                }
                bos.flush();
                bos.close();
                bis.close();

            }
        } catch (Exception e) {
            log.error("unzip file error:", e);
        }finally {
            try {
                if(bis != null) {
                    bis.close();
                }
                if(bis != null) {
                    bos.flush();
                    bos.close();
                }
            } catch (IOException ignored) {
            }
        }

    }


    /**
     * 压缩成ZIP
     *
     * @param srcDir           压缩文件夹路径
     * @param targeFullPath    压缩文件输出流
     * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */
    public static void toZip(String srcDir, String targeFullPath, boolean KeepDirStructure) throws Exception {
        long start = System.currentTimeMillis();
        try (FileOutputStream out = new FileOutputStream(new File(targeFullPath));
             ZipOutputStream zos = new ZipOutputStream(out);) {
            File sourceFile = new File(srcDir);
            compress(sourceFile, zos, sourceFile.getName(), KeepDirStructure);
            long end = System.currentTimeMillis();
            log.debug("zip file success，time：" + (end - start) + " ms");
        } catch (Exception e) {
            log.error("zip file error:", e);
            throw e;
        }
    }


    /**
     * 递归压缩方法
     *
     * @param sourceFile       源文件
     * @param zos              zip输出流
     * @param name             压缩后的名称
     * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         <p>
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws Exception
     */
    private static void compress(File sourceFile, ZipOutputStream zos, String name,
                                 boolean KeepDirStructure) throws Exception {
        byte[] buf = new byte[BUFFER_SIZE];
        try {
            if (sourceFile.isFile()) {
                // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
                zos.putNextEntry(new ZipEntry(name));
                // copy文件到zip输出流中
                int len;
                try (FileInputStream in = new FileInputStream(sourceFile)) {
                    while ((len = in.read(buf)) != -1) {
                        zos.write(buf, 0, len);
                    }
                    zos.closeEntry();
                }
            } else {
                File[] listFiles = sourceFile.listFiles();
                if (listFiles == null || listFiles.length == 0) {
                    // 需要保留原来的文件结构时,需要对空文件夹进行处理
                    if (KeepDirStructure) {
                        // 空文件夹的处理
                        zos.putNextEntry(new ZipEntry(name + File.separator));
                        // 没有文件，不需要文件的copy
                        zos.closeEntry();
                    }
                } else {
                    for (File file : listFiles) {
                        // 判断是否需要保留原来的文件结构
                        if (KeepDirStructure) {
                            // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                            // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                            compress(file, zos, name + File.separator + file.getName(), KeepDirStructure);
                        } else {
                            compress(file, zos, file.getName(), KeepDirStructure);
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
    }

}
