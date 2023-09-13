package vttp_project_backend.records;

public class UserDTO {
    private String userDataId;
    private String email;
    private String displayName;
    private String imageUrl;
    private String token;

    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public UserDTO() {
    }
    public UserDTO(String userDataId, String email, String token) {
        this.userDataId = userDataId;
        this.email = email;
        this.token = token;
    }
    public UserDTO(String userDataId, String email, String displayName, String token) {
        this.userDataId = userDataId;
        this.email = email;
        this.displayName = displayName;
        this.token = token;
    }
    public UserDTO(String userDataId, String email, String displayName, String imageUrl, String token) {
        this.userDataId = userDataId;
        this.email = email;
        this.displayName = displayName;
        this.imageUrl = imageUrl;
        this.token = token;
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
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    
}
