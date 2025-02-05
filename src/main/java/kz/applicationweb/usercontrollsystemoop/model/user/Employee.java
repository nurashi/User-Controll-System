package kz.applicationweb.usercontrollsystemoop.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee extends User {
    private String company;
    private String position;
    private Double salary;

    // Getters and Setters
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    public Double getSalary() { return salary; }
    public void setSalary(Double salary) { this.salary = salary; }
}