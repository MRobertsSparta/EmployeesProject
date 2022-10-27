package com.sparta.controller;

import com.sparta.model.*;
import com.sparta.model.employee.Employee;
import com.sparta.model.employee.EmployeeRecords;
import com.sparta.utilities.StopWatch;
import com.sparta.view.UserInterface;

import java.util.ArrayList;

public class Controller {

    public static void init() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ArrayList<Employee> employees = CSVReader.readFile("resources/EmployeeRecords.csv");
        EmployeeRecords records = EmployeeValidator.validateAll(employees);
        for (Employee employee: records.getCleanRecords()) {
            EmployeeDAO.updatePersons(employee);
        }
        EmployeeDAO.commit();
        stopWatch.stop();
        UserInterface.print(records, stopWatch.getTime());
    }
}
