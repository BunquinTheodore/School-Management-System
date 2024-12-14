-- Create the database
CREATE DATABASE schoolmanagement;
USE schoolmanagement;

-- Department Table
CREATE TABLE Department (
    dept_id INT AUTO_INCREMENT PRIMARY KEY,
    dept_name VARCHAR(100) NOT NULL UNIQUE
);

-- Course Table
CREATE TABLE Course (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL UNIQUE,
    price DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    dept_id INT,
    FOREIGN KEY (dept_id) REFERENCES Department(dept_id) ON DELETE SET NULL
);

-- Teacher Table
CREATE TABLE Teacher (
    teacher_id INT AUTO_INCREMENT PRIMARY KEY,
    teacher_name VARCHAR(100) NOT NULL,
    dept_id INT,
    course_id INT,
    salary DECIMAL(10, 2),
    FOREIGN KEY (dept_id) REFERENCES Department(dept_id) ON DELETE SET NULL,
    FOREIGN KEY (course_id) REFERENCES course(course_id)
);

-- Student Table
CREATE TABLE Student (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    student_name VARCHAR(100) NOT NULL,
    dept_id INT,
    course_id INT,
    FOREIGN KEY (dept_id) REFERENCES Department(dept_id) ON DELETE SET NULL,
    FOREIGN KEY (course_id) REFERENCES Course(course_id) ON DELETE SET NULL
);

-- Subject Table
CREATE TABLE Subject (
    subject_id INT AUTO_INCREMENT PRIMARY KEY,
    subject_name VARCHAR(100) NOT NULL UNIQUE,
    dept_id INT,
    teacher_id INT,
    FOREIGN KEY (dept_id) REFERENCES Department(dept_id) ON DELETE SET NULL,
    FOREIGN KEY (teacher_id) REFERENCES Teacher(teacher_id) ON DELETE SET NULL
);

-- Enrollment Table (Join table for Students and Courses)
CREATE TABLE Enrollment (
    enrollment_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    enrollment_date timestamp NOT NULL DEFAULT current_timestamp,
    FOREIGN KEY (student_id) REFERENCES Student(student_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES Course(course_id) ON DELETE CASCADE
);

-- Payment Table
CREATE TABLE Payment (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    amount_paid DECIMAL(10, 2) NOT NULL,
    payment_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    payment_status ENUM('Pending', 'Completed') DEFAULT 'Pending',
    FOREIGN KEY (student_id) REFERENCES Student(student_id) ON DELETE CASCADE
);


-- Users Table for Authentication
CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL, -- In production, store hashed passwords
    role ENUM('Student', 'Teacher') NOT NULL,
    student_id INT DEFAULT NULL,
    teacher_id INT DEFAULT NULL,
    FOREIGN KEY (student_id) REFERENCES Student(student_id) ON DELETE CASCADE,
    FOREIGN KEY (teacher_id) REFERENCES Teacher(teacher_id) ON DELETE CASCADE
);

CREATE TABLE Grades (
    grade_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    teacher_id INT NOT NULL,
    grade VARCHAR(5),  -- e.g., A+, A, B, etc.
    FOREIGN KEY (student_id) REFERENCES Student(student_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES Course(course_id) ON DELETE CASCADE,
    FOREIGN KEY (teacher_id) REFERENCES Teacher(teacher_id) ON DELETE CASCADE
);

INSERT INTO department (dept_name) VALUES
('CICS'),
('COE'),
('CET');

INSERT INTO teacher (teacher_name, dept_id, salary) VALUES
('qwertyo', 1, 6000),
('asdfghj', 2, 6500),
('zxcvbnm', 2, 8000);

INSERT INTO course (course_name, price, dept_id) VALUES
('Data Structures', 200.0, 1),
('Algorithms', 250.0, 1),
('Linear Algebra', 180.0, 2),
('Calculus', 180.0, 2),
('Quantum Mechanics', 300.0, 3);

INSERT INTO student (student_name, dept_id, course_id) VALUES
('qwerty', 1, 1),
('asdfgh', 2, 2),
('zxcvbn', 3, 3);

INSERT INTO subject (subject_name, dept_id, teacher_id) VALUES
('Data Structures', 1, 1),
('Algorithms', 1, 2),
('Linear Algebra', 2, 2),
('Calculus', 2, 3),
('Quantum Mechanics', 3, 3);

INSERT INTO Enrollment (student_id, course_id, enrollment_date) VALUES
(1, 1, '2024-01-15 10:00:00'), 
(2, 2, '2024-01-16 11:00:00'),
(3, 3, '2024-01-17 09:00:00');

INSERT INTO Payment (student_id, amount_paid, payment_date, payment_status) VALUES
(1, 200.00, '2024-01-15 10:30:00', 'Completed'), 
(2, 250.00, '2024-01-16 11:30:00', 'Completed'), 
(3, 300.00, '2024-01-17 09:30:00', 'Pending');


INSERT INTO Users (username, password, role, student_id, teacher_id) VALUES
('qwer', '1234', 'Student', 1, NULL), -- User for Student qwer
('asdf', '1234', 'Student', 2, NULL), -- User for Student asdf
('zxcv', '1234', 'Student', 3, NULL), -- User for Student zxcv
('qwert', '1234', 'Teacher', NULL, 1), -- User for Teacher qwer
('asdfg', '1234', 'Teacher', NULL, 2), -- User for Teacher asdf
('zxcvb', '1234', 'Teacher', NULL, 3); -- User for Teacher zxcv

INSERT INTO Grades (student_id, course_id, teacher_id, grade) VALUES
(1, 1, 1, 'A'), 
(2, 2, 2, 'B+'),
(3, 3, 3, 'A-');

SELECT * FROM Department;
SELECT * FROM Teacher;
SELECT * FROM Course;
SELECT * FROM Subject;
SELECT * FROM Student;
SELECT * FROM Enrollment;
SELECT * FROM Payment;
SELECT * FROM Users;













