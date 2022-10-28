package com.sparta.controller;

import com.sparta.utilities.MultithreadingOptimiser;

public class App
{
    public static void main( String[] args ) {
        Controller.initMultiThread(16);
        //MultithreadingOptimiser.findOptimalThreadCount();
    }
}
