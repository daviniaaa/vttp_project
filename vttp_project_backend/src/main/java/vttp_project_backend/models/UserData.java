package vttp_project_backend.models;

public class UserData {
    private String userDataId;
    private String email;
    private String displayName;
    private String userPassword;
    private String imageUrl;

    public UserData() {
    }

    public UserData(String email, String userPassword) {
        this.email = email;
        this.userPassword = userPassword;
    }

    public UserData(String email, String displayName, String userPassword) {
        this.email = email;
        this.displayName = displayName;
        this.userPassword = userPassword;
    }

    public UserData(String userDataId, String email, String displayName, String userPassword, String imageUrl) {
        this.userDataId = userDataId;
        this.email = email;
        this.displayName = displayName;
        this.userPassword = userPassword;
        this.imageUrl = imageUrl;
    }

    public String getUserDataId() {
        return userDataId;
    }
    public void setUserDataId(String userDataId) {
        this.userDataId = userDataId;
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
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    
}
