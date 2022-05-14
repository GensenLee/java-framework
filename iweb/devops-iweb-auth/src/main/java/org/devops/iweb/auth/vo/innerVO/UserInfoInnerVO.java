package org.devops.iweb.auth.vo.innerVO;

import lombok.Getter;
import org.devops.core.utils.interfaces.BaseUserInfo;
import org.devops.core.utils.vo.BaseBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserInfoInnerVO extends BaseBean implements BaseUserInfo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 54687923478962834L;

	/**
	 * 会话key
	 */
	private String sessionKey;

	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 集团id
	 */
	private Long companyId;

	/**
	 * 用户实例id
	 */
	private Long userInstanceId;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 账号信息
	 */
	private UserAccount accountInfo;

	@Override
	public String userId() {
		return String.valueOf(userId);
	}

	@Override
	public String userName() {
		return userName;
	}

	@Override
	public String userType() {
		return null;
	}

	@Override
	public String userTypeText() {
		return null;
	}

	@Override
	public Long companyId() {
		return companyId;
	}

	/**
	 * 账号信息
	 */
	@EqualsAndHashCode(callSuper = false)
	@Data
	public static class UserAccount extends BaseBean{
		/**
		 * 账号
		 */
		private String account;

		/**
		 * 原始账号
		 */
		private String orgAccount;

		/**
		 * 邮箱
		 */
		private String email;

		/**
		 * 是否管理员 0否 1是
		 */
		private Integer isAdmin;

		/**
		 * 创建时间
		 */
		private Date createTime;
	}

}
