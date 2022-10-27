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
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.openConnection();
        employeeDAO.dropTable();
        employeeDAO.createTable();
        ArrayList<Employee> employees = CSVReader.readFile("resources/EmployeeRecords.csv");
        EmployeeRecords records = EmployeeValidator.validateAll(employees);
        for (Employee employee: records.getCleanRecords()) {
            employeeDAO.updateTable(employee);
        }
        employeeDAO.commit();
        stopWatch.stop();
        employeeDAO.closeConnection();
        UserInterface.print(records, stopWatch.getTime());
    }

    private static void printEmployeeFromDatabaseByID(int employeeID) {
        UserInterface.printEmployeeFromDatabase(employeeID);
    }
}
