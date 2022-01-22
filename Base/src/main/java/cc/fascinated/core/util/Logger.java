package cc.fascinated.core.util;

import java.util.Date;

public class Logger {

    /**
     * Log the object to console
     *
     * @param obj The object to log
     */
    public void log(Object obj) {
        System.out.println("[" + TimeUtil.formatTime(new Date()) + "] " + obj);
    }
}
