//package kz.applicationweb.usercontrollsystemoop.model.users;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Table;
//import kz.applicationweb.usercontrollsystemoop.model.User;
//
//
//@Entity
//@Table(name = "oop_students")
//public class Student extends User {
//
//    @Column(length = 255, nullable = false, name = "group")
//    private String group;
//
//    @Column(length = 255, nullable = false, name = "faculty")
//    private String faculty;
//
//    @Column(length = 255, nullable = false, name = "course")
//    private int course;
//
//    @Column(length = 255, nullable = false, name = "speciality")
//    private String speciality;
//
//    @Column(length = 255, nullable = false, name = "GPA")
//    private double GPA;
//
//    public Student() {
//    }
//
//    public Student(String name,
//                   String surname,
//                   String email,
//                   java.time.LocalDate dob,
//                   String password,
//                   String job,
//                   String phone,
//                   String address,
//                   String group,
//                   String faculty,
//                   int course,
//                   String speciality,
//                   double GPA) {
//        super(name, surname, email, dob, password, job, phone, address);
//        this.group = group;
//        this.faculty = faculty;
//        this.course = course;
//        this.speciality = speciality;
//        this.GPA = GPA;
//    }
//
//    public String getGroup() {
//        return group;
//    }
//
//    public void setGroup(String group) {
//        this.group = group;
//    }
//
//    public String getFaculty() {
//        return faculty;
//    }
//
//    public void setFaculty(String faculty) {
//        this.faculty = faculty;
//    }
//
//    public int getCourse() {
//        return course;
//    }
//
//    public void setCourse(int course) {
//        this.course = course;
//    }
//
//    public String getSpeciality() {
//        return speciality;
//    }
//
//    public void setSpeciality(String speciality) {
//        this.speciality = speciality;
//    }
//
//    public double getGPA() {
//        return GPA;
//    }
//
//    public void setGPA(double GPA) {
//        this.GPA = GPA;
//    }
//}
