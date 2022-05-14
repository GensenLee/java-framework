package org.devops.mjar.live.transform.operator.requester;

import org.devops.mjar.live.transform.enums.HttpMethod;
import org.devops.mjar.live.transform.model.DefaultRootOperatorParameter;
import org.devops.mjar.live.transform.operator.DefaultOperator;
import org.devops.mjar.live.core.operator.ParameterBuilder;

/**
 * @author GENSEN
 * @date 2021/6/25 11:01
 * @description：总账号请求器
 */
public class DefaultRootRequester extends DefaultOperator<DefaultRootOperatorParameter, DefaultRootOperatorParameter.Builder> {

    public DefaultRootRequester(String targetUri, HttpMethod httpMethod) {
        super(DefaultRootOperatorParameter.Builder.aDefaultRootOperatorParameter(), targetUri, httpMethod);
    }

    private DefaultRootRequester(ParameterBuilder<DefaultRootOperatorParameter> builder, String targetUri, HttpMethod httpMethod) {
        super(builder, targetUri, httpMethod);
    }
}
