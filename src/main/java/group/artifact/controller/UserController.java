package group.artifact.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import group.artifact.entities.User;
import group.artifact.services.UserService;

@RestController // Bunu test et postmande, olmadan retunr edilen data bir html templati mi
                // sunuyor.
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/createUser")
    public User createNewUser(@RequestBody User newUser) {
        return userService.createNewUser(newUser);
    }

    @GetMapping("/getOneUser/{userId}")
    public User getOneUser(@PathVariable Long userId) {
        // getReferenceById kullandığın için optinal bir yapı return etmiyor
        // bu yüzden .orElse() tarzı error handle etme methodu kullanamiyorsun.
        // Try catch falan yapman lazım ama ondan önce hata almaya çalış ne oluyor
        // öğren.
        return userService.getUser(userId);
    }


    @GetMapping("/userActivity")
    public List<Object> getActivity(@RequestParam Long userId) {
        return userService.getActivity(userId);
    }

    @PutMapping("/updateUser/{userId}")
    public User updateOneUser(@RequestBody User user, @PathVariable Long userId) {
        return userService.updateOneUser(user, userId);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public String deleteOne(@PathVariable Long userId) {
        return userService.deleteOneUser(userId);
    }

}
