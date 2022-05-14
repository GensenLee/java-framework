package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import lombok.Data;

import java.util.List;

/**
 * @author fangzy
 * @description：禁言数据
 */
@Data
public class PolyvBanned extends BaseBean {

    /**
     * 当前页码
     */
    private Integer pageNumber;

    /**
     * 分页记录数
     */
    private Integer pageSize;

    /**
     * 总页数
     */
    private Integer totalItems;

    /**
     * 禁言用户ID列表
     */
    private List<String> contents;
}
