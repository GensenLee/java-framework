# 操作日志记录组件
### 配置类上注解 EnableIWebOperationLog 启用组件
```
@EnableIWebOperationLog(
    // 指定项目默认获取用户基础信息的 SpEL 语句
    // 不指定时默认使用iweb的AuthContext.getUserInfoInnerVO()   
    defaultUserInfoDefine = "",
    // 指定数据源名称
    datasource = ""
)
```


### @OperationRecord 注解用法
###### @OperationRecord 用于注解controller接口的方法，每个属性都有默认值
* 1 type 定义操作类型，属性默认值 OperationType.other，定义接口为 查询、删除、修改等操作
* 2 module 定义接口所在功能模块，字符串类型，建议项目定义各个模块的code
* 3 stack 定义接口所在功能项路径，如 用户管理>用户列表>用户编辑
* 4 opDescDefine 操作描述表达式，如 "'删除了' + #vo.userName + '用户'"
* 5 customParams 指定自定义参数param1、param2、param3、param4的解析表达式(SpEL)
* 6 userInfoDefine 用户基础信息获取表达式，默认为获取iweb的userInfo。可执行当前controller对象中的方法，如 #this.getUserInfo()，返回对象需实现接口org.devops.core.utils.interfaces.BaseUserInfo。会覆盖EnableIWebOperationLog.defaultUserInfoDefine
* 7 cacheScope 定义数据储存范围，默认入参出参都储存
* 8 resolver 指定解析器类
* 8.1   resolver.opDescDefineResolver 指定操作描述解析器类，覆盖 opDescDefine属性
* 8.2   resolver.customParamsResolver 指定自定义参数解析器，覆盖 customParams属性
* 8.3   resolver.prepostResolver 指定后置增强器，log插入数据库前调用
* 8.4   resolver.isSkpDefine 指定忽略的保存操作日志的特殊情况的SpEL表达式，返回值必须为布尔类型

```
@RestController
public class TestController {


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
```