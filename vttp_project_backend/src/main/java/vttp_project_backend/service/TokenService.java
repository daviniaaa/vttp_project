// package vttp_project_backend.service;

// import java.time.Instant;
// import java.time.temporal.ChronoUnit;
// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.oauth2.jwt.JwtClaimsSet;
// import org.springframework.security.oauth2.jwt.JwtEncoder;
// import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
// import org.springframework.stereotype.Service;

// import vttp_project_backend.models.UserSecurity;

// @Service
// public class TokenService {
//     @Autowired private JwtEncoder jwtEncoder;

//     public String generateToken(Authentication auth) {
//         UserSecurity userPrincipal = (UserSecurity) auth.getPrincipal();
//         Instant now = Instant.now();
//         String scope = auth.getAuthorities()
//                 .stream()
//                 .map(ga -> ga.getAuthority())
//                 .collect(Collectors.joining(","));
//         JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
//                 .issuer("davho")
//                 .issuedAt(now)
//                 .expiresAt(now.plus(1, ChronoUnit.HOURS))
//                 .subject(userPrincipal.getUser().getUsername())
//                 .claim("scope", scope)
//                 .build();
//         return jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
//     }
// }
