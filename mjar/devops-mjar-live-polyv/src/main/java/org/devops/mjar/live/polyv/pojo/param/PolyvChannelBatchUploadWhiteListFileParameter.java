package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @date 2020/11/3 15:36
 * @description：批量导入频道白名单字段
 */
@Data
public class PolyvChannelBatchUploadWhiteListFileParameter extends ChannelSignBean {

    private Integer rank;

}
