package org.devops.mjar.live.transform.resolve.xml;


import org.devops.mjar.live.core.exception.LiveApiRuntimeException;

/**
 * @author GENSEN
 * @date 2021/6/28 14:33
 * @description：xml解析异常
 */
public class LiveTransformerXmlParsingException extends LiveApiRuntimeException {

    public LiveTransformerXmlParsingException(String message) {
        super(message);
    }

}
