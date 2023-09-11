package vttp_project_backend.service;

import java.util.List;
import java.util.Optional;
// import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import vttp_project_backend.exception.CreateAccountException;
// import vttp_project_backend.exception.ExistingEmailException;
// import vttp_project_backend.exception.NoExistingEmailException;
// import vttp_project_backend.exception.PasswordsDoNotMatchException;
// import vttp_project_backend.exception.WrongPasswordException;
import vttp_project_backend.models.EventDetails;
// import vttp_project_backend.models.UserData;
import vttp_project_backend.repo.EventRepository;
// import vttp_project_backend.repo.UserRepository;

@Service
public class VttpProjectService {
    // @Autowired private UserRepository userRepo;
    @Autowired private EventRepository eventRepo;

    // public String createUser(UserRegistration ur) {
    //     // check if passwords match
    //     if (!ur.getUserPassword().equals(ur.getconfirmPassword())) {
    //         throw new PasswordsDoNotMatchException();
    //     }

    //     // check for existing email
    //     Optional<UserData> existingEmail = userRepo.findUserByEmail(ur.getEmail());
    //     if (existingEmail.isPresent()) {
    //         throw new ExistingEmailException();
    //     }

    //     UserData u = new UserData();

    //     // ensures that no duplicate id
    //     String id = UUID.randomUUID().toString().substring(0, 8);
    //     Optional<UserData> opt = userRepo.findUserById(id);
        
    //     if (opt.isEmpty()) {
    //         u.setUserDataId(id);
    //         u.setDisplayName(ur.getDisplayName());
    //         u.setEmail(ur.getEmail());
    //         u.setUserPassword(ur.getUserPassword());

    //         // in case server error
    //         boolean success = userRepo.createUser(u);
    //         if (!success) {
    //             throw new CreateAccountException();
    //         } 
            
    //         return id;

    //     } else {
    //         return createUser(ur);
    //     }
    // }
    
    // public UserData login(UserData u) {
    //     String email = u.getEmail();
    //     String password = u.getUserPassword();
    //     if(userRepo.findUserByEmail(email).isEmpty()) {
    //         throw new NoExistingEmailException();
    //     }

    //     Optional<UserData> opt = userRepo.login(email, password);
    //     if(opt.isEmpty()) {
    //         throw new WrongPasswordException();
    //     }

    //     return opt.get();
    // }

    public List<EventDetails> getEvents() {
        return eventRepo.getEvents();
    }

    public List<EventDetails> search(String[] searchTerms) {
        return eventRepo.search(searchTerms);
    }

    public EventDetails getEventById(String id) {
        Optional<EventDetails> opt = eventRepo.getEventById(id);

        if (opt.isEmpty()) {
            return null;
        }

        return opt.get();
    }

}
