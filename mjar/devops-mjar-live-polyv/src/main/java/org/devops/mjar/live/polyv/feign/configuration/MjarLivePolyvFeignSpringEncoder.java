package org.devops.mjar.live.polyv.feign.configuration;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;

import java.lang.reflect.Type;

/**
 * @author GENSEN
 * @date 2021/3/8 14:02
 * @description：自定义表单解析
 */
@Slf4j
public class MjarLivePolyvFeignSpringEncoder extends SpringEncoder {

    private MjarLivePolyvFeignMultipartFormEncoder customSpringFormEncoder;

    public MjarLivePolyvFeignSpringEncoder(ObjectFactory<HttpMessageConverters> messageConverters, MjarLivePolyvFeignMultipartFormEncoder customSpringFormEncoder) {
        super(messageConverters);
        this.customSpringFormEncoder = customSpringFormEncoder;
    }

    @Override
    public void encode(Object requestBody, Type bodyType, RequestTemplate request) throws EncodeException {
        /*
         * do something before encoding
         */
//        if (requestBody != null) {
//            Collection<String> contentTypes = request.headers()
//                    .get(HttpEncoding.CONTENT_TYPE);
//            MediaType requestContentType = null;
//            if (contentTypes != null && !contentTypes.isEmpty()) {
//                String type = contentTypes.iterator().next();
//                requestContentType = MediaType.valueOf(type);
//            }
//
//            if (MediaType.MULTIPART_FORM_DATA.equalsTypeAndSubtype(requestContentType)) {
//                try {
//                    customSpringFormEncoder.encode(requestBody, bodyType, request);
//                    return;
//                } catch (EncodeException e) {
//                    log.info("自定义解析失败[{}]", e.getMessage());
//                }
//            }
//        }
        super.encode(requestBody, bodyType, request);
    }
}
