package Employee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class AttendanceManager implements Serializable {
    private List<Student> students;

    public AttendanceManager() {
        students = new ArrayList<>();
    }

    public void addStudent(String name, String id) {
        if (findStudent(id) != null) {
            throw new IllegalArgumentException("Student with ID " + id + " already exists");
        }
        students.add(new Student(name, id));
    }

    public void markAttendance(String id, LocalDate date, boolean present) {
        Student student = findStudent(id);
        if (student != null) {
            student.markAttendance(date, present);
        } else {
            throw new IllegalArgumentException("Student with ID " + id + " not found");
        }
    }

    public List<String> getAbsentees(LocalDate date) {
        List<String> absentees = new ArrayList<>();
        for (Student student : students) {
            if (!student.isPresent(date)) {
                absentees.add(student.getName());
            }
        }
        return absentees;
    }

    public String generateWeeklyReport(LocalDate startDate) {
        StringBuilder report = new StringBuilder();
        report.append("Weekly Attendance Report\n");
        report.append("Period: ").append(startDate).append(" to ").append(startDate.plusDays(6)).append("\n");
        report.append("--------------------------------\n");

        for (Student student : students) {
            int presentCount = student.getAttendanceCount(startDate);
            double percentage = (presentCount / 7.0) * 100.0;
            report.append(String.format("%s (ID: %s) - Present: %d/7 (%.1f%%)%n",
                    student.getName(), student.getId(), presentCount, percentage));
        }

        return report.toString();
    }

    public String generateMonthlyReport(LocalDate startDate) {
        StringBuilder report = new StringBuilder();
        report.append("Monthly Attendance Report\n");
        report.append("Period: ").append(startDate).append(" to ").append(startDate.plusDays(29)).append("\n");
        report.append("--------------------------------\n");

        for (Student student : students) {
            int presentCount = student.getMonthlyAttendanceCount(startDate);
            double percentage = (presentCount / 30.0) * 100.0;
            report.append(String.format("%s (ID: %s) - Present: %d/30 (%.1f%%)%n",
                    student.getName(), student.getId(), presentCount, percentage));
        }

        return report.toString();
    }

    private Student findStudent(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }
} 