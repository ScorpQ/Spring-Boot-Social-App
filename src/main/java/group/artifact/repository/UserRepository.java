package group.artifact.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import group.artifact.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    Optional<User> findById(Long userId);

}
