package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.AppSignBean;
import lombok.Data;

/**
 * @author Gensen.Lee
 * @date 2020/12/16 18:15
 */
@Data
public class PolyvAppCreateParameter extends AppSignBean {

    /**
     * 应用名称
     */
    private String name;

    /**
     * 自定义观看页域名
     */
    @VerifyField(ignore = true)
    private String watchDomain;

    /**
     * 自定义管理后台域名
     */
    @VerifyField(ignore = true)
    private String webappDomain;

    /**
     * 助教域名
     */
    @VerifyField(ignore = true)
    private String assistantDomain;

    /**
     * 自定义管理后台的图标地址
     */
    @VerifyField(ignore = true)
    private String logoImg;

    /**
     * 自定义管理后台的封面图片地址
     */
    @VerifyField(ignore = true)
    private String coverImg;

    /**
     *  应用的并发数
     */
    @VerifyField(ignore = true)
    private Long concurrences;

    /**
     * 应用描述
     */
    @VerifyField(ignore = true)
    private String description;

    /**
     * 应用英文名称
     */
    @VerifyField(ignore = true)
    private String appEnglishName;


    public static final class PolyvAppCreateParameterBuilder extends ParameterBuilder<PolyvAppCreateParameter> {
        private PolyvAppCreateParameter polyvAppCreateParameter;

        private PolyvAppCreateParameterBuilder() {
            polyvAppCreateParameter = new PolyvAppCreateParameter();
        }

        public static PolyvAppCreateParameterBuilder aPolyvAppCreateParameter() {
            return new PolyvAppCreateParameterBuilder();
        }

        public PolyvAppCreateParameterBuilder withName(String name) {
            polyvAppCreateParameter.setName(name);
            return this;
        }

        public PolyvAppCreateParameterBuilder withWatchDomain(String watchDomain) {
            polyvAppCreateParameter.setWatchDomain(watchDomain);
            return this;
        }

        public PolyvAppCreateParameterBuilder withWebappDomain(String webappDomain) {
            polyvAppCreateParameter.setWebappDomain(webappDomain);
            return this;
        }

        public PolyvAppCreateParameterBuilder withAssistantDomain(String assistantDomain) {
            polyvAppCreateParameter.setAssistantDomain(assistantDomain);
            return this;
        }

        public PolyvAppCreateParameterBuilder withLogoImg(String logoImg) {
            polyvAppCreateParameter.setLogoImg(logoImg);
            return this;
        }

        public PolyvAppCreateParameterBuilder withCoverImg(String coverImg) {
            polyvAppCreateParameter.setCoverImg(coverImg);
            return this;
        }

        public PolyvAppCreateParameterBuilder withConcurrences(Long concurrences) {
            polyvAppCreateParameter.setConcurrences(concurrences);
            return this;
        }

        public PolyvAppCreateParameterBuilder withDescription(String description) {
            polyvAppCreateParameter.setDescription(description);
            return this;
        }

        public PolyvAppCreateParameterBuilder withAppEnglishName(String appEnglishName) {
            polyvAppCreateParameter.setAppEnglishName(appEnglishName);
            return this;
        }

        @Override
        public PolyvAppCreateParameter build() {
            return polyvAppCreateParameter;
        }
    }
}
