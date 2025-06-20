# Student Attendance System

A Java-based GUI application for managing student attendance records. This system allows teachers to:
- Add new students
- Mark daily attendance
- Generate weekly and monthly attendance reports
- View absentees for any given day

## Features

- User-friendly graphical interface
- Real-time attendance tracking
- Comprehensive reporting system
- Data persistence
- Easy-to-use student management

## Requirements

- Java JDK 8 or higher
- Git (for cloning the repository)

## Setup

1. Clone the repository:
```bash
git clone https://github.com/YOUR_USERNAME/student-attendance-system.git
cd student-attendance-system
```

2. Compile the project:
```bash
javac src/Employee/*.java
```

3. Run the application:
```bash
java -cp src Employee.AttendanceSystemGUI
```

## Project Structure

```
src/
└── Employee/
    ├── Student.java           # Student class with attendance tracking
    ├── AttendanceManager.java # Core attendance management logic
    └── AttendanceSystemGUI.java # Graphical user interface
```

## Usage

1. Add a new student:
   - Enter student name and ID
   - Click "Add Student"

2. Mark attendance:
   - Select student from dropdown
   - Check/uncheck the "Present" checkbox
   - Click "Mark Attendance"

3. Generate reports:
   - Click "Generate Weekly Report" for weekly statistics
   - Click "Generate Monthly Report" for monthly statistics
   - Click "Show Absentees" to view today's absentees

## License

This project is licensed under the MIT License - see the LICENSE file for details. 