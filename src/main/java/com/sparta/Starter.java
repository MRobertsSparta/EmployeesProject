package com.sparta;

import javax.xml.crypto.Data;
import java.util.Calendar;

public class Starter {
    public static void start()
    {
        Employee employee = new Employee();
        createEmployee(employee);
        DataToDatabase.updatePersons(employee);
    }
    private static void createEmployee(Employee e) {
        e.setId(1330);
        e.setTitle("Mr.");
        e.setFirstName("John");
        e.setLastName("Smith");
        e.setMiddleInitial("A");
        e.setGender('M');
        e.setEmail("john.smith@email.com");
        e.setSalary(19000);
        Calendar cal = Calendar.getInstance();
        cal.set(1970, Calendar.JANUARY, 1);
        e.setDateOfBirth(cal.getTime());
        cal.set(2000, Calendar.JANUARY, 1);
        e.setDateOfJoining(cal.getTime());
    }

}
