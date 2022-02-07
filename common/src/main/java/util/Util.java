package util;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.logging.*;

public class Util {
    public static final SecureRandom rand = new SecureRandom();
    public static final Logger logger = Logger.getLogger(Util.class.getName());

    private Util() {
        throw new IllegalStateException("Utility class");
    }

    public static void setupLogger() {
        FileHandler fh;
        try {
            // This block configure the logger with handler and formatter
            fh = new FileHandler("Seasons.log");
            Util.logger.setUseParentHandlers(false);
            fh.setFormatter(new SimpleFormatter() {
                private static final String format = "[%1$tF %1$tT] [%2$-7s] %3$s %n";

                @Override
                public synchronized String format(LogRecord lr) {
                    return String.format(format,
                            new Date(lr.getMillis()),
                            lr.getLevel().getLocalizedName(),
                            lr.getMessage()
                    );
                }
            });
            Util.logger.addHandler(fh);
            Util.logger.setLevel(Level.ALL);
            // the following statement is used to log any messages

        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    public static int getNextInt(int limit) {
        return rand.nextInt(limit);
    }
}
