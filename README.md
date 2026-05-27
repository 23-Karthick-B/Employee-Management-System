# Employee Management System (EMS)

A full stack Employee Management System built to simplify employee administration, authentication, leave handling, and payroll management.

The application provides separate dashboards and permissions for Admins and Employees with secure JWT based authentication and a clean responsive UI.

---

# ✨ Highlights

- JWT Authentication & Authorization
- Admin & Employee Roles
- Payslip PDF Generation
-  Leave Application & Approval Workflow
-  Spring Security + BCrypt Encryption
-  Responsive Tailwind UI
-  RESTful APIs using Spring Boot
-  Employee CRUD Management
-  Role-Based Access Control

---

# 🛠️ Tech Stack

## Backend

| Technology | Purpose |
|---|---|
| Java 17 | Core Backend Language |
| Spring Boot | Backend Framework |
| Spring Security | Authentication & Authorization |
| JWT | Secure Token-Based Authentication |
| Spring Data JPA | Database Access |
| Hibernate | ORM |
| MySQL | Database |

---

## Frontend

| Technology | Purpose |
|---|---|
| HTML5 | Structure |
| Tailwind CSS | Styling |
| JavaScript | Client-Side Logic |

---

# 📂 Project Structure

```bash
EMS/
│
├── backend/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   ├── dto/
│   ├── security/
│   ├── config/
│   └── exception/
│
├── frontend/
│   ├── html/
│   ├── js/
|
└── README.md
```

---

# 🔐 Authentication System

The application uses JWT (JSON Web Tokens) for secure authentication.

After successful login:

- A JWT token is generated
- The token is stored in localStorage
- Protected APIs require the token in the Authorization header

Example:

```http
Authorization: Bearer <token>
```

---

# 👨‍💼 Admin Features

Admins have complete access to employee management operations.

### Admin Capabilities

- Add Employees
- View Employee List
- Search Employees
- Update Employee Details
- Soft Delete Employees
- View Leave Requests
- Approve / Reject Leaves
- Manage Employee Information

---

## Admin Login 

```json
{
  "username": "admin",
  "password": "admin123"
}
```

---

# 👨‍💻 Employee Features

Employees can manage their own profile and requests.

### Employee Capabilities

- Secure Login
- View Dashboard
- View Personal Profile
- Update Profile
- Change Password
- Apply Leave
- Download Payslip PDF

---

## Employee Login

```json
{
  "email": "employee@gmail.com",
  "password": "password"
}
```

---

# 🛡️ Role-Based Access Control

| Feature | Admin | Employee |
|---|---|---|
| Add Employee | ✅ | ❌ |
| Update Employee | ✅ | ❌ |
| Delete Employee | ✅ | ❌ |
| View Employees | ✅ | ❌ |
| View Own Profile | ❌ | ✅ |
| Update Own Profile | ❌ | ✅ |
| Apply Leave | ❌ | ✅ |
| Approve Leave | ✅ | ❌ |
| Download Payslip | ❌ | ✅ |

---

# 🗄️ Database Design

## Employees Table

| Column | Type |
|---|---|
| id | Integer |
| name | String |
| email | String |
| phoneNumber | String |
| department | String |
| salary | Double |
| password | String |
| isActive | Boolean |
| createdAt | LocalDateTime |
| updatedAt | LocalDateTime |

---

## Leave Requests Table

| Column | Type |
|---|---|
| id | Integer |
| employeeId | Integer |
| leaveDate | LocalDate |
| reason | String |
| status | String |
| appliedAt | LocalDateTime |

---

# 📡 API Endpoints

# 🔑 Authentication APIs

## Admin Login

```http
POST /auth/login
```

## Employee Login

```http
POST /employee/auth/login
```

---

# 👨‍💼 Admin APIs

## Get Employees

```http
GET /api/ems/employees
```

## Add Employee

```http
POST /api/ems/employees
```

## Update Employee

```http
PATCH /api/ems/employees/{id}
```

## Delete Employee

```http
DELETE /api/ems/employees/{id}
```

---

# 👨‍💻 Employee APIs

## Get Profile

```http
GET /employee/me
```

## Update Profile

```http
PATCH /employee/me
```

## Apply Leave

```http
POST /employee/leave
```

## Download Payslip

```http
GET /employee/payslip
```

---

# 📝 Leave Management Workflow

## Employee Side

1. Select leave dates
2. Enter leave reason
3. Submit request
4. Request status becomes:

```text
PENDING
```

---

## Admin Side

Admins can:

- View all leave requests
- Approve leave requests
- Reject leave requests

Possible statuses:

```text
PENDING
APPROVED
REJECTED
```

---

# 🧾 Payslip Module

Employees can download their payslip as a PDF document.

### Payslip Includes

- Employee Details
- Salary Breakdown
- Deductions
- Net Salary

### PDF Generation

Implemented using:

```text
jsPDF
```

---

# 🖥️ Frontend Pages

| Page | Purpose |
|---|---|
| login.html | Admin & Employee Login |
| index.html | Admin Dashboard |
| dashboard.html | Employee Dashboard |
| create.html | Create Employee |
| update.html | Admin Employee Update |
| empUpdate.html | Employee Profile Update |
| leave.html | Leave Application |
| welcome.html | Admin Welcome Page |

---

# 🔒 Security Features

- JWT Authentication
- BCrypt Password Encryption
- Role-Based Authorization
- Stateless Session Management
- Secure API Access
- CORS Configuration

---

# ⚙️ How To Run

## 1️⃣ Clone Repository

```bash
git clone <repository-url>
```

---

## 2️⃣ Configure MySQL

Update:

```properties
application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ems
spring.datasource.username=root
spring.datasource.password=yourpassword
```

---

## 3️⃣ Run Backend

```bash
mvn spring-boot:run
```

Backend runs on:

```text
http://localhost:8080
```

---

## 4️⃣ Run Frontend

Open:

```text
login.html
```

in the browser.

---





# 📌 Conclusion

This Employee Management System demonstrates a complete full-stack workflow with secure authentication, role-based authorization, employee operations, leave workflows, and PDF generation.

The project focuses on clean architecture, modular backend development, and responsive frontend design using modern technologies.

---
