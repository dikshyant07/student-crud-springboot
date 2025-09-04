# Student CRUD Application

This is a simple CRUD application built with Spring Boot.  
It follows a proper layered architecture (Controller → Service → Repository → Entity → DTO) and includes input validations along with global exception handling for cleaner and more maintainable code.  

> Note: This project does not include Spring Security.

---

## Requirements
To run this project, you’ll need:

- Java 17 or higher  
- Maven 3.8+  
- MySQL 8+  
- Git  

---

## Database Setup
1. Open MySQL and create a database called **jpa**:
   ```sql
   CREATE DATABASE jpa;
Inside src/main/resources/, create a new file named application-yourName.yml and add the following configuration
(replace your_mysql_username and your_mysql_password with your actual credentials):
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/jpa
    username: your_mysql_username
    password: your_mysql_password
    driver-class-name: com.mysql.cj.jdbc.Driver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL8Dialect

In the main application.yml, set the active profile to the one you just created:

spring:
  profiles:
    active: yourName
