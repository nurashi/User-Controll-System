package kz.applicationweb.usercontrollsystemoop.dto.response;

import java.time.LocalDate;

import kz.applicationweb.usercontrollsystemoop.model.user.Student;

public class StudentResponse {
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private LocalDate dateOfBirth;
    private String email;
    private String phone;
    private String address;
    private String university;
    private Double gpa;

    public StudentResponse(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.surname = student.getSurname();
        this.age = student.getAge();
        this.dateOfBirth = student.getDateOfBirth();
        this.email = student.getEmail();
        this.phone = student.getPhone();
        this.address = student.getAddress();
        this.university = student.getUniversity();
        this.gpa = student.getGpa();
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public Integer getAge() { return age; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getUniversity() { return university; }
    public Double getGpa() { return gpa; }
}
