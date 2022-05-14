package org.devops.mjar.live.transform.model;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.Body;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import org.devops.mjar.live.core.sign.ParamCollect;
import lombok.Data;
import org.devops.mjar.live.core.sign.SignIgnore;

import java.util.Map;

/**
 * @author GENSEN
 * @date 2021/6/24 18:18
 * @description：默认频道请求参数构造器
 */
@Data
public class DefaultChannelOperatorParameter extends ChannelSignBean implements PostingBodyProvider{

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


    public static final class Builder extends ParameterBuilder<DefaultChannelOperatorParameter> {
        private DefaultChannelOperatorParameter defaultChannelOperatorParameter;

        private Builder() {
            defaultChannelOperatorParameter = new DefaultChannelOperatorParameter();
        }

        public static Builder aDefaultChannelOperatorParameter() {
            return new Builder();
        }

        public Builder withQueryMap(Map<String, Object> queryMap) {
            if (queryMap.containsKey(MjarLiveConstant.Key.CHANNEL_ID_KEY)) {
                defaultChannelOperatorParameter.setChannelId(queryMap.get(MjarLiveConstant.Key.CHANNEL_ID_KEY).toString());
            }
            defaultChannelOperatorParameter.setQueryMap(queryMap);
            return this;
        }

        public Builder withBody(Object body) {
            defaultChannelOperatorParameter.setBody(body);
            return this;
        }

        @Override
        public DefaultChannelOperatorParameter build() {
            return defaultChannelOperatorParameter;
        }
    }
}
