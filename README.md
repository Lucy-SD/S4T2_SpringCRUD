# 🍃 Spring Boot REST API - Fruit Management System

**👨‍💻 Author:** Lucy Castro

**🧠 Learning Focus:** Spring Boot, REST APIs, Layered Architecture, TDD, Docker

**🛠️ IDE:** IntelliJ IDEA

**☕ Java SDK:** 21

### 📦 Build Tool: Maven


## 📝 Description

In this task, you will develop three independent Spring Boot applications, each with a REST API that implements complete CRUD operations (Create, Read, Update, Delete) on different entities. You will work with three different databases: H2, MySQL, and MongoDB.

Through these practices you will learn to:

 - Create REST APIs using Spring Boot

- Manage data persistence with Spring Data JPA and Spring Data MongoDB

- Properly apply HTTP verbs and manage status codes

- Implement dynamic routes with Path Params and Query Params

- Write and execute automated tests using TDD

- Manage exceptions globally with GlobalExceptionHandler

- Structure the project following the MVC pattern

- Create relationships between entities using JPA

- Introduce the use of DTOs and validate input data

- Create a Dockerfile to package the application

## 💻 Technologies Used
- ☕ Java 21

- 🌱 Spring Boot

- 🧪 JUnit 5 & Mockito

- 📭 MockMvc

- 🗃️ Maven

- 🚀 Apache Tomcat (Embedded)

- 📡 RESTful Web Services

## 🎯 Exercise Levels
### ⭐ Level 1 — CRUD with H2
REST API to manage fruit stock with H2 database

📋 User Stories
Register a new fruit

Acceptance Criteria: HTTP 201 Created with valid data, HTTP 400 Bad Request with invalid data

Consult all fruits

Acceptance Criteria: HTTP 200 OK with JSON array, empty array if no fruits

Consult a specific fruit

Acceptance Criteria: HTTP 200 OK if exists, HTTP 404 Not Found if doesn't exist

Modify an existing fruit

Acceptance Criteria: HTTP 200 OK with valid data, HTTP 404/400 on errors

Delete a fruit

Acceptance Criteria: HTTP 204 No Content if exists, HTTP 404 Not Found if doesn't exist

### ⭐⭐ Level 2 — MySQL Refactor
Migration from H2 database to MySQL

🔄 Main Changes
MySQL connection configuration in application.properties

MySQL Driver dependency

Permanent data persistence

Environment variables configuration

📦 Additional Dependencies
MySQL Driver

### ⭐⭐⭐ Level 3 — MongoDB Refactor
Migration to NoSQL database with MongoDB

🔄 Main Changes
MongoDB configuration in application.properties

Spring Data MongoDB dependency

Change from JPA annotations to MongoDB

Repository pattern with MongoRepository

## 🧪 Testing Strategy
TDD Approach: Test-driven development

@SpringBootTest with MockMvc for REST endpoints

Mockito for service unit tests

Integration Tests for complete application testing

## 🐳 Docker
Dockerfile with multi-stage build

Build stage: Compilation and JAR generation

Final stage: Lightweight image for production

## 🚀 Deployment
Configured port: server.port=9000

Executable JAR with Maven

Embedded Tomcat

Configuration through environment variables

## 📚 Key Concepts Covered
✅ REST principles and API implementation

✅ Controllers with @RestController

✅ HTTP methods (GET, POST, PUT, DELETE)

✅ URL parameters with @PathVariable and @RequestParam

✅ JSON data handling with @RequestBody

✅ Automated testing with MockMvc and @SpringBootTest

✅ Dependency Injection and IoC containers

✅ Layered architecture (Controller-Service-Repository)

✅ Build and deploy executable JAR

✅ Global exception handling

✅ Data validation with Bean Validation

✅ DTO pattern

✅ Multiple database configurations

✅ Dockerization of Spring Boot applications

## 🤝 Contributions
### ⭐ Star the repository
### 🍴 Fork the project
### 📥 Create a pull request

## 🌐 Deployment
For educational purposes only.

## 🚀 Thanks for Visiting! = )
