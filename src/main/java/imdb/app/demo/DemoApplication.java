package imdb.app.demo;

import imdb.app.demo.entities.AppUser;
import imdb.app.demo.entities.Authority;
import imdb.app.demo.entities.Role;
import imdb.app.demo.repos.AppUserRepository;
import imdb.app.demo.repos.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepo, AppUserRepository userRepo, PasswordEncoder passwordEncoder) {
		return args -> {
			if (roleRepo.findByAuthority("ADMIN").isPresent()) {
				return;
			}
			roleRepo.save(new Role(Authority.USER.toString()));
			roleRepo.save(new Role(Authority.ADMIN.toString()));
			roleRepo.save(new Role(Authority.CRITIC.toString()));
		};
	}

}
