package vttp_project_backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Autowired private UserAuthentication userAuth;
    
    @Bean
    public SecurityFilterChain web(HttpSecurity http) throws Exception {

        http
            .addFilterBefore(new JwtAuthenticationFilter(userAuth), BasicAuthenticationFilter.class)
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(requests -> requests
                // .requestMatchers("/**").permitAll())
                .requestMatchers("/api/createbooth", "/api/settings").authenticated()
                .anyRequest().permitAll());
            
            // .addFilterAt(new JwtAuthenticationFilter(userAuth), BasicAuthenticationFilter.class)
            // .httpBasic(null);
        return http.build();
    }
}
