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
    private String passwordConfirm;
    
    public UserRegistration(String email, String displayName, String userPassword, String passwordConfirm) {
        this.email = email;
        this.displayName = displayName;
        this.userPassword = userPassword;
        this.passwordConfirm = passwordConfirm;
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

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    
    
}
