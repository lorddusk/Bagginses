package nl.lang2619.bagginses.references;

import nl.lang2619.bagginses.Bagginses;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tim on 2/9/2015.
 */
public final class Log {
    static final Logger logger = LogManager.getLogger("Bagginses");

    private static FastDateFormat dateFormat = FastDateFormat.getInstance("HH:mm:ss");
    private static long lastLogReport = -1;

    public static void info(String message, Object... data) {
        logger.info(getMessage(message, data));
    }

    public static void warn(String message, Object... data) {
        logger.warn(getMessage(message, data));
    }

    public static void error(String message, Object... data) {
        logger.error(getMessage(message, data));
    }

    private static String getMessage(String message, Object... data) {
        for (int a = data.length - 1; a >= 0; a--)
            message = message.replace("$" + a, data[a] == null ? "null" : String.valueOf(data[a]));
        return message;
    }
}
