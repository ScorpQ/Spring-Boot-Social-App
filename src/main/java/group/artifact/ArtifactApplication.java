package group.artifact;

import java.util.NoSuchElementException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import group.artifact.entities.User;
import group.artifact.repository.UserRepository;

@SpringBootApplication
public class ArtifactApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtifactApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(UserRepository userRepository) throws Exception {
		return (args) -> {
			User u1 = new User();
			u1.setName("John");
			userRepository.save(u1);

			User saved = userRepository.findById(u1.getId()).orElseThrow(NoSuchElementException::new);
		};
	}
}
