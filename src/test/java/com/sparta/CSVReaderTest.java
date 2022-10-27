package com.sparta;

import com.sparta.model.CSVReader;
import com.sparta.model.employee.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CSVReaderTest {

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    static ArrayList<Employee> employeeList = new ArrayList<>();

    @BeforeAll
    public static void setup() {
        employeeList = CSVReader.readFile("resources/EmployeeRecords.csv");
    }

    @Test
    @DisplayName("Test that the first row of the CSV has been populated into the ArrayList correctly")
    public void testFirstRowOfCSV() {
        String[] firstRow = {"198429","Mrs.", "Serafina", "I", "Bumgarner","F","serafina.bumgarner@exxonmobil.com", "21/09/1982", "01/02/2008", "69294"};
        Assertions.assertEquals(firstRow[0], employeeList.get(0).getId()+"");
        Assertions.assertEquals(firstRow[1], employeeList.get(0).getTitle());
        Assertions.assertEquals(firstRow[2], employeeList.get(0).getFirstName());
        Assertions.assertEquals(firstRow[3], employeeList.get(0).getMiddleInitial());
        Assertions.assertEquals(firstRow[4], employeeList.get(0).getLastName());
        Assertions.assertEquals(firstRow[5], Character.toString(employeeList.get(0).getGender()));
        Assertions.assertEquals(firstRow[6], employeeList.get(0).getEmail());
        Assertions.assertEquals(firstRow[7], dateFormat.format(employeeList.get(0).getDateOfBirth()));
        Assertions.assertEquals(firstRow[8], dateFormat.format(employeeList.get(0).getDateOfJoining()));
        Assertions.assertEquals(firstRow[9], employeeList.get(0).getSalary()+"");
    }

    @Test
    @DisplayName("Test that the last row of the CSV has been populated into the ArrayList correctly")
    public void testLastRowOfCSV() {
        String[] firstRow = {"133641","Mr.", "Chas", "F", "Hurdle","M","chas.hurdle@gmail.com", "20/04/1995", "28/05/2016", "45102"};
        Assertions.assertEquals(firstRow[0], employeeList.get(9999).getId()+"");
        Assertions.assertEquals(firstRow[1], employeeList.get(9999).getTitle());
        Assertions.assertEquals(firstRow[2], employeeList.get(9999).getFirstName());
        Assertions.assertEquals(firstRow[3], employeeList.get(9999).getMiddleInitial());
        Assertions.assertEquals(firstRow[4], employeeList.get(9999).getLastName());
        Assertions.assertEquals(firstRow[5], Character.toString(employeeList.get(9999).getGender()));
        Assertions.assertEquals(firstRow[6], employeeList.get(9999).getEmail());
        Assertions.assertEquals(firstRow[7], dateFormat.format(employeeList.get(9999).getDateOfBirth()));
        Assertions.assertEquals(firstRow[8], dateFormat.format(employeeList.get(9999).getDateOfJoining()));
        Assertions.assertEquals(firstRow[9], employeeList.get(9999).getSalary()+"");
    }

    @Test
    @DisplayName("Test that row with no middle initial of the CSV has been populated into the ArrayList correctly")
    public void testNoMiddleInitalRowOfCSV() {
        String[] firstRow = {"444753","Ms.", "Judith", "", "Owen","F","judith.owen@gmail.com", "14/02/1958", "17/05/1982", "76503"};
        Assertions.assertEquals(firstRow[0], employeeList.get(38).getId()+"");
        Assertions.assertEquals(firstRow[1], employeeList.get(38).getTitle());
        Assertions.assertEquals(firstRow[2], employeeList.get(38).getFirstName());
        Assertions.assertEquals(firstRow[3], employeeList.get(38).getMiddleInitial());
        Assertions.assertEquals(firstRow[4], employeeList.get(38).getLastName());
        Assertions.assertEquals(firstRow[5], Character.toString(employeeList.get(38).getGender()));
        Assertions.assertEquals(firstRow[6], employeeList.get(38).getEmail());
        Assertions.assertEquals(firstRow[7], dateFormat.format(employeeList.get(38).getDateOfBirth()));
        Assertions.assertEquals(firstRow[8], dateFormat.format(employeeList.get(38).getDateOfJoining()));
        Assertions.assertEquals(firstRow[9], employeeList.get(38).getSalary()+"");
    }

}
