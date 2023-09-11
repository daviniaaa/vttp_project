package vttp_project_backend.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vttp_project_backend.config.UserAuthentication;
import vttp_project_backend.models.UserData;
import vttp_project_backend.records.LoginDTO;
import vttp_project_backend.records.RegisterDTO;
import vttp_project_backend.records.UserDTO;
import vttp_project_backend.service.UserService;


@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthentication userAuth;
    
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
}
