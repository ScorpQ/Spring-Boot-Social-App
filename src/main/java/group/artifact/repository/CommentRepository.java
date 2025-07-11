package group.artifact.repository;

import group.artifact.entities.Comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, String> {

    List<Comment> findByUserIdAndPostId(Long userId, String postId);

    List<Comment> findByUserId(Long userId);

    List<Comment> findByPostId(String postId);
}
