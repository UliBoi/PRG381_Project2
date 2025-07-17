# Student Wellness Management System - Milestone 2

This Java Swing desktop application is part of the PRG381 project focused on **object-oriented programming (OOP)** and **basic MVC architecture**. The system allows for managing student wellness appointments, counselor information, and user feedback using a local embedded **Derby (JavaDB)** database.

---

## Project Structure

WellnessManagementSystem/
├── controller/
│ └── CounselorController.java
├── model/
│ ├── Appointment.java
│ ├── AppointmentDAO.java
│ ├── Counselor.java
│ ├── CounselorDAO.java
│ ├── Feedback.java
│ ├── FeedbackDAO.java
├── studentwellnessdb/
│ ├── DBConnection.java
│ └── DbInit.java
├── view/
│ ├── MainDashboard.java
│ └── CounselorPanel.java

---

## Features Implemented

### 1. **Appointments**
- Book, view, update, and cancel appointments.
- Each appointment records:
  - Student name
  - Counselor
  - Date & Time
  - Status (Scheduled, Cancelled, Completed)

### 2. **Counselors**
- Add, update, delete, and view counselor records.
- Fields include name, specialization, and availability.
- Managed using MVC (Controller + DAO + View integration).

### 3. **Feedback**
- Submit and manage feedback from students.
- Ratings are stored as integers (1–5).
- Each feedback entry includes student name, rating, and comments.

---

## Technologies Used

| Tech            | Details                                  |
|-----------------|-------------------------------------------|
| Language        | Java (JDK 17)                             |
| GUI Framework   | Java Swing (NetBeans GUI Builder)         |
| Database        | JavaDB / Derby Embedded                   |
| Architecture    | MVC (Model-View-Controller)               |
| Database Access | JDBC (via DBConnection helper)            |

---

## Database

- Located at:  
  `WellnessManagementSystem/Database/wellnessDB`

- Automatically created and initialized via:
  ```java
  DBConnection.getConnection();
  DbInit.TableCreation();

## How to Run
1. Open the project in NetBeans.
2. Ensure JavaDB is enabled.
3. Run MainDashboard.java.

4. Use the tabs to:
    Manage Appointments
    Manage Counselors
    Submit/View Feedback

## Notes
- Database files are excluded from Git using .gitignore.

- Followed OOP principles:

- Each model has private fields + public getters/setters.
- Controller classes handle communication between view and DAO.
- Clean exception handling for all DB operations.

## Contributors
Team Member 1: Gordon Mullin 600248 – MainDashboard, GUI layout, & DAO

Team Member 2: Rodney Mlostshwa 600439 – Appointment logic, DB tests

Team Member 3: Neo Polori 600508 – Counselor logic, DB tests

Team Member 4: Ulrigh Oosthuizen 577952 –  Feedback logic, CRUD, DB integration, README