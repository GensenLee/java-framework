package org.devops.iweb.auth.service;

import java.util.List;
import java.util.Map;

import org.devops.core.model.dto.ModelWhere;
import org.devops.core.model.emun.ModelOperator;
import org.devops.core.utils.exception.CommonException;
import org.devops.core.utils.util.BeanUtil;
import org.devops.core.utils.util.LongUtil;
import org.devops.core.utils.util.StringUtil;
import org.devops.core.utils.verify.VerifyUtil;
import org.devops.core.utils.vo.PageResult;
import org.devops.iweb.auth.model.AuthApp;
import org.devops.iweb.auth.model.AuthAppInstance;
import org.devops.iweb.auth.repository.AuthAppInstanceRepository;
import org.devops.iweb.auth.repository.AuthAppRepository;
import org.devops.iweb.auth.vo.innerVO.UserInfoInnerVO;
import org.devops.iweb.auth.vo.searchVO.AuthAppInstanceSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthAppInstanceService {

	
	@Autowired
	private AuthAppInstanceRepository authAppInstanceRepository;
	
	@Autowired
	private AuthAppRepository authAppRepository;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object list(AuthAppInstanceSearchVO svo,UserInfoInnerVO userInfoInnerVO) throws CommonException{
		
		ModelWhere mw = new ModelWhere();
		mw.add(AuthAppInstance.COMPANY_ID, userInfoInnerVO.getCompanyId());
		
		if(StringUtil.isNotEmpty(svo.getQuickSearch())){
			mw.add(AuthAppInstance.NAME,svo.getQuickSearch(),ModelOperator.LIKE);
		}
		
		if(LongUtil.isNotZero(svo.getAppId())){
			mw.add(AuthAppInstance.APP_ID, svo.getAppId());
		}
		List<AuthAppInstance> liAuthAppInstance = authAppInstanceRepository
				.where(mw)
				.limit(svo.getStart(),svo.getSize())
				.orderBy(AuthAppInstance.ID,"DESC")
				.list();
		
		List<Map> mapList = authAppRepository.join(liAuthAppInstance, AuthAppInstance.APP_ID, AuthApp.ID,
				AuthApp.CN_NAME,"appName",AuthApp.TYPE,AuthApp.TYPE).list(Map.class);
		PageResult pr = PageResult.get();
		pr.setList(mapList);
		pr.setCount(authAppInstanceRepository.where(mw).count());
		
		return pr;
	}
	
	public Object save(AuthAppInstance authAppInstance,UserInfoInnerVO userInfoInnerVO) throws CommonException{
		VerifyUtil.verify(authAppInstance);
		authAppInstance.setCompanyId(userInfoInnerVO.getCompanyId());
		AuthApp authApp = authAppRepository.get(authAppInstance.getAppId());
		if(authApp != null){
			authAppInstance.setCode(authApp.getCode());
		}
		if(LongUtil.isZero(authAppInstance.getId())){
			BeanUtil.initCreate(authAppInstance, userInfoInnerVO.getUserName());
		}else{
			BeanUtil.initModify(authAppInstance, userInfoInnerVO.getUserName());
		}
		return authAppInstanceRepository.save(authAppInstance);
	}
	
}
