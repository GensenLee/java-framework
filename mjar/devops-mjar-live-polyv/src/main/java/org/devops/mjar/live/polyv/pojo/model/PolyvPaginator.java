package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Gensen.Lee
 * @date 2019/4/24 10:21
 */
@Data
@ApiModel("列表")
public class PolyvPaginator<T> extends BaseBean {
    private Integer pageSize;
    @ApiModelProperty("当前页")
    private Integer pageNumber;
    public Integer getPageNumber(){
        if (this.pageNumber == null) {
            return page;
        }
        return pageNumber;
    }
    private Integer page;
    public Integer getPage(){
        if (this.page == null) {
            return pageNumber;
        }
        return page;
    }
    @ApiModelProperty("记录的总数")
    private Integer totalItems;
    @ApiModelProperty("是否为第一页，值为：true/false")
    private Boolean firstPage;
    @ApiModelProperty("下一页编号")
    private Integer nextPageNumber;
    @ApiModelProperty("当前页第一条记录在总记录中的位置")
    private Integer startRow;
    @ApiModelProperty("总页数")
    private Integer totalPages;
    @ApiModelProperty("是否为最后一页，值为：true/false")
    private Boolean lastPage;
    @ApiModelProperty("上一页编号")
    private Integer prePageNumber;
    @ApiModelProperty("当前页最后一个记录在总记录中的位置")
    private Integer endRow;
    @ApiModelProperty("当前页纪录数")
    private Integer limit;
    @ApiModelProperty("当前页第一条记录在总记录中的位置")
    private Integer offset;

    @ApiModelProperty("列表数据")
    private List<T> contents;
}
