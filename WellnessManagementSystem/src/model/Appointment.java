package model;

import java.sql.Date;

public class Appointment {
    private String student;
    private String counselor;
    private Date date;
    private String time;
    private Status status;  // Changed from String to enum

    // Define possible status values as an enum
    public enum Status {
        SCHEDULED("Scheduled"),
        CONFIRMED("Confirmed"),
        CANCELLED("Cancelled"),
        COMPLETED("Completed");

        private final String displayName;

        Status(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }


        public static Status fromString(String text) {
            for (Status s : Status.values()) {
                if (s.displayName.equalsIgnoreCase(text)) {
                    return s;
                }
            }
            throw new IllegalArgumentException("No constant with text " + text + " found");
        }
    }

    // Constructors
    public Appointment(String student, String counselor, Date date, String time, Status status) {
        this.student = student;
        this.counselor = counselor;
        this.date = date;
        this.time = time;
        this.status = status;
    }


    public Appointment(String student, String counselor, String dateStr, String time, String status) {
        this(student, counselor, Date.valueOf(dateStr), time, Status.fromString(status));
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

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }


    public void setStatus(String status) {
        this.status = Status.fromString(status);
    }


    public String getStatusValue() {
        return status.name();  // Returns "SCHEDULED", "CONFIRMED" etc.
    }

    @Override
    public String toString() {
        return student + " - " + counselor + " on " + date.toString() + " at " + time + " [" + status + "]";
    }
}
