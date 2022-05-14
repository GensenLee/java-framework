package org.devops.iweb.auth.service;

import java.util.List;

import org.devops.core.model.dto.ModelWhere;
import org.devops.core.utils.exception.CommonException;
import org.devops.core.utils.util.BeanUtil;
import org.devops.core.utils.util.LongUtil;
import org.devops.core.utils.verify.VerifyUtil;
import org.devops.core.utils.vo.PageResult;
import org.devops.iweb.auth.model.AuthKeyValue;
import org.devops.iweb.auth.repository.AuthKeyValueRepository;
import org.devops.iweb.auth.vo.innerVO.UserInfoInnerVO;
import org.devops.iweb.auth.vo.searchVO.AuthConfigSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthConfigService {

	@Autowired
	private AuthKeyValueRepository authKeyValueRepository;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object list(AuthConfigSearchVO svo) throws CommonException{
		
		ModelWhere mw = new ModelWhere();

		List<AuthKeyValue> liAuthKeyValue = authKeyValueRepository.where(mw).limit(svo.getStart(),svo.getSize()).list();
		
		PageResult pr = new PageResult();
		pr.setList(liAuthKeyValue);
		pr.setCount(authKeyValueRepository.where(mw).count());
		
		return pr;
	}
	
	public Object save(AuthKeyValue authKeyValue,UserInfoInnerVO userInfoInnerVO) throws CommonException{
		VerifyUtil.verify(authKeyValue,AuthKeyValue.KEY,AuthKeyValue.VALUE,AuthKeyValue.ID);
		if(LongUtil.isZero(authKeyValue.getId())){
			BeanUtil.initCreate(authKeyValue, userInfoInnerVO.getUserName());
		}else{
			BeanUtil.initModify(authKeyValue, userInfoInnerVO.getUserName());
		}
		return authKeyValueRepository.save(authKeyValue);
	}
}
