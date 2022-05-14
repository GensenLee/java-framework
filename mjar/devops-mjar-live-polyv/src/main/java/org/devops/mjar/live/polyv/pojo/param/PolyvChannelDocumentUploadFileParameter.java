package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;

@Data
public class PolyvChannelDocumentUploadFileParameter extends ChannelSignBean {

    private String type;

    /**
     * 文档名称，默认不传使用ppt上传的文件获取到的文件名作为文档名称，文档名称不得超过100个字符
     */
    private String docName;

    /**
     * 文档上传转换成功回调地址
     */
    private String callbackUrl;

    /**
     * 文件地址url（需要可访问的地址），file和url只需要传递其中一个，如果传递了url和file，以file字段为准
     */
    private String url;

}
