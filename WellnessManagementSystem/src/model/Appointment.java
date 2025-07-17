package model;
import java.sql.Date;

public class Appointment {
    private String student;
    private String counselor;
    private Date date;
    private String time;
    private String status;  // Now just a plain String

    // Constructor
    public Appointment(String student, String counselor, Date date, String time, String status) {
        this.student = student;
        this.counselor = counselor;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public Appointment(String student, String counselor, String dateStr, String time, String status) {
    this(student, counselor, Date.valueOf(dateStr), time, status);  
}       

    // Getters & Setters
    public String getStudent() { return student; }
    public void setStudent(String student) { this.student = student; }

    public String getCounselor() { return counselor; }
    public void setCounselor(String counselor) { this.counselor = counselor; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return student + " - " + counselor + " on " + date.toString() + " at " + time + " [" + status + "]";
    }
}
