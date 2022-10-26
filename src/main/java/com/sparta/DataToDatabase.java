package com.sparta;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DataToDatabase {
    private static final String URL= "jdbc:mysql://localhost:3306/employeedatabase?serverTimezone=GMT";
    //private Properties properties = new Properties();
    private static Connection connection = null;
    private static final String Update_Persons = "INSERT INTO employees (EmployeeID, namePrefix, firstName, middleInitial, lastName, gender, email, birthday,\n" +
            "joinDate, salary) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static void updatePersons(Employee employee)
    {
        try(PreparedStatement preparedStatement = connection.prepareStatement(Update_Persons))
        {
            preparedStatement.setInt(1, employee.getId());
            preparedStatement.setString(2, employee.getTitle());
            preparedStatement.setString(3, employee.getFirstName());
            preparedStatement.setString(4, employee.getMiddleInitial());
            preparedStatement.setString(5, employee.getLastName());
            preparedStatement.setString(6, "" + employee.getGender());
            preparedStatement.setString(7, employee.getEmail());
            preparedStatement.setDate(8, new Date(employee.getDateOfBirth().getTime()));
            preparedStatement.setDate(9, new Date(employee.getDateOfJoining().getTime()));
            preparedStatement.setInt(10, employee.getSalary());
            int hasRun = preparedStatement.executeUpdate();
//            if(hasRun > 0)
//            {
//                System.out.println("Sent to database");
//            }
//            else {
//                System.out.println("Did not update");
//            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }
    static{
        try{
            connection = DriverManager.getConnection(URL,"root", "root");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
