package kz.applicationweb.usercontrollsystemoop.config;

import kz.applicationweb.usercontrollsystemoop.model.task.TaskStatus;
import kz.applicationweb.usercontrollsystemoop.model.user.Admin;
import kz.applicationweb.usercontrollsystemoop.repository.AdminRepository;
import kz.applicationweb.usercontrollsystemoop.repository.TaskStatusRepository;

import java.util.List;

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
    private final TaskStatusRepository taskStatusRepository;
    private final PasswordEncoder passwordEncoder;

    public DatabaseSeeder(
        AdminRepository adminRepository,
        TaskStatusRepository taskStatusRepository, 
        PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.taskStatusRepository = taskStatusRepository;
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

    @Bean
    public TaskStatus seedTaskStatuses(){
        if(taskStatusRepository.count() == 0){
            taskStatusRepository.saveAll(List.of(
                new TaskStatus("New", "New task"),
                new TaskStatus("In Progress", "Task in progress"),
                new TaskStatus("Done", "Task is done"),
                new TaskStatus("Canceled", "Task is canceled")
            ));
        }
        return null;
    }
}
