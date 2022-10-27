package com.sparta.controller;

import com.sparta.model.*;
import com.sparta.model.employee.Employee;
import com.sparta.model.employee.EmployeeRecords;
import com.sparta.utilities.StopWatch;
import com.sparta.view.UserInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Controller {

    public static void init() {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.openConnection();
        employeeDAO.dropTable();
        employeeDAO.createTable();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ArrayList<Employee> employees = CSVReader.readFile("resources/EmployeeRecordsLarge.csv");

        EmployeeRecords records = EmployeeValidator.validateAll(employees);

        employeeDAO.updateTable(records.getCleanRecords());
        employeeDAO.commit();
        employeeDAO.closeConnection();

        stopWatch.stop();
        UserInterface.print(records, stopWatch.getTime());
    }

    public static void initMultiThread(int numberOfThreads) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.openConnection();
        employeeDAO.dropTable();
        employeeDAO.createTable();
        employeeDAO.closeConnection();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ArrayList<Employee> employees = CSVReader.readFile("resources/EmployeeRecordsLarge.csv");
        EmployeeRecords records = EmployeeValidator.validateAll(employees);

        int employeesPerThread = records.getCleanRecords().size() / numberOfThreads;
        DAOThread[] threads = new DAOThread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new DAOThread(records.getCleanRecords(), employeesPerThread * i, employeesPerThread * (i + 1));
            threads[i].start();
        }

        try {
            for (DAOThread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        stopWatch.stop();
        UserInterface.print(records, stopWatch.getTime());
    }
}