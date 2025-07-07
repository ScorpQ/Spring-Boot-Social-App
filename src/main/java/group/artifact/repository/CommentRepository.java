package group.artifact.repository;

import group.artifact.entities.Comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, String> {

    List<Comment> findByUserIDAndPostID(Long userId, String postId);

    List<Comment> findByUserID(Long userId);

    List<Comment> findByPostID(String postId);
}
