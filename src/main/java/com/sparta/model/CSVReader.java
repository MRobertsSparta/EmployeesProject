package com.sparta.model;

import com.sparta.model.employee.Employee;
import com.sparta.utilities.logging.CustomLogger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CSVReader {

    private static final Logger logger = CustomLogger.getLogger();

    public static ArrayList<Employee> readFile(String fileName) {
        logger.log(Level.INFO, "Started read file method with file: " + fileName);
        ArrayList<Employee> employeeList = new ArrayList<>();
        logger.log(Level.FINER, "Created ArrayList to store Employee objects populated from the CSV file");
        try {
            String line;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] employeeInfo = line.split(",");
                Employee employee = new Employee();
                String dateFormat = "dd/MM/yyyy";
                employee.setEmployeeDetails(employeeInfo, dateFormat);
                employeeList.add(employee);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.log(Level.FINER, "Return the ArrayList of Employee Objects");
        return employeeList;
    }
}
