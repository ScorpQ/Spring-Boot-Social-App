package group.artifact.services;

import java.lang.foreign.Linker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import group.artifact.dto.PostCreateRequest;
import group.artifact.entities.Post;
import group.artifact.repository.PostRepository;
import group.artifact.repository.UserRepository;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public List<Post> getAllPosts(Optional<Long> userId) {
        if (userId.isPresent()) {
            return postRepository.findByUserId(userId.get());
        }
        return postRepository.findAll();
    }

    public Post getPost(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createPost(PostCreateRequest createPost) {
        userService.getOneUser(createPost.getUserId()).ifPresentOrElse(foundUser -> {
            Post newPost = new Post();
            newPost.setText(null);
            return postRepository.save(newPost);
        },
                () -> {
                    return null;
                });

    }

}
