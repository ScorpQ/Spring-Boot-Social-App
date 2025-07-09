package group.artifact.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import group.artifact.entities.User;
import group.artifact.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    public void deleteOneUser(Long userId) {
        User deletedUser = this.getUser(userId);
        if (deletedUser != null) {
            userRepository.deleteById(userId);
        }

    }
}
