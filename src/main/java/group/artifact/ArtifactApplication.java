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
}
