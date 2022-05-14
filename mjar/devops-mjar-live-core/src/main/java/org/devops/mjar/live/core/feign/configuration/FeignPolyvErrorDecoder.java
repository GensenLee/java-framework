package org.devops.mjar.live.core.feign.configuration;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.devops.core.utils.util.FastJsonUtils;
import org.devops.core.utils.util.ListUtil;
import org.devops.core.utils.util.StringUtil;
import org.devops.mjar.live.core.handler.MjarLiveContext;
import org.devops.mjar.live.core.exception.LiveApiException;
import org.devops.mjar.live.core.model.PolyvApiResult;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author GENSEN
 */
@Slf4j
public class FeignPolyvErrorDecoder implements ErrorDecoder {

    /**
     * 外部实现该接口可实现多次解析
     */
    @Autowired(required = false)
    private List<ExpandErrorDecoder> expandErrorDecoderList;

    private ExpandErrorDecoder expandErrorDecoder = new ExpandErrorDecoder.Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        MjarLiveContext.getContext().getMjarLiveFeignResponseHandler().handle(response);
        String message = "";
        try {
            message = Util.toString(response.body().asReader(Charset.defaultCharset()));
            log.error("#### PolyvFeignErrorDecoder feign接口异常 methodKey [{}] , message [{}]", methodKey, message);
            PolyvApiResult<?> errorBean = FastJsonUtils.getBean(message, PolyvApiResult.class);
            if (StringUtil.isEmpty(errorBean.getMessage())) {
                JSONObject jsonObject = JSON.parseObject(message);
                errorBean.setMessage(jsonObject.getString("msg"));
            }
            if (StringUtil.isEmpty(errorBean.getMessage())) {
                PolyvApiResult.Error error = errorBean.getError();
                if (error != null && StringUtil.isNotEmpty(error.getDesc())) {
                    errorBean.setMessage(error.getDesc());
                }
            }
            LiveApiException apiException = new LiveApiException(errorBean.getCode(), errorBean.getMessage());
            apiException.setErrorBean(errorBean);
            return apiException;
        } catch (Exception e) {
            return expandErrorDecoder(methodKey, message, response);
        }
    }

    private Exception expandErrorDecoder(String methodKey, String message, Response response) {
        if (ListUtil.isNull(expandErrorDecoderList)) {
            return expandErrorDecoder.decode(methodKey, message, response);
        }
        Exception exception;
        for (ExpandErrorDecoder errorDecoder : expandErrorDecoderList) {
            exception = errorDecoder.decode(methodKey, message, response);
            if (exception != null) {
                return exception;
            }
        }
        return expandErrorDecoder.decode(methodKey,message, response);
    }


    /**
     * 当 {@link FeignPolyvErrorDecoder#decode(String, Response)} 解析失败时调用
     */
    public interface ExpandErrorDecoder {
        /**
         * @param methodKey
         * @param message
         * @param response
         * @return
         */
        Exception decode(String methodKey, String message, Response response);

        class Default implements ExpandErrorDecoder {

            @Override
            public Exception decode(String methodKey, String message, Response response) {
                if (log.isDebugEnabled()) {
                    log.error("#### ExpandErrorDecoder.Default feign接口异常 methodKey [{}] , message [{}]", methodKey, message);
                }
                return new LiveApiException(response.status(), message);
            }
        }
    }
}
