package com.sparta;

import java.util.List;

public class UserInterface {
    static List<Employee> cleanRecord;
    static List<Employee> corruptedRecord;

    static int numberOfCleanRecord;
    static int numberOfCorruptedRecord;
    static int numberOfDuplicateRecord;         //records with duplicate id
    static int numberOfInvalidRecord;         //records with gender X, middleInitial FALSE, invalid date

    static int timeBeforeMultithread;
    static int timeAfterMultithread;

    public static void print(){
        System.out.println("----Results of Reading the Employee CSV File----");
        System.out.println("Number of clean records: " + numberOfCleanRecord);
        System.out.println("Number of corrupted records: " + numberOfCorruptedRecord);
        System.out.println("\t- " + numberOfDuplicateRecord + " records are duplicate in employee ID");
        System.out.println("\t- " + numberOfInvalidRecord + " records contain invalid fields");

        System.out.println("\n----Results of Multithread Speed Test----");
        System.out.println("Time taken before implementing multiple threads: " + timeBeforeMultithread);
        System.out.println("Time taken after implementing multiple threads: " + timeAfterMultithread);
    }
}
