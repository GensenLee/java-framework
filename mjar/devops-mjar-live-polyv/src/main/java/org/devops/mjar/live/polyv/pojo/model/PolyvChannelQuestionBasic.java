package org.devops.mjar.live.polyv.pojo.model;

import org.devops.core.utils.vo.BaseBean;

/**
 * @author fangzy
 * @description：问卷基本信息
 */
public class PolyvChannelQuestionBasic extends BaseBean {

    /**
     * 问卷Id
     */
    private String questionnaireId;

    /**
     * 题目数组
     */
    private String[] questionIds;

    /**
     * 题目标题
     */
    private String questionnaireTitle;
}
