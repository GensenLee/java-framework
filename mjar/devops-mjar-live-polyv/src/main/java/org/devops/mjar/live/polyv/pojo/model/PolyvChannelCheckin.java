package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author GENSEN
 * @date 2021/7/12 18:23
 * @description：发起签到记录
 */

@NoArgsConstructor
@Data
public class PolyvChannelCheckin extends BaseBean {


    /**
     * 签到时间
     */
    private Date createtime;
    /**
     * 签到id
     */
    private String checkinid;
    /**
     * 场次号
     */
    private String sessionId;
    /**
     * 房间号
     */
    private String roomid;
}
