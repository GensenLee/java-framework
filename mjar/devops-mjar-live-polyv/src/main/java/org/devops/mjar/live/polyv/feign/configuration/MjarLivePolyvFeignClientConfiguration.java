package org.devops.mjar.live.polyv.feign.configuration;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.springfox.SwaggerJsonSerializer;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Logger;
import feign.QueryMapEncoder;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import org.devops.mjar.live.core.feign.configuration.FeignPolyvErrorDecoder;
import org.devops.mjar.live.core.feign.configuration.FeignGZIPResponseDecoder;
import org.devops.mjar.live.core.feign.interceptor.MjarLiveFeignRequestInterceptor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.AnnotatedParameterProcessor;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;


/**
 * @author GENSEN
 * 20210908 部分接口可能出现字段传null是报错，可通过定义一个application/json类型的HttpMessageConverter，json转换是定义null值字段忽略
 */
@Configuration
public class MjarLivePolyvFeignClientConfiguration {


    @Bean
    public Decoder feignDecoder() {
        final HttpMessageConverter fastJsonConverter = getFastJsonConverter();
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(fastJsonConverter);
        return new FeignGZIPResponseDecoder(new ResponseEntityDecoder(new SpringDecoder(objectFactory)));
    }

    @Bean
    public Encoder feignEncoder() {
        final HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter(customObjectMapper());
        final FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        final MjarLivePolyvFormHttpMessageConverter mjarLivePolyvFormHttpMessageConverter = new MjarLivePolyvFormHttpMessageConverter();

        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(mjarLivePolyvFormHttpMessageConverter, formHttpMessageConverter, jacksonConverter);
        return new MjarLivePolyvFeignSpringEncoder(objectFactory, new MjarLivePolyvFeignMultipartFormEncoder());
    }

    public ObjectMapper customObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        //Customize as much as you want
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Bean
    public MjarLiveFeignRequestInterceptor feignClientInterceptor() {
        return new MjarLiveFeignRequestInterceptor();
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignPolyvErrorDecoder();
    }

    @Bean
    public AnnotatedParameterProcessor polyvApiAnnotatedParameterProcessor() {
        return new MjarLivePolyvRequestParamParameterProcessor();
    }

    @Bean
    public QueryMapEncoder polyvApiQueryMapEncoder() {
        return new MjarLivePolyvQueryMapEncoder();
    }


    private FastJsonHttpMessageConverter getFastJsonConverter() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        MediaType mediaTypeJson = MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE);
        supportedMediaTypes.add(mediaTypeJson);
        converter.setSupportedMediaTypes(supportedMediaTypes);
        FastJsonConfig config = new FastJsonConfig();
        config.getSerializeConfig().put(JSON.class, new SwaggerJsonSerializer());
        config.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);
        converter.setFastJsonConfig(config);
        return converter;
    }

    @Bean
    public Logger.Level level(){
        return Logger.Level.FULL;
    }

}