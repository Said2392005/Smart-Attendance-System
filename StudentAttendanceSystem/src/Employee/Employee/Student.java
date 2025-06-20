package Employee;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Student implements java.io.Serializable {
    private String name;
    private String id;
    private Map<LocalDate, Boolean> attendance;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.attendance = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void markAttendance(LocalDate date, boolean present) {
        attendance.put(date, present);
    }

    public boolean isPresent(LocalDate date) {
        return Boolean.TRUE.equals(attendance.get(date));
    }

    public int getAttendanceCount(LocalDate startDate) {
        int count = 0;
        for (int i = 0; i < 7; i++) {
            LocalDate date = startDate.plusDays(i);
            if (isPresent(date)) {
                count++;
            }
        }
        return count;
    }

    public int getMonthlyAttendanceCount(LocalDate startDate) {
        int count = 0;
        for (int i = 0; i < 30; i++) {
            LocalDate date = startDate.plusDays(i);
            if (isPresent(date)) {
                count++;
            }
        }
        return count;
    }
} 