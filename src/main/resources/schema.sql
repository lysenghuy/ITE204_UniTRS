CREATE DATABASE IF NOT EXISTS unitrs_db;
USE unitrs_db;

-- 1. Users Table (Handles Students, Faculty, Deans, and Admins)
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_identifier VARCHAR(50) NOT NULL UNIQUE, -- Student ID (e.g. '60-24-04-91') or Staff Username/Email
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    role ENUM('STUDENT', 'PROFESSOR', 'DEAN', 'ADMIN') NOT NULL,
    major VARCHAR(100),
    is_verified BOOLEAN DEFAULT FALSE,           -- Requires Admin verification for Students
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Terms Master Table
CREATE TABLE IF NOT EXISTS terms (
    id INT AUTO_INCREMENT PRIMARY KEY,
    term_number INT NOT NULL UNIQUE,             -- e.g. 1 through 12
    term_name VARCHAR(50) NOT NULL               -- e.g. 'Term 5'
);

-- 3. Courses Master Table
CREATE TABLE IF NOT EXISTS courses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    course_code VARCHAR(20) NOT NULL UNIQUE,     -- e.g. 'ITE 204'
    course_title VARCHAR(150) NOT NULL,          -- e.g. 'Java Enterprise Edition'
    credits INT DEFAULT 3
);

-- 4. Term Course Bundles (Mapping courses belonging to a specific term)
CREATE TABLE IF NOT EXISTS term_courses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    term_id INT NOT NULL,
    course_id INT NOT NULL,
    FOREIGN KEY (term_id) REFERENCES terms(id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE,
    UNIQUE KEY uq_term_course (term_id, course_id)
);

-- 5. Class Sections (Actual scheduled classes per term, shift, and professor)
CREATE TABLE IF NOT EXISTS class_sections (
    id INT AUTO_INCREMENT PRIMARY KEY,
    term_id INT NOT NULL,
    course_id INT NOT NULL,
    professor_id INT NOT NULL,
    session_shift ENUM('MORNING', 'AFTERNOON', 'EVENING') NOT NULL,
    room VARCHAR(30) NOT NULL,                   -- e.g. 'Room 504'
    days_of_week VARCHAR(50) NOT NULL,           -- e.g. 'Mon - Fri'
    academic_year VARCHAR(20) NOT NULL,          -- e.g. '2025-2026'
    FOREIGN KEY (term_id) REFERENCES terms(id) ON DELETE RESTRICT,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE RESTRICT,
    FOREIGN KEY (professor_id) REFERENCES users(id) ON DELETE RESTRICT
);

-- 6. Student Enrollments
CREATE TABLE IF NOT EXISTS enrollments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    class_section_id INT NOT NULL,
    enrolled_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (class_section_id) REFERENCES class_sections(id) ON DELETE CASCADE,
    UNIQUE KEY uq_student_section (student_id, class_section_id)
);

-- 7. Assessment & Grades Table
CREATE TABLE IF NOT EXISTS grades (
    id INT AUTO_INCREMENT PRIMARY KEY,
    enrollment_id INT NOT NULL UNIQUE,
    attendance_score DECIMAL(5,2) DEFAULT 0.00,  -- Max 15
    assignment_score DECIMAL(5,2) DEFAULT 0.00,  -- Max 25
    midterm_score DECIMAL(5,2) DEFAULT 0.00,     -- Max 30
    final_score DECIMAL(5,2) DEFAULT 0.00,       -- Max 30
    total_score DECIMAL(5,2) DEFAULT 0.00,       -- Max 100
    letter_grade VARCHAR(2) DEFAULT 'F',
    gpa_point DECIMAL(3,2) DEFAULT 0.00,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (enrollment_id) REFERENCES enrollments(id) ON DELETE CASCADE
);

-- Seed Initial Terms
INSERT IGNORE INTO terms (term_number, term_name) VALUES
(1, 'Term 1'), (2, 'Term 2'), (3, 'Term 3'), (4, 'Term 4'),
(5, 'Term 5'), (6, 'Term 6'), (7, 'Term 7'), (8, 'Term 8');

-- Seed Initial Courses
INSERT IGNORE INTO courses (course_code, course_title, credits) VALUES
('ITE 204', 'Java Enterprise Edition', 3),
('ITE 205', 'Database Systems Administration', 3),
('ITE 206', 'Computer Networks', 3),
('ENG 201', 'Advanced Academic English', 3);

-- Seed Initial Staff Roles (Verified staff accounts)
INSERT IGNORE INTO users (user_identifier, password, full_name, email, role, major, is_verified, is_active) VALUES
('admin', 'admin123', 'System Administrator', 'admin@unitrs.edu', 'ADMIN', 'IT Infrastructure', TRUE, TRUE),
('dean@unitrs.edu', 'dean123', 'Dean of Academic Affairs', 'dean@unitrs.edu', 'DEAN', 'Computer Science', TRUE, TRUE),
('prof.sok@unitrs.edu', 'prof123', 'Prof. Sok Chan', 'prof.sok@unitrs.edu', 'PROFESSOR', 'Computer Science', TRUE, TRUE);