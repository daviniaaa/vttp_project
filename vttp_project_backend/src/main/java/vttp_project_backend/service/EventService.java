package vttp_project_backend.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp_project_backend.models.BoothDetails;
import vttp_project_backend.models.ExternalApi.DataObject;
import vttp_project_backend.repo.BoothRepository;
import vttp_project_backend.repo.EventRepository;

@Service
public class EventService {
    @Autowired EventRepository eventRepo;
    @Autowired BoothRepository boothRepo;

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

    public List<DataObject> search(String[] searchTerms) {
        return eventRepo.search(searchTerms);
    }

    public Optional<DataObject> getEventById(String uuid) {
        return eventRepo.getEventById(uuid);
    }

    public List<DataObject> getEventsByUser(String id) {
        List<DataObject> list = new LinkedList<>();

        List<BoothDetails> boothList = boothRepo.findBoothsByUser(id);

        for (BoothDetails b : boothList) {
            Optional<DataObject> opt = eventRepo.getEventById(b.getEventDetailsId());

            if (opt.isEmpty())
                continue;
            
            list.add(opt.get());
        }

        return list;
    }

}
