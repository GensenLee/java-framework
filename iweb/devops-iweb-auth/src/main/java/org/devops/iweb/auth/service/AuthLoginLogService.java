package org.devops.iweb.auth.service;

import java.util.List;
import java.util.Map;

import org.devops.core.model.dto.ModelWhere;
import org.devops.core.utils.exception.CommonException;
import org.devops.core.utils.util.LongUtil;
import org.devops.core.utils.vo.PageResult;
import org.devops.iweb.auth.constant.AuthConstant;
import org.devops.iweb.auth.model.AuthLoginLog;
import org.devops.iweb.auth.model.AuthUser;
import org.devops.iweb.auth.repository.AuthLoginLogRepository;
import org.devops.iweb.auth.repository.AuthUserRepository;
import org.devops.iweb.auth.vo.innerVO.UserInfoInnerVO;
import org.devops.iweb.auth.vo.searchVO.AuthLoginLogSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthLoginLogService {

	
	@Autowired
	private AuthLoginLogRepository authLoginLogRepository;
	
	@Autowired
	private AuthUserRepository authUserRepository;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object list(AuthLoginLogSearchVO svo,UserInfoInnerVO userInfoInnerVO) throws CommonException{
		
		ModelWhere mw = new ModelWhere();
		
		if(LongUtil.isZero(svo.getCompanyId())){
			mw.add(AuthUser.COMPANY_ID,userInfoInnerVO.getCompanyId());
		}else if(LongUtil.toLong(svo.getCompanyId()) != AuthConstant.Common.ALL){
			mw.add(AuthUser.COMPANY_ID,svo.getCompanyId());
		}
		
		List<AuthLoginLog> authLoginLog = authLoginLogRepository.where(mw)
				.orderBy(AuthLoginLog.ID,"DESC")
				.limit(svo.getStart(),svo.getSize()).list();
		
		List<Map> listResult = authUserRepository.join(authLoginLog, AuthLoginLog.USER_ID, AuthUser.ID, AuthUser.NAME,"userName").list(Map.class);
		PageResult pr = new PageResult();
		pr.setList(listResult);
		pr.setCount(authLoginLogRepository.where(mw).count());
		
		return pr;
	}
	
}
