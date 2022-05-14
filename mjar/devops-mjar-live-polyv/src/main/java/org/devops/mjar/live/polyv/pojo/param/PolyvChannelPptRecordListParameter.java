package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.polyv.enums.PptRecordStatus;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

/**
 * @author bigboss
 * @description：查询重制课件任务列表
 */
@Data
public class PolyvChannelPptRecordListParameter extends ChannelSignBean {

    /**
     * 创建重制课件任务接口的videoId
     */
    @VerifyField(ignore = true)
    private String videoId;

    /**
     * 页数，默认为1
     */
    @VerifyField(ignore = true)
    private Integer page;

    /**
     * 每页显示的数据条数，默认20
     */
    @VerifyField(ignore = true)
    private Integer pageSize;

    /**
     * 查询的记录的开始时间，13位位毫秒级时间戳
     */
    @VerifyField(ignore = true)
    private Long startTime;

    /**
     * 查询的记录的结束时间，13位位毫秒级时间戳
     */
    @VerifyField(ignore = true)
    private Long endTime;

    /**
     * 重制课件模块中的场次id
     */
    @VerifyField(ignore = true)
    private String sessionId;

    /**
     * 状态值waiting：等待处理process：处理中success：重制成功fail：重制失败uploaded：上传点播成功uploadFailed：上传点播失败
     */
    @VerifyField( ignore = true )
    private String status;

    public static final class PolyvChannelPptRecordListParameterBuilder extends ParameterBuilder<PolyvChannelPptRecordListParameter> {
        private PolyvChannelPptRecordListParameter polyvChannelPptRecordListParameter;

        private PolyvChannelPptRecordListParameterBuilder() {
            polyvChannelPptRecordListParameter = new PolyvChannelPptRecordListParameter();
        }

        public static PolyvChannelPptRecordListParameterBuilder aPolyvChannelPptRecordListParameter() {
            return new PolyvChannelPptRecordListParameterBuilder();
        }

        public PolyvChannelPptRecordListParameterBuilder withVideoId(String videoId) {
            polyvChannelPptRecordListParameter.setVideoId(videoId);
            return this;
        }

        public PolyvChannelPptRecordListParameterBuilder withPage(Integer page) {
            polyvChannelPptRecordListParameter.setPage(page);
            return this;
        }

        public PolyvChannelPptRecordListParameterBuilder withStatus(PptRecordStatus status) {
            polyvChannelPptRecordListParameter.setStatus(status.getCode());
            return this;
        }

        public PolyvChannelPptRecordListParameterBuilder withPageSize(Integer pageSize) {
            polyvChannelPptRecordListParameter.setPageSize(pageSize);
            return this;
        }

        public PolyvChannelPptRecordListParameterBuilder withStartTime(Long startTime) {
            polyvChannelPptRecordListParameter.setStartTime(startTime);
            return this;
        }

        public PolyvChannelPptRecordListParameterBuilder withEndTime(Long endTime) {
            polyvChannelPptRecordListParameter.setEndTime(endTime);
            return this;
        }

        public PolyvChannelPptRecordListParameterBuilder withSessionId(String sessionId) {
            polyvChannelPptRecordListParameter.setSessionId(sessionId);
            return this;
        }

        @Override
        public PolyvChannelPptRecordListParameter build() {
            return polyvChannelPptRecordListParameter;
        }
    }
}
