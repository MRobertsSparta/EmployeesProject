package com.sparta.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomLogger {

    private static final Logger logger = Logger.getLogger("Custom Logger");

    static {
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.ALL);
        logger.addHandler(CustomFileHandler.getFileHandler());
    }
    public static Logger getLogger() {
        return logger;
    }

}
