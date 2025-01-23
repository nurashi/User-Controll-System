package kz.applicationweb.usercontrollsystemoop.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.time.LocalDate;
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
    public void registerNewUser(@RequestBody User user){
        userService.addNewUser(user);
    }


    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
    }


    @PutMapping("/users{id}")
    public void updateUser(
            @PathVariable Integer userId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) int age,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String job,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String phone
    ){
        userService.updateUser(userId, name, surname, age, email, password, job, address, phone);
    }
}
