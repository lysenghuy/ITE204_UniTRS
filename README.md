# UniTRS - University Management System (UMS)

A 4-tier role-based Jakarta EE web application tailored for the academic, scheduling, and cohort enrollment structure of Universities (University of Cambodia Academic Model).

---

## Course & Team Information

* **Course:** ITE 204 - Java Enterprise Edition
* **Target Platform:** Jakarta EE 10 / Apache Tomcat 11.0.x
* **Institution Reference:** University of Cambodia (UC) Academic Model
* **Instructor:** Chum Rasy
* **Members:** Hor Tongan | Dita Rector | Ly Senghuy

---

## Default Accounts (Pre-Seeded for Testing)

| Role | Identifier / Email | Password | Status | Permissions |
| :--- | :--- | :--- | :--- | :--- |
| **Admin** | `admin` | `admin123` | Active & Verified | User Governance, Student Verification Queue, Role Provisioning |
| **Dean** | `dean@uc.edu.kh` | `dean123` | Active & Verified | Term-Course Bundling, Class Section Scheduling, Academic Overview |
| **Professor** | `prof.sok@uc.edu.kh` | `prof123` | Active & Verified | Teaching Schedule, Class Roster, 4-Component Continuous Grade Entry |
| **Student** | *(Self-register via UI)* | *(User set)* | Requires Verification | Term Bundle Enrollment, Weekly Timetable, Real-time Transcript & GPA |

---

## Core Features by Role

- **Student Portal:** UC ID-based registration (format: `60-24-04-91`), pending-verification security state, atomic batch term-bundle enrollment, weekly class timetable with shift hours, and dynamic grade transcripts.
- **Professor Portal:** View assigned sections and shifts (Morning, Afternoon, Evening), inspect class rosters, and submit 4-component continuous assessments (Attendance 15%, Assignments 25%, Midterm 30%, Final 30%) with automated letter grade (A-F) and GPA calculation.
- **Dean Portal:** Course catalog management, academic term course bundling (`term_courses`), section scheduling (shift, room, lecturer allocation), and cohort enrollment overview.
- **Admin Portal:** User account provisioning, role management (Student, Professor, Dean, Admin), credential resets, and identity verification approval for newly registered students.

---

## Tech Stack & Architecture

- **Language & Runtime:** Java 17+ (LTS)
- **Web Specifications:** Jakarta EE 10 (`jakarta.servlet.*`, `jakarta.servlet.jsp.*`, JSTL 3.0)
- **Web Container:** Apache Tomcat 11.0.x (with Maven Cargo embedded support for development)
- **Database & Persistence:** MySQL 8.0+ / MariaDB using raw JDBC + Data Access Object (DAO) Pattern with `PreparedStatement`
- **Presentation Tier:** JSP (JavaServer Pages) + JSTL (Jakarta Standard Tag Library 3.0) + Bootstrap 5.3
- **Build System:** Apache Maven (with Maven Wrapper `mvnw` / `mvnw.cmd`)

---

## Getting Started

### 1. Prerequisites
- **JDK 17** or later installed
- **MySQL 8.0+** or **MariaDB** installed and running
- **IntelliJ IDEA** or terminal with Maven

---

### 2. Database Setup

#### Windows
1. Open MySQL Command Line Client, PowerShell, or MySQL Workbench.
2. Create the application user (if not already created):
   ```sql
   CREATE USER IF NOT EXISTS 'ums_user'@'localhost' IDENTIFIED BY 'ums_pass123';
   GRANT ALL PRIVILEGES ON uc_ums_db.* TO 'ums_user'@'localhost';
   FLUSH PRIVILEGES;
   ```
3. Import the schema script:
   ```cmd
   mysql -u root -p < src\main\resources\schema.sql
   ```
   *(Or open `src/main/resources/schema.sql` in MySQL Workbench / HeidiSQL and execute all queries).*

#### macOS
1. Open Terminal.
2. Create the application user:
   ```bash
   mysql -u root -p -e "CREATE USER IF NOT EXISTS 'ums_user'@'localhost' IDENTIFIED BY 'ums_pass123'; GRANT ALL PRIVILEGES ON uc_ums_db.* TO 'ums_user'@'localhost'; FLUSH PRIVILEGES;"
   ```
3. Import the schema script:
   ```bash
   mysql -u root -p < src/main/resources/schema.sql
   ```

#### Linux
1. Open Terminal.
2. Create the application user:
   ```bash
   sudo mysql -e "CREATE USER IF NOT EXISTS 'ums_user'@'localhost' IDENTIFIED BY 'ums_pass123'; GRANT ALL PRIVILEGES ON uc_ums_db.* TO 'ums_user'@'localhost'; FLUSH PRIVILEGES;"
   ```
3. Import the schema script:
   ```bash
   sudo mysql < src/main/resources/schema.sql
   ```

---

### 3. Build & Package

Build the deployable Web Archive (`WAR`) file:

- **Windows (Command Prompt / PowerShell):**
  ```cmd
  .\mvnw.cmd clean package
  ```
- **macOS / Linux (Terminal):**
  ```bash
  ./mvnw clean package
  ```

---

### 4. Running the Application

#### Option A: Embedded Tomcat via Maven 
Runs Tomcat embedded on port 8080 without requiring a standalone installation:

- **Windows:**
  ```cmd
  .\mvnw.cmd cargo:run
  ```
- **macOS / Linux:**
  ```bash
  ./mvnw cargo:run
  ```

Open your browser and navigate to:
```
http://localhost:8080/ums
```

#### Option B: Deploying to Standalone Tomcat 11
1. Copy `target/ums.war` to your Tomcat `webapps/` directory.
2. Start Tomcat:
   - **Windows:** `.\bin\catalina.bat run`
   - **macOS / Linux:** `./bin/catalina.sh run`
3. Open your browser and navigate to `http://localhost:8080/ums`.