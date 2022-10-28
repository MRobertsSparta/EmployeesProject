package com.sparta.utilities.logging;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class CustomFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(record.getInstant(), ZoneOffset.UTC);
        return getIndent(record.getLevel())
                + DateTimeFormatter.ofPattern("yyyy-MM-dd:hh:mm:ss").format(dateTime)
                + " " + record.getLevel()
                + " @ " + record.getSourceClassName()
                + "#" + record.getSourceMethodName()
                + "() :\n " + getIndent(record.getLevel())
                + record.getMessage() + "\n\n";
    }

    private String getIndent(Level level) {
        String indent = "";
        switch (level.getName()) {
            case "FINEST":
                indent += "    ";
            case "FINER":
                indent += "    ";
            case "FINE":
                indent += "    ";
                break;
        }
        return indent;
    }
}
