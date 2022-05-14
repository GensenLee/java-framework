package org.devops.mjar.live.transform.operator.requester;

import org.devops.mjar.live.transform.enums.HttpMethod;
import org.devops.mjar.live.transform.model.DefaultChannelOperatorParameter;
import org.devops.mjar.live.transform.operator.DefaultOperator;
import org.devops.mjar.live.core.operator.ParameterBuilder;

/**
 * @author GENSEN
 * @date 2021/6/25 11:01
 * @description：频道请求器
 */
public class DefaultChannelRequester extends DefaultOperator<DefaultChannelOperatorParameter, DefaultChannelOperatorParameter.Builder> {

    public DefaultChannelRequester(String targetUri, HttpMethod httpMethod) {
        super(DefaultChannelOperatorParameter.Builder.aDefaultChannelOperatorParameter(), targetUri, httpMethod);
    }

    private DefaultChannelRequester(ParameterBuilder<DefaultChannelOperatorParameter> builder, String targetUri, HttpMethod httpMethod) {
        super(builder, targetUri, httpMethod);
    }
}
