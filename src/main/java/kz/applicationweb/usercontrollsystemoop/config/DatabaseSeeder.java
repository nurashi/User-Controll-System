package kz.applicationweb.usercontrollsystemoop.config;

import kz.applicationweb.usercontrollsystemoop.model.user.Admin;
import kz.applicationweb.usercontrollsystemoop.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Bean;

@Configuration
public class DatabaseSeeder {

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword;

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public DatabaseSeeder(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public Admin seedAdminUser() {
        if (adminRepository.findByEmail(adminEmail).isEmpty()) {
            Admin admin = new Admin(adminEmail, passwordEncoder.encode(adminPassword));
            adminRepository.save(admin);
            return admin;
        }
        return null;
    }
}
