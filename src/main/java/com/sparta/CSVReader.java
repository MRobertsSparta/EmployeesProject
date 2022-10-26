package com.sparta;

import com.sparta.logging.CustomLogger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

public class CSVReader {

    private final Logger logger = CustomLogger.getLogger();

    public static ArrayList<Employee> readFile(String fileName) {
        ArrayList<Employee> employeeList = new ArrayList<>();
        try {
            String line;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            line = bufferedReader.readLine();
            while((line=bufferedReader.readLine())!=null) {
                String[] employeeInfo = line.split(",");
                Employee employee = new Employee();
                setEmployeeDetails(employeeInfo, employee);
                employeeList.add(employee);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    private static void setEmployeeDetails(String[] employeeInfo, Employee employee) {
        employee.setId(Integer.parseInt(employeeInfo[0]));
        employee.setTitle(employeeInfo[1]);
        employee.setFirstName(employeeInfo[2]);
        employee.setMiddleInitial(employeeInfo[3].replace("FALSE", ""));
        employee.setLastName(employeeInfo[4]);
        employee.setGender(employeeInfo[5].charAt(0));
        employee.setEmail(employeeInfo[6]);
        employee.setDateOfBirth(parseDate(employeeInfo[7]));
        employee.setDateOfJoining(parseDate(employeeInfo[8]));
        employee.setSalary(Integer.parseInt(employeeInfo[9]));
    }

    private static Date parseDate(String dateString){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


}
