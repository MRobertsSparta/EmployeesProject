package com.sparta.model;

import com.sparta.model.employee.Employee;
import com.sparta.utilities.logging.CustomLogger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeDAO {
    private final String URL = "jdbc:mysql://localhost:3306/employeedatabase?serverTimezone=GMT";
    private Properties properties = new Properties();
    private Connection connection = null;

    private static final Logger logger = CustomLogger.getLogger();

    public void dropTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(Queries.DROP_TABLE);
            logger.log(Level.INFO, "Dropped Table if an employees table already exists");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(Queries.CREATE_TABLE);
            logger.log(Level.INFO, "Created a new employees table");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTable(ArrayList<Employee> employees) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Queries.UPDATE_TABLE)) {
            logger.log(Level.INFO, "Populate Database with ArrayList of Employee objects");
            for(Employee employee:employees) {
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
                preparedStatement.addBatch();
                logger.log(Level.FINER, "Add statement to Batch");
            }
            preparedStatement.executeBatch();
            logger.log(Level.INFO, "Execute Batch process");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void commit() {
        try {
            connection.commit();
            logger.log(Level.INFO, "Committed any pending transactions to the employees database");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void openConnection() {
        try {
            properties.load(new FileReader("resources/login.properties"));
            connection = DriverManager.getConnection(URL, properties.getProperty("username"), properties.getProperty("password"));
            connection.setAutoCommit(false);
            logger.log(Level.INFO, "Create connection with the database");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
            logger.log(Level.INFO, "Close connection with the database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Employee getEmployeeByID(int employeeID) {
        logger.log(Level.INFO, "Retrieve employee information from employees database based on their ID");
        Employee employee = new Employee();
        String[] employeeInfo = new String[10];
        String dateFormat = "yyyy-MM-dd";
        try (PreparedStatement preparedStatement = connection.prepareStatement(Queries.SELECT_EMPLOYEE)) {
            preparedStatement.setInt(1, employeeID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    for (int i = 0; i < employeeInfo.length; i++) {
                        employeeInfo[i] = resultSet.getString(i + 1);
                    }
                }
                employee.setEmployeeDetails(employeeInfo, dateFormat);
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public int getNumberOfRowsInTable() {
        logger.log(Level.INFO, "Check number of rows within employees table to confirm all data has been populated correctly");
        try (PreparedStatement preparedStatement = connection.prepareStatement(Queries.COUNT_ROWS)) {
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
