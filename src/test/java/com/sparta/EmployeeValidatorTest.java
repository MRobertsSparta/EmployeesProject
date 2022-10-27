package com.sparta;

import com.sparta.model.employee.Employee;
import com.sparta.model.employee.EmployeeRecords;
import com.sparta.model.EmployeeValidator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class EmployeeValidatorTest {

    private Employee testEmployee = new Employee();

    private void createEmployee(Employee e) {
        e.setId(1);
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

    @BeforeEach
    void setupTestEmployee() {
        createEmployee(testEmployee);
    }

    @Test
    @DisplayName("Test isValid returns true when given a valid employee.")
    void testValidEmployee() {
        Assertions.assertTrue(EmployeeValidator.isValid(testEmployee));
    }

    @ParameterizedTest()
    @ValueSource(strings = {"Mr.", "Ms.", "Mrs.", "Dr.", "Drs.", "Prof.", "Hon."})
    @DisplayName("Test isValid returns true when given a valid employee for all possible titles")
    void testValidTitles(String title) {
        testEmployee.setTitle(title);
        Assertions.assertTrue(EmployeeValidator.isValid(testEmployee));
    }

    @ParameterizedTest
    @ValueSource(strings = {"INVALID", "Mr", ""})
    @DisplayName("Test isValid returns false when the given employee has a corrupted title.")
    void testInvalidTitles(String title) {
        testEmployee.setTitle(title);
        Assertions.assertFalse(EmployeeValidator.isValid(testEmployee));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Test isValid returns false when the given employee has a null title.")
    void testNullTitle(String title) {
        testEmployee.setTitle(title);
        Assertions.assertFalse(EmployeeValidator.isValid(testEmployee));
    }

    @ParameterizedTest
    @ValueSource(strings = {"John1", ".John", ""})
    @DisplayName("Test isValid returns false when the given employee has a corrupted first name.")
    void testInvalidFirstNames(String firstName) {
        testEmployee.setFirstName(firstName);
        Assertions.assertFalse(EmployeeValidator.isValid(testEmployee));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Test isValid returns false when the given employee has a null first name.")
    void testNullFirstName(String firstName) {
        testEmployee.setFirstName(firstName);
        Assertions.assertFalse(EmployeeValidator.isValid(testEmployee));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Smith1", ".Smith", ""})
    @DisplayName("Test isValid returns false when the given employee has a corrupted last name.")
    void testInvalidLastNames(String lastName) {
        testEmployee.setLastName(lastName);
        Assertions.assertFalse(EmployeeValidator.isValid(testEmployee));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Test isValid returns false when the given employee has a null last name.")
    void testNullLastName(String lastName) {
        testEmployee.setLastName(lastName);
        Assertions.assertFalse(EmployeeValidator.isValid(testEmployee));
    }

    public static String[] getAllPossibleMiddleInitials() {
        return " A B C D E F G H I J K L M N O P Q R S T U V W X Y Z".split(" ");
    }

    @ParameterizedTest()
    @MethodSource("getAllPossibleMiddleInitials")
    @DisplayName("Test isValid returns true when given a valid employee for all possible middle initials")
    void testValidMiddleInitials(String initial) {
        testEmployee.setMiddleInitial(initial);
        Assertions.assertTrue(EmployeeValidator.isValid(testEmployee));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "AB", "1", "TRUE"})
    @DisplayName("Test isValid returns false when the given employee has a corrupted middle initial.")
    void testInvalidMiddleInitials(String initial) {
        testEmployee.setMiddleInitial(initial);
        Assertions.assertFalse(EmployeeValidator.isValid(testEmployee));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Test isValid returns false when the given employee has a null middle initial.")
    void testNullMiddleInitial(String initial) {
        testEmployee.setMiddleInitial(initial);
        Assertions.assertFalse(EmployeeValidator.isValid(testEmployee));
    }

    @ParameterizedTest()
    @ValueSource(chars = {'M', 'F'})
    @DisplayName("Test isValid returns true when given a valid employee for all possible genders.")
    void testValidGenders(char gender) {
        testEmployee.setGender(gender);
        Assertions.assertTrue(EmployeeValidator.isValid(testEmployee));
    }

    @ParameterizedTest
    @ValueSource(chars = {'m', 'X'})
    @DisplayName("Test isValid returns false when the given employee has a corrupted gender.")
    void testInvalidGenders(char gender) {
        testEmployee.setGender(gender);
        Assertions.assertFalse(EmployeeValidator.isValid(testEmployee));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -19000})
    @DisplayName("Test isValid returns false when the given employee has a corrupted salary.")
    void testInvalidSalaries(int salary) {
        testEmployee.setSalary(salary);
        Assertions.assertFalse(EmployeeValidator.isValid(testEmployee));
    }

    private static ArrayList<Date> getInvalidDates() {
        int[][] intArrs = {{3000, 1, 1}, {2022, 10, 24}};
        ArrayList<Date> dates = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        for (int[] intArr: intArrs) {
            cal.set(intArr[0], intArr[1], intArr[2]);
            dates.add(cal.getTime());
        }
        return dates;
    }

    @ParameterizedTest
    @MethodSource("getInvalidDates")
    @DisplayName("Test isValid returns false when the given employee has a corrupted date of birth.")
    void testInvalidDatesOfBirth(Date dateOfBirth) {
        testEmployee.setDateOfBirth(dateOfBirth);
        Assertions.assertFalse(EmployeeValidator.isValid(testEmployee));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Test isValid returns false when the given employee has a null date of birth.")
    void testNullDateOfBirth(Date dateOfBirth) {
        testEmployee.setDateOfBirth(dateOfBirth);
        Assertions.assertFalse(EmployeeValidator.isValid(testEmployee));
    }

    @ParameterizedTest
    @MethodSource("getInvalidDates")
    @DisplayName("Test isValid returns false when the given employee has a corrupted date of joining.")
    void testInvalidDatesOfJoining(Date dateOfJoining) {
        testEmployee.setDateOfJoining(dateOfJoining);
        Assertions.assertFalse(EmployeeValidator.isValid(testEmployee));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Test isValid returns false when the given employee has a null date of joining.")
    void testNullDateOfJoining(Date dateOfJoining) {
        testEmployee.setDateOfJoining(dateOfJoining);
        Assertions.assertFalse(EmployeeValidator.isValid(testEmployee));
    }

    @Test
    @DisplayName("Test isValid returns false when the given employee was younger than 18 when joining.")
    void testJoinedWhenYoungerThan18() {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY,1);
        testEmployee.setDateOfBirth(cal.getTime());
        cal.set(2017, Calendar.DECEMBER, 31);
        testEmployee.setDateOfJoining(cal.getTime());
        Assertions.assertFalse(EmployeeValidator.isValid(testEmployee));
    }

    @Test
    @DisplayName("Test valid employee is added to the 'cleanRecords' arrayList when passed to validateAll.")
    void testValidEmployeeIsAddedToCorrectCollection() {
        ArrayList<Employee> testEmployeeArrayList = new ArrayList<>();
        testEmployeeArrayList.add(testEmployee);
        EmployeeRecords records = EmployeeValidator.validateAll(testEmployeeArrayList);

        Assertions.assertTrue(records.getCleanRecords().contains(testEmployee));
    }

    @Test
    @DisplayName("Test corrupt employee is added to the 'corruptRecords' arrayList when passed to validateAll.")
    void testInvalidEmployeeIsAddedToCorrectCollection() {
        testEmployee.setGender('X');

        ArrayList<Employee> testEmployeeArrayList = new ArrayList<>();
        testEmployeeArrayList.add(testEmployee);
        EmployeeRecords records = EmployeeValidator.validateAll(testEmployeeArrayList);

        Assertions.assertTrue(records.getInvalidRecords().contains(testEmployee));
    }

    @Test
    @DisplayName("Test duplicate employee is only added to the 'corruptRecords' arrayList when passed to validateAll.")
    void testDuplicateEmployeeIsAddedToCorrectCollection() {
        Employee testEmployeeDuplicate = new Employee();
        createEmployee(testEmployeeDuplicate);

        ArrayList<Employee> testEmployeeArrayList = new ArrayList<>();
        testEmployeeArrayList.add(testEmployee);
        testEmployeeArrayList.add(testEmployeeDuplicate);
        EmployeeRecords records = EmployeeValidator.validateAll(testEmployeeArrayList);

        Assertions.assertEquals(1, records.getCleanRecords().size());
        Assertions.assertTrue(records.getDuplicateRecords().contains(testEmployeeDuplicate));
    }
}
