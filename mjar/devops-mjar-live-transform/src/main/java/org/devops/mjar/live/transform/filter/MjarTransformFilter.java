package org.devops.mjar.live.transform.filter;

import org.devops.mjar.live.transform.process.ProcessPerformer;
import org.devops.mjar.live.transform.resolve.profile.MjarTransformProfileFactory;
import org.devops.mjar.live.transform.exception.FilterExceptionHandler;
import org.devops.mjar.live.transform.filter.interceptor.PreprocessResult;
import org.devops.mjar.live.transform.filter.interceptor.TransformPreprocessInterceptor;
import org.devops.mjar.live.transform.process.TransformProcessorChainFactory;
import org.devops.mjar.live.transform.transformer.TransformerFactory;
import org.devops.mjar.live.transform.transformer.Transformer;
import org.devops.mjar.live.transform.transformer.TransformerConfig;
import lombok.SneakyThrows;
import org.devops.core.utils.util.RequestUtil;
import org.devops.mjar.live.core.handler.MjarLiveContext;
import org.devops.mjar.live.core.servlet.RequestResolver;
import org.devops.mjar.live.core.sign.LiveApiProfiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author GENSEN
 * @date 2021/6/24 19:54
 * @description：过滤器
 */
@Order(1)
@WebFilter(filterName = "MjarTransformFilter", urlPatterns = "/*")
public class MjarTransformFilter implements Filter {

    @Autowired
    @Lazy
    private MjarTransformProfileFactory mjarTransformProfileFactory;

    @Autowired
    @Lazy
    private TransformerFactory transformerFactory;

    @Autowired(required = false)
    private TransformPreprocessInterceptor preprocessInterceptor;

    @Autowired
    @Lazy
    private FilterExceptionHandler filterExceptionHandler;

    @Autowired
    @Lazy
    private RequestResolver requestResolver;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpServletRequest httpServletRequest = requestResolver.resolve(request);
        MjarTransformHandler.setRequest(httpServletRequest);
        MjarTransformHandler.setResponse(httpServletResponse);
        // 前置请求验证，如签名
        boolean preprocessing = preprocessing(httpServletRequest, httpServletResponse);
        if (!preprocessing) {
            MjarTransformHandler.clean();
            return;
        }

        // 构建profile
        LiveApiProfiles profile = mjarTransformProfileFactory.getProfile(httpServletRequest);
        MjarLiveContext.getContext().getMjarLiveProfileHandler().set(profile);

        // 处理过程
        boolean processing;
        try {
            processing = processing(httpServletRequest, httpServletResponse);
        } catch (Exception exception) {
            boolean handle = filterExceptionHandler.handle(httpServletRequest, httpServletResponse, exception);
            if (handle) {
                return;
            } else {
                throw exception;
            }
        } finally {
            MjarTransformHandler.clean();
        }

        // 清理过程数据
        MjarLiveContext.getContext().clean();

        if (!processing) {
            chain.doFilter(request, response);
        }
    }

    /**
     * 前置检查
     *
     * @param request
     * @return
     */
    private boolean preprocessing(HttpServletRequest request, HttpServletResponse response) {
        if (preprocessInterceptor == null) {
            return true;
        }
        PreprocessResult preprocessResult = preprocessInterceptor.processing(request);
        if (preprocessResult == null) {
            return true;
        }
        if (preprocessResult.isLegal()) {
            return true;
        }
        Object rejectedData = preprocessResult.getRejectedData();
        if (rejectedData == null) {
            return false;
        }
        RequestUtil.writeToResponse(response, rejectedData);
        return false;
    }

    @Override
    public void destroy() {
    }


    /**
     * @param request
     * @param response
     * @return
     */
    private boolean processing(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String uri = request.getRequestURI();
        TransformerConfig transformerConfig = transformerFactory.get(uri, request.getMethod());
        if (transformerConfig == null) {
            return false;
        }

        ProcessPerformer processPerformer = new ProcessPerformer(request, response);

        // 全局前置增强
        processPerformer.process(TransformProcessorChainFactory.getGlobalPreTransformProcessorChain());

        specifiedProcess(request, response, transformerConfig, processPerformer);

        // 全局后置增强
        processPerformer.process(TransformProcessorChainFactory.getGlobalPostTransformProcessorChain());

        Object result = processPerformer.getProcessData();
        if (result != null) {
            RequestUtil.writeToResponse(response, result);
        }
        return true;
    }

    /**
     * 针对单个transformer执行过程
     * @param request
     * @param response
     * @param transformerConfig
     * @param processPerformer
     * @throws Exception
     */
    private void specifiedProcess(HttpServletRequest request, HttpServletResponse response, TransformerConfig transformerConfig, ProcessPerformer processPerformer) throws Exception {
        // 前置增强处理
        processPerformer.process(transformerConfig.getPreTransformProcessorChain());

        // 主过程
        Transformer transformer = transformerConfig.getTransformer();
        Object result = transformer.trans(request, response);
        processPerformer.updateProcessData(result);

        // 后置增强处理
        processPerformer.process(transformerConfig.getPostTransformProcessorChain());
    }

}
