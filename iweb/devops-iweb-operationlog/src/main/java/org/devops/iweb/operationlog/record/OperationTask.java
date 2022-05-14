package org.devops.iweb.operationlog.record;

import lombok.extern.slf4j.Slf4j;
import org.devops.core.utils.constant.CommonConstant;
import org.devops.core.utils.interfaces.BaseUserInfo;
import org.devops.core.utils.modules.ip2region.GeoIPResult;
import org.devops.core.utils.modules.log.request.RequestGlobalHandler;
import org.devops.core.utils.spring.SpringContextUtil;
import org.devops.core.utils.util.BeanUtil;
import org.devops.core.utils.util.FastJsonUtils;
import org.devops.core.utils.util.IPUtil;
import org.devops.core.utils.util.StringUtil;
import org.devops.core.utils.vo.ApiResult;
import org.devops.iweb.operationlog.annotation.CustomParamDefine;
import org.devops.iweb.operationlog.annotation.OperationRecord;
import org.devops.iweb.operationlog.enums.CacheScope;
import org.devops.iweb.operationlog.model.OperationLog;
import org.devops.iweb.operationlog.repository.OperationLogRepository;
import org.devops.iweb.operationlog.repository.SubTableControl;
import org.devops.iweb.operationlog.resolver.CustomParamResolver;
import org.devops.iweb.operationlog.resolver.OperationDescriptionResolver;
import org.devops.iweb.operationlog.resolver.PrepostResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 操作记录任务
 *
 * @author GENSEN
 */
@Slf4j
public class OperationTask implements Runnable, OperationContext {

    private static final ExpressionParser EXPRESSION_PARSER = new SpelExpressionParser();

    private final Object[] input;
    private final Object output;
    private final Throwable throwable;
    private final BaseUserInfo userInfo;
    private final OperationRecord operationRecord;
    private final Date recordTime;
    private final HttpServletRequest request;
    private final String requestId;
    private final EvaluationContext evaluationContext;

    public OperationTask(Object[] input, Object output, Throwable throwable, BaseUserInfo userInfo, OperationRecord operationRecord, EvaluationContext evaluationContext) {
        this.input = input;
        this.output = output;
        this.throwable = throwable;
        this.userInfo = userInfo;
        this.operationRecord = operationRecord;
        this.recordTime = new Date();
        this.evaluationContext = evaluationContext;
        this.request = RequestGlobalHandler.getRequest();
        this.requestId = RequestGlobalHandler.getRequestId();
    }

    @Override
    public void run() {
        SubTableControl.setByTime(recordTime);
        evaluationContext.setVariable("input", input);
        evaluationContext.setVariable("output", output);
        evaluationContext.setVariable("outputObject", output != null ? ((ApiResult<?>) output).getObject() : null);
        evaluationContext.setVariable("throwable", throwable);
        evaluationContext.setVariable("recordTime", recordTime);

        OperationLog operationLog = new OperationLog();
        operationLog.setCompanyId(userInfo.companyId());
        operationLog.setRecordTime(recordTime);
        operationLog.setResult(throwable == null ? "ok" : "no");
        operationLog.setFailReason(throwable == null ? "" : throwable.getMessage().substring(0, Math.min(250, throwable.getMessage().length())));
        operationLog.setModule(operationRecord.module());
        if (operationRecord.stack().length > 0) {
            operationLog.setStack(CommonConstant.COMMA_MARK + StringUtil.join(operationRecord.stack(), CommonConstant.COMMA_MARK) + CommonConstant.COMMA_MARK);
        }
        operationLog.setUserId(userInfo.userId());
        operationLog.setUserName(userInfo.userName());
        operationLog.setUserTypeCode(userInfo.userType());
        operationLog.setUserTypeText(userInfo.userTypeText());
        operationLog.setType(operationRecord.type().name());

        handleRequest(operationLog);

        convertInputOutput(operationLog);

        handleCustomParam(operationLog);

        String operationDescription = parseOperationDescription();
        operationLog.setOperationDescription(operationDescription);

        PrepostResolver prepostResolver = ResolverFactory.getResolverOnThread(operationRecord.resolver().prepostResolver());
        prepostResolver.bindContext(this);
        prepostResolver.prepost(operationLog);
        OperationLogRepository repository = SpringContextUtil.getBean(OperationLogRepository.class);
        repository.closeLog().insert(operationLog);
        ResolverFactory.cleanThread();
        SubTableControl.clean();
    }

    /**
     * 解析日志描述
     * @return
     */
    private String parseOperationDescription() {
        OperationDescriptionResolver resolver = ResolverFactory.getResolverOnThread(operationRecord.resolver().opDescDefineResolver());
        resolver.bindContext(this);
        // 解析日志描述，实现类方式
        String operationDescription = resolver.parseOperationDescription(input, output);
        if (StringUtil.isEmpty(operationDescription) && StringUtil.isNotEmpty(operationRecord.opDescDefine())) {
            // 解析日志描述，spel方式
            operationDescription = EXPRESSION_PARSER.parseExpression(operationRecord.opDescDefine()).getValue(evaluationContext, String.class);
        }
        return operationDescription;
    }

    /**
     * 转化保存输入输出
     *
     * @param operationLog
     */
    private void convertInputOutput(OperationLog operationLog) {
        List<CacheScope> cacheScopes = Arrays.asList(operationRecord.cacheScope());
        if (cacheScopes.contains(CacheScope.input) && input != null && input.length > 0) {
            for (int i = 0; i < input.length; i++) {
                // 转化不可序列化参数
                if (null == input[i]) {
                    continue;
                }

                if (input[i] instanceof MultipartFile) {
                    input[i] = ((MultipartFile) input[i]).getOriginalFilename();
                    continue;
                }

                if (input[i] instanceof File) {
                    input[i] = ((File) input[i]).getName();
                    continue;
                }

//                if (!(input[i] instanceof Serializable)) {
//                    input[i] = input[i].getClass().getSimpleName();
//                }

            }
            operationLog.setInput(FastJsonUtils.toJsonString(input));
        }
        if (cacheScopes.contains(CacheScope.output) && output != null) {
            operationLog.setOutput(FastJsonUtils.toJsonString(output));
        }
    }

    /**
     * 处理请求
     *
     * @param operationLog
     */
    private void handleRequest(OperationLog operationLog) {
        operationLog.setRequestId(requestId);
        if (request == null) {
            return;
        }
        operationLog.setRequestIp(IPUtil.getIpAddr(request));
        operationLog.setRequestMethod(request.getMethod().toUpperCase());
        operationLog.setRequestReferer(request.getHeader("Referer"));
        operationLog.setRequestUrl(request.getRequestURL().toString() + (StringUtil.isEmpty(request.getQueryString()) ? "" : "?" + request.getQueryString()));
        operationLog.setRequestUri(request.getRequestURI());
        operationLog.setRequestHost(request.getHeader("host"));
        operationLog.setUserAgent(request.getHeader("User-Agent"));
        GeoIPResult ipRegion = IPUtil.getIpRegion(operationLog.getRequestIp());
        operationLog.setCountry(ipRegion.getCountry());
        operationLog.setProvince(ipRegion.getProvince());
//        operationLog.setCity(ipRegion.getCity());
    }

    /**
     * 处理自定义参数
     * @param operationLog
     */
    private void handleCustomParam(OperationLog operationLog){
        CustomParamDefine[] customParamDefines = operationRecord.customParams();
        for (CustomParamDefine customParamDefine : customParamDefines) {
            try {
                String value = EXPRESSION_PARSER.parseExpression(customParamDefine.define()).getValue(evaluationContext, String.class);
                Field field = BeanUtil.getField(operationLog.getClass(), customParamDefine.param().getName());
                field.setAccessible(true);
                field.set(operationLog, value);
                field.setAccessible(false);
            } catch (EvaluationException | ParseException | IllegalAccessException e) {
                log.error( customParamDefine.param().getName() + "：" + e.getClass().getName() + "：" + e.getMessage(), e);
            }
        }

        CustomParamResolver resolver = ResolverFactory.getResolverOnThread(operationRecord.resolver().customParamsResolver());
        resolver.bindContext(this);
        CustomParamResolver.CustomParams customParams = resolver.parseParam(input);
        if (customParams != null) {
            operationLog.setParam1(customParams.getParam1());
            operationLog.setParam2(customParams.getParam2());
            operationLog.setParam3(customParams.getParam3());
            operationLog.setParam4(customParams.getParam4());
        }

    }


    //<editor-fold desc="getter">

    @Override
    public Object[] getInput() {
        return input;
    }

    @Override
    public Object getOutput() {
        return output;
    }

    @Override
    public Throwable getThrowable() {
        return throwable;
    }

    @Override
    public BaseUserInfo getUserInfo() {
        return userInfo;
    }

    @Override
    public OperationRecord getOperationRecord() {
        return operationRecord;
    }

    @Override
    public Date getRecordTime() {
        return recordTime;
    }
    //</editor-fold>


}