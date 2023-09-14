package vttp_project_backend.models;

public class BoothDetails {
    private String boothId;
    private String userDataId;
    private String eventDetailsId;
    private String boothName;
    private String description;
    
    public BoothDetails() {
    }

    public BoothDetails(String userDataId, String boothName, String description) {
        this.userDataId = userDataId;
        this.boothName = boothName;
        this.description = description;
    }

    public BoothDetails(String boothId, String userDataId, String eventDetailsId, String boothName,
            String description) {
        this.boothId = boothId;
        this.userDataId = userDataId;
        this.eventDetailsId = eventDetailsId;
        this.boothName = boothName;
        this.description = description;
    }
    public String getBoothId() {
        return boothId;
    }
    public void setBoothId(String boothId) {
        this.boothId = boothId;
    }
    public String getUserDataId() {
        return userDataId;
    }
    public void setUserDataId(String userDataId) {
        this.userDataId = userDataId;
    }
    public String getEventDetailsId() {
        return eventDetailsId;
    }
    public void setEventDetailsId(String eventDetailsId) {
        this.eventDetailsId = eventDetailsId;
    }
    public String getBoothName() {
        return boothName;
    }
    public void setBoothName(String boothName) {
        this.boothName = boothName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
