package kz.applicationweb.usercontrollsystemoop.service;

import kz.applicationweb.usercontrollsystemoop.dto.request.AuthRequest;
import kz.applicationweb.usercontrollsystemoop.dto.response.AuthResponse;
import kz.applicationweb.usercontrollsystemoop.model.user.User;

public interface AuthService {
    AuthResponse register(User user);

    AuthResponse login(AuthRequest request);
}
