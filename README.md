# Business Tool Manager

[![Spring Boot Version](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java Version](https://img.shields.io/badge/Java-17-blue.svg)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

**Business Tool Manager** is an enterprise-grade full-stack web application developed with **Spring Boot 3**, **Spring Security 6**, **Spring Data JPA**, **MySQL**, and **Thymeleaf**. It provides businesses with a centralized platform to manage internal software tools, employee directories, corporate client relationships, and project tracking with budget governance.

---

## 🚀 Key Features

- **🔐 Robust Authentication & Role-Based Access Control (RBAC)**
  - Powered by Spring Security 6 with BCrypt password hashing.
  - Granular role management supporting `ADMIN`, `MANAGER`, and `USER` roles.
  - Secure session handling, CSRF protection, and role-restricted actions.

- **🛠️ Business Tool Catalog**
  - Register, categorize, and track operational tools and software subscriptions.
  - Monitor subscription costs, tool ownership (`offered_by`), and status (`ACTIVE`, `INACTIVE`, `MAINTENANCE`).

- **👥 Employee Directory & Staff Management**
  - Complete employee profiles with department, position, contact details, salary, and employment status.
  - Track employment lifecycle (`ACTIVE`, `ON_LEAVE`, `TERMINATED`).

- **🏢 Client Relationship Management (CRM)**
  - Maintain corporate client databases, primary contact details, address records, and industry domains.
  - Track client status (`ACTIVE`, `INACTIVE`, `PROSPECT`).

- **📊 Project Lifecycle & Budget Tracking**
  - Manage client projects from planning through completion.
  - Prioritization tiers (`LOW`, `MEDIUM`, `HIGH`, `CRITICAL`), milestone timelines, and financial budget allocation.

- **🎨 Modern Responsive Interface**
  - Modular server-rendered UI utilizing **Thymeleaf** with **Thymeleaf Layout Dialect**.
  - Dynamic UI rendering based on authenticated user privileges via `thymeleaf-extras-springsecurity6`.

---

## 🛠️ Tech Stack

| Component | Technology | Version |
| :--- | :--- | :--- |
| **Language** | Java (JDK) | 17 |
| **Framework** | Spring Boot | 3.2.0 |
| **Security** | Spring Security | 6.x |
| **Persistence** | Spring Data JPA / Hibernate | 3.2.x |
| **Database** | MySQL Server | 8.0+ |
| **View Template** | Thymeleaf & Layout Dialect | 3.x |
| **Code Efficiency** | Project Lombok | 1.18.x |
| **Build Tool** | Apache Maven | 3.8+ |

---

## 📋 Prerequisites

Before running the application, make sure the following are installed and configured:

1. **Java Development Kit (JDK) 17+**
   ```bash
   java -version
   ```
2. **Apache Maven 3.8+**
   ```bash
   mvn -version
   ```
3. **MySQL Server 8.0+** running locally on port `3306`.

---

## ⚙️ Setup and Installation

### 1. Clone the Repository
```bash
git clone https://github.com/your-org/business-manager.git
cd business-manager
```

### 2. Configure Database
Create the MySQL database and populate initial tables and seed data using the provided `schema.sql`:

```bash
mysql -u root -p < src/main/resources/schema.sql
```

Alternatively, you can run the script inside MySQL Workbench, DBeaver, or the MySQL CLI:
```sql
CREATE DATABASE IF NOT EXISTS business_manager_db;
```

### 3. Configure Database Credentials
Edit [`src/main/resources/application.properties`](src/main/resources/application.properties) with your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/business_manager_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=your_mysql_password
```

### 4. Build and Run Application
Build the project and start the Spring Boot application using Maven:

```bash
mvn clean install
mvn spring-boot:run
```

Once started, the application will be accessible at:
```
http://localhost:8080
```

---

## 🔑 Default Login Credentials

Initial accounts are pre-seeded in `schema.sql`:

| Username | Password | Role | Permissions |
| :--- | :--- | :--- | :--- |
| `admin` | `admin123` | **ADMIN** | Full administrative system access across all modules |
| `manager` | `admin123` | **MANAGER** | Can manage tools, employees, clients, and projects |
| `johndoe` | `admin123` | **USER** | Read-only / tool discovery and profile access |

*(Note: Passwords are encrypted in the database using BCrypt hashing.)*

---

## 📁 Project Structure

```
d:\business-manager\
├── pom.xml                                      # Maven dependencies and build definition
├── README.md                                    # Project documentation
├── .gitignore                                   # Git ignore rules
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/
    │   │       └── businessmanager/
    │   │           └── BusinessManagerApplication.java  # Main Application Entrypoint
    │   └── resources/
    │       ├── application.properties           # Application configuration
    │       ├── schema.sql                       # Database schema and seed data
    │       ├── static/                          # Static assets (CSS, JS, Images)
    │       └── templates/                       # Thymeleaf templates & layouts
    └── test/
        └── java/                                # Automated tests
```

---

## 🌐 Application Endpoints

| Endpoint | HTTP Method | Accessible To | Description |
| :--- | :--- | :--- | :--- |
| `/login` | `GET` | Public | User authentication login page |
| `/logout` | `POST` | Authenticated | Terminate session and logout |
| `/` or `/dashboard` | `GET` | Authenticated | Main administrative dashboard & KPIs |
| `/tools` | `GET` | Authenticated | Browse business tools catalog |
| `/tools/create` | `GET`, `POST` | `ADMIN`, `MANAGER` | Add a new business tool |
| `/tools/edit/{id}` | `GET`, `POST` | `ADMIN`, `MANAGER` | Update existing tool |
| `/tools/delete/{id}`| `POST` | `ADMIN` | Remove tool from catalog |
| `/employees` | `GET` | `ADMIN`, `MANAGER` | View employee directory |
| `/employees/create` | `GET`, `POST` | `ADMIN` | Register new employee |
| `/clients` | `GET` | `ADMIN`, `MANAGER` | Corporate clients listing |
| `/clients/create` | `GET`, `POST` | `ADMIN`, `MANAGER` | Onboard new client |
| `/projects` | `GET` | Authenticated | View active client projects |
| `/projects/create` | `GET`, `POST` | `ADMIN`, `MANAGER` | Create new client project |

---

## 📸 Screenshots

*(Screenshots will be included upon UI asset generation)*

| Dashboard View | Tools Catalog |
|:---:|:---:|
| *Placeholder: `docs/screenshots/dashboard.png`* | *Placeholder: `docs/screenshots/tools.png`* |

| Employee Directory | Project Tracker |
|:---:|:---:|
| *Placeholder: `docs/screenshots/employees.png`* | *Placeholder: `docs/screenshots/projects.png`* |

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).
