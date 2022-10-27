package com.sparta.model;

public interface Queries {
    String UPDATE_TABLE = "INSERT INTO employees (EmployeeID, namePrefix, firstName, middleInitial, lastName, gender, email, birthday,\n" +
            "joinDate, salary) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    String DROP_TABLE = "DROP TABLE IF EXISTS employees;";

    String CREATE_TABLE = "CREATE TABLE employees(\n" +
            "employeeID INT PRIMARY KEY,\n" +
            "namePrefix VARCHAR(5),\n" +
            "firstName VARCHAR(20),\n" +
            "middleInitial VARCHAR(1),\n" +
            "lastName VARCHAR(20),\n" +
            "gender VARCHAR(1),\n" +
            "email VARCHAR(50),\n" +
            "birthday DATE,\n" +
            "joinDate DATE,\n" +
            "salary int\n" +
            ");";

    String SELECT_EMPLOYEE = "SELECT * FROM employees WHERE employeeId = ?";
}
