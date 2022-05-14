package org.devops.core.utils.util.m3u8;


import org.devops.core.utils.exception.CommonRuntimeException;
import org.devops.core.utils.util.DoubleUtil;
import org.devops.core.utils.util.HttpUtils;
import org.devops.core.utils.util.StringUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;


public class M3U8Util {

    public static void download(String url, M3U8DownloadHandler m3u8DownloadHandler) {
        if (!url.contains(".m3u8")) {
            throw new CommonRuntimeException("请传入正确的m3u8文件");
        }
        byte[] buffer = HttpUtils.downloadToByte(url);
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        String basePath = url.substring(0, url.lastIndexOf("/") + 1);
        M3U8DownloadVO m3u8DownloadVO = new M3U8DownloadVO();
        m3u8DownloadVO.setLength(0L);
        if (buffer != null) {
            m3u8DownloadVO.setBuffer(buffer);
            m3u8DownloadVO.setLength((long) buffer.length);
        }
        m3u8DownloadVO.setFileName(fileName);
        m3u8DownloadVO.setType("m3u8");

        Pattern reg = Pattern.compile("^#EXTINF:(.*),$", Pattern.MULTILINE);

        String m3u8 = new String(buffer);
        Matcher matcher = reg.matcher(m3u8);
        double duration = 0d;

        while (matcher.find()) {
            duration += DoubleUtil.toDouble(matcher.group(1));
        }

        m3u8DownloadVO.setDuration(duration);

        m3u8DownloadHandler.handler(m3u8DownloadVO);

        buffer = null;
        m3u8DownloadVO.setBuffer(null);

        reg = Pattern.compile("(.*\\.ts)", Pattern.MULTILINE);
        matcher = reg.matcher(m3u8);
        while (matcher.find()) {
            downloadTs(basePath + matcher.group(0), m3u8DownloadHandler);
        }

    }

    public static void downloadTs(String url, M3U8DownloadHandler m3u8DownloadHandler) {
        if (!url.contains(".ts")) {
            throw new CommonRuntimeException("请传入正确的ts文件");
        }
        byte[] buffer = HttpUtils.downloadToByte(url);
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        M3U8DownloadVO m3u8DownloadVO = new M3U8DownloadVO();
        m3u8DownloadVO.setLength(0L);
        if (buffer != null) {
            m3u8DownloadVO.setBuffer(buffer);
            m3u8DownloadVO.setLength((long) buffer.length);
        }
        m3u8DownloadVO.setFileName(fileName);
        m3u8DownloadVO.setType("ts");
        m3u8DownloadHandler.handler(m3u8DownloadVO);
        buffer = null;
        m3u8DownloadVO.setBuffer(null);
    }

    public static void getByZip(InputStream in, M3U8DownloadHandler m3u8DownloadHandler) {
        ZipInputStream zis = new ZipInputStream(in, Charset.forName("GBK"));
        try {
            Map<String, byte[]> mapM3U8 = new HashMap<String, byte[]>();
            Map<String, String> mapName = new HashMap<>();
            ByteArrayOutputStream swapStream = null;
            byte[] buff = new byte[100]; //buff用于存放循环读取的临时数据
            byte[] buffer = null;
            String m3u8 = "";
            String fileName = "";
            String m3u8Name = "";
            ZipEntry entry = null;
            while ((entry = zis.getNextEntry()) != null) {
                String orgName = entry.getName();
                // 去掉文件夹
                if (entry.isDirectory()) {
                    continue;
                }
                orgName = orgName.substring(orgName.lastIndexOf("/") + 1);
                if (StringUtil.isEmpty(orgName)) {
                    continue;
                }
                try {
                    swapStream = new ByteArrayOutputStream();
                    int rc = 0;
                    while ((rc = zis.read(buff, 0, 100)) > 0) {
                        swapStream.write(buff, 0, rc);
                    }
                    if (orgName.toLowerCase().contains("m3u8")) {
                        // m3u8
                        buffer = swapStream.toByteArray();
                        m3u8 = new String(buffer);
                        fileName = UUID.randomUUID().toString().replaceAll("-", "") + ".m3u8";
                        m3u8Name = fileName;
                        mapM3U8.put(fileName, buffer);
                    } else {
                        // ts
                        fileName = UUID.randomUUID().toString().replaceAll("-", "") + ".ts";
                        mapM3U8.put(fileName, swapStream.toByteArray());
                        mapName.put(orgName, fileName);
                    }
                } catch (Exception e) {
                } finally {
                    if (swapStream != null) {
                        try {
                            swapStream.close();
                        } catch (IOException e) {
                        } finally {
                            swapStream = null;
                        }
                    }
                }
            }

            buff = null;
            buffer = null;

            for (String name : mapName.keySet()) {
                m3u8 = m3u8.replace(name, mapName.get(name));
            }

            mapM3U8.put(m3u8Name, m3u8.getBytes());

            for (String name : mapM3U8.keySet()) {
                M3U8DownloadVO m3u8DownloadVO = new M3U8DownloadVO();
                m3u8DownloadVO.setLength(0L);
                buffer = mapM3U8.get(name);
                if (buffer != null) {
                    m3u8DownloadVO.setBuffer(buffer);
                    m3u8DownloadVO.setLength((long) buffer.length);
                }
                m3u8DownloadVO.setFileName(name);
                if (name.toLowerCase().contains("m3u8")) {
                    // m3u8
                    m3u8DownloadVO.setType("m3u8");
                    Pattern reg = Pattern.compile("^#EXTINF:(.*),$", Pattern.MULTILINE);
                    Matcher matcher = reg.matcher(m3u8);
                    double duration = 0d;

                    while (matcher.find()) {
                        duration += DoubleUtil.toDouble(matcher.group(1));
                    }
                    m3u8DownloadVO.setDuration(duration);
                } else {
                    // ts
                    m3u8DownloadVO.setType("ts");
                }

                m3u8DownloadHandler.handler(m3u8DownloadVO);
                buffer = null;
                m3u8DownloadVO.setBuffer(null);
            }


        } catch (Exception e) {
            new CommonRuntimeException(e);
        } finally {
            if (zis != null) {
                try {
                    zis.close();
                } catch (IOException e) {
                }
            }
        }

    }

    public static void getByZip(File file, M3U8DownloadHandler m3u8DownloadHandler) {
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(file, Charset.forName("GBK"));
            Enumeration<?> entries = zipFile.entries();
            Map<String, byte[]> mapM3U8 = new HashMap<String, byte[]>();
            Map<String, String> mapName = new HashMap<>();
            InputStream inputStream = null;
            ByteArrayOutputStream swapStream = null;
            byte[] buff = new byte[100]; //buff用于存放循环读取的临时数据
            byte[] buffer = null;
            String m3u8 = "";
            String fileName = "";
            String m3u8Name = "";
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String orgName = entry.getName();
                // 去掉文件夹
                if (entry.isDirectory()) {
                    continue;
                }
                orgName = orgName.substring(orgName.lastIndexOf("/") + 1);
                if (StringUtil.isEmpty(orgName)) {
                    continue;
                }
                try {
                    inputStream = zipFile.getInputStream(entry);
                    swapStream = new ByteArrayOutputStream();
                    int rc = 0;
                    while ((rc = inputStream.read(buff, 0, 100)) > 0) {
                        swapStream.write(buff, 0, rc);
                    }
                    if (orgName.toLowerCase().contains("m3u8")) {
                        // m3u8
                        buffer = swapStream.toByteArray();
                        m3u8 = new String(buffer);
                        fileName = UUID.randomUUID().toString().replaceAll("-", "") + ".m3u8";
                        m3u8Name = fileName;
                        mapM3U8.put(fileName, buffer);
                    } else {
                        // ts
                        fileName = UUID.randomUUID().toString().replaceAll("-", "") + ".ts";
                        mapM3U8.put(fileName, swapStream.toByteArray());
                        mapName.put(orgName, fileName);
                    }
                } catch (Exception e) {
                } finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e) {
                        } finally {
                            inputStream = null;
                        }
                    }
                    if (swapStream != null) {
                        try {
                            swapStream.close();
                        } catch (IOException e) {
                        } finally {
                            swapStream = null;
                        }
                    }
                }
            }

            buff = null;
            buffer = null;

            for (String name : mapName.keySet()) {
                m3u8 = m3u8.replace(name, mapName.get(name));
            }

            mapM3U8.put(m3u8Name, m3u8.getBytes());

            for (String name : mapM3U8.keySet()) {
                M3U8DownloadVO m3u8DownloadVO = new M3U8DownloadVO();
                m3u8DownloadVO.setLength(0L);
                buffer = mapM3U8.get(name);
                if (buffer != null) {
                    m3u8DownloadVO.setBuffer(buffer);
                    m3u8DownloadVO.setLength((long) buffer.length);
                }
                m3u8DownloadVO.setFileName(name);
                if (name.toLowerCase().contains("m3u8")) {
                    // m3u8
                    m3u8DownloadVO.setType("m3u8");
                    Pattern reg = Pattern.compile("^#EXTINF:(.*),$", Pattern.MULTILINE);
                    Matcher matcher = reg.matcher(m3u8);
                    double duration = 0d;

                    while (matcher.find()) {
                        duration += DoubleUtil.toDouble(matcher.group(1));
                    }
                    m3u8DownloadVO.setDuration(duration);

                } else {
                    // ts
                    m3u8DownloadVO.setType("ts");
                }

                m3u8DownloadHandler.handler(m3u8DownloadVO);
                buffer = null;
                m3u8DownloadVO.setBuffer(null);
            }


        } catch (Exception e) {
            new CommonRuntimeException(e);
        }

    }

//	public static void main(String[] args) {
//		download("https://liveservice.cmschina.com/livestore/caacca0abdbf48fb8d85276041de3c3f_1609928688066_720P.m3u8", new M3U8DownloadHandler() {
//			@Override
//			public void handler(M3U8DownloadVO vo) {
//				try {
//					FileOutputStream downloadFile = new FileOutputStream("E:\\data\\tmp\\download\\" + vo.getFileName());
//					downloadFile.write(vo.getBuffer());
//			        downloadFile.close();
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});


//		getByZip(new File("F:\\xhz\\Desktop\\polyv-招商证券\\mp4转m3u8\\output.zip"), new M3U8DownloadHandler() {
//			@Override
//			public void handler(M3U8DownloadVO vo) {
//				try {
//					FileOutputStream downloadFile = new FileOutputStream("E:\\data\\tmp\\download\\zip\\" + vo.getFileName());
//					downloadFile.write(vo.getBuffer());
//			        downloadFile.close();
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

//		try {
//			getByZip(new FileInputStream(new File("F:\\xhz\\Desktop\\polyv-招商证券\\mp4转m3u8\\output.zip")), new M3U8DownloadHandler() {
//				@Override
//				public void handler(M3U8DownloadVO vo) {
//					try {
//						FileOutputStream downloadFile = new FileOutputStream("E:\\data\\tmp\\download\\zip\\" + vo.getFileName());
//						downloadFile.write(vo.getBuffer());
//				        downloadFile.close();
//
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			});
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
