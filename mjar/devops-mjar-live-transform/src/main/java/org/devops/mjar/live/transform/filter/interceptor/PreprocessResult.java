package org.devops.mjar.live.transform.filter.interceptor;

import lombok.Data;

/**
 * @author GENSEN
 * @date 2021/6/30 10:24
 * @description：前置处理结果
 */
@Data
public class PreprocessResult {

    public final static String ILLEGAL_REQUEST = "illegal request";

    public final static PreprocessResult FORBIDDEN_REQUEST = new PreprocessResult(false);
    public final static PreprocessResult LEGAL_REQUEST = new PreprocessResult(true);


    /**
     * 请求是否合法
     */
    private boolean legal;

    /**
     * 驳回请求返回数据
     */
    private Object rejectedData;

    public PreprocessResult(Object rejectedData) {
        this.legal = false;
        this.rejectedData = rejectedData;
    }

    public PreprocessResult(boolean legal) {
        this.legal = legal;
        if (legal) {
            this.rejectedData = ILLEGAL_REQUEST;
        }
    }
}
