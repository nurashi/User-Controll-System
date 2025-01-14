package kz.applicationweb.usercontrollsystem.Controller;


import kz.applicationweb.usercontrollsystem.Model.User;
import kz.applicationweb.usercontrollsystem.repository.UserRepository;
import kz.applicationweb.usercontrollsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    public List<User> getUsers() {
        return userService.getUsers();
    }


    @PostMapping("users")
    public void registerNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }


    @DeleteMapping(path = "users/{userId}")
    public void deleteUser(@PathVariable("userId") int userId) {
        userService.deleteUser(userId);
    }

    @PutMapping(path = "users/{id}")
    public void updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
        userService.updateUserById(user.getId(), user.getName(), user.getSurname(), user.getEmail());
    }
}
