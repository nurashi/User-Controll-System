package kz.applicationweb.usercontrollsystemoop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kz.applicationweb.usercontrollsystemoop.dto.request.AuthRequest;
import kz.applicationweb.usercontrollsystemoop.dto.request.RegisterRequest;
import kz.applicationweb.usercontrollsystemoop.dto.response.AuthResponse;
import kz.applicationweb.usercontrollsystemoop.model.user.User;
import kz.applicationweb.usercontrollsystemoop.service.AuthService;
import kz.applicationweb.usercontrollsystemoop.util.UserFactory;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth")
public class AuthController {

    private final AuthService authService;
    private final UserFactory userFactory;

    @Autowired
    public AuthController(AuthService authService, UserFactory userFactory) {
        this.authService = authService;
        this.userFactory = userFactory;
    }

    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        User user = userFactory.createUser(request);
        return authService.register(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthRequest request) {
        return authService.login(request);
    }
}
