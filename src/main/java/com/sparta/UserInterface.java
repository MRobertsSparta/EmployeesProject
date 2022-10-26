package com.sparta;

public class UserInterface {

    public static void print(EmployeeRecords records){
        System.out.println("----Results of Reading the Employee CSV File----");
        System.out.println("Number of clean records: " + records.getCleanRecords().size());
        System.out.println("Number of corrupted records: " + records.getCorruptRecords().size());
//        System.out.println("\t- " + records.getDuplicateRecords().size() + " records are duplicate in employee ID");
//        System.out.println("\t- " + records.InvalidRecords().size() + " records contain invalid fields");
    }
}
