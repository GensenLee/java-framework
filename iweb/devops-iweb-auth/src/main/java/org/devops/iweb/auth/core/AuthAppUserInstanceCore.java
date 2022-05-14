package org.devops.iweb.auth.core;

import org.devops.core.utils.exception.CommonException;
import org.devops.iweb.auth.model.AuthAppUserInstance;
import org.devops.iweb.auth.redis.IWebAuthRedisDao;
import org.devops.iweb.auth.vo.innerVO.UserInfoInnerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthAppUserInstanceCore {

	@Autowired
	private IWebAuthRedisDao iWebAuthRedisDao;
	
	/**
	 * @Description 跳到具体的某个页面
	 * @author xhz
	 * @date 2018年9月18日 下午4:00:00
	 * @param authAppUserInstance
	 * @param req
	 * @param res
	 * @throws CommonException
	 * @lastModifier
	 */
	public void toInstance(AuthAppUserInstance authAppUserInstance,UserInfoInnerVO userInfoInnerVO) {
		userInfoInnerVO.setUserInstanceId(authAppUserInstance.getId());
		iWebAuthRedisDao.set(userInfoInnerVO);
	}
}
