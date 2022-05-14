package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @author GENSEN
 * @date 2020/10/29 17:18
 * @description：频道号列表
 */
@Data
@ApiModel("频道号列表")
public class PolyvChannelList extends BaseBean {

    private List<PolyvChannel> channels;

}
