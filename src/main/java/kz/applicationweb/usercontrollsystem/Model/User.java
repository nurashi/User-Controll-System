package kz.applicationweb.usercontrollsystem.Model;

import jakarta.persistence.*;

import java.util.Date;



@Entity
@Table
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String dob;
    private boolean gender;
    private String job;
    private String address;
    private String phone;
    private String created_at;
    private int age;


    public User() {}

    public User(String created_at,
                String phone,
                String address,
                String job,
                boolean gender,
                String dob,
                String password,
                String email,
                String surname,
                String name,
                int age) {
        this.created_at = created_at;
        this.phone = phone;
        this.address = address;
        this.job = job;
        this.gender = gender;
        this.dob = dob;
        this.password = password;
        this.email = email;
        this.surname = surname;
        this.name = name;
        this.age = age;
    }

    public User(int id,
                String name,
                String surname,
                String email,
                String password,
                String dob,
                boolean gender,
                String job,
                String address,
                String phone,
                String created_at,
                int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.job = job;
        this.address = address;
        this.phone = phone;
        this.created_at = created_at;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
