package org.devops.web.test.test.wx;

import com.github.binarywang.wxpay.service.WxPayService;
import org.devops.mjar.autotest.action.AbstractJUnit4ServiceAction;
import org.devops.mjar.weixin.configuration.EnableWeixin;
import org.devops.mjar.weixin.factory.WxServiceFactory;
import org.junit.Test;

/**
 * @author GENSEN
 * @version 1.00
 * @time 2022/2/16 9:59
 * @description
 */
@EnableWeixin
public class WxPayTest extends AbstractJUnit4ServiceAction {

    @Test
    public void test() {
        WxPayService payService = WxServiceFactory.getPayService();


//        payService.parseOrderNotifyV3Result()

    }


    public static void main(String[] args) {
//        System.out.println(quickPow(2, 40));
        numWays(7, 2, "");
        numWays(7, 1, "");
    }


    public static long quickPow(long a, int pow) {
        System.out.println("a = " + a + " pow = " + pow);
        if (pow % 2 == 1) {
            return quickPow(a, pow - 1) * a;
        }
        if (pow == 2) {
            return a * a;
        }
        long tmp = quickPow(a, pow / 2);
        return tmp * tmp;
    }

    static int a;

    public static long numWays(int n, int step, String path) {
        if (n == 0) {
            System.out.println("path = " + path + " n=" + n + " step=" + step);
            return 0;
        }
        if (n == 1) {
            path += 1;
            System.out.println("path = " + path + " n=" + n + " step=" + step);
            return 1;
        }
        if (n == 2) {
            System.out.println("path = " + path + "11 n=" + n + " step=" + step);
            System.out.println("path = " + path + "2 n=" + n + " step=" + step);
            return 2;
        }

        n -= step;
        path = path + step;
        return numWays(n, 1, path) + numWays(n, 2, path);
    }


}
