package org.devops.mjar.live.core.operator;

import org.devops.core.utils.util.StringUtil;
import org.devops.mjar.live.core.handler.MjarLiveContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

/**
 * @author GENSEN
 * @date 2021/3/17 15:06
 * @description：日志打印
 */
public class LiveApiLogger {
    private static final Logger logger = LoggerFactory.getLogger(LiveApiLogger.class);

    /**
     * append log with pattern
     *
     * @param clazz
     * @param requestId
     * @param appendLogPattern   like "aaa {} bbb {} ccc"
     * @param appendLogArguments like "111, true"
     */
    public static void logRequest(Class<?> clazz, String requestId, String appendLogPattern, Object... appendLogArguments) {
        FormattingTuple ft = MessageFormatter.arrayFormat(appendLogPattern, appendLogArguments);
        String appendLog = ft.getMessage();
        log(requestId, clazz, appendLog);
    }

    /**
     * append log
     *
     * @param requestId
     * @param clazz
     * @param appendLog
     */
    public static void log(String requestId, Class<?> clazz, String appendLog) {
        StringBuffer stringBuffer = new StringBuffer();
        if (StringUtil.isEmpty(requestId)) {
            requestId = MjarLiveContext.getContext().getMjarLiveRequestIdHandler().get();
        }
        stringBuffer
                .append("REQUEST_ID[")
                .append(clazz.getSimpleName())
                .append("][")
                .append(requestId)
                .append("]-");
        stringBuffer.append(appendLog != null ? appendLog : "");
        String formatAppendLog = stringBuffer.toString();
        logger.info(">>>>>>>>>>> {}", formatAppendLog);
    }

    /**
     * @param clazz
     * @param appendLog
     */
    public static void log(Class<?> clazz, String appendLog) {
        String rqid = MjarLiveContext.getContext().getMjarLiveRequestIdHandler().get();
        log(rqid, clazz, appendLog);
    }

    public static void log(String requestId, Throwable throwable){
        logger.error("REQUEST_ID[" + requestId + "]", throwable);
    }

    public static boolean isDebugOpen(){
        return logger.isDebugEnabled();
    }

}
