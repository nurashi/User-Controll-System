package kz.applicationweb.usercontrollsystemoop.dto.request;

import jakarta.validation.constraints.NotBlank;

public class RegisterRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String role;

    // Getters and Setters
    public String getEmail() { return email; }
    public void setEmail(String username) { this.email = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
