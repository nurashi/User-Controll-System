package kz.applicationweb.usercontrollsystemoop.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student extends User {
    private String university;
    private Double gpa;

    // Getters and Setters
    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }
}