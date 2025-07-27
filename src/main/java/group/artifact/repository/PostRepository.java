package group.artifact.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import group.artifact.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {

    List<Post> findByUserId(Long id);

}
