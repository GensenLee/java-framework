package org.devops.mjar.live.polyv.pojo.param;

import org.devops.core.utils.verify.VerifyField;
import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import org.devops.mjar.live.core.sign.SignIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author fangzy
 * @description：上传文档到某个频道
 */
@Data
public class PolyvChannelDocumentUploadParameter extends ChannelSignBean {

    /**
     * 上传的文件不超过50M，格式限制为（ppt， pdf，pptx，doc，docx，wps，xls，xlsx），file和url只需要传递其中一个，如果传递了url和file，以file字段为准
     */
    @SignIgnore
    @VerifyField(ignore = true)
    private MultipartFile file;

    /**
     * 转换类型，默认不传转普通，因为只有ppt，pptx可以转动画，其他类型文件会自动转成普通；文件转动画转失败会直接把类型转为普通
     * common：转普通图片
     * animate：转动画效果
     */
    @VerifyField(ignore = true)
    private String type;

    /**
     * 文档名称，默认不传使用ppt上传的文件获取到的文件名作为文档名称，文档名称不得超过100个字符
     */
    @VerifyField(ignore = true)
    private String docName;

    /**
     * 文档上传转换成功回调地址
     */
    @VerifyField(ignore = true)
    private String callbackUrl;

    /**
     * 文件地址url（需要可访问的地址），file和url只需要传递其中一个，如果传递了url和file，以file字段为准
     */
    @VerifyField(ignore = true)
    private String url;

    public enum Type {
        COMMON("common","转普通图片"),
        ANIMATE("animate", "转动画效果");

        Type(String value, String name) {
            this.value = value;
            this.name = name;
        }

        private String value;
        private String name;
    }

    public static final class PolyvChannelDocumentUploadParameterBuilder extends ParameterBuilder<PolyvChannelDocumentUploadParameter> {
        private PolyvChannelDocumentUploadParameter polyvChannelDocumentUploadParameter;

        private PolyvChannelDocumentUploadParameterBuilder() {
            polyvChannelDocumentUploadParameter = new PolyvChannelDocumentUploadParameter();
        }

        public static PolyvChannelDocumentUploadParameterBuilder aPolyvChannelDocumentUploadParameter() {
            return new PolyvChannelDocumentUploadParameterBuilder();
        }

        public PolyvChannelDocumentUploadParameterBuilder withFile(MultipartFile file) {
            polyvChannelDocumentUploadParameter.setFile(file);
            return this;
        }

        public PolyvChannelDocumentUploadParameterBuilder withType(Type type) {
            polyvChannelDocumentUploadParameter.setType(type.value);
            return this;
        }

        public PolyvChannelDocumentUploadParameterBuilder withDocName(String docName) {
            polyvChannelDocumentUploadParameter.setDocName(docName);
            return this;
        }

        public PolyvChannelDocumentUploadParameterBuilder withCallbackUrl(String callbackUrl) {
            polyvChannelDocumentUploadParameter.setCallbackUrl(callbackUrl);
            return this;
        }

        public PolyvChannelDocumentUploadParameterBuilder withUrl(String url) {
            polyvChannelDocumentUploadParameter.setUrl(url);
            return this;
        }

        @Override
        public PolyvChannelDocumentUploadParameter build() {
            return polyvChannelDocumentUploadParameter;
        }
    }
}
