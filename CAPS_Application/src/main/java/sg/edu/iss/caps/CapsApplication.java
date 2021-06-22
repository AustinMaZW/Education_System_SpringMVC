package sg.edu.iss.caps;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CapsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapsApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
		};
	}

}
