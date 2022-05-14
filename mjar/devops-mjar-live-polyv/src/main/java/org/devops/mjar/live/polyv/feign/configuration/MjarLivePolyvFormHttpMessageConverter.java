/*
 * Copyright 2002-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.devops.mjar.live.polyv.feign.configuration;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.http.converter.*;
import org.springframework.lang.Nullable;
import org.springframework.util.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 自定义Post参数转换
 *
 * @author GENSEN
 */
public class MjarLivePolyvFormHttpMessageConverter implements HttpMessageConverter<Object> {

    /**
     * The default charset used by the converter.
     */
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private static final MediaType DEFAULT_FORM_DATA_MEDIA_TYPE =
            new MediaType(MediaType.APPLICATION_FORM_URLENCODED, DEFAULT_CHARSET);


    private List<MediaType> supportedMediaTypes = new ArrayList<>();

    private List<HttpMessageConverter<?>> partConverters = new ArrayList<>();

    private Charset charset = DEFAULT_CHARSET;


    public MjarLivePolyvFormHttpMessageConverter() {
        this.supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        this.supportedMediaTypes.add(MediaType.MULTIPART_FORM_DATA);
        this.supportedMediaTypes.add(MediaType.MULTIPART_MIXED);

        this.partConverters.add(new ByteArrayHttpMessageConverter());
        this.partConverters.add(new StringHttpMessageConverter());
        this.partConverters.add(new ResourceHttpMessageConverter());

        applyDefaultCharset();
    }


    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Collections.unmodifiableList(this.supportedMediaTypes);
    }


    /**
     * Apply the configured charset as a default to registered part converters.
     */
    private void applyDefaultCharset() {
        for (HttpMessageConverter<?> candidate : this.partConverters) {
            if (candidate instanceof AbstractHttpMessageConverter) {
                AbstractHttpMessageConverter<?> converter = (AbstractHttpMessageConverter<?>) candidate;
                // Only override default charset if the converter operates with a charset to begin with...
                if (converter.getDefaultCharset() != null) {
                    converter.setDefaultCharset(this.charset);
                }
            }
        }
    }

    @Override
    public boolean canRead(Class<?> clazz, @Nullable MediaType mediaType) {
        if (!MultiValueMap.class.isAssignableFrom(clazz)) {
            return false;
        }
        if (mediaType == null) {
            return true;
        }
        for (MediaType supportedMediaType : getSupportedMediaTypes()) {
            if (supportedMediaType.getType().equalsIgnoreCase("multipart")) {
                // We can't read multipart, so skip this supported media type.
                continue;
            }
            if (supportedMediaType.includes(mediaType)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canWrite(Class<?> clazz, @Nullable MediaType mediaType) {

        if (mediaType == null || MediaType.ALL.equals(mediaType)) {
            return true;
        }
        for (MediaType supportedMediaType : getSupportedMediaTypes()) {
            if (supportedMediaType.isCompatibleWith(mediaType)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public MultiValueMap<String, String> read(@Nullable Class<? extends Object> clazz,
                                              HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

        MediaType contentType = inputMessage.getHeaders().getContentType();
        Charset charset = (contentType != null && contentType.getCharset() != null ?
                contentType.getCharset() : this.charset);
        String body = StreamUtils.copyToString(inputMessage.getBody(), charset);

        String[] pairs = StringUtils.tokenizeToStringArray(body, "&");
        MultiValueMap<String, String> result = new LinkedMultiValueMap<>(pairs.length);
        for (String pair : pairs) {
            int idx = pair.indexOf('=');
            if (idx == -1) {
                result.add(URLDecoder.decode(pair, charset.name()), null);
            } else {
                String name = URLDecoder.decode(pair.substring(0, idx), charset.name());
                String value = URLDecoder.decode(pair.substring(idx + 1), charset.name());
                result.add(name, value);
            }
        }
        return result;
    }

    @Override
    public void write(Object map, @Nullable MediaType contentType, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        Map<String, Object> formData = ParameterConverterHelper.converter(map);
        writeForm(formData, contentType, outputMessage);
    }

    private void writeForm(Map<String, Object> formData, @Nullable MediaType contentType,
                           HttpOutputMessage outputMessage) throws IOException {

        contentType = getFormContentType(contentType);
        outputMessage.getHeaders().setContentType(contentType);

        Charset charset = contentType.getCharset();
        Assert.notNull(charset, "No charset"); // should never occur

        byte[] bytes = serializeForm(formData, charset).getBytes(charset);
        outputMessage.getHeaders().setContentLength(bytes.length);

        if (outputMessage instanceof StreamingHttpOutputMessage) {
            StreamingHttpOutputMessage streamingOutputMessage = (StreamingHttpOutputMessage) outputMessage;
            streamingOutputMessage.setBody(outputStream -> StreamUtils.copy(bytes, outputStream));
        } else {
            StreamUtils.copy(bytes, outputMessage.getBody());
        }
    }

    protected MediaType getFormContentType(@Nullable MediaType contentType) {
        if (contentType == null) {
            return DEFAULT_FORM_DATA_MEDIA_TYPE;
        } else if (contentType.getCharset() == null) {
            return new MediaType(contentType, this.charset);
        } else {
            return contentType;
        }
    }

    protected String serializeForm(Map<String, Object> formData, Charset charset) {
        StringBuilder builder = new StringBuilder();
        formData.forEach((name, value) -> {
            try {
                if (builder.length() != 0) {
                    builder.append('&');
                }
                builder.append(URLEncoder.encode(name, charset.name()));
                if (value != null) {
                    builder.append('=');
                    builder.append(URLEncoder.encode(String.valueOf(value), charset.name()));
                }
            } catch (UnsupportedEncodingException ex) {
                throw new IllegalStateException(ex);
            }
        });

        return builder.toString();
    }

}
