package vttp_project_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp_project_backend.models.EventDetails;
import vttp_project_backend.service.VttpProjectService;

@Controller
@RequestMapping("/api")
public class VttpProjectController {
    @Autowired private VttpProjectService service;

    // @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<String> createAccount(@RequestBody @Valid UserRegistration u) {
    //     System.out.println("postmapping called! createAccount() + u >> " + u.getUserPassword() + "confirm " + u.getconfirmPassword());
    //     String response = "";

    //     // exceptions handled so no need to catch
    //     response = service.createUser(u);
    //     return ResponseEntity.ok(response);
    // }

    // @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<UserData> login(@RequestBody UserData u) {
    //     System.out.println("postmapping called! login()");
    //     System.out.println("u >> " + u.getEmail());

    //     UserData user = new UserData();

    //     // exceptions handled so no need to catch
    //     user = service.login(u);
    //     return ResponseEntity.ok(user);
    // }

    // @PostMapping("/login")
    // public ResponseEntity<String> login(Authentication auth) {
    //     System.out.println("auth >> " + auth);
    //     try {
    //     String token = tokenSvc.generateToken(auth); 
    // System.out.println("token >> " + token);
    //     return ResponseEntity.ofNullable(token);
    // }
    //     catch (Exception e) {
            
    //     }
    //     return ResponseEntity.ok("");
    // }

    @GetMapping(path = "/home", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EventDetails>> home() {
        System.out.println("getmapping called! home()");
        List<EventDetails> events =  service.getEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping(path = "/event/{eventId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventDetails> getEvent(@PathVariable String eventId) {
        System.out.println("getmpping called! getEvent()");
        EventDetails e = service.getEventById(eventId);

        return ResponseEntity.ok(e);
    }

    @GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EventDetails>> search(@RequestParam String keyword) {
        System.out.println("getmapping called! search()");

        String[] keywords = keyword.split("%20");
        List<EventDetails> events =  service.search(keywords);
        return ResponseEntity.ok(events);
    }
}
