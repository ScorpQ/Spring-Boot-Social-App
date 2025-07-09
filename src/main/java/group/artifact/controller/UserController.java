package group.artifact.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import group.artifact.entities.User;
import group.artifact.repository.UserRepository;
import group.artifact.services.UserService;

@RestController // Bunu test et postmande, olmadan retunr edilen data bir html templati mi
                // sunuyor.
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/user")
    public User createNewUser(@RequestBody User newUser) {
        return userService.createNewUser(newUser);
    }

    @GetMapping("/user/{userId}")
    public User getOneUser(@PathVariable Long userId) {
        // getReferenceById kullandığın için optinal bir yapı return etmiyor
        // bu yüzden .orElse() tarzı error handle etme methodu kullanamiyorsun.
        // Try catch falan yapman lazım ama ondan önce hata almaya çalış ne oluyor
        // öğren.
        return userService.getUser(userId);
    }

    @PutMapping("/user/{userId}")
    public User updateOneUser(@RequestBody User user, @PathVariable Long userId) {
        return userService.updateOneUser(user, userId);
    }

    @DeleteMapping("/user/{userId}")
    public void deleteOne(@PathVariable Long userId) {
        userRepository.deleteById(userId);
    }

}
