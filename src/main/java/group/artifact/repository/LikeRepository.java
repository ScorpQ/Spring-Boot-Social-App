package group.artifact.repository;

import java.util.List;
import group.artifact.entities.Like;
import group.artifact.entities.User;
import group.artifact.entities.Post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    
    List<Like> findByUserIdAndPostId(Long userId, String postId);

    List<Like> findByUserId(Long userId);

    List<Like> findByPostId(String postId);
}
