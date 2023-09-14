package vttp_project_backend.service;

import java.nio.CharBuffer;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import vttp_project_backend.exception.CreateAccountException;
import vttp_project_backend.exception.DeleteAccountException;
import vttp_project_backend.exception.ExistingEmailException;
import vttp_project_backend.exception.NoExistingEmailException;
import vttp_project_backend.exception.WrongPasswordException;
import vttp_project_backend.models.UserData;
import vttp_project_backend.models.UserSettings;
import vttp_project_backend.records.LoginDTO;
import vttp_project_backend.records.RegisterDTO;
import vttp_project_backend.records.UserDTO;
import vttp_project_backend.repo.UserRepository;

@Service
public class UserService {
    
    @Autowired private UserRepository userRepo;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JavaMailSender mailSender;

    public UserDTO login(LoginDTO loginDTO) {
        if(userRepo.findUserByEmail(loginDTO.email()).isEmpty()) {
            throw new NoExistingEmailException();
        }
        UserData user = userRepo.findUserByEmail(loginDTO.email()).get();
            
        
        if (passwordEncoder.matches(CharBuffer.wrap(loginDTO.userPassword()), user.getUserPassword())) {
                return new UserDTO(user.getUserDataId(), user.getEmail(), user.getDisplayName(), "");
        } else {
            System.out.println("login >> " + loginDTO.userPassword().toString());
            System.out.println("wrpped >> " + CharBuffer.wrap(loginDTO.userPassword()));
            System.out.println("getUserPassword >> " + user.getUserPassword());
            throw new WrongPasswordException();
        }

    }

    @Transactional(rollbackFor = CreateAccountException.class)
    public UserData createUser(RegisterDTO registerDTO) {
        Optional<UserData> optionalUser = userRepo.findUserByEmail(registerDTO.email());

        if (optionalUser.isPresent()) {
            throw new ExistingEmailException();
        }

        // ensures that no duplicate id
        String id = UUID.randomUUID().toString().substring(0, 8);
        Optional<UserData> opt = userRepo.findUserById(id);

        if (opt.isEmpty()) {
            UserData user = new UserData(id, registerDTO.email(), registerDTO.displayName(), registerDTO.userPassword(), "");
            user.setUserPassword(passwordEncoder.encode(CharBuffer.wrap(registerDTO.userPassword())));

            // in case server error
            boolean success = userRepo.createUser(user);
            boolean success2 = userRepo.createUserSettings(id);
            if (!success && !success2) {
                throw new CreateAccountException();
            } 
            
            // send thank you email
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(user.getEmail());
            email.setSubject("Thank you for Creating an Account!");
            email.setText("No reply needed.");
            mailSender.send(email);

            return user;

        } else {
            return createUser(registerDTO);
        }

    }

    public Optional<UserData> findUserById(String id) {
        return userRepo.findUserById(id);
    }

    public Optional<UserSettings> getUserSettings(String id) {
        return userRepo.getUserSettings(id);
    }

    public boolean updateUserSettings(UserSettings u) {
        return userRepo.updateUserSettings(u);
    }

    public boolean updateUserData(String url, String id) {
        return userRepo.updateUserData(url, id);
    }

    @Transactional(rollbackFor = DeleteAccountException.class)
    public boolean deleteUser(String id) {

        boolean success2 = userRepo.deleteUserSettings(id);
        boolean success = userRepo.deleteUser(id);

        if (!success && !success2 ) {
            throw new DeleteAccountException();
        }

        return true;
    }
}
