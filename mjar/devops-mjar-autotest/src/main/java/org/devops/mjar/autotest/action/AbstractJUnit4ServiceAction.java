package org.devops.mjar.autotest.action;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AbstractJUnit4ServiceAction  extends AbstractJUnit4SpringContextTests {

}
