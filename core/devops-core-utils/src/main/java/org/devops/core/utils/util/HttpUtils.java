package org.devops.core.utils.util;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.conn.ConnectTimeoutException;
import org.devops.core.utils.vo.HttpResult;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * @author xhz
 * @Description HTTP请求处理帮助类
 * <p>
 * 全局超时时间通过以下两个方式指定
 * <link>#setConnectTimeout</link>
 * <link>#setReadTimeout</link>
 */
public class HttpUtils {
    private static final Log logger = LogFactory.getLog(HttpUtils.class);

    /**
     * 连接池管理
     */
    private static final MultiThreadedHttpConnectionManager CONNECTION_MANAGER;
    /**
     * 连接超时，毫秒
     */
    private static final int CONNECT_TIMEOUT = 15000;
    /**
     * 读超时，毫秒
     */
    private static final int READ_TIMEOUT = 15000;

    static {
        CONNECTION_MANAGER = new MultiThreadedHttpConnectionManager();
        HttpConnectionManagerParams params = CONNECTION_MANAGER.getParams();
        params.setDefaultMaxConnectionsPerHost(100);
        setTimeoutDefault();
    }

    /**
     * 设置连接超时
     *
     * @param timeout
     */
    public static void setConnectTimeout(int timeout) {
        HttpConnectionManagerParams managerParams = CONNECTION_MANAGER.getParams();
        managerParams.setConnectionTimeout(timeout);
    }

    /**
     * 设置读超时
     *
     * @param timeout
     */
    public static void setReadTimeout(int timeout) {
        HttpConnectionManagerParams managerParams = CONNECTION_MANAGER.getParams();
        managerParams.setSoTimeout(timeout);
    }

    /**
     * 恢复默认超时
     */
    public static void setTimeoutDefault() {
        HttpConnectionManagerParams managerParams = CONNECTION_MANAGER.getParams();
        // 设置连接超时时间(单位毫秒)
        managerParams.setConnectionTimeout(CONNECT_TIMEOUT);
        // 设置读数据超时时间(单位毫秒)
        managerParams.setSoTimeout(READ_TIMEOUT);
    }


    /**
     * @param url
     * @param headerMap
     * @return
     */
    public static String doGet(String url, MultiValueMap<String, String> headerMap) {
        HttpResult httpResult = doHttpGet(url, headerMap);
        return httpResult.readResponseAsString();
    }

    /**
     * 带参数集请求
     *
     * @param url
     * @param param
     * @return
     */
    public static String doGet(String url, Map<String, Object> param) {
        if (param != null && !param.isEmpty()) {
            String s = mapToUrlString(param);
            url = url + "?" + s;
        }
        HttpResult httpResult = doHttpGet(url, new HashMap<>());
        return httpResult.readResponseAsString();
    }

    /**
     * 带参数集和头部请求
     *
     * @param url
     * @param param
     * @param headerMap
     * @return
     */
    public static String doGet(String url, Map<String, Object> param, MultiValueMap<String, String> headerMap) {
        if (param != null && !param.isEmpty()) {
            String s = mapToUrlString(param);
            url = url + "?" + s;
        }
        HttpResult httpResult = doHttpGet(url, headerMap);
        return httpResult.readResponseAsString();
    }

    /**
     * 默认超时时间的GET请求
     * 发起HTTP GET请求
     *
     * @param url
     * @return
     */
    public static HttpResult doHttpGet(String url, Map<String, Object> param) {
        if (param != null && !param.isEmpty()) {
            String s = mapToUrlString(param);
            url = url + "?" + s;
        }
        return doHttpGet(url, null, CONNECT_TIMEOUT, READ_TIMEOUT);
    }

    /**
     * 默认超时时间的GET请求
     * 发起HTTP GET请求
     *
     * @param url
     * @return
     */
    public static HttpResult doHttpGet(String url) {
        return doHttpGet(url, null, CONNECT_TIMEOUT, READ_TIMEOUT);
    }

    /**
     * 默认超时时间的GET请求
     * 发起HTTP GET请求
     *
     * @param url
     * @param headerMap
     * @return
     */
    public static HttpResult doHttpGet(String url, MultiValueMap<String, String> headerMap) {
        return doHttpGet(url, headerMap, CONNECT_TIMEOUT, READ_TIMEOUT);
    }

    /**
     * 发起HTTP GET请求
     *
     * @param url
     * @param headerMap
     * @param connectionTimeOut
     * @param readTimeOut
     * @return
     */
    public static HttpResult doHttpGet(String url, MultiValueMap<String, String> headerMap, int connectionTimeOut, int readTimeOut) {
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        HttpClientParams httpClientParams = new HttpClientParams();
        httpClientParams.setSoTimeout(readTimeOut);
        httpClientParams.setConnectionManagerTimeout(connectionTimeOut);
        HttpMethod method = new GetMethod(url);
        return doHttpRequest(url, headerMap, httpClientParams, method, null);
    }

    /**
     * 发起http请求
     *
     * @param url
     * @param headerMap
     * @param httpClientParams
     * @param method
     * @param postStream       post请求体
     * @return
     */
    private static HttpResult doHttpRequest(String url, MultiValueMap<String, String> headerMap, HttpClientParams httpClientParams, HttpMethod method, Object postStream) {
        logger.info(url);
        String exceptionMessage = "";
        long period = 0;
        boolean isException = false;
        long start = 0;
        int code = 200;
        HttpClient client = new HttpClient(CONNECTION_MANAGER);
        if (headerMap != null) {
            for (Entry<String, List<String>> entry : headerMap.entrySet()) {
                for (String value : entry.getValue()) {
                    method.addRequestHeader(entry.getKey(), value);
                }
            }
        }
        client.setParams(httpClientParams);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            if (method instanceof PostMethod && postStream != null) {
                String bodyString;
                RequestEntity requestEntity = null;
                if (postStream instanceof String) {
                    bodyString = (String) postStream;
                    requestEntity = new StringRequestEntity(bodyString, MediaType.TEXT_HTML_VALUE, StandardCharsets.UTF_8.name());
                } else {
                    bodyString = FastJsonUtils.toJsonString(postStream);
                    requestEntity = new StringRequestEntity(bodyString, MediaType.APPLICATION_JSON_VALUE, StandardCharsets.UTF_8.name());
                }
                ((PostMethod) method).setRequestEntity(requestEntity);
            }
            start = System.currentTimeMillis();
            client.executeMethod(method);
            StreamUtil.copy(method.getResponseBodyAsStream(), byteArrayOutputStream);
            period = System.currentTimeMillis() - start;
            code = method.getStatusCode();
        } catch (SocketTimeoutException e) {
            exceptionMessage = "网络连接超时 " + e.getMessage() + " 设置的超时时间为：" + CONNECT_TIMEOUT + "毫秒";
            period = System.currentTimeMillis() - start;
            isException = false;
            //经常出现这种异常，需要做分布式的检查，同时需要把报警逻辑拆分到另一个进程做处理,现在简单做一个sleep操作
            //注意：sleep容易引发任务执行不完的异常
            try {
                Thread.sleep(5000);
            } catch (Exception ex) {
                logger.error("[Exception 出错啦]", ex);
            }
        } catch (ConnectTimeoutException e) {
            exceptionMessage = "连接超时 " + e.getMessage() + " 设置的超时时间为：" + CONNECT_TIMEOUT + "毫秒";
            period = System.currentTimeMillis() - start;
            isException = false;
        } catch (UnknownHostException e) {
            exceptionMessage = "不能解析的域名：" + e.getMessage();
            period = System.currentTimeMillis() - start;
            isException = true;
            logger.info("URI exception : " + e.getMessage(), e);
        } catch (ConnectException e) {
            exceptionMessage = "拒绝连接的域名 " + e.getMessage();
            period = System.currentTimeMillis() - start;
            isException = true;
            logger.info("URI exception : " + e.getMessage(), e);
        } catch (Exception e) {
            exceptionMessage = e.getClass().getSimpleName() + " " + e.getMessage() + " 设置的超时时间为：" + CONNECT_TIMEOUT + "毫秒";
            period = System.currentTimeMillis() - start;
            isException = true;
            logger.info("URI exception : " + e.getMessage(), e);
        } finally {
            method.releaseConnection();
        }

        StringBuffer requestHeader = new StringBuffer();
        StringBuffer responseHeader = new StringBuffer();
        Header[] reqHd = method.getRequestHeaders();
        Header[] respHd = method.getResponseHeaders();
        if (reqHd != null) {
            for (Header header : reqHd) {
                requestHeader.append(header.toString()).append("\n");
            }
        }
        if (respHd != null) {
            for (Header header : respHd) {
                responseHeader.append(header.toString()).append("\n");
            }
        }
        HttpResult res = new HttpResult();
        res.setExceptionMessage(exceptionMessage);
        res.setUrl(url);
        res.setReqTime(start);
        res.setRespPeriod(period);
        res.setException(isException);
        res.setRequestHeader(requestHeader.toString());
        res.setResponse(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        res.setResponseHeader(responseHeader.toString());
        res.setResponseCode(code);
        return res;
    }

    /**
     * post 请求
     *
     * @param url
     * @return
     */
    public static HttpResult doHttpPost(String url) {
        return doHttpPost(url, null, null, CONNECT_TIMEOUT, READ_TIMEOUT);
    }

    /**
     * post 请求
     *
     * @param url
     * @param headerMap
     * @return
     */
    public static HttpResult doHttpPost(String url, MultiValueMap<String, String> headerMap) {
        return doHttpPost(url, headerMap, null, CONNECT_TIMEOUT, READ_TIMEOUT);
    }

    /**
     * post 请求
     *
     * @param url
     * @param body
     * @return
     */
    public static HttpResult doHttpPost(String url, Object body) {
        return doHttpPost(url, null, body, CONNECT_TIMEOUT, READ_TIMEOUT);
    }

    /**
     * post 请求
     *
     * @param url
     * @param param
     * @return
     */
    public static HttpResult doHttpPost(String url, Map<String, Object> param) {
        if (param != null && !param.isEmpty()) {
            String s = mapToUrlString(param);
            url = url + "?" + s;
        }
        return doHttpPost(url, null, null, CONNECT_TIMEOUT, READ_TIMEOUT);
    }

    /**
     * post 请求
     *
     * @param url
     * @param param
     * @param body
     * @return
     */
    public static HttpResult doHttpPost(String url, Map<String, Object> param, Object body) {
        if (param != null && !param.isEmpty()) {
            String s = mapToUrlString(param);
            url = url + "?" + s;
        }
        return doHttpPost(url, null, body, CONNECT_TIMEOUT, READ_TIMEOUT);
    }

    /**
     * post 请求
     *
     * @param url
     * @param headerMap
     * @param body
     * @return
     */
    public static HttpResult doHttpPost(String url, MultiValueMap<String, String> headerMap, Map<String, Object> param, Object body) {
        if (param != null && !param.isEmpty()) {
            String s = mapToUrlString(param);
            url = url + "?" + s;
        }
        return doHttpPost(url, headerMap, body, CONNECT_TIMEOUT, READ_TIMEOUT);
    }

    /**
     * post 请求
     *
     * @param url
     * @param headerMap
     * @param body
     * @return
     */
    public static HttpResult doHttpPost(String url, MultiValueMap<String, String> headerMap, Object body) {
        return doHttpPost(url, headerMap, body, CONNECT_TIMEOUT, READ_TIMEOUT);
    }

    /**
     * post 请求
     *
     * @param url
     * @param headerMap
     * @param connectionTimeOut
     * @param readTimeOut
     * @return
     */
    public static HttpResult doHttpPost(String url, MultiValueMap<String, String> headerMap, Object body, int connectionTimeOut, int readTimeOut) {
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        HttpClientParams httpClientParams = new HttpClientParams();
        httpClientParams.setSoTimeout(readTimeOut);
        httpClientParams.setConnectionManagerTimeout(connectionTimeOut);
        HttpMethod method = new PostMethod(url);
        return doHttpRequest(url, headerMap, httpClientParams, method, body);
    }

    /**
     * 带参数请求
     *
     * @param url
     * @param param
     * @return
     */
    public static String doPost(String url, Map<String, Object> param) {
        if (param != null && !param.isEmpty()) {
            String s = mapToUrlString(param);
            url = url + "?" + s;
        }
        HttpResult httpResult = doHttpPost(url);
        return httpResult.readResponseAsString();
    }

    /**
     * 带参数请求和头部
     *
     * @param url
     * @param param
     * @return
     */
    public static String doPost(String url, Map<String, Object> param, MultiValueMap<String, String> headerMap) {
        if (param != null && !param.isEmpty()) {
            String s = mapToUrlString(param);
            url = url + "?" + s;
        }
        HttpResult httpResult = doHttpPost(url, headerMap, null);
        return httpResult.readResponseAsString();
    }

    /**
     * 带请求体请求
     *
     * @param url
     * @param param
     * @param body
     * @return
     */
    public static String doPost(String url, Map<String, Object> param, Object body) {
        if (param != null && !param.isEmpty()) {
            String s = mapToUrlString(param);
            url = url + "?" + s;
        }
        HttpResult httpResult = doHttpPost(url, body);
        return httpResult.readResponseAsString();
    }

    /**
     * 带请求体和头部请求
     *
     * @param url
     * @param headerMap
     * @param body
     * @return
     */
    public static String doPost(String url, MultiValueMap<String, String> headerMap, Object body) {
        HttpResult httpResult = doHttpPost(url, headerMap, body);
        return httpResult.readResponseAsString();
    }


    /**
     * 带参数和请求体请求
     *
     * @param url
     * @param body
     * @return
     */
    public static String doPost(String url, Object body) {
        HttpResult httpResult = doHttpPost(url, body);
        return httpResult.readResponseAsString();
    }

    /**
     * 不带参数
     *
     * @param url
     * @return
     */
    public static String doPost(String url) {
        HttpResult httpResult = doHttpPost(url);
        return httpResult.readResponseAsString();
    }

    /**
     * @param map
     * @return
     */
    private static String mapToUrlString(Map<String, Object> map) {
        StringBuilder param = new StringBuilder();
        if (map != null && !map.isEmpty()) {
            for (Entry<String, Object> stringStringEntry : map.entrySet()) {
                if (stringStringEntry.getValue() == null) {
                    continue;
                }
                if (param.length() > 0) {
                    param.append('&');
                }

                Entry<String, Object> entry = stringStringEntry;
                String key = entry.getKey();
                String value = String.valueOf(entry.getValue());
                try {
                    if (value != null) {
                        param.append(key).append('=').append(URLEncoder.encode(value, StandardCharsets.UTF_8.name()));
                    }
                } catch (UnsupportedEncodingException e) {
                    logger.info(e.getMessage(), e);
                }
            }
        }
        return param.toString();
    }

    /**
     * 后端取累加图表数据，超时时间设置为10分钟
     *
     * @param url
     * @param map
     * @return
     */
    public static String doGraphPost(String url, Map<String, Object> map) {
        String param = mapToUrlString(map);
        // logger.info(param.toString());
        StringBuilder sb = new StringBuilder();
        BufferedReader in = null;
        try {
            logger.info("get data url :" + url + "?" + param);
            URL postURL = new URL(url);
            HttpURLConnection con = (HttpURLConnection) postURL.openConnection();

            con.setUseCaches(false); // do not use cache
            con.setDoOutput(true); // use for output
            con.setDoInput(true); // use for Input
            con.setConnectTimeout(15000);
            con.setReadTimeout(600000);
            con.setRequestMethod("POST"); // use the POST method to submit the
            PrintWriter out = new PrintWriter(con.getOutputStream());
            out.print(param.toString()); // send to server
            out.close(); // close outputstream
            in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
            String aline;
            while (null != (aline = in.readLine())) {
                sb.append(aline).append('\n');
            }
        } catch (IOException e) {
            logger.error("[Exception 出错啦]", e);
            logger.error(url + "|" + e.getMessage(), e);
            return "";
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.info(e.getMessage(), e);
                }
                in = null;
            }
        }
        // logger.info(url+"?"+param.toString());
        return sb.toString();
    }

    private static final String BOUNDARYSTR = "XMKSSS***********";
    private static final String BOUNDARY = "--" + BOUNDARYSTR + "\r\n";

    /**
     * 上传
     *
     * @param urlString
     * @param fileName
     * @param fileByte
     * @return
     */
    public static String upload(String urlString, String fileName, byte[] fileByte) {
        HttpURLConnection urlConnection = null;
        try {
            Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("fileName", fileName);
            Map<String, String> propertys = new HashMap<String, String>();
            URL url;
            url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setRequestProperty("Content-type", "multipart/form-data;boundary=" + BOUNDARYSTR);
            urlConnection.connect();
            BufferedOutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());

            StringBuffer param = new StringBuffer();
            for (String key : parameters.keySet()) {
                param.append(BOUNDARY);
                param.append("Content-Disposition:form-data;name=\"");
                param.append(key);
                param.append("\"\r\n\r\n");
                param.append(parameters.get(key));
                param.append("\r\n");
            }
            out.write(param.toString().getBytes());
            out.write(BOUNDARY.getBytes());
            StringBuilder filenamesb = new StringBuilder();
            filenamesb.append("Content-Disposition:form-data;Content-Type:application/octet-stream;name=\"file\";filename=\"");
            filenamesb.append(fileName + "\"\r\n\r\n");
            out.write(filenamesb.toString().getBytes());
            out.write(fileByte);
            out.write("\r\n\r\n".getBytes());
            out.write(("--" + BOUNDARYSTR + "--\r\n").getBytes());
            out.flush();
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String aline;
            while (null != (aline = in.readLine())) {
                sb.append(aline);
            }
            return sb.toString();
        } catch (IOException e) {
            logger.error("[Exception 出错啦]", e);
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return "";
    }


    /**
     * 发起HTTP GET请求
     *
     * @param url
     * @return
     */
    public static InputStream download(String url) {
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        HttpResult httpResult = doHttpGet(url, new HashMap<>());
        return httpResult.getResponse();
    }

    /**
     * 发起HTTP GET请求
     *
     * @param url
     * @return
     */
    public static byte[] downloadToByte(String url) {
        InputStream download = download(url);
        try {
            return StreamUtil.copyToByteArray(download);
        } catch (IOException e) {
            logger.error("downloadToByte IOException", e);
        }
        return null;
    }

    /**
     * 下载到本地文件
     *
     * @param url    下载地址
     * @param toPath 本地文件地址
     * @return
     */
    public static void downloadToFile(String url, String toPath) {
        logger.info("download url ：" + url);
        InputStream inputStream = null;
        try {
            URL downloadUrl = new URL(url);
            HttpURLConnection con = (HttpURLConnection) downloadUrl.openConnection();

            con.setUseCaches(false); // do not use cache
            con.setDoOutput(true); // use for output
            con.setDoInput(true); // use for Input
            con.setConnectTimeout(15000);
            con.setReadTimeout(600000);
            con.setRequestMethod("GET"); // use the POST method to submit the
            con.connect();
            inputStream = con.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            FileOutputStream fileOutputStream = new FileOutputStream(toPath);
            BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);
            byte[] buf = new byte[4096];
            int length = bis.read(buf);
            //保存文件
            while (length != -1) {
                bos.write(buf, 0, length);
                length = bis.read(buf);
            }
            bos.flush();
            bos.close();
            bis.close();
            con.disconnect();
        } catch (IOException e) {
            logger.error("[Exception 出错啦]", e);
            logger.error(url + "|" + e.getMessage(), e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.info(e.getMessage(), e);
                }
                inputStream = null;
            }
        }
    }

}
