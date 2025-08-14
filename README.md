# 🗓️ Schedule API Develop — Session Auth Edition

> A secure RESTful API for managing schedules, comments, and user accounts with **session-based authentication** and role-restricted actions.  
> Create, view, update, and delete schedules & comments — only when you're logged in.
<img width="1097" height="639" alt="image" src="https://github.com/user-attachments/assets/727675ff-1f8f-404d-a4b5-d6f610318e8f" />

---

## Built with

![JDK 17](https://img.shields.io/badge/JDK-17-orange?logo=java&logoColor=white)
[![Spring Boot 3.5.4](https://img.shields.io/badge/Spring%20Boot-3.5.4-6DB33F?logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-6DB33F?logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?logo=mysql&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-ED1C24?logo=java&logoColor=white)
![spring-security-crypto](https://img.shields.io/badge/Spring%20Security%20Crypto-6DB33F?logo=springsecurity&logoColor=white)

---

## Features

### User Management
- Sign up with username, email, and password
- Log in with email and password, and the server creates a session
- Log out, which invalidates the session
- Update and delete your own profile

### Schedule Management
- Create a new schedule (**login required**)
- View all schedules or filter by user
- View a specific schedule by ID
- Update only your own schedule
- Delete only your own schedule
- Auto-generated timestamps for creation and modification

### Comment Management
- Add comments to schedules (**login required**)
- View all comments for a specific schedule
- View a single comment by ID
- Update only your own comments
- Delete only your own comments

### Security
- **Session-based authentication** using `HttpSession` and a custom `LoginFilter`
- Password hashing with `BCryptPasswordEncoder`
- Global exception handling with `@RestControllerAdvice`
---

### Security & Authorization Flow

- **Login**: A user logs in, and the server stores their information (`LOGIN_USER`) in a session.
- **Protected endpoints**: These API endpoints require an active session to be accessed. A `LoginFilter` checks for the presence of this session before allowing access.
- **Authentication Method**: The system uses a session and cookie-based authentication method, not JSON Web Tokens (JWT).
- **Password Security**: Passwords are never stored as plain text. They are stored as **hashed** values in the database and are never sent back in API responses.
---

## API Reference

Full documentation now lives:

🔗 [Check API Docs from this link](https://documenter.getpostman.com/view/47183182/2sB3BGHVTr)

---

## Entity Relationship Diagram

Visual representation of the database schema:

🔗 [View ERD from this link](https://github.com/4x2vk/ScheduleApiDevelop/issues/1#issuecomment-3186705590)

---

## Example Requests

### Sign Up
```http
POST /users/signup
Content-Type: application/json

{
  "username": "honggildong",
  "email": "hong@gmail.com",
  "password": "hong123"
}
```
### Login
```http
POST /login
Content-Type: application/json

{
  "email": "hong@gmail.com",
  "password": "hong123"
}
```
## 📁 Directory Structure
```
src/
├── common/
│   ├── config/        # FilterConfig, PasswordEncoder config
│   ├── filter/        # LoginFilter
│   ├── exception/     # Custom exceptions & handlers
│   └── session/       # SessionConst
├── user/
│   ├── controller/    # UserController, LoginController
│   ├── dto/           # User DTOs
│   ├── entity/        # User entity
│   ├── repository/    # UserRepository
│   └── service/       # UserService
├── schedule/
│   ├── controller/    # ScheduleController
│   ├── dto/           # Schedule DTOs
│   ├── entity/        # Schedule entity
│   ├── repository/    # ScheduleRepository
│   └── service/       # ScheduleService
├── comments/
│   ├── controller/    # CommentController
│   ├── dto/           # Comment DTOs
│   ├── entity/        # Comment entity
│   ├── repository/    # CommentRepository
│   └── service/       # CommentService
└── ScheduleApiApplication.java
```
