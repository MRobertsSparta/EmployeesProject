package com.sparta;

import com.sparta.model.EmployeeDAO;
import com.sparta.model.employee.Employee;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

public class TestEmployeeDAO {

    private static final EmployeeDAO dao = new EmployeeDAO();

    @BeforeAll
    static void initDAO() {
        dao.openConnection();
        dao.dropTable();
        dao.createTable();;
    }

    @AfterAll
    static void closeDAO() {
        dao.closeConnection();
    }

    @BeforeEach
    void resetTable() {
        dao.dropTable();
        dao.createTable();
    }

    private ArrayList<Employee> getEmployeeArrayList(int id) {
        String[] employeeDetails = (id + ",Mr.,Ray,J,Fritz,M,ray.fritz@sbcglobal.net" +
                ",01/09/1972,09/06/2013,180292").split(",");
        Employee employee = new Employee();
        employee.setEmployeeDetails(employeeDetails, "dd/MM/yyyy");

        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(employee);
        return employees;
    }

    @Test
    @DisplayName("Test that the number of rows in the table can be retrieved")
    void testNumberOfRows() {
        Assertions.assertEquals(0, dao.getNumberOfRowsInTable());
    }

    @Test
    @DisplayName("Test that an employee is inserted into the table")
    void testEmployeeIsInserted() {
        dao.updateTable(getEmployeeArrayList(1111));
        Assertions.assertEquals(1, dao.getNumberOfRowsInTable());
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5, 6})
    @DisplayName("Test that multiple employees can be inserted into the table")
    void testMultipleEmployeesAreInserted(int length) {
        ArrayList<Employee> employees = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            employees.addAll(getEmployeeArrayList(i));
        }
        dao.updateTable(employees);
        Assertions.assertEquals(length, dao.getNumberOfRowsInTable());
    }

    @ParameterizedTest
    @ValueSource(ints = {1234, 12345, 123456})
    @DisplayName("Test an employee can be retrieved from the table")
    void testEmployeeIsRetrieved(int id) {
        dao.updateTable(getEmployeeArrayList(id));
        Employee employee = dao.getEmployeeByID(id);

        Assertions.assertEquals(id, employee.getId());
        Assertions.assertEquals("Ray Fritz", employee.getFirstName()
                + " " + employee.getLastName());
    }
}
