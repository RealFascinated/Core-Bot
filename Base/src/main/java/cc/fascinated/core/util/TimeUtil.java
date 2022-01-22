package cc.fascinated.core.util;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    /**
     * The date formatter to use when formatting the time.
     */
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy hh:mm:ss");

    /**
     * Format the given time and return the formatted time.
     *
     * @param time The time to format in ms
     * @return Formatted time
     */
    private static String formatTime(long time) {
        return formatTime(new Date(time));
    }

    /**
     * Format the given time and return the formatted time.
     *
     * @param date The time to format
     * @return Formatted time
     */
    public static String formatTime(Date date) {
        return dateFormat.format(date);
    }
}
