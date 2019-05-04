package services;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.FileHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class MyLogger {
    private MyLogger() {}

    static private Formatter CSVFormat = new Formatter() {
        public String format(LogRecord record) {
            String level = record.getLevel().toString();

            Date timestamp = new Date();

            String action = record.getMessage();
            String name = record.getLoggerName();

            String ans = level + ", " + timestamp + ", " + action + ", " + name + "\n\n";
            return ans;
        }
    };

    public static Logger getInstance() throws IOException {
        Logger logger = Logger.getLogger("hospital.Logger");

        FileHandler handler = new FileHandler("./src/main/resources/logging.txt", true);
        handler.setFormatter(MyLogger.CSVFormat);
        logger.addHandler(handler);
        logger.setUseParentHandlers(false);

        return logger;
    }
}
