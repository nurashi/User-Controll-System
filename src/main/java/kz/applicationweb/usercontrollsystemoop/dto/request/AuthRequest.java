package kz.applicationweb.usercontrollsystemoop.dto.request;

import jakarta.validation.constraints.NotBlank;

public class AuthRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    // Getters and Setters
    public String getEmail() { return email; }
    public void setEmail(String username) { this.email = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
