package group.artifact.services;

import java.util.List;
import java.util.Optional;
import group.artifact.entities.Comment;
import group.artifact.repository.CommentRepository;

public class CommentService {

    public CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllComments(Optional<Long> userId, Optional<String> postId) {
        if (userId.isPresent() && postId.isPresent()) {
            return commentRepository.findByUserIDAndPostID(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findByUserID(userId.get());
        } else if (postId.isPresent()) {
            return commentRepository.findByPostID(postId.get());
        } else { // postId and userId is not present
            return commentRepository.findAll();
        }
        return null;
    }

    public Comment getComment(String commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }
}
