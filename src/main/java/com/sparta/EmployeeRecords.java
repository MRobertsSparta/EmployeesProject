package com.sparta;

import java.util.ArrayList;
import java.util.Arrays;

public class EmployeeRecords {

    private ArrayList<Employee> cleanRecords;
    private ArrayList<Employee> corruptRecords;

    public EmployeeRecords(ArrayList<Employee> cleanRecords, ArrayList<Employee> corruptRecords) {
        this.cleanRecords = cleanRecords;
        this.corruptRecords = corruptRecords;
    }

    public ArrayList<Employee> getCleanRecords() {
        return cleanRecords;
    }

    public ArrayList<Employee> getCorruptRecords() {
        return corruptRecords;
    }
}
