-- Create the database
CREATE DATABASE IF NOT EXISTS attendance_db;
USE attendance_db;

-- Create students table
CREATE TABLE IF NOT EXISTS students (
    id VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- Create attendance table
CREATE TABLE IF NOT EXISTS attendance (
    student_id VARCHAR(10),
    date DATE,
    is_present BOOLEAN,
    PRIMARY KEY (student_id, date),
    FOREIGN KEY (student_id) REFERENCES students(id)
); 