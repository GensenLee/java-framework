package org.devops.mjar.live.polyv.operator.requester;

import org.devops.mjar.live.polyv.operator.FunctionOperator;
import org.devops.mjar.live.core.model.PolyvApiResult;
import org.devops.mjar.live.polyv.pojo.param.PolyvAppUploadImageFileParameter;

import java.util.List;
import java.util.function.Function;

/**
 * @author GENSEN
 * @date 2020/10/27 11:13
 * @description：图片上传请求
 */
public class PolyvAppUploadImageFileRequester extends FunctionOperator<PolyvAppUploadImageFileParameter,
        PolyvAppUploadImageFileParameter.PolyvAppUploadImageFileParameterBuilder, List<String>> {

    public PolyvAppUploadImageFileRequester(Function<PolyvAppUploadImageFileParameter, PolyvApiResult<List<String>>> function) {
        super(PolyvAppUploadImageFileParameter.PolyvAppUploadImageFileParameterBuilder.aPolyvAppUploadImageFileParameter(), function);
    }
}
