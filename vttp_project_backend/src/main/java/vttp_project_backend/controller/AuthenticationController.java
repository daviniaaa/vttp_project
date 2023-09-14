package vttp_project_backend.controller;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import vttp_project_backend.config.UserAuthentication;
import vttp_project_backend.models.BoothDetails;
import vttp_project_backend.models.UserData;
import vttp_project_backend.models.UserSettings;
import vttp_project_backend.records.LoginDTO;
import vttp_project_backend.records.RegisterDTO;
import vttp_project_backend.records.UserDTO;
import vttp_project_backend.service.BoothService;
import vttp_project_backend.service.ImageService;
import vttp_project_backend.service.UserService;


@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired private UserService userService;
    @Autowired private UserAuthentication userAuth;
    @Autowired private BoothService boothService;
    @Autowired private ImageService imageService;
    
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginDTO loginDto) {

        UserDTO user = userService.login(loginDto);
        user.setToken(userAuth.createToken(user));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> register(@RequestBody RegisterDTO registerDTO) {
        UserData user = userService.createUser(registerDTO);
        UserDTO createdUser = new UserDTO(user.getUserDataId(), user.getEmail(), user.getDisplayName(), "");
        createdUser.setToken(userAuth.createToken(new UserDTO(user.getUserDataId(), user.getEmail(), "")));
        return ResponseEntity.created(URI.create("/users/" + createdUser.getUserDataId())).body(createdUser);
    }

    @GetMapping("/createbooth")
    public ResponseEntity<String> accessCreateBooth() {
        return null;
    }

    @PostMapping("/createbooth/{eventId}")
    public ResponseEntity<BoothDetails> createBooth(@RequestBody BoothDetails b, @PathVariable String eventId) {
        
        BoothDetails booth = boothService.createBooth(b, eventId);
        return ResponseEntity.ok(booth);
    }

    @GetMapping("/settings/{id}")
    public ResponseEntity<UserSettings> accessSettings(@PathVariable String id) {
        Optional<UserSettings> opt = userService.getUserSettings(id);

        if (opt.isEmpty())
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));

        return ResponseEntity.ok(opt.get());
    }

    @PutMapping("/settings")
    public ResponseEntity<UserSettings> updateSettings(@RequestBody UserSettings u) {
        boolean success = userService.updateUserSettings(u);

        if (!success)
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        
        Optional<UserSettings> opt = userService.getUserSettings(u.getUserDataId());

        if(opt.isEmpty())
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));

        return ResponseEntity.ok(opt.get());
    }

    @PutMapping(path = "/upload/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, 
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createPost(@RequestPart MultipartFile file, 
    @PathVariable String id) throws IOException {

        String url = imageService.uploadPicture(file);

        boolean success = userService.updateUserData(url, id);
        if (!success)
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));

        return ResponseEntity.ok(url);
    }

    @DeleteMapping(path = "/delete/profile/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {

        boolean success = userService.deleteUser(id);

        if(!success)
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));

        return ResponseEntity.ok(id);
    }

}
