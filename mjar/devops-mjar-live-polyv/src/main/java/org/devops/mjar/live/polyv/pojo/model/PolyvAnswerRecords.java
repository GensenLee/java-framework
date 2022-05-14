package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @author Gensen.Lee
 */
@Data
@ApiModel("答题卡")
public class PolyvAnswerRecords extends BaseBean {
    private String questionId;
    private String questionTitle;
    private String answer;
    private Integer total;
    private Integer correctCount;
    private List<PolyvOptionsBean> options;
    private List<PolyvRecordsBean> records;

}
