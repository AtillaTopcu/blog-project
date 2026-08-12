# 📝 Full-Stack Blog Application

A full-stack blog web application developed using **Spring Boot** and **React.js**.

The project was developed as part of a full-stack development training and focuses on building a layered backend architecture, RESTful APIs, authentication, database integration, and a React-based frontend.

---

## 🚀 Features

* 🔐 User authentication and authorization
* 🔑 Password encryption with Password Encoder
* 📝 Blog post management
* 👤 User management
* 🔄 RESTful API communication
* ✅ Request validation
* 🗄️ Database persistence
* 📚 API documentation with Swagger/OpenAPI
* 📱 Responsive frontend
* 🌐 React-based user interface

---

## 🛠️ Technologies

### Backend

* Java
* Spring Boot
* Spring Framework
* Spring Data JPA
* Hibernate
* Spring Security
* Password Encoder
* Lombok
* Model Mapper
* Spring Validation
* Swagger / OpenAPI

### Frontend

* React.js
* JavaScript
* HTML5
* CSS3
* Bootstrap 5
* Axios

### Database

* PostgreSQL
* MySQL
* H2 Database

---

## 🏗️ Architecture

The backend follows a layered architecture to separate application responsibilities.

```text
┌──────────────────────────┐
│       React.js           │
│        Frontend          │
└────────────┬─────────────┘
             │
             │ REST API
             ▼
┌──────────────────────────┐
│      Spring Boot         │
│         Backend          │
├──────────────────────────┤
│      Controller          │
│          ↓               │
│       Service            │
│          ↓               │
│      Repository          │
│          ↓               │
│        Database          │
└──────────────────────────┘
```

DTOs are used between the API and business layers to separate external request/response models from database entities.

---

## 🔐 Authentication & Security

The application uses **Spring Security** for authentication and authorization.

Passwords are securely encoded using **Password Encoder** before being stored in the database.

Protected API endpoints require authenticated users.

---

## 📚 REST API & Swagger

The backend exposes RESTful APIs for application operations.

API documentation is provided using **OpenAPI / Swagger**, allowing available endpoints to be viewed and tested directly through the Swagger interface.

---

## 🗄️ Database Support

The application supports multiple relational databases:

* PostgreSQL
* MySQL
* H2 Database

Spring Data JPA and Hibernate are used for database interaction and object-relational mapping.

---

## 📂 Project Structure

```text
blog/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── ...
│       │
│       └── resources/
│
├── frontend/
│   ├── src/
│   ├── public/
│   └── package.json
│
├── pom.xml
└── README.md
```

---

## ⚙️ Installation

### 1. Clone the repository

```bash
git clone YOUR_REPOSITORY_URL
cd blog
```

### 2. Backend

Make sure Java and Maven are installed.

Configure the database connection in:

```text
src/main/resources/application.properties
```

or:

```text
src/main/resources/application.yml
```

Then run the application:

```bash
mvn spring-boot:run
```

### 3. Frontend

Navigate to the React application:

```bash
cd frontend
```

Install dependencies:

```bash
npm install
```

Start the development server:

```bash
npm start
```

or, if using Vite:

```bash
npm run dev
```

---

## 🎯 Purpose

This project was developed to gain practical experience with:

* Spring Boot application development
* RESTful API design
* Layered architecture
* Spring Security
* Database management with JPA/Hibernate
* React.js frontend development
* Client-server communication
* API documentation with Swagger/OpenAPI
