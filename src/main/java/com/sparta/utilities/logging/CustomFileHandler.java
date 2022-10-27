package com.sparta.utilities.logging;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Level;

public class CustomFileHandler {

    public static FileHandler fileHandler;

    static {
        try {
            LocalDateTime time = LocalDateTime.now();
            String url = "src/main/resources/sort_manager_log.log";
            fileHandler = new FileHandler(url, true);
            fileHandler.setLevel(Level.ALL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static FileHandler getFileHandler() {
        return fileHandler;
    }
}
