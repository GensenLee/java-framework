package org.devops.core.utils.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "PageResult", description = "分页参数类")
@Data
@EqualsAndHashCode(callSuper = false)
public class PageResult<T> extends BaseBean {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public PageResult() {
    }

    public PageResult(long count, List<T> list) {
        this.count = count;
        this.list = list;
    }

    /**
     * 数量
     */
    @ApiModelProperty("总条数")
    private long count = 0;

    /**
     * 列表
     */
    @ApiModelProperty(value = "列表", dataType = "List")
    private List<T> list = new ArrayList<T>();

    /**
     * 获取一个新的PageResult
     *
     * @return
     */
    public static <T> PageResult<T> get() {
        PageResult<T> pageResult = new PageResult<T>();

        pageResult.setCount(0L);
        pageResult.setList(new ArrayList<T>());

        return pageResult;
    }

}
