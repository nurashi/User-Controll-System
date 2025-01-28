package kz.applicationweb.usercontrollsystemoop.model;


import kz.applicationweb.usercontrollsystemoop.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandlineRunner(UserRepository userRepository) {
        return args -> {
            userRepository.saveAll(
                    List.of()
            );
        };
    }
}