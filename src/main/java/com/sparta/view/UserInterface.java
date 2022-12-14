package com.sparta.view;

import com.sparta.model.EmployeeDAO;
import com.sparta.model.employee.Employee;
import com.sparta.model.employee.EmployeeRecords;

import java.util.ArrayList;

public class UserInterface {

    public static void print(EmployeeRecords records, long time){
        System.out.println("----Results of Reading the Employee CSV File----");
        System.out.println("Number of clean records: " + records.getCleanRecords().size());
        System.out.println("Number of corrupted records: "
                + (records.getInvalidRecords().size() + records.getDuplicateRecords().size()) );
        System.out.println("\t- " + records.getDuplicateRecords().size() + " records are duplicate in employee ID.");
        printEmployees(records.getDuplicateRecords());
        System.out.println("\t- " + records.getInvalidRecords().size() + " records contain invalid fields.");
        printEmployees(records.getInvalidRecords());
        System.out.println("\nTime Taken: " + time + " nanoseconds");
    }

    private static void printEmployees(ArrayList<Employee> employees) {

        for (Employee employee: employees) {
            System.out.println("\t\t-" + employee.toString());
        }
    }

    public static void printEmployeeFromDatabase(int employeeID) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Employee employee = employeeDAO.getEmployeeByID(employeeID);
        System.out.println(employee.toString());
    }
}
