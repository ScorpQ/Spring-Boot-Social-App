package group.artifact.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import group.artifact.dto.PostCreateRequest;
import group.artifact.dto.PostResponse;
import group.artifact.entities.Post;
import group.artifact.repository.PostRepository;
import group.artifact.entities.User;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        if (userId.isPresent()) {
            List<Post> findedPosts = postRepository.findByUserId(userId.get());
            return findedPosts.stream().map(PostResponse::new).toList();
        }
        List<Post> findedPosts = postRepository.findAll();
        return findedPosts.stream().map(PostResponse::new).toList();
    }

    public Post getPost(String postId) {
        return postRepository.findById(postId).orElse(null);
    }

    // Yeni bir post create ederken DTO yapısı kullandık çünkü
    // Post nesnesinde user_id tutulmuyor. DTO ile işi çözmeye çalıştık.
    // Ama aynı durumu Comment nesnesi için kod ile çözdük
    public Post createPost(PostCreateRequest createPost) {
        User user = userService.getUser(createPost.getUserId());
        if (user == null) {
            throw new RuntimeException("Kullanıcı bulunamadı! ID: " + createPost.getUserId());
        }

        Post newPost = new Post();
        newPost.setText(createPost.getText());
        newPost.setTitle(createPost.getTitle());
        newPost.setUser(user);
        return postRepository.save(newPost);
    }

    public Post updatePost(String postId, PostCreateRequest createPost) {
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
