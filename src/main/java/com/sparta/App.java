package com.sparta;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class App
{
    public static void main( String[] args )
    {
        ArrayList<Employee> testArr = new ArrayList<>();
        testArr = CSVReader.readFile("C:\\Users\\hamza\\Documents\\Sparta\\Projects\\GroupProjectWeek4\\EmployeeRecords.csv");

        System.out.println(testArr.get(14).getDateOfBirth());

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(dateFormat.format(testArr.get(14).getDateOfBirth()));

        //CSVReader.testReadFile("C:\\Users\\hamza\\Documents\\Sparta\\Projects\\GroupProjectWeek4\\EmployeeRecords.csv");

    }
}
