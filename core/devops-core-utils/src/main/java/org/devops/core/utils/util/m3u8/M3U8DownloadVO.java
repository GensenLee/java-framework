package org.devops.core.utils.util.m3u8;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.devops.core.utils.vo.BaseBean;

@Data
@EqualsAndHashCode(callSuper=false)
public class M3U8DownloadVO extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	byte[] buffer;
	
	long length;
	
	String fileName;
	
	String type;
	
	/**
	 * 当仅当视频格式为m3u8的时候有值 
	 */
	double duration;

}
