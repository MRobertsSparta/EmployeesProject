package com.sparta;

public class StopWatch {

    private long startTime;
    private long stopTime;

    public void start() {
        startTime = System.nanoTime();
    }

    public void stop() {
        stopTime = System.nanoTime();
    }

    public long getTime() {
        return stopTime - startTime;
    }
}

