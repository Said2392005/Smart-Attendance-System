#!/bin/bash

# Wait for MySQL to be ready
echo "Waiting for MySQL to be ready..."
sleep 10

# Create database and tables
docker exec -i 0cc68bb5d0ff23fb5d0814670f29d141f45f7987913a017cd292295107fa059c mysql -uroot -ppassword << 'EOF'
CREATE DATABASE IF NOT EXISTS attendance_db;
USE attendance_db;

CREATE TABLE IF NOT EXISTS students (
    id VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS attendance (
    student_id VARCHAR(10),
    date DATE,
    is_present BOOLEAN,
    PRIMARY KEY (student_id, date),
    FOREIGN KEY (student_id) REFERENCES students(id)
);
EOF

echo "Database setup completed!"