package group.artifact.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import group.artifact.entities.Post;
import group.artifact.entities.User;
import group.artifact.repository.LikeRepository;
import group.artifact.repository.PostRepository;
import group.artifact.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    UserService(UserRepository userRepository, LikeRepository likeRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
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

    public List<Post> getActivity(Long userId) {
        Optional<User> targetUser = userRepository.findById(userId);
        if(targetUser.isPresent()) {
            List<Post> post = postRepository.findTopByUserId(userId, Sort.by("createDate").descending());
            return post;
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
