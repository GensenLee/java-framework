package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import lombok.Data;

import java.util.List;

@Data
public class PolyvChannelViewLogV3 extends BaseBean {

    private String status;

    private List<PolyvChannelViewLogV1> result;

}
