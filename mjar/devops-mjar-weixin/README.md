# 微信工具模块整合
https://gitee.com/binary/weixin-java-tools

未加入 weixin-java-open 模块支持


wx.mp   前缀为公众号配置
wx.open 前缀为开放平台配置
wx.ma   前缀为小程序配置
wx.pay  前缀为支付配置


###配置类上添加注解 EnableWeixin 启动组件
```
@EnableWeixin(
    //数据源
    datasource = "",
    //初始化配置模式（默认从配置文件读取，ProfileLoadMode.DatabaseLoader从数据库读取）
    initialMode = ProfileLoadMode.PropertiesLoader
)
```

###使用 Autowired 注入相应的服务
```
    /**
     * 小程序service
     */
    @Autowired
    private WxMaService wxMaService;
    
    /**
     * 公众号service
     */
    @Autowired
    private WxMpService wxMpService;
    
    /**
     * 微信支付service
     */
    @Autowired
    private WxPayService wxPayService;
    
    /**
     * 微信第三方平台service
     */
    @Autowired
    private WxOpenService wxOpenService;
    
```