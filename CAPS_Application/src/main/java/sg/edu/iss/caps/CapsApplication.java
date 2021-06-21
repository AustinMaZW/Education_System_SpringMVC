package sg.edu.iss.caps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sg.edu.iss.caps.login.Admin;
import sg.edu.iss.caps.login.AdminRepository;

@SpringBootApplication
public class CapsApplication {

	@Autowired
	AdminRepository arepo;
	
	public static void main(String[] args) {
		SpringApplication.run(CapsApplication.class, args);
	}
	@Bean
	CommandLineRunner runner() {
		return args -> {
			Admin a = new Admin("admin", "admin", null, "admin", "admin");
			arepo.save(a);
		};
	}

}
