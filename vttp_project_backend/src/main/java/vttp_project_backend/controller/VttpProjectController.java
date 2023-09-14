package vttp_project_backend.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import vttp_project_backend.models.BoothDetails;
// import vttp_project_backend.exception.CustomException;
import vttp_project_backend.models.UserData;
import vttp_project_backend.models.ExternalApi.DataObject;
import vttp_project_backend.service.BoothService;
import vttp_project_backend.service.EventService;
import vttp_project_backend.service.ImageService;
import vttp_project_backend.service.UserService;

@RestController
@RequestMapping("/api")
public class VttpProjectController {
    @Autowired private EventService eventService;
    @Autowired private UserService userService;
    @Autowired private BoothService boothService;

    @GetMapping(path = "/home", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DataObject>> home() {
        System.out.println("getmapping called! home()");
        List<DataObject> events =  eventService.getEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping(path = "/event/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataObject> getEventById(@PathVariable String id) {
        System.out.println("getmapping called! getEventById()");
        Optional<DataObject> event =  eventService.getEventById(id);

        if (event.isEmpty()) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        return ResponseEntity.ok(event.get());
    }

    @GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DataObject>> search(@RequestParam String keyword) {
        System.out.println("getmapping called! search()");

        String[] keywords = keyword.split("%20");
        List<DataObject> events =  eventService.search(keywords);
        return ResponseEntity.ok(events);
    }

    @GetMapping(path = "/profile/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserData> getUser(@PathVariable String id) {
        Optional<UserData> opt = userService.findUserById(id);

        if(opt.isEmpty())
            // throw new CustomException("No user with id " + id, HttpStatus.NOT_FOUND);
            return new ResponseEntity<UserData>(HttpStatusCode.valueOf(404));
        
        return ResponseEntity.ok(opt.get());
    }

    @GetMapping(path = "/booths/{eventId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BoothDetails>> getBooths(@PathVariable String eventId) {
        System.out.println("getmapping called! getBooths()");
        List<BoothDetails> events =  boothService.getBooths(eventId);
        return ResponseEntity.ok(events);
    }

    @GetMapping(path = "/profile/{id}/events")
    public ResponseEntity<List<DataObject>> getEventsByUser(@PathVariable String id) {
        List<DataObject> list = eventService.getEventsByUser(id);

        if (list.size() == 0) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }

        return ResponseEntity.ok(list);
    }

    

    
}
