package kz.applicationweb.usercontrollsystemoop.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }


    @PostMapping("/users")
    public void registerNewUser(User user){
        userService.addNewUser(user);
    }

    @DeleteMapping("/users")
    public void deleteUser(Integer userId){
        userService.deleteUser(userId);
    }

    @PutMapping("/users")
    public void updateUser(Integer userId, String name, String surname, String email, String password, String job, String adress, String phone, int age){
        userService.updateUser(userId, name, surname, email, password, job, adress, phone, age);
    }
}
