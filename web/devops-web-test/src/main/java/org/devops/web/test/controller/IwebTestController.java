package org.devops.web.test.controller;

import org.devops.core.utils.vo.ApiResult;
import org.devops.iweb.auth.context.AuthContext;
import org.devops.iweb.auth.vo.innerVO.UserInfoInnerVO;
import org.devops.iweb.operationlog.annotation.CustomParamDefine;
import org.devops.iweb.operationlog.annotation.OperationRecord;
import org.devops.iweb.operationlog.enums.OperationType;
import org.devops.web.test.vo.TestVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GENSEN
 * @date 2021/11/18 18:58
 * @description：
 */
@RestController
public class IwebTestController {


    @GetMapping("/api/instance/test")
    @OperationRecord(type = OperationType.query, module = "TEST", stack = "API",
            customParams = {
                    @CustomParamDefine(
                            param = CustomParamDefine.Param.PARAM1,
                            define = "#vo.a"
                    ),
                    @CustomParamDefine(
                            param = CustomParamDefine.Param.PARAM2,
                            define = "#vo.b"
                    ),
                    @CustomParamDefine(
                            param = CustomParamDefine.Param.PARAM4,
                            define = "#vo.a"
                    )
            },
            userInfoDefine = "#this.getUserInfo()", opDescDefine = "'测试接口请求，参数a=' + #vo.a + ' 参数b=' + #vo.b"
        )
    public ApiResult test(TestVO vo) {
        return ApiResult.getSuccess(vo);
    }

    public UserInfoInnerVO getUserInfo() {
        return AuthContext.getUserInfoInnerVO();
    }

}
