package vttp_project_backend.service;

import java.nio.CharBuffer;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import vttp_project_backend.exception.CreateAccountException;
import vttp_project_backend.exception.ExistingEmailException;
import vttp_project_backend.exception.NoExistingEmailException;
import vttp_project_backend.exception.WrongPasswordException;
import vttp_project_backend.models.UserData;
import vttp_project_backend.records.LoginDTO;
import vttp_project_backend.records.RegisterDTO;
import vttp_project_backend.records.UserDTO;
import vttp_project_backend.repo.UserRepository;

public class UserService {
    
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO login(LoginDTO loginDTO) {
        if(userRepo.findUserByEmail(loginDTO.username()).isEmpty()) {
            throw new NoExistingEmailException();
        }
        UserData user = userRepo.findUserByEmail(loginDTO.username()).get();
            
        
        if (passwordEncoder.matches(CharBuffer.wrap(loginDTO.password()),
            user.getPassword())) {
                return new UserDTO(user.getUserDataId(), user.getUsername(), "");
        } else {
            throw new WrongPasswordException();
        }

    }

    public UserData register(RegisterDTO registerDTO) {
        Optional<UserData> optionalUser = userRepo.findUserByEmail(registerDTO.username());

        if (optionalUser.isPresent()) {
            throw new ExistingEmailException();
        }

        UserData user = new UserData(registerDTO.id(), registerDTO.username(), registerDTO.password().toString(), registerDTO.email(), "");
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(registerDTO.password())));

        if (userRepo.createUser(user)) {
            return user;
        }
        else {
            throw new CreateAccountException();
        }
    }
}
