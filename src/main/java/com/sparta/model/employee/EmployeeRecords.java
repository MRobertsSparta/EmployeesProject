package com.sparta.model.employee;

import java.util.ArrayList;

public class EmployeeRecords {

    private ArrayList<Employee> cleanRecords;
    private ArrayList<Employee> invalidRecords;
    private ArrayList<Employee> duplicateRecords;

    public EmployeeRecords(ArrayList<Employee> cleanRecords, ArrayList<Employee> invalidRecords, ArrayList<Employee> duplicateRecords) {
        this.cleanRecords = cleanRecords;
        this.invalidRecords = invalidRecords;
        this.duplicateRecords = duplicateRecords;
    }

    public ArrayList<Employee> getCleanRecords() {
        return cleanRecords;
    }

    public ArrayList<Employee> getInvalidRecords() {
        return invalidRecords;
    }

    public ArrayList<Employee> getDuplicateRecords() {
        return duplicateRecords;
    }
}
