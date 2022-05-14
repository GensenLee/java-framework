package org.devops.iweb.auth.service;

import java.util.List;

import org.devops.core.model.dto.ModelWhere;
import org.devops.core.model.emun.ModelCondition;
import org.devops.core.model.emun.ModelOperator;
import org.devops.core.utils.exception.CommonException;
import org.devops.core.utils.util.BeanUtil;
import org.devops.core.utils.util.LongUtil;
import org.devops.core.utils.util.StringUtil;
import org.devops.core.utils.verify.VerifyUtil;
import org.devops.core.utils.vo.PageResult;
import org.devops.iweb.auth.model.AuthApp;
import org.devops.iweb.auth.repository.AuthAppRepository;
import org.devops.iweb.auth.vo.innerVO.UserInfoInnerVO;
import org.devops.iweb.auth.vo.searchVO.AuthAppSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthAppService {

	
	@Autowired
	private AuthAppRepository authAppRepository;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object list(AuthAppSearchVO svo) throws CommonException{
		
		ModelWhere mw = new ModelWhere();
		if(StringUtil.isNotEmpty(svo.getQuickSearch())){
			ModelWhere smw = new ModelWhere();
			smw.add(AuthApp.CODE,svo.getQuickSearch(),ModelOperator.LIKE);
			smw.add(AuthApp.CN_NAME,svo.getQuickSearch(),ModelOperator.LIKE,ModelCondition.OR);
			mw.add(smw);
		}
		if(svo.getType() != null){
			mw.add(AuthApp.TYPE,svo.getType());
		}
		
		List<AuthApp> liAuthApp = authAppRepository.where(mw)
				.orderBy(AuthApp.ID,"DESC")
				.limit(svo.getStart(),svo.getSize()).list();
		
		PageResult pr = new PageResult();
		pr.setList(liAuthApp);
		pr.setCount(authAppRepository.where(mw).count());
		
		return pr;
	}
	
	public Object save(AuthApp authApp,UserInfoInnerVO userInfoInnerVO) throws CommonException{
		VerifyUtil.verify(authApp);
		if(LongUtil.isZero(authApp.getId())){
			authApp.setType(1);
			BeanUtil.initCreate(authApp, userInfoInnerVO.getUserName());
		}else{
			BeanUtil.initModify(authApp, userInfoInnerVO.getUserName());
		}
		return authAppRepository.save(authApp);
	}
	
	public Object del(String ids) throws CommonException{
		return authAppRepository.where(AuthApp.ID,ids,ModelOperator.IN).delete();
	}
}
