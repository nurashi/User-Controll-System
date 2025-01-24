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



    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        try {
            User user = userService.findByUsernameAndPassword(username, password);
            String token = JwtUtil.generateToken(user.getName(),user.isAdmin());

            return ResponseEntity.ok("Login successful! Your token: " + token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
