//package org.devops.web.test.test.controller;
//
//import java.util.Date;
//import java.util.List;
//
//import org.devops.core.model.emun.ModelOperator;
//import org.devops.core.utils.util.DateUtil;
//import org.devops.core.utils.util.FastJsonUtils;
//import org.devops.core.utils.vo.ApiResult;
//import org.devops.iweb.auth.vo.inVO.AuthLoginInVO;
//import org.devops.mjar.autotest.action.AbstractJUnit4ControllerAction;
//import org.devops.web.test.model.AuthUserTest;
//import org.devops.web.test.repository.AuthUserTestRepository;
//import org.junit.Assert;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.github.jsonzou.jmockdata.JMockData;
//import com.sinmn.mjar.file.core.FileOssCore;
//import com.sinmn.mjar.message.core.MessageEmailCore;
//import com.sinmn.mjar.message.core.MessageTopicCore;
//import com.sinmn.mjar.message.vo.MessageVO;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//public class HealthTestController extends AbstractJUnit4ControllerAction {
//	
//	@Autowired
//	private AuthUserTestRepository authUserTestRepository;
//	
//	@Autowired
//	private MessageEmailCore messageEmailCore;
//	
//	@Autowired
//	private FileOssCore fileOssCore;
//	
//	@Autowired
//	private MessageTopicCore messageTopicCore;
//	
//	@Test
//	public void testMessage(){
//		messageTopicCore.push("TEST", "123456", "123213");
//	}
//	
//	@Test
//	@Ignore
//	public void testSendEmail(){
//		messageEmailCore.send(-1,"EMAIL_SEND_CODE","zhangxh@sinmn.com",new MessageVO(new MessageVO("subject123","1131")));
//	}
//	
//	@Test
//	@Ignore
//	public void testOssPush(){
////		TestExcelVO vo = new TestExcelVO();
////		vo.setDate("77463566");
////		
////		List<TestExcelRowVO> list = new ArrayList<>();
////		
////		TestExcelRowVO row = new TestExcelRowVO();
////		row.setTest1("test1");
////		row.setTest2("test2");
////		list.add(row);
////		list.add(row);
////		list.add(row);
////		list.add(row);
////		
////		List<TestExcelRow2VO> list2 = new ArrayList<>();
////		TestExcelRow2VO row2 = new TestExcelRow2VO();
////		row2.setTest1("test1test1");
////		row2.setTest2("test2test2");
////		row2.setTest3("test3test3");
////		row2.setTest4("test4test4");
////		row2.setTest5("test5test5");
////		row2.setTest6("test6test6");
////		row2.setTest7("test7test7");
////		list2.add(row2);
////		list2.add(row2);
////		list2.add(row2);
////		row.setList(list2);
////	
////		vo.setTestExcelRowVO(list);
////		
////		Workbook workbook = ExcelExportUtil.exportXls(vo);
////		ByteArrayInputStream is = ExcelExportUtil.saveAsInputStreamAndClose(workbook);
////		System.out.println(fileOssCore.upload(is, FileType.XLS));
//
//	}
//	
//	@Test
//	@Ignore
//	public void test(){
//		
//		//ApiResult<List<AuthUserTest>> str = testRestTemplate.getForObject("/health.do", ApiResult.class);
//		//ApiResult<List<AuthUserTest>> str = testRestCore.getList("/health.do?a=testtest", AuthUserTest.class);
//		//AuthAppSearchVO p = JMockData.mock(AuthAppSearchVO.class);
//		//p = new AuthAppSearchVO();
//		//ApiResult<PageResult<AuthUserTest>> str = testRestCore.getPage("/health.do?a=testtest",p, AuthUserTest.class);
//		//ApiResult<PageResult<AuthApp>> result = testRestCore.postPage("/admin/auth/instance/authApp/list.do",p, AuthApp.class);
//		//log.info(FastJsonUtils.toJsonString(result));
//		
//		//Assert.assertThat(result.getObject().getCount(),Matchers.greaterThanOrEqualTo(1L));
//		
//		
//		List<AuthUserTest> list = authUserTestRepository
//				
//				.where(AuthUserTest.CREATE_TIME,new Date(),ModelOperator.EGT)
//				.where(AuthUserTest.CREATE_TIME,DateUtil.addHours(new Date(), 1),ModelOperator.ELT)
//				
//				.list();
//		
//		log.info(FastJsonUtils.toJsonString(list));
//	}
//	
//	@Test
//	@Ignore
//	public void login(){
//		AuthLoginInVO params = JMockData.mock(AuthLoginInVO.class);
//		params.setAccount("admin");
//		params.setPasswd("admin123456");
//		ApiResult<Object> result = testRestCore.post("/admin/auth/common/authUser/login.do",params);
//		log.info(FastJsonUtils.toJsonString(result));
//		Assert.assertEquals(result.getCode(), 200);
//	}
//
//	@Override
//	protected String getSessionKey() {
//		return "d4ec093153c14426a7827113f39f96ec";
//	}
//
//	@Override
//	protected Class<?> getTargetClass() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}
