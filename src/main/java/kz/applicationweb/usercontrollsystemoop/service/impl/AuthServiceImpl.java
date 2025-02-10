package kz.applicationweb.usercontrollsystemoop.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.TransactionScoped;
import kz.applicationweb.usercontrollsystemoop.dto.request.AuthRequest;
import kz.applicationweb.usercontrollsystemoop.dto.response.AuthResponse;
import kz.applicationweb.usercontrollsystemoop.exception.IncorrectCredentialsException;
import kz.applicationweb.usercontrollsystemoop.exception.UserNotFoundException;
import kz.applicationweb.usercontrollsystemoop.model.user.Employee;
import kz.applicationweb.usercontrollsystemoop.model.user.Student;
import kz.applicationweb.usercontrollsystemoop.model.user.User;
import kz.applicationweb.usercontrollsystemoop.repository.AdminRepository;
import kz.applicationweb.usercontrollsystemoop.repository.EmployeeRepository;
import kz.applicationweb.usercontrollsystemoop.repository.StudentRepository;
import kz.applicationweb.usercontrollsystemoop.service.AuthService;
import kz.applicationweb.usercontrollsystemoop.util.JwtUtil;
import kz.applicationweb.usercontrollsystemoop.util.ReflectionUtils;

@Service
@TransactionScoped
public class AuthServiceImpl implements AuthService {

    private final EmployeeRepository employeeRepository;
    private final StudentRepository studentRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthServiceImpl(EmployeeRepository employeeRepository, StudentRepository studentRepository,
            AdminRepository adminRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.employeeRepository = employeeRepository;
        this.studentRepository = studentRepository;
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public AuthResponse register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user instanceof Employee) {
            employeeRepository.save((Employee) user);
        } else if (user instanceof Student) {
            studentRepository.save((Student) user);
        }
        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token);
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        User user = employeeRepository.findByEmail(request.getEmail()).orElse(null);
        if (user == null) {
            user = studentRepository.findByEmail(request.getEmail()).orElse(null);
        }
        if (user == null) {
            user = adminRepository.findByEmail(request.getEmail()).orElse(null);
        }
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            String token = jwtUtil.generateToken(user);
            return new AuthResponse(token);
        } else {
            throw new IncorrectCredentialsException();
        }
    }

    @Override
    public Map<String, String> me(String token) {
        String role = jwtUtil.extractRole(token);
        try {

            if (role.equals("admin")) {
                return ReflectionUtils
                        .convertUsingReflection(adminRepository.findByEmail(jwtUtil.extractUsername(token))
                                .orElseThrow(() -> new UserNotFoundException("User not found")));
            } else if (role.equals("employee")) {
                return ReflectionUtils
                        .convertUsingReflection(employeeRepository.findByEmail(jwtUtil.extractUsername(token))
                                .orElseThrow(() -> new UserNotFoundException("User not found")));
            } else if (role.equals("student")) {
                return ReflectionUtils
                        .convertUsingReflection(studentRepository.findByEmail(jwtUtil.extractUsername(token))
                                .orElseThrow(() -> new UserNotFoundException("User not found")));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
