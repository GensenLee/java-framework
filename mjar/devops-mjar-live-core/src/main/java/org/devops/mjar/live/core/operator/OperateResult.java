package org.devops.mjar.live.core.operator;

import org.devops.core.utils.vo.BaseBean;
import feign.Response;
import lombok.Getter;
import lombok.Setter;

/**
 * @author GENSEN
 * @date 2020/10/27 10:23
 * @description：操作过程结果
 */
public class OperateResult<R> extends BaseBean {

    /**
     * polyv api 请求过程持有
     */
    private RequestHandler requestHandler;

    /**
     * feign 组件结果持有
     */
    @Getter
    private Response response;

    public OperateResult(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }
    /**
     * 结果数据
     */
    @Getter
    private R result;

    /**
     * 请求用时,毫秒
     */
    @Getter
    private long timeUse;

    /**
     * 开始
     */
    @Getter
    private long startMilliseconds;

    /**
     * 结束
     */
    @Getter
    private long endMilliseconds;

    /**
     * 捕获到的异常, 无异常则为null
     */
    @Getter
    @Setter
    private Throwable throwable;

    public void start() {
        this.startMilliseconds = System.currentTimeMillis();
        requestHandler.log("feign request start");
    }

    public void end() {
        this.endMilliseconds = System.currentTimeMillis();
        this.timeUse = this.endMilliseconds - this.startMilliseconds;
        requestHandler.log("feign request time use [{}]ms", timeUse);
    }

    public void setResult(R result) {
        this.result = result;
    }

    void setResponse(Response response) {
        this.response = response;
    }
}
