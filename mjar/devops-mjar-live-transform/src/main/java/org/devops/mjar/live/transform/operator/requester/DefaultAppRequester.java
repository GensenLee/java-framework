package org.devops.mjar.live.transform.operator.requester;

import org.devops.mjar.live.transform.enums.HttpMethod;
import org.devops.mjar.live.transform.model.DefaultAppOperatorParameter;
import org.devops.mjar.live.transform.operator.DefaultOperator;
import org.devops.mjar.live.core.operator.ParameterBuilder;

/**
 * @author GENSEN
 * @date 2021/6/25 11:01
 * @description：应用请求器
 */
public class DefaultAppRequester extends DefaultOperator<DefaultAppOperatorParameter, DefaultAppOperatorParameter.Builder> {

    public DefaultAppRequester(String targetUri, HttpMethod httpMethod) {
        super(DefaultAppOperatorParameter.Builder.aDefaultAppOperatorParameter(), targetUri, httpMethod);
    }

    private DefaultAppRequester(ParameterBuilder<DefaultAppOperatorParameter> builder, String targetUri, HttpMethod httpMethod) {
        super(builder, targetUri, httpMethod);
    }
}
