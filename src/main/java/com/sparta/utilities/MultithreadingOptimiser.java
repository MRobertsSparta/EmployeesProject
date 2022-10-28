package com.sparta.utilities;

import com.sparta.controller.Controller;
import com.sparta.model.EmployeeDAO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class MultithreadingOptimiser {

    private static final int attempts = 10;
    private static final int max = 64;

    public static void findOptimalThreadCount() {
        long[][] times = new long[max][attempts];
        end: for (int x = 0; x < max; x++) {
            for (int y = 0; y < attempts; y++) {
                System.out.println("\nThread count: " + (x + 1) + " Attempt: " + (y + 1) + "\n");
                long time = Controller.initMultiThread(x + 1);
                times[x][y] = time;

                EmployeeDAO dao = new EmployeeDAO();
                dao.openConnection();
                System.out.println("Verified number of rows in table is: " + dao.getNumberOfRowsInTable());
                dao.closeConnection();
            }
        }

        printInCSVFormat(times);
    }

    private static void printInCSVFormat(long[][] times) {
        long bestTime = Long.MAX_VALUE;
        int bestAttempt = 0;
        int bestCount = 0;

        System.out.println("Thread Count, Attempt 1, Attempt 2, Attempt 3, Attempt 4, Attempt 5, Attempt 6, Attempt 7, Attempt 8, Attempt 9, Attempt 10, Average");
        for (int x = 0; x < times.length; x++) {
            System.out.print((x + 1) + ",");
            long total = 0;
            for (int y = 0; y < times[x].length; y++) {
                total += times[x][y];
                if (times[x][y] < bestTime) {
                    bestTime = times[x][y];
                    bestAttempt = y + 1;
                    bestCount = x + 1;
                }
                System.out.print(times[x][y] + ",");
            }
            System.out.print((total / attempts) + "\n") ;
        }
        System.out.println("Best: " + bestTime + " (T" + bestCount + ",A" + bestAttempt + ")");
    }
}
