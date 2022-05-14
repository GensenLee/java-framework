package org.devops.mjar.live.core.servlet;

import org.devops.core.utils.util.StreamUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * 可复用请求体
 * @author GENSEN
 */
public class BodyReaderHttpServletResponseWrapper extends HttpServletResponseWrapper {


    /**
     * 用于将流保存下来
     */
    private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    public BodyReaderHttpServletResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    public byte[] getBody() {
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public ServletOutputStream getOutputStream() {
        return new ServletOutputStreamWrapper(this.byteArrayOutputStream , this);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(new OutputStreamWriter(this.byteArrayOutputStream , this.getCharacterEncoding()));
    }

    @Data
    @AllArgsConstructor
    private static class ServletOutputStreamWrapper extends ServletOutputStream {
        private ByteArrayOutputStream outputStream;
        private HttpServletResponse response;
        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setWriteListener(WriteListener listener) {

        }
        @Override
        public void write(int b) throws IOException {
            this.outputStream.write(b);
        }

        @Override
        public void flush() throws IOException {
            if (! this.response.isCommitted()) {
                byte[] body = this.outputStream.toByteArray();
                ServletOutputStream outputStream = this.response.getOutputStream();
                outputStream.write(body);
                outputStream.flush();
            }
        }
    }

}
 