package com.sparta;

import com.sparta.logging.CustomLogger;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class EmployeeValidator {

    private final Logger logger = CustomLogger.getLogger();
    private static final long EIGHTEEN_YEARS = 568025136000l;  //18 years = 568025136000 milliseconds
    private static final Pattern TITLE_REGEX = Pattern.compile("(M(rs?|s)|Drs?|Prof|Hon)\\.");
    private static final Pattern NAME_REGEX = Pattern.compile("[A-Za-z]+");
    private static final Pattern MIDDLE_INITIAL_REGEX = Pattern.compile("([A-Z]?)");
    private static final Pattern EMAIL_REGEX = Pattern.compile(".+@.+\\..+");

    public static EmployeeRecords validateAll(ArrayList<Employee> employees) {
        HashSet<Employee> employeeSet = new HashSet<>();
        ArrayList<Employee> corruptions = new ArrayList<>();
        for (Employee employee : employees) {
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
        return TITLE_REGEX.matcher(title).matches();
    }

    private static boolean validateName(String name) {
        return NAME_REGEX.matcher(name).matches();
    }

    private static boolean validateMiddleInitial(String initial) {
        return MIDDLE_INITIAL_REGEX.matcher(initial).matches();
    }

    private static boolean validateEmail(String email) {
        return EMAIL_REGEX.matcher(email).matches();
    }

    private static boolean validateGender(char gender) {
        switch (gender) {
            case 'M':
            case 'F':
                return true;
            default:
                return false;
        }
    }

    private static boolean validateDate(Date date) {
        return date.before(new Date());
    }

    private static boolean validateJoinedWhenOver18(Date dateOfBirth, Date dateOfJoining) {
        return (dateOfJoining.getTime() - dateOfBirth.getTime() > EIGHTEEN_YEARS);
    }

    private static boolean validateSalary(int salary) {
        return (salary > 0);
    }

}
