package com.sparta;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DataToDatabase {
    private String URL= "jdbc:mysql://localhost:3306/employeedatabase?serverTimezone=GMT";
    private Properties properties = new Properties();
    private Connection connection = null;
    private static final String Update_Persons = "INSERT INTO spartans EmployeeID WHERE , namePrefix, firstName, middleInitial, lastName, gender, email, birthday, joinDate, salary)";
    //EmployeeID, namePrefix, firstName, middleInitial, lastName, gender, email, birthday,
    //joinDate, salary
    //private static final String Update_Persons = "UPDATE persons SET lastName=? WHERE personId=? AND lastName!=?";

    private Connection connectToDatabase()
    {
        try{
            properties.load(new FileReader("resources/login.properties"));
            connection = DriverManager.getConnection(URL,properties.getProperty("username"), properties.getProperty("password"));
        }
        catch(IOException e)
        {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    public void updatePersons(int EmployeeID, String namePrefix)
    {
        try(PreparedStatement preparedStatement = connectToDatabase().prepareStatement(Update_Persons))
        {
            preparedStatement.setInt(1, EmployeeID);
            preparedStatement.setString(2, namePrefix);
            //preparedStatement.setString(3, firstName);
            preparedStatement.setInt(1, EmployeeID);
            preparedStatement.setInt(1, EmployeeID);
            preparedStatement.setInt(1, EmployeeID);
            preparedStatement.setInt(1, EmployeeID);
            preparedStatement.setInt(1, EmployeeID);
            preparedStatement.setInt(1, EmployeeID);
            preparedStatement.setInt(1, EmployeeID);
//            preparedStatement.setInt(2, EmployeeID);
//            preparedStatement.setString(3, namePrefix);
            int hasRun = preparedStatement.executeUpdate();
            if(hasRun > 0)
            {
                System.out.println("Lastname of the person with personId " + EmployeeID + " has been updated to " + namePrefix);
            }
            else {
                System.out.println("Did not update");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
