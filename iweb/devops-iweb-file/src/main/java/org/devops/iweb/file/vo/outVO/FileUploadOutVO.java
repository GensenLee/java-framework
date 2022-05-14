package org.devops.iweb.file.vo.outVO;

import org.devops.core.utils.vo.BaseBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(description="自定义上传文件")
public class FileUploadOutVO extends BaseBean {

	@ApiModelProperty("文件路径，不包含域名")
	public String path;
	
	@ApiModelProperty("文件完整路径，包含域名")
	public String fullPath;
	
	@ApiModelProperty("文件原名字")
	public String oriName;
	
	@ApiModelProperty("文件大小")
	public Long size;
	
	@ApiModelProperty("域名")
	public String domain;
}
