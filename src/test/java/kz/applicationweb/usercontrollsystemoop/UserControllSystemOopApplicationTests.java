package kz.applicationweb.usercontrollsystemoop;

import kz.applicationweb.usercontrollsystemoop.model.User;
import kz.applicationweb.usercontrollsystemoop.model.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class UserControllSystemOopApplicationTests {


    @Autowired private UserRepository userRepository;

    @Test
    void contextLoads() {
        User user = new User();
        user.setName("Aisha");
        user.setSurname("Erbolat");
        user.setAge(17);
        user.setEmail("aisha.Erbolat@gmail.com");
        user.setPassword("123123");
        user.setJob("headman");
        user.setPhone("72345678971");
        user.setAddress("Astana, Kazakhstan");
        User savedUser = userRepository.save(user);


        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }


    @Test
    public void testListAll() {
        Iterable<User> users = userRepository.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate() {
        Integer userId = 1;

        Optional<User> optionalUser = userRepository.findById(userId);

        User user = optionalUser.get();
        user.setPassword("Prekol!");
        userRepository.save(user);


        User updatedUser = userRepository.findById(userId).get();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("Prekol!");
    }


    @Test
    public void testGet() {
        Integer userId = 1;
        Optional<User> optionalUser = userRepository.findById(userId);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }


    @Test
    public void testDelete() {
        Integer userId = 1;
        userRepository.deleteById(userId);

        Optional<User> optionalUser = userRepository.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}
