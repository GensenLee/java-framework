# 测试模块

###测试类继承 AbstractJUnit4ServiceAction 

```
public class XxxTest extends AbstractJUnit4ServiceAction {

}
```

###@ActiveProfiles 注解指定配置文件
```
@ActiveProfiles(value = {"base","dev"})
public class XxxTest extends AbstractJUnit4ServiceAction {

}
```
