package com.sparta;

public class UserInterface {

    public static void print(EmployeeRecords records){
        System.out.println("----Results of Reading the Employee CSV File----");
        System.out.println("Number of clean records: " + records.getCleanRecords().size();
        System.out.println("Number of corrupted records: " + records.getCorruptRecords().size();
        System.out.println("\t- " + records.getDuplicateRecords() + " records are duplicate in employee ID");
        System.out.println("\t- " + records.InvalidRecords() + " records contain invalid fields");

        System.out.println("\n----Results of Multithread Speed Test----");
        System.out.println("Time taken before implementing multiple threads: " + records.getTimeBeforeMultithread());
        System.out.println("Time taken after implementing multiple threads: " + records.getTimeAfterMultithread());
    }
}
