# Employee CSV Data Migration Project
![java_badge](https://img.shields.io/badge/-Java-lightgrey?style=for-the-badge&logo=appveyor)
![maven_badge](https://img.shields.io/badge/-Maven-yellow?style=for-the-badge&logo=appveyor)
![sql_badge](https://img.shields.io/badge/-SQL-blue?style=for-the-badge&logo=appveyor)
![databases](https://img.shields.io/badge/-Databases-red?style=for-the-badge&logo=appveyor)
![threads](https://img.shields.io/badge/-Multithreading-brightgreen?style=for-the-badge&logo=appveyor)
![performance](https://img.shields.io/badge/-Performance-orange?style=for-the-badge&logo=appveyor)

**Developed by Maxwell, William , Hamzah, Cheung and Riya**

### **Table Of Contents**
* [**About Project**](#about-project)
    - [Built With](#built-with)
    - [Dependencies](#dependencies)
* [**Requirements**](#requirements)
* [**Getting Started and Program Overview**](#getting-started-and-program-overview)
    - [Installation](#installation)
    - [Program Structure](#program-structure)
* [**Thread Results and Analysis**](#threads-results-and-analysis)
    - [Results](#results)
    - [Analysis](#analysis)
* [**Use of Functional Programming**](#use-of-functional-programming)
* [**Testing**](#testing)
    - [JUnit Testing](#junit-testing)
    - [Manual Testing](#manual-testing)
* [**Future Development**](#future-development)

## About Project

This project is developed as a team of 5, following agile methodologies, good programming practices in OOP, SOLID, design patterns, testing, and logging

The project's functionality includes being able to read, validate corrupted files and write CSV Files to the database.

### Built With

* IntelliJ IDEA (Community Edition)
* junit-jupiter:5.8.2
* MySQL Server and WorkBench

### Dependencies

* junit.jupiter Version 5.9.0
* mysql 8.0.30

***
## Requirements

* Read data from a Employee.csv file, parse it, populate objects and add to a collection.
* Efficiently write the data from the objects to a relational database using JDBC.
* Corrupt or duplicated data should be added to a separate collections.
* Provide a simple user interface to display the results of reading the file.
* Add multithreading to the application for writing the data to the database, comparing the execution time with the single-threaded version.

***
## Getting Started and Program Overview

Run the project using IntelliJ Community Edition.
Make sure to install the dependencies and software included.

### Installation

Clone the repository below.
> git clone https://github.com/MRobertsSparta/EmployeesProject.git


### Program Structure

**Model**

→ Employee package which contains the Employee class which acts as DTO by encapsulating the data and reduce the number of calls to the database and
EmployeeRecords class which gets an ArrayList of clean, invalid and duplicate records<br>

→ EmployeeValidator class which checks for corruptions by using regular expressions to check for invalid records and HashMaps to check for duplicate records in the data,
and adds them to a separate collections.<br>

→ EmployeeDAO class which provides CRUD operations such as dropping, creating, and updating tables, as well as selecting records from database by ID
without exposing details about the database<br>

→ Queries interface which stores all the sql queries that can be used by the EmployeeDAO class<br>

→ CSVReader class to read in the Employee.csv file by using both FileReader and BufferedReader as well as formatting the date.<br>

**View**

→ UserInterface class which prints the results of reading the in the CSV file, by printing out no. of clean, corrupted, duplicate and invalid records.
Also there is a method that prints out an individual record from database given person ID.<br>

**Controller**

→ Controller class that acts as a middle man requesting and responding to changes made between the database and user interface
It is also where the multithreading took place which impacted a lot on the time it took to do each task and produce a result.<br>

→ DAOThread class which is where the thread was initialized and the run method was implemented.<br>

**Utilities**

→ Logging package is created which contains the Custom File Handler and the Custom Logger.<br>
These are used for logging throughout the project.

→ StopWatch class which includes a start and stop method that start and stops the timer.<br>

## Threads Results and Analysis
### Results

Below are the results produced when trying out multiple threads in increasing the performance of the program.

![threading performance](https://github.com/MRobertsSparta/EmployeesProject/blob/dev/programscreenshots/threadperformance.png)

### Analysis

We coded a simple algorithm that will run the program 10 times for each thread.<br>
This smaller program then increases the number of threads until it reached 64, so we could determine our most efficient number for our project.<br>
We found that our fastest number of threads was 16, which gave us our best time so far of 1.06 seconds.<br>

***
## Use of Functional Programming
We tried using functional programming to increase the efficiency of our project, but this only made it slower.<br>
We think this was due to streams not being efficient with large amounts of data.<br>

***
## Testing

### JUnit Testing

JUnit testing allowed us to test the classes in the model of the project such as using parameterized tests to test for multiple values without having to do any long manual testing.<br>

JUnit testing allowed us to maintain our program to ensure that it works without any error.<br>

Errors can be found by asserting results and comparing it to the actual result.<br>

Looking at our test coverage, the average % of code coverage is low due to code being unsuitable for unit testing.<br>
The classes which use it the most are within the model package of our project which have good testing coverage.<br>

### Manual Testing

The parts of the program which were not suitable for unit testing were tested manually.<br>

***
## Future Development

For future improvements we would like to implement a user-friendly UI to view records in the database.<br>
Also implementing more operations the user could do to the database such as deleting, adding a new record etc.<br>


