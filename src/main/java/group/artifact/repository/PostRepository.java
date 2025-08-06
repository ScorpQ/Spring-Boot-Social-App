package group.artifact.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import group.artifact.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {

    List<Post> findByUserId(Long id);

    @Query("SELECT P FROM Post P WHERE P.user.id = ?1")
    List<Post> findTopByUserId(Long id);

    @Query("SELECT text, title, likeCount FROM Post P WHERE P.user.id = ?1")
    List<Post> findTopByUserId(Long id, Sort sort);
    
}
