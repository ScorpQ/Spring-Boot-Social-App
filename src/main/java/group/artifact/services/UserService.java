package group.artifact.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import group.artifact.entities.Comment;
import group.artifact.entities.Post;
import group.artifact.entities.User;
import group.artifact.repository.CommentRepository;
import group.artifact.repository.LikeRepository;
import group.artifact.repository.PostRepository;
import group.artifact.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    UserService(UserRepository userRepository, CommentRepository commentRepository, PostRepository postRepository, LikeRepository likeRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createNewUser(User newUser) {
        return userRepository.saveAndFlush(newUser);
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
    
    public User updateOneUser(User userData, Long userId) {
        Optional<User> targetUser = userRepository.findById(userId);
        if (targetUser.isPresent()) {
            User user = targetUser.get();
            user.setName(userData.getName());
            user.setEmail(userData.getEmail());
            return userRepository.saveAndFlush(user);
        }
        return null;
    }

    public List<Object> getActivity(Long userId) {
        Optional<User> targetUser = userRepository.findById(userId);
        if(targetUser.isPresent()) {
            List<Post> posts = postRepository.findTopByUserId(userId, Sort.by("createDate").descending());
            // List<Comment> comment = commentRepository.findTopByPostId(postIds, Sort.by("createDate").descending());
            if (!posts.isEmpty()) {  // Posts dolu ise devam et
                List<String> postIds = posts.stream().map(Post::getId).collect(Collectors.toList());
                List<Comment> comments = commentRepository.findUserCommentsByPostId(postIds, Sort.by("createDate").descending());
                //List<Like> likes = likeRepository.(postIds, Sort.by("createDate").descending());
                List<Object> result = new ArrayList<>();
                result.addAll(comments);
                result.addAll(posts);
                return result;
            }
            
            return null;
        }
        return null;
    }

    public String deleteOneUser(Long userId) {
        User deletedUser = this.getUser(userId);
        if (deletedUser != null) {
            userRepository.deleteById(userId);
            return "Deleted";
        }

        return "No User";
    }
}
