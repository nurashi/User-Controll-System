package kz.applicationweb.usercontrollsystemoop.dto.request;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class CreateStudentRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Surname is required")
    private String surname;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @Pattern(regexp = "^\\+?[0-9\\-\\s()]*$", message = "Invalid phone number format")
    private String phone;

    private String address;

    @NotBlank(message = "University is required")
    private String university;

    @Positive(message = "GPA must be positive")
    private Double gpa;

    // Getters and Setters

    public @NotBlank(message = "Name is required") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is required") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Surname is required") String getSurname() {
        return surname;
    }

    public void setSurname(@NotBlank(message = "Surname is required") String surname) {
        this.surname = surname;
    }

    public @NotNull(message = "Date of birth is required") @Past(message = "Date of birth must be in the past") LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(
            @NotNull(message = "Date of birth is required") @Past(message = "Date of birth must be in the past") LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public @Email(message = "Email should be valid") @NotBlank(message = "Email is required") String getEmail() {
        return email;
    }

    public void setEmail(
            @Email(message = "Email should be valid") @NotBlank(message = "Email is required") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Password is required") @Size(min = 8, message = "Password must be at least 8 characters") String getPassword() {
        return password;
    }

    public void setPassword(
            @NotBlank(message = "Password is required") @Size(min = 8, message = "Password must be at least 8 characters") String password) {
        this.password = password;
    }

    public @Pattern(regexp = "^\\+?[0-9\\-\\s()]*$", message = "Invalid phone number format") String getPhone() {
        return phone;
    }

    public void setPhone(
            @Pattern(regexp = "^\\+?[0-9\\-\\s()]*$", message = "Invalid phone number format") String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public @NotBlank(message = "University is required") String getUniversity() {
        return university;
    }

    public void setUniversity(@NotBlank(message = "University is required") String university) {
        this.university = university;
    }

    public @Positive(message = "GPA must be positive") Double getGpa() {
        return gpa;
    }

    public void setGpa(@Positive(message = "GPA must be positive") Double gpa) {
        this.gpa = gpa;
    }
}
