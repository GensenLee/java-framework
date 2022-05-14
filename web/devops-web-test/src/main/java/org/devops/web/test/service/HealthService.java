package org.devops.web.test.service;

import java.util.List;

import cn.binarywang.wx.miniapp.api.WxMaService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.devops.core.utils.exception.CommonException;
import org.devops.core.utils.util.BeanUtil;
import org.devops.core.utils.vo.PageResult;
import org.devops.iweb.auth.model.AuthUser;
import org.devops.iweb.auth.repository.AuthUserRepository;
import org.devops.mjar.weixin.factory.WxServiceFactory;
import org.devops.web.test.model.AuthUserTest;
import org.devops.web.test.redis.TestReids;
import org.devops.web.test.repository.AuthUserTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HealthService {

	@Autowired
	private AuthUserTestRepository authUserTestRepository;
	
//	@Autowired
//	private OrgAuthUserRepository orgAuthUserRepository;
	
	@Autowired
	private AuthUserRepository authUserRepository;
	
	@Autowired
	private TestReids testReids;


	public void testRedis(String v){
		testReids.set(v, v);
	}

	@Transactional(rollbackFor = RuntimeException.class)
	public Object test(){
//		testReids.set("xxxxxxxxx2");
//		return testReids.get();
//		Object result = authUserTestRepository
//					//.where(AuthUserTest.ID,"0,1",ModelOperator.BETWEEN)
//					//.where(AuthUserTest.NAME,"test",ModelOperator.LIKE_LEFT)
//					//.orderBy(AuthUserTest.ID,"DESC")
//					.limit(0,20)
//					.include(AuthUserTest.NAME)
//					.distinct()
//					.orderBy("max(id)")
//					.list();
//		Object result = orgAuthUserRepository
//				.limit(0,20)
//				.list();
		Object result = authUserRepository
				.limit(0,20)
				.list();
		PageResult p = new PageResult(1L,(List)result);
		result = p;
//		List<AuthUserTest> insertModel = new ArrayList<AuthUserTest>();
//		AuthUserTest a = new AuthUserTest();
//		a.setId(17L);
//		a.setName("new n'ame 123");
//		a.setCreateTime(new Date());
//		a.setModifyTime(new Date());
//		insertModel.add(a);
//		a = new AuthUserTest();
//		a.setId(18L);
//		a.setName("new name 456");
//		a.setCreateTime(new Date());
//		a.setModifyTime(new Date());
//		a.setAccount("123");
//		insertModel.add(a);
////		authUserTestRepository.insert(insertModel);
////		authUserTestRepository
////			.where(AuthUserTest.CREATE_TIME,new Date(),ModelOperator.LT)
////			.update(AuthUserTest.NAME,"testtest312312312");
////		authUserTestRepository
////		.update(insertModel);
////		authUserTestRepository
////			.where(AuthUserTest.CREATE_TIME,new Date(),ModelOperator.GT)
////			.update(insertModel);
//		//throw new CommonRuntimeException();
////		authUserTestRepository.add(AuthUserTest.IS_ADMIN, 1)
////			.where(AuthUserTest.ID,1)
////			.update();
//		authUserTestRepository
//			.add(AuthUserTest.IS_ADMIN, 1)
//			.sub(AuthUserTest.IS_ACTIVE, 2)
//			.where(AuthUserTest.ID,1)
//			.update();
//		
////		authUserTestRepository.sub(AuthUserTest.IS_ADMIN, 1)
////			.where(AuthUserTest.ID,1)
////			.update();
////		authUserTestRepository.sub(AuthUserTest.IS_ADMIN,AuthUserTest.IS_ACTIVE, 2)
////			.where(AuthUserTest.ID,1)
////			.update();
////	
////		authUserTestRepository.mcl(AuthUserTest.IS_ADMIN, 1)
////			.where(AuthUserTest.ID,1)
////			.update();
////		authUserTestRepository.mcl(AuthUserTest.IS_ADMIN,AuthUserTest.IS_ACTIVE, 2)
////			.where(AuthUserTest.ID,1)
////			.update();
////
////		authUserTestRepository.divide(AuthUserTest.IS_ADMIN, 1)
////			.where(AuthUserTest.ID,1)
////			.update();
////		authUserTestRepository.divide(AuthUserTest.IS_ADMIN,AuthUserTest.IS_ACTIVE, 2)
////			.where(AuthUserTest.ID,1)
////			.update();
////		throw new CommonRuntimeException();
		return result;

	}

	@Autowired
	private WxMaService wxMaService;

	public Object wxTest() throws WxErrorException {
		WxMpService mpService = WxServiceFactory.getMpService();



		return wxMaService.getAccessToken();
//		return "";
	}

	@Transactional(rollbackFor = RuntimeException.class)
	public void transactionTest(String a) throws CommonException {
		AuthUser authUser = authUserRepository.get();
		authUser.setId(null);
		authUser.setName("transactionTest");
		authUserRepository.insert(authUser);
		testReids.set("Test:" + System.currentTimeMillis(), String.valueOf(System.currentTimeMillis()));

		authUserTestRepository.redis().updateRedis(BeanUtil.copy(authUser, AuthUserTest.class));
		if (a.equalsIgnoreCase("t")) {
			throw new CommonException("回滚");
		}
	}

}
