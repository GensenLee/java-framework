package org.devops.mjar.live.core.servlet;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
 
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.devops.core.utils.util.StreamUtil;

/**
 * 可复用请求体
 * @author GENSEN
 */
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper implements CommonRequestWrapper {

    //用于将流保存下来
    private byte[] requestBody = null;

	public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
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
	    setAttribute(RESPONSE_DATA, data);
    }

    @Override
    public Object getConvertedData() {
        return getAttribute(RESPONSE_DATA);
    }

    public static final String RESPONSE_DATA = "com.sinmn.polyv.core.servlet.BodyReaderHttpServletRequestWrapper.CONVERTED_DATA";
}
 