package vttp_project_backend.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserRegistration {
    @NotBlank @Email
    private String email;
    @NotBlank
    private String displayName;
    @NotBlank
    private String userPassword;
    @NotBlank
    private String confirmPassword;
    
    public UserRegistration(String email, String displayName, String userPassword, String confirmPassword) {
        this.email = email;
        this.displayName = displayName;
        this.userPassword = userPassword;
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getconfirmPassword() {
        return confirmPassword;
    }

    public void setconfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    
    
}
