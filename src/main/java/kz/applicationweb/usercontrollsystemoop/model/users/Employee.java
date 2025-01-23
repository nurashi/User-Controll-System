//package kz.applicationweb.usercontrollsystemoop.model.users;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Table;
//import kz.applicationweb.usercontrollsystemoop.model.User;
//
//
//@Entity
//@Table(name = "OOP_Employees")
//public class Employee extends User {
//
//    @Column(length = 255, nullable = false, name = "position")
//    private String position;
//
//
//    @Column(length = 255, nullable = false, name = "salary")
//    private double salary;
//
//
//    @Column(length = 255, nullable = false, name = "department")
//    private String department;
//
//
//    public Employee() {
//    }
//
//    public Employee(String name,
//                    String surname,
//                    String email,
//                    java.time.LocalDate dob,
//                    String password,
//                    String job,
//                    String phone,
//                    String address,
//                    String position,
//                    double salary,
//                    String department) {
//        super(name, surname, email, dob, password, job, phone, address);
//        this.position = position;
//        this.salary = salary;
//        this.department = department;
//    }
//
//    public String getPosition() {
//        return position;
//    }
//
//    public void setPosition(String position) {
//        this.position = position;
//    }
//
//    public double getSalary() {
//        return salary;
//    }
//
//    public void setSalary(double salary) {
//        this.salary = salary;
//    }
//
//    public String getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(String department) {
//        this.department = department;
//    }
//}
