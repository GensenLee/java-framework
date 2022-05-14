package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;
import lombok.Data;

/**
 * @author fangzy
 * @description：严禁词信息
 */
@Data
public class PolyvBadWords extends BaseBean {

    /**
     * 直播账户id
     */
    private String userId;

    /**
     * 为该频道严禁词的数量，如果修改全部频道，count为该该账户严禁词的全部数量
     */
    private String count;
}
