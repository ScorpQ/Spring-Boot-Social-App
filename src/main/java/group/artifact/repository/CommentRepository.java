package group.artifact.repository;

import group.artifact.entities.Comment;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, String> {

    List<Comment> findByUserIdAndPostId(Long userId, String postId);

    List<Comment> findByUserId(Long userId);

    List<Comment> findByPostId(String postId);

   @Query("SELECT C FROM Comment C WHERE C.user.id IN (?1)")
    List<Comment> findUserCommentsByPostId(Long userId, Sort sort);
}
