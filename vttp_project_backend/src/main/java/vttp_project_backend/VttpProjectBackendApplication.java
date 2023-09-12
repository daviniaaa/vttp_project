package vttp_project_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VttpProjectBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(VttpProjectBackendApplication.class, args);
	}

}
