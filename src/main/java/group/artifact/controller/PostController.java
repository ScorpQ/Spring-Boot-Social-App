package group.artifact.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import group.artifact.dto.PostCreateRequest;
import group.artifact.dto.PostResponse;
import group.artifact.entities.Post;
import group.artifact.services.PostService;

@RestController // Bunu test et postmande, olmadan retunr edilen data bir html templati mi
                // sunuyor.
public class PostController {

    private final PostService postService;

    PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/getOnePost")
    public Post getPost(@RequestParam("postId") String postId) {
        return postService.getPost(postId);
    }

    @GetMapping("/getAllOrUserPosts")
    public List<PostResponse> getAllPosts(@RequestParam("userId") Optional<Long> userId) {
        return postService.getAllPosts(userId);
    }

    @PostMapping("/createPost")
    public Post createPost(@RequestBody PostCreateRequest newPost) {
        return postService.createPost(newPost);
    }

    @PutMapping("/updatePost")
    public Post updatePost(@RequestParam("postId") String postId, @RequestBody PostCreateRequest updatePost) {
        return postService.updatePost(postId, updatePost);
    }
}
