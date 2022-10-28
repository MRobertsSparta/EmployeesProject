package com.sparta;

import com.sparta.controller.Controller;
import com.sparta.model.EmployeeDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestMultithreading {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 8, 16})
    @DisplayName("Test that the correct number of records are added after multithreading")
    void testMultiThreading(int numOfThreads) {
        Controller.initMultiThread(numOfThreads);
        EmployeeDAO dao = new EmployeeDAO();
        dao.openConnection();
        Assertions.assertEquals(65494, dao.getNumberOfRowsInTable());
        dao.closeConnection();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 8, 16})
    @DisplayName("Test the correct number of threads are active")
    void testActiveThreads(int numOfThreads) {
        Counter counter = new Counter();
        counter.start();
        Controller.initMultiThread(numOfThreads);
        counter.setCounting(false);
        Assertions.assertEquals(numOfThreads + 4, counter.getCount());
    }

    private class Counter extends Thread {
        private int count = 0;
        private boolean counting = true;

        @Override
        public void run() {
            while (counting) {
                int threadCount = Thread.activeCount();
                if (threadCount > count) {
                    count = threadCount;
                }
            }
        }

        public int getCount() {
            return count;
        }

        public void setCounting(boolean counting) {
            this.counting = counting;
        }
    }


}
