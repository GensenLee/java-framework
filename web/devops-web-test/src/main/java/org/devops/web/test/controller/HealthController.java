package org.devops.web.test.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.devops.core.utils.exception.CommonException;
import org.devops.core.utils.vo.ApiResult;
import org.devops.iweb.operationlog.annotation.OperationRecord;
import org.devops.web.test.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Api(value = "心跳包", tags = {"心跳包"})
@Slf4j
//@ApiIgnore
public class HealthController {
	
	@Autowired
	private HealthService healthService;


	@PostMapping(value="/upload.do")
	public void upload(@RequestPart("file") MultipartFile file){
		System.out.println("file.getOriginalFilename() = " + file.getOriginalFilename());
		System.out.println("file.getName() = " + file.getName());
	}

	@RequestMapping(value="/redis.do")
	@ApiOperation(value = "redis", notes = "redis")
	public void testRedis(String v){
		healthService.testRedis(v);
	}
	
	//@GetMapping(value="/health.do")
	@RequestMapping(value="/health.do")
	@ApiOperation(value = "心跳", notes = "心跳")
	@OperationRecord
	public ApiResult health() throws WxErrorException {

		return ApiResult.getSuccess(healthService.test());
//		TestExcelVO exportVO = new TestExcelVO();
//		
//		exportVO.setTitleA("ttttttt1");
//		exportVO.setTitleB("ttttttt2");
//		exportVO.setTitleC("ttttttt3");
//		exportVO.setTitleD("ttttttt4");
//		exportVO.setValueA("vvvvvvv1");
//		
//		List<TestExcelRowVO> listRow = new ArrayList<TestExcelRowVO>();
//		listRow.add(new TestExcelRowVO(new Date(),"1",1,1.1));
//		listRow.add(new TestExcelRowVO(new Date(),"2",2,2.2));
//		listRow.add(new TestExcelRowVO(new Date(),"3",3,3.3));
//		
//		exportVO.setList(listRow);
//		
//		//System.out.println(export(exportVO,"C:\\Users\\xhz\\Desktop\\test\\test.xls"));
//		
//		ByteArrayOutputStream os = new ByteArrayOutputStream();
//		try {
//			ExcelUtil.export(exportVO, os);
//			byte[] bytes = os.toByteArray();
//			response.reset();
//            response.setContentType("application/msexcel;charset=utf-8");
//            response.setHeader("Content-disposition", "attachment;filename= "+ "1.xls"); 
//           response.getOutputStream().write(bytes);
//           response.getOutputStream().flush();
//           response.getOutputStream().close();
//           os.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
//		return ApiResult.SUCCESS;
	}

	@RequestMapping(value="/wxTest.do")
	@ApiOperation(value = "wxTest", notes = "wxTest")
	public ApiResult wxTest() throws WxErrorException {
		return ApiResult.getSuccess(healthService.wxTest());
	}

	@RequestMapping(value="/transactionTest.do")
	@ApiOperation(value = "transactionTest", notes = "transactionTest")
	public ApiResult transactionTest(String a) throws CommonException {
		healthService.transactionTest(a);
		return ApiResult.success();
	}
}
