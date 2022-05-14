package org.devops.iweb.file.vo.outVO;

import org.devops.core.utils.vo.BaseBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(description="OSS签名返回数据")
public class OssSingatureOutVO extends BaseBean {

	@ApiModelProperty("对应OSSAccessKeyId")
	private String accessid;
	@ApiModelProperty("上传需要用到的域名")
	private String host;
	@ApiModelProperty("对应policy")
	private String policy;
	@ApiModelProperty("对应signature")
	private String signature;
	private String expire;
	private Long end_time;
	private String dirs;
}
