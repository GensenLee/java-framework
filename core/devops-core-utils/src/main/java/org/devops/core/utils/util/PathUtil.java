package org.devops.core.utils.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PathUtil {

    public static String join(String source, String... args) {
        String[] tmp = source.replaceAll("\\\\", "/").split("/");
        if (args.length == 0) {
            return StringUtil.join(tmp, String.valueOf(File.separatorChar));
        }

        List<String> liTmp = new ArrayList<String>(Arrays.asList(tmp));

        for (String arg : args) {
            String[] t = arg.replaceAll("\\\\", "/").split("/");
            for (String s : t) {
                if (".".equals(s)) {
                } else if ("..".equals(s)) {
                    liTmp.remove(liTmp.size() - 1);
                } else {
                    if (StringUtil.isEmpty(s.trim())) {
                        continue;
                    }
                    liTmp.add(s);
                }
            }
        }
        return StringUtil.join(liTmp, String.valueOf(File.separatorChar));
    }
}
