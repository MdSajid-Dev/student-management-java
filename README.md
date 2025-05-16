# Student Management System (Java CLI + MySQL)

A simple command-line Student Management System application built using Java and connected to a MySQL database through JDBC.  
This application allows users to add, view, search, update, and delete student records via a clean and interactive CLI interface.

---

## Features

- Add new student records (Roll No, Name, Course)
- View all students stored in the database
- Search for a student by Roll No
- Update student details
- Delete student records
- Connects to MySQL using JDBC for persistent storage

---

## Technologies Used

- Java (JDK 8 or higher)
- MySQL Server
- JDBC (Java Database Connectivity)
- Command Line Interface (CLI)

---

## Prerequisites

- Java Development Kit (JDK) installed and configured  
- MySQL Server installed and running  
- MySQL Connector/J (JDBC driver) downloaded  

---

## Setup Instructions

### 1. Create the MySQL Database and Table

Open **phpMyAdmin** or MySQL command line and execute the following:

```sql
CREATE DATABASE student_db;

USE student_db;

CREATE TABLE students (
  roll_no INT PRIMARY KEY,
  name VARCHAR(100),
  course VARCHAR(100)
);
2. Download MySQL Connector/J
Download the latest MySQL JDBC driver from:
https://dev.mysql.com/downloads/connector/j/

Extract the .jar file (mysql-connector-j-9.3.0.jar).

3. Compile the Java Program
Open your terminal or command prompt in the project folder containing StudentManagement.java and run:

Windows:

bash
Copy
Edit
javac -cp .;mysql-connector-j-9.3.0.jar StudentManagement.java
Linux/Mac:

bash
Copy
Edit
javac -cp .:mysql-connector-j-9.3.0.jar StudentManagement.java
4. Run the Program
Windows:

bash
Copy
Edit
java -cp .;mysql-connector-j-9.3.0.jar StudentManagement
Linux/Mac:

bash
Copy
Edit
java -cp .:mysql-connector-j-9.3.0.jar StudentManagement
Usage
On running, you will see a menu like:

sql
Copy
Edit
--- Student Management System ---
1. Add Student
2. View All Students
3. Search Student
4. Update Student
5. Delete Student
6. Exit
Choose option: 
Follow the prompts to manage student records.

Notes
Replace mysql-connector-j-9.3.0.jar with your actual MySQL Connector JAR filename.

Ensure the MySQL server is running before launching the application.

The program uses the MySQL user root with no password by default. Update the USER and PASS constants in the Java file if your setup differs.

License
This project is open-source and free to use.

Author
Mohamed Sajid M
GitHub Profile
LinkedIn Profile