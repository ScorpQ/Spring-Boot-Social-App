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

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User createNewUser(User newUser) {
        return userRepository.saveAndFlush(newUser);
    }

    public Optional<User> getOneUser(Long userId) {
        return userRepository.findById(userId);
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
        this.getOneUser(userId).ifPresentOrElse(
                foundUser -> {
                    userRepository.deleteById(userId);
                },
                () -> {
                    throw new RuntimeException("Kullanıcı bulunamadı! ID: " + userId);
                });
    }
}
