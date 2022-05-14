package org.devops.iweb.auth.service;

import java.util.List;
import java.util.UUID;

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
import org.devops.iweb.auth.model.AuthAppUserInstance;
import org.devops.iweb.auth.model.AuthCompany;
import org.devops.iweb.auth.model.AuthUser;
import org.devops.iweb.auth.repository.AuthAppInstanceRepository;
import org.devops.iweb.auth.repository.AuthAppRepository;
import org.devops.iweb.auth.repository.AuthAppUserInstanceRepository;
import org.devops.iweb.auth.repository.AuthCompanyRepository;
import org.devops.iweb.auth.repository.AuthUserRepository;
import org.devops.iweb.auth.util.PasswdUtil;
import org.devops.iweb.auth.vo.inVO.AuthCompanyInVO;
import org.devops.iweb.auth.vo.innerVO.UserInfoInnerVO;
import org.devops.iweb.auth.vo.searchVO.AuthCompanySearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthCompanyService {

	
	@Autowired
	private AuthCompanyRepository authCompanyRepository;
	
	@Autowired
	private AuthUserRepository authUserRepository;
	
	@Autowired
	private AuthAppRepository authAppRepository;
	
	@Autowired
	private AuthAppInstanceRepository authAppInstanceRepository;
	
	@Autowired
	private AuthAppUserInstanceRepository authAppUserInstanceRepository;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object list(AuthCompanySearchVO svo) throws CommonException{
		
		ModelWhere mw = new ModelWhere();
		
		if(StringUtil.isNotEmpty(svo.getQuickSearch())){
			mw.add(AuthCompany.NAME,svo.getQuickSearch(),ModelOperator.LIKE);
		}
		
		List<AuthCompany> liAuthCompany = authCompanyRepository.where(mw)
				.orderBy(AuthCompany.ID,"DESC")
				.limit(svo.getStart(),svo.getSize()).list();
		
		PageResult pr = new PageResult();
		pr.setList(liAuthCompany);
		pr.setCount(authCompanyRepository.where(mw).count());
		
		return pr;
	}
	
	public Object listAll(AuthCompanySearchVO svo) throws CommonException{
		
		ModelWhere mw = new ModelWhere();
		
		if(StringUtil.isNotEmpty(svo.getQuickSearch())){
			mw.add(AuthCompany.NAME,svo.getQuickSearch(),ModelOperator.LIKE);
		}
		
		return authCompanyRepository.where(mw)
				.orderBy(AuthCompany.ID,"DESC")
				.limit(svo.getStart(),svo.getSize()).list();
		
	}
	
	@Transactional
	public Object save(AuthCompanyInVO authCompanyInVO,UserInfoInnerVO userInfoInnerVO) throws CommonException{
		
		if(LongUtil.isZero(authCompanyInVO.getId())){
			VerifyUtil.verify(authCompanyInVO,"appId",AuthCompany.NAME,AuthCompany.SHORT_NAME,AuthCompany.ACCOUNT);
			if(authCompanyRepository.where(AuthCompany.ACCOUNT,authCompanyInVO.getAccount()).isExists()){
				throw new CommonException("该账号已经存在");
			}
			BeanUtil.initCreate(authCompanyInVO, userInfoInnerVO.getUserName());
			authCompanyRepository.save(authCompanyInVO);
			
			//创建一个账号
			AuthUser authUser = new AuthUser();
			BeanUtil.initCreate(authUser, userInfoInnerVO.getUserName());
			authUser.setCompanyId(authCompanyInVO.getId());
			authUser.setIsAdmin(1);
			authUser.setOrgAccount(authCompanyInVO.getAccount());
			authUser.setAccount(authCompanyInVO.getAccount());
			authUser.setName(authCompanyInVO.getShortName()+"管理员");
			
			authUser.setPasswdSuffix(UUID.randomUUID().toString().replaceAll("-", ""));
			authUser.setPasswd(PasswdUtil.getPasswd("123456", authUser.getPasswdSuffix()));
			authUserRepository.save(authUser);
			
			AuthApp authApp = authAppRepository.get(authCompanyInVO.getAppId());
			
			AuthAppInstance authAppInstance = new AuthAppInstance();
			authAppInstance.setAppId(authCompanyInVO.getAppId());
			authAppInstance.setCompanyId(authCompanyInVO.getId());
			authAppInstance.setName(authApp.getCnName()+"-"+authCompanyInVO.getName());
			BeanUtil.initCreate(authAppInstance, userInfoInnerVO.getUserName());
			authAppInstanceRepository.save(authAppInstance);
			
			AuthAppUserInstance authAppUserInstance = new AuthAppUserInstance();
			authAppUserInstance.setAppId(authCompanyInVO.getAppId());
			authAppUserInstance.setAppInstanceId(authAppInstance.getId());
			authAppUserInstance.setCompanyId(authCompanyInVO.getId());
			authAppUserInstance.setUserId(authUser.getId());
			authAppUserInstance.setIsAdmin(1);
			BeanUtil.initCreate(authAppUserInstance, userInfoInnerVO.getUserName());
			authAppUserInstanceRepository.save(authAppUserInstance);
			
		}else{
			BeanUtil.initModify(authCompanyInVO, userInfoInnerVO.getUserName());
			authCompanyRepository.update(authCompanyInVO);
		}
		
		return null;
		
	}
	
}
