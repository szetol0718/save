
# Personal Finance Tracker 💸

A full-stack web application for tracking income and expenses, built with **Spring Boot**, **MySQL**, and **Thymeleaf**.

## 🔧 Features
- User authentication and secure login
- Add, edit, and delete income/expense transactions
- Filter by category and date
- Monthly summary and dashboard
- Responsive UI using Thymeleaf templates
- RESTful API backend

## 🚀 Getting Started

### 1. Prerequisites
- Java 17+
- Maven
- MySQL

### 2. Clone the Repository
```bash
git clone https://github.com/szetol0718/save.git
cd save/personal-finance-tracker
```

### 3. MySQL Setup
```sql
CREATE DATABASE finance;
-- Optional user creation
CREATE USER 'finance_user'@'localhost' IDENTIFIED BY 'yourpassword';
GRANT ALL PRIVILEGES ON finance.* TO 'finance_user'@'localhost';
```

### 4. Configure `application.properties`
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/finance
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
```

### 5. Run the Application
```bash
./mvnw spring-boot:run
```

Then go to `http://localhost:8080`.

## 📂 Project Structure
- `model/`: JPA entities (`User`, `Transaction`)
- `repository/`: Spring Data interfaces
- `controller/`: MVC controllers for routing
- `service/`: Business logic
- `templates/`: Thymeleaf views

## 📜 License
This project is open source and available under the [MIT License](LICENSE).

---

**Author:** [szetol0718](https://github.com/szetol0718)
