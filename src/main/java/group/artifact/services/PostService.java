package group.artifact.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import group.artifact.dto.PostCreateRequest;
import group.artifact.entities.Post;
import group.artifact.repository.PostRepository;

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

    // Yeni bir post create ederken DTO yapısı kullandık çünkü
    // Post nesnesinde user_id tutulmuyor. DTO ile işi çözmeye çalıştık.
    // Ama aynı durumu Comment nesnesi için kod ile çözdük
    public Post createPost(PostCreateRequest createPost) {
        return userService.getOneUser(createPost.getUserId()).map(foundUser -> {
            Post newPost = new Post();
            newPost.setText(createPost.getText());
            newPost.setTitle(createPost.getTitle());
            newPost.setUser(foundUser);
            return postRepository.save(newPost);
        })
                .orElse(null);
    }

    public Post updatePost(Long postId, PostCreateRequest createPost) {
        Optional<Post> targetPost = postRepository.findById(postId);
        if (targetPost.isPresent()) {
            Post post = targetPost.get();
            post.setText(createPost.getText());
            post.setTitle(createPost.getTitle());
            return postRepository.save(post);
        }
        return null;
    }

}
