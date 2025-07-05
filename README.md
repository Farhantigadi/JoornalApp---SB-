# 📒 Journal Management Application

A secure, scalable backend for managing personal journals with role-based access control, built using **Java**, **Spring Boot**, **JWT Authentication**, and integrated with **SonarCloud** for code quality.

---

## 📄 Overview

This application allows authenticated users to create, view, update, and delete personal journal entries. Administrators have extended privileges to manage users and view all entries. Authentication is handled with JWT tokens, and Spring Security enforces strict access control.

---

## 🔧 Tech Stack

- 💻 Java 17  
- 🛠️ Spring Boot 3  
- 🔐 Spring Security with JWT  
- 🗄️ JPA (Hibernate)  
- 🧮 MySQL  
- 📝 Swagger (OpenAPI)  
- ✅ JUnit & Mockito  
- 📊 SonarCloud  
- 🔍 Postman  

---

## 🔐 Key Features

### 👥 Public APIs

| Endpoint              | Method | Description                 |
|----------------------|--------|-----------------------------|
| `/public/signup`     | POST   | Register new user           |
| `/public/login`      | POST   | Authenticate & get JWT token |

### 👤 User APIs

| Endpoint                     | Method  | Description                        |
|-----------------------------|---------|------------------------------------|
| `/user/{id}`                | GET     | Get user details by ID             |
| `/user/{id}`                | DELETE  | Delete user by ID                  |
| `/user/delete`             | DELETE  | Delete the logged-in user          |
| `/user/allUsers`           | DELETE  | Delete all users                   |
| `/user/update`             | PUT     | Update logged-in user              |

### 📓 Journal APIs

| Endpoint            | Method | Description                       |
|---------------------|--------|-----------------------------------|
| `/journal`          | GET    | Get logged-in user's journal entries |
| `/journal`          | POST   | Create new journal entry          |
| `PUT /journal/{id}` | PUT    | *Not yet implemented*             |
| `DELETE /journal/{id}` | DELETE | *Not yet implemented*           |

### 🛡️ Admin APIs

| Endpoint                      | Method | Description                          |
|------------------------------|--------|--------------------------------------|
| `/admin/greet`               | GET    | Check authenticated admin identity   |
| `/admin/create-admin-user`   | POST   | Create new admin (admin-only)        |
| `/admin/allUsers`            | GET    | View all registered users            |




---

## 📊 Code Quality

- 📈 Integrated with **SonarCloud** for static analysis
- ✅ Clean code, low complexity
- 🧼 Maintained high test coverage standards

---

## 📚Access Swagger
http://localhost:8080/swagger-ui.html
---
 


## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/journal-app.git
cd journal-app

spring.datasource.username=your_name
spring.datasource.password=your_password




