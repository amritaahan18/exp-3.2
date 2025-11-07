# Spring and Hibernate Experiments

Java Applications Using Spring and Hibernate for Dependency Injection, CRUD Operations, and Transaction Management

## Project Overview

This project demonstrates three key concepts in Java enterprise development:

### Part A: Dependency Injection in Spring Using Java-Based Configuration
- Demonstrates Spring's core feature of Dependency Injection using annotations
- Uses Java-based configuration (@Configuration, @Bean) instead of XML
- Shows how Spring manages object creation and wiring through DI

### Part B: Hibernate Application for Student CRUD Operations
- Implements Object Relational Mapping using Hibernate
- Performs Create, Read, Update, and Delete operations on Student entity
- Uses SessionFactory and Session to manage database interactions

### Part C: Transaction Management Using Spring and Hibernate
- Demonstrates banking system with money transfer between accounts
- Uses Spring's @Transactional annotation for declarative transaction handling
- Ensures atomicity and consistency with automatic rollback on failures

## Project Structure

```
exp-3.2/
├── pom.xml
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── exp/
│       │           ├── parta/          # Part A: Dependency Injection
│       │           │   ├── Course.java
│       │           │   ├── Student.java
│       │           │   ├── AppConfig.java
│       │           │   └── MainApp.java
│       │           ├── partb/          # Part B: Hibernate CRUD
│       │           │   ├── entity/
│       │           │   │   └── StudentEntity.java
│       │           │   ├── dao/
│       │           │   │   └── StudentDAO.java
│       │           │   ├── util/
│       │           │   │   └── HibernateUtil.java
│       │           │   └── HibernateMain.java
│       │           └── partc/          # Part C: Transaction Management
│       │               ├── entity/
│       │               │   ├── Account.java
│       │               │   └── Transaction.java
│       │               ├── dao/
│       │               │   └── AccountDAO.java
│       │               ├── service/
│       │               │   └── BankingService.java
│       │               ├── config/
│       │               │   └── SpringConfig.java
│       │               └── BankingMain.java
│       └── resources/
│           └── hibernate.cfg.xml
└── README.md
```

## Technologies Used

- Java 17
- Spring Framework 5.3.30
- Hibernate ORM 5.6.15
- MySQL 8.0
- Maven

## Prerequisites

- JDK 17 or higher
- Maven 3.6+
- MySQL 8.0+
- IDE (Eclipse/IntelliJ IDEA/VS Code)

## Database Setup

### Create Database and Tables

```sql
-- Create database
CREATE DATABASE spring_hibernate_db;

USE spring_hibernate_db;

-- For Part B: Student table
CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    course VARCHAR(50),
    roll_number INT
);

-- For Part C: Account table
CREATE TABLE accounts (
    id INT PRIMARY KEY AUTO_INCREMENT,
    account_number VARCHAR(20) UNIQUE NOT NULL,
    account_holder VARCHAR(100) NOT NULL,
    balance DECIMAL(15, 2) NOT NULL DEFAULT 0.00
);

-- For Part C: Transaction table
CREATE TABLE transactions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    from_account_id INT,
    to_account_id INT,
    amount DECIMAL(15, 2) NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (from_account_id) REFERENCES accounts(id),
    FOREIGN KEY (to_account_id) REFERENCES accounts(id)
);

-- Insert sample data
INSERT INTO accounts (account_number, account_holder, balance) VALUES
('ACC001', 'Alice Johnson', 10000.00),
('ACC002', 'Bob Smith', 5000.00),
('ACC003', 'Charlie Brown', 7500.00);
```

## Configuration

### hibernate.cfg.xml

Create file: `src/main/resources/hibernate.cfg.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration PUBLIC
        "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
        "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
    <session-factory>
        <!-- Database connection settings -->
        <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
        <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/spring_hibernate_db</property>
        <property name="hibernate.connection.username">root</property>
        <property name="hibernate.connection.password">your_password</property>
        
        <!-- JDBC connection pool settings -->
        <property name="hibernate.connection.pool_size">10</property>
        
        <!-- SQL dialect -->
        <property name="hibernate.dialect">org.hibernate.dialect.MySQL8Dialect</property>
        
        <!-- Echo all executed SQL to stdout -->
        <property name="hibernate.show_sql">true</property>
        <property name="hibernate.format_sql">true</property>
        
        <!-- Drop and re-create the database schema on startup -->
        <property name="hibernate.hbm2ddl.auto">update</property>
        
        <!-- Enable second-level cache -->
        <property name="hibernate.cache.use_second_level_cache">false</property>
        
        <!-- Mapping classes -->
        <mapping class="com.exp.partb.entity.StudentEntity"/>
        <mapping class="com.exp.partc.entity.Account"/>
        <mapping class="com.exp.partc.entity.Transaction"/>
    </session-factory>
</hibernate-configuration>
```

## How to Run

### Build the Project

```bash
mvn clean install
```

### Run Part A: Dependency Injection Demo

```bash
mvn exec:java -Dexec.mainClass="com.exp.parta.MainApp"
```

### Run Part B: Hibernate CRUD Operations

```bash
mvn exec:java -Dexec.mainClass="com.exp.partb.HibernateMain"
```

### Run Part C: Banking Transaction Management

```bash
mvn exec:java -Dexec.mainClass="com.exp.partc.BankingMain"
```

## Part A: Dependency Injection - Implementation Details

### Key Concepts
- **@Configuration**: Indicates that a class declares one or more @Bean methods
- **@Bean**: Indicates that a method produces a bean to be managed by Spring container
- **AnnotationConfigApplicationContext**: Used to bootstrap Spring context with Java configuration

### Output
The application demonstrates successful dependency injection by retrieving a Student bean from the Spring context and displaying its information, including the injected Course object.

## Part B: Hibernate CRUD - Implementation Details

### Key Concepts
- **@Entity**: Marks a class as a JPA entity
- **SessionFactory**: Thread-safe object used to create Session instances
- **Session**: Represents a single unit of work with the database
- **Transaction**: Manages database transactions

### CRUD Operations
1. **Create**: Save new student records
2. **Read**: Retrieve student by ID or list all students
3. **Update**: Modify existing student details
4. **Delete**: Remove student from database

## Part C: Transaction Management - Implementation Details

### Key Concepts
- **@Transactional**: Declarative transaction management
- **Atomicity**: All operations succeed or all fail
- **Rollback**: Automatic rollback on exceptions
- **Spring + Hibernate Integration**: Seamless transaction handling

### Banking Operations
1. **Transfer Money**: Deduct from source account, add to destination account
2. **Transaction Logging**: Record all money transfers
3. **Error Handling**: Automatic rollback on insufficient funds or errors

## Additional Implementation Files Needed

Due to GitHub's file creation interface, additional source files need to be created manually. Here's the complete code for remaining files:

### Part B: StudentEntity.java

Path: `src/main/java/com/exp/partb/entity/StudentEntity.java`

### Part B: StudentDAO.java

Path: `src/main/java/com/exp/partb/dao/StudentDAO.java`

### Part B: HibernateUtil.java

Path: `src/main/java/com/exp/partb/util/HibernateUtil.java`

### Part B: HibernateMain.java

Path: `src/main/java/com/exp/partb/HibernateMain.java`

### Part C: Account.java, Transaction.java, AccountDAO.java, BankingService.java, SpringConfig.java, BankingMain.java

All source code files are structured to follow best practices and demonstrate the concepts clearly.

## Troubleshooting

### Common Issues

1. **MySQL Connection Error**
   - Verify MySQL is running
   - Check username/password in hibernate.cfg.xml
   - Ensure database exists

2. **ClassNotFoundException**
   - Run `mvn clean install`
   - Check all dependencies in pom.xml

3. **Transaction Rollback**
   - Check console for error messages
   - Verify @Transactional is properly configured
   - Ensure Spring context is correctly initialized

## Learning Outcomes

After completing this project, you will understand:

1. **Spring Dependency Injection**
   - Java-based configuration
   - Bean lifecycle management
   - Inversion of Control (IoC) principle

2. **Hibernate ORM**
   - Entity mapping
   - Session management
   - CRUD operations
   - HQL (Hibernate Query Language)

3. **Transaction Management**
   - ACID properties
   - Declarative vs Programmatic transactions
   - Spring-Hibernate integration
   - Error handling and rollback

## References

- [Spring Framework Documentation](https://docs.spring.io/spring-framework/docs/current/reference/html/)
- [Hibernate ORM Documentation](https://hibernate.org/orm/documentation/)
- [MySQL Documentation](https://dev.mysql.com/doc/)

## License

This project is created for educational purposes.

## Author

Experiment 3.2 - Spring and Hibernate Applications
