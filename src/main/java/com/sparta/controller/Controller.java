package com.sparta.controller;

import com.sparta.model.*;
import com.sparta.model.employee.Employee;
import com.sparta.model.employee.EmployeeRecords;
import com.sparta.utilities.StopWatch;
import com.sparta.utilities.logging.CustomLogger;
import com.sparta.view.UserInterface;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {

    private static final Logger logger = CustomLogger.getLogger();

    public static void init() {
        logger.log(Level.INFO, "CSV Data Migration application started.");
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
        logger.log(Level.INFO, "Stopwatch stopped with total time of: " + stopWatch.getTime());
        UserInterface.print(records, stopWatch.getTime());
        logger.log(Level.INFO, "CSV Data Migration process finished.");
    }

    public static long initMultiThread(int numberOfThreads) {
        logger.log(Level.INFO, "CSV Data Migration multi-threaded application started.");
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.openConnection();
        employeeDAO.dropTable();
        employeeDAO.createTable();
        employeeDAO.closeConnection();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ArrayList<Employee> employees = CSVReader.readFile("resources/EmployeeRecordsLarge.csv");
        logger.log(Level.INFO, "Populated ArrayList with " + employees.size() + " Employee objects");
        EmployeeRecords records = EmployeeValidator.validateAll(employees);

        logger.log(Level.INFO, "Created Employee Records object with " + records.getCleanRecords().size() +
                " clean records." + records.getInvalidRecords() + " invalid records and " + records.getDuplicateRecords() + " duplicate records.");


        float employeesPerThread = records.getCleanRecords().size() / (float) numberOfThreads;
        DAOThread[] threads = new DAOThread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new DAOThread(records.getCleanRecords(), (int)(employeesPerThread * i), (int)(employeesPerThread * (i + 1)));
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
        logger.log(Level.INFO, "Stopwatch stopped with total time of: " + stopWatch.getTime());
        UserInterface.print(records, stopWatch.getTime());
        return stopWatch.getTime();
    }
}