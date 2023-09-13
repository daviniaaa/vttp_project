// package vttp_project_backend.service;

// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import vttp_project_backend.exception.NoExistingEmailException;
// import vttp_project_backend.models.UserData;
// import vttp_project_backend.models.UserSecurity;
// import vttp_project_backend.repo.UserRepository;

// @Service
// public class SecurityService implements UserDetailsService {
//     @Autowired UserRepository userRepo;

//     @Override
//     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//         System.out.println("email >> " + email);
//         Optional<UserData> opt = userRepo.findUserByEmail(email);
//         if (opt.isEmpty()) {
//             System.out.println("help");
//             throw new NoExistingEmailException();
//         }

//         return new UserSecurity(opt.get());
//     }
    
// }
