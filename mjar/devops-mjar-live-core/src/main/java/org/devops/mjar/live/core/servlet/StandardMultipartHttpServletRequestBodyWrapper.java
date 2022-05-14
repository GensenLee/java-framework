package org.devops.mjar.live.core.servlet;

import org.devops.core.utils.util.StreamUtil;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author GENSEN
 * @date 2021/7/19 17:21
 * @description：文件请求体包装
 */
public class StandardMultipartHttpServletRequestBodyWrapper extends StandardMultipartHttpServletRequest implements CommonRequestWrapper {
    //用于将流保存下来
    private byte[] requestBody = null;

    public StandardMultipartHttpServletRequestBodyWrapper(HttpServletRequest request) throws IOException {
        super(request);
        requestBody = StreamUtil.copyToByteArray(request.getInputStream());
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(requestBody);
        return new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return bais.read();
            }
            @Override
            public boolean isFinished() {
                return false;
            }
            @Override
            public boolean isReady() {
                return false;
            }
            @Override
            public void setReadListener(ReadListener readListener) {

            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException{
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public void setConvertedData(Object data) {
        setAttribute(BodyReaderHttpServletRequestWrapper.RESPONSE_DATA, data);
    }

    @Override
    public Object getConvertedData() {
        return getAttribute(BodyReaderHttpServletRequestWrapper.RESPONSE_DATA);
    }

}
