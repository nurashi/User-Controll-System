package kz.applicationweb.usercontrollsystemoop.model;


import io.jsonwebtoken.Jwts;
import kz.applicationweb.usercontrollsystemoop.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
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
    public void deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
    }


    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable("id") Integer id, @RequestBody User user){
        userService.updateUser(user.getId(), user.getName(), user.getSurname(), user.getAge(), user.getEmail(), user.getPassword(), user.getJob(), user.getPhone(), user.getAddress());
    }


    @GetMapping("/users/search")
    public List<User> searchUsers(@RequestParam(required = false) String name,
                                  @RequestParam(required = false) String address) {
        return userService.searchUsers(name, address);
    }


}
