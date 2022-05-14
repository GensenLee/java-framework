package org.devops.core.utils.modules.future;


import org.devops.core.utils.modules.future.proxy.FutureProxy;

/**
 * @author GENSEN
 * @date 2021/6/3 11:44
 * @description：包说明
 */
public class package_info {
    /*
    * 代理
    * 代理方法的执行，并行、串行
    *
    * */


    public static void main(String[] args) {
        userProxy();
//        notUserProxy();
    }

    private static void userProxy(){
        // 使用例子
        FutureProxy futureProxy = new FutureProxy();
        Target target = new Target();
        Target proxyClass = futureProxy.getProxyClass(target);
        long start = System.currentTimeMillis();
        // 启动方法
        proxyClass.testProxy("a");
        proxyClass.testProxy("b");

        // 获取返回值
        String a = proxyClass.testProxy("a");
        String b = proxyClass.testProxy("b");
        long time = System.currentTimeMillis() - start;
        System.out.println("耗时 time = " + time);
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }

    private static void notUserProxy(){
        // 正常并行方式
        Target target = new Target();
        long start = System.currentTimeMillis();
        String a = target.testProxy("a");
        String b = target.testProxy("b");
        long time = System.currentTimeMillis() - start;
        System.out.println("耗时 time = " + time);
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }

    static class Target {
        public String testProxy(String string){
            // do something
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread().getName() + "::" + string;
        }
    }

}
