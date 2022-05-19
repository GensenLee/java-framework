package org.devops.core.utils.modules.log.request;

import org.devops.core.utils.util.RequestUtil;

public class RequestHelper extends RequestUtil {

    protected static void setRequestId(String requestId) {
        RequestUtil.setRequestId(requestId);
    }

     protected static void clean() {
         RequestUtil.clean();
    }

}
