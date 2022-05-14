package org.devops.mjar.live.polyv.client;

/**
 * @author GENSEN
 * @date 2021/3/8 16:02
 * @description：client构造器
 */
public class PolyvClientBuilder {

    /** 根用户client
     * @return
     */
    public static PolyvRootClientBuilder standardRootClient(){
        return PolyvRootClientBuilder.standard();
    }

    /** 应用client
     * @return
     */
    public static PolyvAppClientBuilder standardAppClient(){
        return PolyvAppClientBuilder.standard();
    }

    /** 频道client
     * @return
     */
    public static PolyvChannelClientBuilder standardChannelClient(){
        return PolyvChannelClientBuilder.standard();
    }

}
