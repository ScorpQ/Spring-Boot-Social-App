package group.artifact.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import group.artifact.entities.Comment;
import group.artifact.services.CommentService;

@RestController
// @RestController
public class CommentController {

    public CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments")
    public List<Comment> getAllComments(
            @RequestParam("userId") Optional<Long> userId,
            @RequestParam("postId") Optional<String> postId) {
        return commentService.getAllComments(userId, postId);
    }

    @GetMapping("/comments/{commentId}")
    public Comment getComment(@PathVariable String commentId) {
        return commentService.getComment(commentId);
    }

    @PostMapping("/comments")
    public Comment createComment(@RequestBody Comment newComment) {
        return commentService.createComment(newComment);
    }
}