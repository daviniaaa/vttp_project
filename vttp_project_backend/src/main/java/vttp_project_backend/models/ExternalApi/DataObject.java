package vttp_project_backend.models.ExternalApi;

public record DataObject(String uuid, String name, String type, String[] tags, 
    String description, String body, Location location, Address address, 
    EventDetailListObject[] eventDetailList, 
    Thumbnail[] thumbnails) {
    
}
