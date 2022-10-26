package com.sparta;

import com.sparta.*;

import java.util.ArrayList;

public class Controller {

    public static void init() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ArrayList<Employee> employees = CSVReader.readFile("resources/EmployeeRecords.csv");
        EmployeeRecords records = EmployeeValidator.validateAll(employees);
        for (Employee employee: records.getCleanRecords()) {
            DataToDatabase.updatePersons(employee);
        }
        DataToDatabase.commit();
        stopWatch.stop();
        UserInterface.print(records, stopWatch.getTime());
    }
}
