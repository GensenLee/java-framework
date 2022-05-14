package org.devops.mjar.live.transform.transformer;

import org.devops.mjar.live.transform.resolve.HostProvider;
import org.devops.mjar.live.transform.resolve.mapper.ConvertResult;
import org.devops.mjar.live.transform.resolve.mapper.ParamsConvertType;
import org.devops.mjar.live.transform.resolve.mapper.ParamsConverter;
import org.devops.mjar.live.transform.resolve.mapper.TransformConvertField;
import org.devops.mjar.live.transform.resolve.mapper.converter.BodyBodyConverter;
import org.devops.mjar.live.transform.enums.HttpMethod;
import org.devops.mjar.live.transform.model.DefaultAppOperatorParameter;
import org.devops.mjar.live.transform.model.DefaultChannelOperatorParameter;
import org.devops.mjar.live.transform.operator.DefaultOperator;
import org.devops.core.utils.util.FastJsonUtils;
import org.devops.core.utils.util.IntUtil;
import org.devops.core.utils.util.ListUtil;
import org.devops.core.utils.util.RequestUtil;
import lombok.Setter;
import org.apache.http.HttpStatus;
import org.devops.mjar.live.core.constant.MjarLiveConstant;
import org.devops.mjar.live.core.model.PolyvApiResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author GENSEN
 * @date 2021/6/28 15:55
 * @description：配置文件定义transformer
 */
public class TransformerManualTransformer implements Transformer {

    public TransformConvertField request_root;
    public TransformConvertField response_root;

    public TransformerManualTransformer(List<TransformConvertField> requestConcerts, List<TransformConvertField> responseConcerts) {
        request_root = new TransformConvertField(TransformConvertField.ROOT_KEY, TransformConvertField.ROOT_KEY);
        toPolyvMappers.put(request_root.concatKey(), request_root);

        response_root = new TransformConvertField(TransformConvertField.ROOT_KEY, TransformConvertField.ROOT_KEY);
        fromPolyvMappers.put(response_root.concatKey(), response_root);

        if (ListUtil.isNotNull(requestConcerts)) {
            for (TransformConvertField convert : requestConcerts) {
                if (convert.depth() == 0) {
                    request_root.addChild(convert);
                }
                toPolyvMappers.put(convert.concatKey(), convert);
            }
        }
        if (ListUtil.isNotNull(responseConcerts)) {
            for (TransformConvertField convert : responseConcerts) {
                if (convert.depth() == 0) {
                    response_root.addChild(convert);
                }
                fromPolyvMappers.put(convert.concatKey(), convert);
            }
        }
    }

    /**
     * 属性 -> 值mapper 集合
     * 二级属性 通过点"."分割
     * 如 data.id
     * 解析 to polyv
     */
    private final Map<String, TransformConvertField> toPolyvMappers = new HashMap<>();

    /**
     * 属性 -> 值mapper 集合
     * 二级属性 通过点"."分割
     * 如 data.id
     * 解析 from polyv
     */
    private final Map<String, TransformConvertField> fromPolyvMappers = new HashMap<>();

    @Setter
    private String targetUri;

    @Setter
    private ParamsConvertType paramsConvertType;

    @Setter
    private HttpMethod httpMethod;

    @Override
    public Object trans(HttpServletRequest request, HttpServletResponse response) {
        String host = HostProvider.apiHost();
        String url = host + this.targetUri;
        ParamsConverter converter = this.paramsConvertType.converter();
        ConvertResult convertResult = converter.convertRequest(this.toPolyvMappers, request);
        DefaultChannelOperatorParameter channelOperatorParameter = new DefaultChannelOperatorParameter();
        channelOperatorParameter.setQueryMap(convertResult.getQueryMap());
        channelOperatorParameter.setBody(convertResult.getBody());
        DefaultOperator operator = buildDefaultOperator(url, convertResult);
        PolyvApiResult<Object> apiResult = operator.execute();
        response.reset();
        response.setStatus(IntUtil.isZero(apiResult.getCode()) ? HttpStatus.SC_OK : apiResult.getCode());
        Map<String, Object> convertMap = new BodyBodyConverter().getConvertMap(this.fromPolyvMappers, FastJsonUtils.getMap(apiResult));
        RequestUtil.writeToResponse(response, convertMap);
        return convertMap;
    }

    private final static Pattern PATH_PATTERN = Pattern.compile("\\{([^}]+)}");

    public final static List<String> PATH_PARAM = new ArrayList<>();

    static {
        PATH_PARAM.add(MjarLiveConstant.Key.CHANNEL_ID_KEY);
        PATH_PARAM.add(MjarLiveConstant.Key.USER_ID_KEY);
    }

    private DefaultOperator buildDefaultOperator(String url, ConvertResult convertResult) {
        Map<String, Object> map = convertResult.getQueryMap() != null ? convertResult.getQueryMap() : FastJsonUtils.getMap(convertResult.getBody());
        Matcher matcher = PATH_PATTERN.matcher(url);
        // 替换url路径中的参数
        while (matcher.find()) {
            String group = matcher.group(1);
            if (PATH_PARAM.contains(group)) {
                url = url.replace("{" + group + "}", map.get(group).toString());
            }
        }

        if (map.containsKey(MjarLiveConstant.Key.CHANNEL_ID_KEY)) {
            DefaultOperator<DefaultChannelOperatorParameter, DefaultChannelOperatorParameter.Builder> operator =
                    new DefaultOperator<>(DefaultChannelOperatorParameter.Builder.aDefaultChannelOperatorParameter(), url, this.httpMethod);
            operator.parameter()
                    .withQueryMap(convertResult.getQueryMap())
                    .withBody(convertResult.getBody());
            return operator;
        }
        DefaultOperator<DefaultAppOperatorParameter, DefaultAppOperatorParameter.Builder> operator =
                new DefaultOperator<>(DefaultAppOperatorParameter.Builder.aDefaultAppOperatorParameter(), url, this.httpMethod);
        operator.parameter()
                .withQueryMap(convertResult.getQueryMap())
                .withBody(convertResult.getBody());
        return operator;
    }


}
