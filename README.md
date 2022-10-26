# Employee CSV Data Migration Project

**Developed by Maxwell, William , Hamzah, Cheung and Riya**

This project is developed as a team of 5, following agile methodologies, good programming practices in OOP, SOLID, design patterns, testing, and logging

The project's functionality includes being able to read, validate corrupted files and write CSV Files to the database.

**Built With**

* IntelliJ IDEA (Community Edition)
* junit-jupiter:5.8.2
* MySQL Server and WorkBench

**Dependencies**

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
## Getting Started

Run the project using IntelliJ Community Edition. 
Make sure to install the dependencies and software inlcuded.

**Installation**

Clone the repository below.
> git clone https://github.com/MRobertsSparta/EmployeesProject.git


### Program Structure
→ ValidateEmployee class which checks for corruptions in the data and adds them to a separate collection.<br>

→ DataToDatabase class which uploads all data to the database.<br>

→ UserInterface class which prints the results of reading the file.<br>

→ CSVReader class to read in the Employee.csv file.<br>

→ Logging package is created which contains the Custom File Handler and the Custom Logger.<br>
These are used for logging throughout the project.

***
## Threads Results and Analysis
**Results**


**Analysis**


***
## Use of Functional Programming





***
## Testing 

**JUnit Testing**


**Manual Testing**