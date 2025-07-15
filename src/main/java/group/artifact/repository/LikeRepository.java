package group.artifact.repository;

import java.util.List;
import group.artifact.entities.Like;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByUserAndPostId(Long userId, String postId);

    List<Like> findByUserId(Long userId);

    List<Like> findByPostId(String postId);
}
