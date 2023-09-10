package vttp_project_backend.models;

public class EventDetails {
    private String eventDetailsId;
    private String userDataId;
    private String title;
    private String description;
    private String imageUrl;
    private String category;

    public EventDetails() {
    }

    public EventDetails(String eventDetailsId, String userDataId, String title, 
    String description, String imageUrl, String category) {
        this.eventDetailsId = eventDetailsId;
        this.userDataId = userDataId;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }
    
    public String getEventDetailsId() {
        return eventDetailsId;
    }
    public void setEventDetailsId(String eventDetailsId) {
        this.eventDetailsId = eventDetailsId;
    }
    public String getUserDataId() {
        return userDataId;
    }
    public void setUserDataId(String userDataId) {
        this.userDataId = userDataId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    
    
}
