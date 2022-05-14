/**
 * @author GENSEN
 * @date 2021/9/8 18:51
 * @description： /*
 */
package org.devops.core.utils.modules.log.forging;
/*
 * 防日志伪造功能
 * 过滤log中的非法字符
 * 功能默认开启，如需关闭，注释META-INF\services\ch.qos.logback.classic.spi.Configurator
 * */


// TODO: 2021/9/9 发现CustomTTLLLayout仅在项目启动日志输出时被调用，该方案需要优化