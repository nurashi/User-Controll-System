package kz.applicationweb.usercontrollsystemoop.dto.response;

import java.time.LocalDate;

import kz.applicationweb.usercontrollsystemoop.model.user.Employee;

public class EmployeeResponse {
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private LocalDate dateOfBirth;
    private String email;
    private String phone;
    private String address;
    private String company;
    private String position;
    private Double salary;

    public EmployeeResponse(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.surname = employee.getSurname();
        this.age = employee.getAge();
        this.dateOfBirth = employee.getDateOfBirth();
        this.email = employee.getEmail();
        this.phone = employee.getPhone();
        this.address = employee.getAddress();
        this.company = employee.getCompany();
        this.position = employee.getPosition();
        this.salary = employee.getSalary();
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public Integer getAge() { return age; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getCompany() { return company; }
    public String getPosition() { return position; }
    public Double getSalary() { return salary; }
}