package group.artifact.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import group.artifact.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
