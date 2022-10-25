package com.sparta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.TreeSet;

public class EmployeeValidator {

    public static EmployeeRecords validateAll(ArrayList<Employee> employees) {
        TreeSet<Employee> employeeSet = new TreeSet<>(new Employee.EmployeeComparator());
        ArrayList<Employee> corruptions = new ArrayList<>();
        for (Employee employee: employees) {
            if (!isValid(employee) || !employeeSet.add(employee)) {
                corruptions.add(employee);
            }
        }
        EmployeeRecords records = new EmployeeRecords(new ArrayList<>(employeeSet), corruptions);
        return records;
    }

    public static boolean isValid(Employee employee) {
        try {
            return (
                    validateTitle(employee.getTitle())
                            && validateName(employee.getFirstName())
                            && validateName(employee.getLastName())
                            && validateMiddleInitial(employee.getMiddleInitial())
                            && validateGender(employee.getGender())
                            && validateEmail(employee.getEmail())
                            && validateDate(employee.getDateOfBirth())
                            && validateDate(employee.getDateOfJoining())
                            && validateJoinedWhenOver18(employee.getDateOfBirth(), employee.getDateOfJoining())
                            && validateSalary(employee.getSalary())
            );
        } catch (NullPointerException ex) {
            return false;
        }
    }

    private static boolean validateTitle(String title) {
        return title.matches("(M(rs?|s)|Drs?|Prof|Hon)\\.");
    }

    private static boolean validateName(String name) {
        return name.matches("[A-Za-z]+");
    }

    private static boolean validateMiddleInitial(String initial) {
        return initial.matches("[A-Z]?");
    }

    private static boolean validateGender(char gender) {
        return (gender + "").matches("[FM]");
    }

    private static boolean validateEmail(String email) {
        return email.matches(".+@.+\\..+");
    }

    private static boolean validateDate(Date date) {
        return date.before(new Date());
    }

    private static boolean validateJoinedWhenOver18(Date dateOfBirth, Date dateOfJoining) {
        return (dateOfJoining.getTime() - dateOfBirth.getTime() > 568025136000l); //18 years = 568025136000 milliseconds
    }

    private static boolean validateSalary(int salary) {
        return (salary > 0);
    }

}
