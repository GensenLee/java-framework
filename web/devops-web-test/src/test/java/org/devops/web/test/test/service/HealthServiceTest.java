package org.devops.web.test.test.service;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.lang.StringUtils;
import org.devops.core.utils.constant.FileType;
import org.devops.core.utils.util.BeanUtil;
import org.devops.core.utils.util.FastJsonUtils;
import org.devops.core.utils.util.identifier.SnowflakeIdGenerator;
import org.devops.core.utils.util.identifier.ULID;
import org.devops.iweb.auth.model.AuthUser;
import org.devops.iweb.auth.repository.AuthUserRepository;
import org.devops.iweb.file.configuration.EnableIWebFile;
import org.devops.iweb.file.core.FileCosCore;
import org.devops.iweb.file.core.FileOssCore;
import org.devops.iweb.file.utils.FileUploadUtil;
import org.devops.iweb.file.vo.FileUploadInfoVO;
import org.devops.mjar.autotest.action.AbstractJUnit4ServiceAction;
import org.devops.web.test.bean.datasource.DataSourceType;
import org.devops.web.test.model.AuthUserTest;
import org.devops.web.test.model.AuthUserTest2;
import org.devops.web.test.repository.AuthUserTest2Repository;
import org.devops.web.test.repository.AuthUserTestRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableIWebFile(datasource = DataSourceType.NAME)
public class HealthServiceTest extends AbstractJUnit4ServiceAction{

	@Autowired
	private AuthUserTestRepository authUserTestRepository;

	@Autowired
	private AuthUserRepository authUserRepository;
	
	@Test
	public void test() {
//		AuthUserTest vo = new AuthUserTest();
//		vo.setAccount("123312");
//		BeanUtil.initCreate(vo, "测试");
//		authUserTestRepository.insert(vo);
//		authUserTestRepository.test();
//		System.out.println(FastJsonUtils.toJsonString(authUserTestRepository.redis().listAllByRedis().list()));
		AuthUser authUser = authUserRepository.get();
		authUser.setId(null);
		authUserRepository.insert(authUser);
	}

	@Autowired
	private AuthUserTest2Repository authUserTest2Repository;

	@Test
	public void idTest(){
		List<AuthUserTest> userTestList = authUserTestRepository
				.exclude("id")
				.limit(10).list();

		List<AuthUserTest2> copy = BeanUtil.copy(userTestList, AuthUserTest2.class);
		authUserTest2Repository.insert(copy);
		for (AuthUserTest2 authUserTest : copy) {
			System.out.println(authUserTest);
		}
	}

	@Autowired
	private FileCosCore fileCosCore;

	@Test
	public void cosTest() throws FileNotFoundException {
		FileUploadInfoVO metadata = FileUploadUtil.createMetadata(FileType.MP4);
		metadata.setFileName("video_272x480.mp4");
		String upload = fileCosCore.upload(-1L, new FileInputStream("D:\\video_272x480.mp4"), metadata);
		System.out.println("upload = " + upload);
	}
//
//	@Test
//	public void autoIdTest(){
//		AuthUserTest authUserTest = new AuthUserTest();
//		AuthUserTest authUserTest1 = authUserTestRepository.processAutoId(authUserTest);
//		System.out.println(authUserTest1.toJsonString());
//
//		List<AuthUserTest> authUserTestList = new ArrayList<>(10);
//		authUserTestList.add(new AuthUserTest());
//		authUserTestList.add(new AuthUserTest());
//		authUserTestList.add(new AuthUserTest());
//		authUserTestList.add(new AuthUserTest());
//		authUserTestList.add(new AuthUserTest());
//		authUserTestList.add(new AuthUserTest());
//		authUserTestList.add(new AuthUserTest());
//		authUserTestList.add(new AuthUserTest());
//		authUserTestList = authUserTestRepository.processAutoId(authUserTestList);
//		System.out.println(FastJsonUtils.toJsonString(authUserTestList));
//
//
//		AuthUserTest authUserTest2 = new AuthUserTest();
//		authUserTest1 = authUserTestRepository.processAutoId(authUserTest2);
//		System.out.println(authUserTest1.toJsonString());
//
//	}

	public static void main(String[] args) throws IOException {
//		System.out.println(Long.toUnsignedString(45645646546458L, 55));
//		System.out.println(Long.toUnsignedString(45645646546458L, 505));


		doExecuteCommand(Arrays.asList("dir"));


	}

	private static void doExecuteCommand(final List<String>command)throws IOException {

//		OutputStream infoOutputStream=new LoggerPrintStream(false);
//		PumpStreamHandler streamHandler=new PumpStreamHandler(infoOutputStream);

		CommandLine commandLine= CommandLine.parse(StringUtils.join(command,""));
		DefaultExecutor executor=new DefaultExecutor();
		executor.setExitValues(new int[]{0,1});
//		executor.setStreamHandler(streamHandler);

		int exitValue=executor.execute(commandLine);

		System.out.println(exitValue);

	}

}
