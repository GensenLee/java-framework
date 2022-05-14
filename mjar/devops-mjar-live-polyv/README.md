# polyv-api saas核心api

https://shimo.im/sheets/GJDX6YgyrPJhqGth/MODOC/ 
《polyv api sdk工具使用文档》，可复制链接后用石墨文档 App 或小程序打开


1. 用户通过PolyvClientBuilder获取channel/app/root三种client类型的构造器
2. 传入配置后通过build()方法构建一个client对象
3. 通过client对象获取各种request请求
4. 为request对象设置请求参数
5. 调用request.execute()方法得到执行结果

        示例：
        PolyvChannelClient channelClient = PolyvClientBuilder
                .channelStandardClient()
                .withAppId("xxxxxxxx")
                .withAppSecret("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")
                .withUserId("xxxxxxxx")
                .withChannelId("xxxxxxxx")
                .build();
        
        PolyvChannelDirectPlayUrlRequester request = channelClient.directPlayUrlRequest();
        request.parameter()
                .withSecretKey("xxxxxxxxxx")
                .withNickname("xxxxxxxx")
                .withUserid("xxxxxxxx")
                .withAvatar("xxxxxxxx");
        PolyvApiResult<PolyvUrl> result = request.execute();
        System.out.println(result.getData());
        
        
   
     
##全局默认配置设置方式

考虑到某些项目始终只使用单一应用做频道管理，或始终只使用单一频道，而提供一种全局配置方式。
当配置缺失时，会尝试去获取默认配置，如果默认配置也不存在或不完整时则抛出异常。
当构建 channelClient 时仅调用 withChannelId() 设置了channelId，则也会去获取默认的应用配置


        示例：
        PolyvChannelClient channelClient = PolyvClientBuilder
                .channelStandardClient()
                .withChannelId("xxxxxxxx")
                .build();
        
        PolyvChannelDirectPlayUrlRequester request = channelClient.directPlayUrlRequest();
        request.parameter()
                .withSecretKey("xxxxxxxxxx")
                .withNickname("xxxxxxxx")
                .withUserid("xxxxxxxx")
                .withAvatar("xxxxxxxx");
        PolyvApiResult<PolyvUrl> result = request.execute();
        System.out.println(result.getData());


###配置文件key


    root client：
    * polyv.client.default.root.appId #appId
    * polyv.client.default.root.appSecret #appSecret


    app client：
    * polyv.client.default.app.appId #appId
    * polyv.client.default.app.appSecret #appSecret
    * polyv.client.default.app.userId #userId
    
    
    channel client：
    * polyv.client.default.channel.appId #appId
    * polyv.client.default.channel.appSecret #appSecret
    * polyv.client.default.channel.userId #userId
    * polyv.client.default.channel.channelId #channelId