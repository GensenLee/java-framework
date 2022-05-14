package org.devops.mjar.live.transform.model;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import org.devops.mjar.live.core.sign.Body;
import org.devops.mjar.live.core.sign.ParamCollect;
import lombok.Data;
import org.devops.mjar.live.core.sign.SignIgnore;

import java.util.Map;

/**
 * @author GENSEN
 * @date 2021/6/24 18:18
 * @description：默认应用请求参数构造器
 */
@Data
public class DefaultAppOperatorParameter extends AppSignBean implements PostingBodyProvider{

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


    public static final class Builder extends ParameterBuilder<DefaultAppOperatorParameter> {
        private DefaultAppOperatorParameter defaultAppOperatorParameter;

        private Builder() {
            defaultAppOperatorParameter = new DefaultAppOperatorParameter();
        }

        public static Builder aDefaultAppOperatorParameter() {
            return new Builder();
        }

        public Builder withQueryMap(Map<String, Object> queryMap) {
            defaultAppOperatorParameter.setQueryMap(queryMap);
            return this;
        }

        public Builder withBody(Object body) {
            defaultAppOperatorParameter.setBody(body);
            return this;
        }

        @Override
        public DefaultAppOperatorParameter build() {
            return defaultAppOperatorParameter;
        }
    }
}
