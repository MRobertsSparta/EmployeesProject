package com.sparta.controller;

import com.sparta.model.EmployeeDAO;
import com.sparta.model.employee.Employee;

import java.util.ArrayList;

public class DAOThread extends Thread {

    private ArrayList<Employee> employees;
    private int start;
    private int stop;

    public DAOThread(ArrayList<Employee> employees, int start, int stop) {
        super();
        this.employees = employees;
        this.start = start;
        this.stop = stop;
    }

    @Override
    public void run() {
        EmployeeDAO dao = new EmployeeDAO();
        dao.openConnection();
        dao.updateTable(new ArrayList<>(employees.subList(start, stop)));
        dao.commit();
        dao.closeConnection();
    }
}
