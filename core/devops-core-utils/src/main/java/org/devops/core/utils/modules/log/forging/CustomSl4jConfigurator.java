package org.devops.core.utils.modules.log.forging;


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.Configurator;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.LayoutBase;
import ch.qos.logback.core.encoder.LayoutWrappingEncoder;
import ch.qos.logback.core.spi.ContextAwareBase;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author GENSEN
 * @date 2021/5/13 16:20
 * @description：
 */
public class CustomSl4jConfigurator extends ContextAwareBase implements Configurator {

    @Override
    public void configure(LoggerContext lc) {
        addInfo("Setting up custom configuration.");

        ConsoleAppender<ILoggingEvent> ca = new ConsoleAppender<ILoggingEvent>();
        ca.setContext(lc);
        ca.setName("console");
        LayoutWrappingEncoder<ILoggingEvent> encoder = new LayoutWrappingEncoder<ILoggingEvent>();
        encoder.setContext(lc);


        // same as
        // PatternLayout layout = new PatternLayout();
        // layout.setPattern("%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n");

        LayoutBase layout = null;
        ServiceLoader<LayoutBase> serviceLoader = ServiceLoader.load(LayoutBase.class);
        Iterator<LayoutBase> iterator = serviceLoader.iterator();
        if (iterator.hasNext()) {
            layout = iterator.next();

            /*
            * 如果需要配置自定义Layout
            * 资源目录下新建文件夹 resources\META-INF\services
            * 目录中新建文件ch.qos.logback.core.LayoutBase
            * 文件内容为LayoutBase子类全限定名，如 com.spring.runner.layout.TestTTLLLayout
            * */
        }else {
            layout  = new CustomTTLLLayout();
            addInfo(CustomSl4jConfigurator.class.getName() + " >>> 开启防日志伪造攻击Log Forging");
        }

        // 自定义布局，将日志参数中的特殊字符做转义
        // 防止Log Forging日志伪造攻击

        layout.setContext(lc);
        layout.start();
        encoder.setLayout(layout);

        ca.setEncoder(encoder);
        ca.start();

        Logger rootLogger = lc.getLogger(Logger.ROOT_LOGGER_NAME);
        rootLogger.addAppender(ca);
    }
}
