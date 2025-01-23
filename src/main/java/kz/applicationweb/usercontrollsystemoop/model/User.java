package kz.applicationweb.usercontrollsystemoop.model;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "OOP_Users")
public class User {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255, nullable = false, name = "name")
    private String name;

    @Column(length = 255, nullable = false, name = "surname")
    private String surname;

    @Column(length = 255, nullable = false, name = "age")
    private int age;

    @Column(length = 255, nullable = false, name = "dob")
    private LocalDate dob;

    @Column(length = 255, nullable = false, name = "email")
    private String email;

    @Column(length = 255, nullable = false, name = "password")
    private String password;

    @Column(length = 255, nullable = false, name = "job")
    private String job;

    @Column(length = 255, nullable = false, name = "phone")
    private String phone;

    @Column(length = 255, nullable = false, name = "address")
    private String address;

    public User() {
    }

    public User(String name,
                String surname,
                String email,
                LocalDate dob,
                String password,
                String job,
                String phone,
                String address) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dob = dob;
        setAge();
        this.password = password;
        this.job = job;
        this.phone = phone;
        this.address = address;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge() {
        this.age = calculateAge(dob);
    }

    public int getAge() {
        return age;
    }
    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.age = calculateAge(dob);
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public int calculateAge(LocalDate dob) {
        return Period.between(dob, LocalDate.now()).getYears();
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", job='" + job + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
