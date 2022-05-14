package org.devops.mjar.live.core.model;

import org.devops.core.utils.vo.BaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.http.HttpStatus;

/**
 * @author Gensen.Lee
 * @param <T>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PolyvApiResult<T> extends BaseBean {

    public PolyvApiResult() {
        this.code = 200;
    }

    public PolyvApiResult(Status status) {
        this.code = status.code;
        this.status = status.status;
    }

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 状态
     */
    private String status;
    /**
     * 信息
     */
    private String message;
    /**
     * 数据
     */
    private T data;

    /**
     * 错误信息
     */
    private Error error;

    public boolean isSuccess(){
        return HttpStatus.SC_OK == this.code;
    }

    public static <T> PolyvApiResult<T> getSuccess(T data){
        PolyvApiResult<T> result = new PolyvApiResult<T>(Status.SUCCESS);
        result.data = data;
        return result;
    }

    /**
     * 状态
     */
    @Getter
    public enum Status{
        SUCCESS(200 ,"success"),
        ERROR(-1 ,"error");
        /**
         * 状态码
         */
        private Integer code;
        /**
         * 状态
         */
        private String status;

        Status(Integer code, String status) {
            this.code = code;
            this.status = status;
        }
    }

    @Data
    public static class Error {
        private Integer code;
        private String desc;
    }

}
