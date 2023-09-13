package vttp_project_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp_project_backend.models.ExternalApi.DataObject;
import vttp_project_backend.repo.EventRepository;

@Service
public class EventService {
    @Autowired EventRepository eventRepo;

    public void addToDatabase(DataObject d) {
        if (eventRepo.getEventById(d.uuid()).isEmpty()) {
            boolean success = eventRepo.addEvent(d);

            if (!success)
                System.err.println("Error while adding DataObject " + d.uuid());      
            
            System.out.println("DataObject " + d.uuid() + " successfully added!");
        } else {
            System.out.println("DataObject " + d.uuid() + " already exists in database");
        }
    }
    
    public List<DataObject> getEvents() {
        return eventRepo.getEvents();
    }
}
