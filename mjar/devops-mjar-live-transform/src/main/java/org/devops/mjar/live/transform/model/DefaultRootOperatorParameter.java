package org.devops.mjar.live.transform.model;

import lombok.Data;
import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import org.devops.mjar.live.core.sign.Body;
import org.devops.mjar.live.core.sign.ParamCollect;
import org.devops.mjar.live.core.sign.SignIgnore;

import java.util.Map;

/**
 * @author GENSEN
 * @date 2021/6/24 18:18
 * @description：默认总账号请求参数构造器
 */
@Data
public class DefaultRootOperatorParameter extends AppSignBean implements PostingBodyProvider{

    /**
     * 请求参数
     */
    @ParamCollect
    @VerifyField(ignore = true)
    private Map<String, Object> queryMap;

    /**
     * 请求体
     */
    @SignIgnore
    @VerifyField(ignore = true)
    @Body
    private Object body;

    @Override
    public Object body() {
        return body;
    }


    public static final class Builder extends ParameterBuilder<DefaultRootOperatorParameter> {
        private DefaultRootOperatorParameter defaultRootOperatorParameter;

        private Builder() {
            defaultRootOperatorParameter = new DefaultRootOperatorParameter();
        }

        public static Builder aDefaultRootOperatorParameter() {
            return new Builder();
        }

        public Builder withQueryMap(Map<String, Object> queryMap) {
            defaultRootOperatorParameter.setQueryMap(queryMap);
            return this;
        }

        public Builder withBody(Object body) {
            defaultRootOperatorParameter.setBody(body);
            return this;
        }

        @Override
        public DefaultRootOperatorParameter build() {
            return defaultRootOperatorParameter;
        }
    }
}
