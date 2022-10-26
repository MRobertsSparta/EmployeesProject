package com.sparta.logging;

import com.sparta.*;

import java.util.ArrayList;

public class Controller {

    public static void init() {
        ArrayList<Employee> employees = CSVReader.readFile("resources/EmployeeRecords.csv");
        EmployeeRecords records = EmployeeValidator.validateAll(employees);
        for (Employee employee: records.getCleanRecords()) {
            DataToDatabase.updatePersons(employee);
        }
        UserInterface.print(records);
    }
}
