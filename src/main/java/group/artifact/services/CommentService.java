package group.artifact.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import group.artifact.entities.User;
import group.artifact.entities.Post;
import group.artifact.entities.Comment;
import group.artifact.repository.CommentRepository;

@Service
public class CommentService {

    public CommentRepository commentRepository;
    public UserService userService;
    public PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Comment> getAllComments(Optional<Long> userId, Optional<String> postId) {
        if (userId.isPresent() && postId.isPresent()) {
            return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return commentRepository.findByPostId(postId.get());
        } else { // postId and userId is not present
            return commentRepository.findAll();
        }
    }

    public Comment getComment(String commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    // Yeni bir post create ederken DTO yapısı kullanmıştık çünkü
    // Post nesnesinde user_id tutulmuyordu. Aynı durum Comment nesnesi
    // için de geçerli Postmand'den sadece user_id ve post_id göndermemiz lazım
    // Post ve User nesnesinin tamamını değil.
    // Ama tam anlamak için Comment nesnesi kullanıp user_id ve post_id göndermeyi
    // deneyebilirsin.....
    public Comment createComment(Comment newComment) {
        User user = userService.getUser(newComment.getUser().getId());
        Post post = postService.getPost(newComment.getPost().getId());

        if (user != null) {
            return newComment;
        }
        if (post != null) {
            return newComment;
        }

        commentRepository.save(newComment);
        return newComment;
    }
}
