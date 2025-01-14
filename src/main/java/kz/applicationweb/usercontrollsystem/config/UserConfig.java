package kz.applicationweb.usercontrollsystem.config;


import kz.applicationweb.usercontrollsystem.Model.User;
import kz.applicationweb.usercontrollsystem.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner cmdRunner(UserRepository repository) {
        return args -> {
            User user1 = new User(
                    1,
                    "Bake",
                    "Sake",
                    "Bake.Sake@gmail.com",
                    "examplePassword",
                    "1990-01-01",
                    true,
                    "Software Developer",
                    "123 Main St, New York, NY 10030",
                    "1234567890",
                    "2021-01-01",
                    17
            );
            User user2 = new User(
                    2,
                    "Balenbay",
                    "Balenbayev",
                    "Balenbay.Balenbay@gmail.com",
                    "balenbay123",
                    "1990-02-01",
                    true,
                    "Lecturer",
                    "123 Main St,Astana, Infinity country 10030",
                    "1231321321",
                    "2020-01-01",
                    20
            );

            repository.saveAll(
                List.of(user1, user2)
            );
        };
    }

}
