package kz.applicationweb.usercontrollsystemoop.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    public void addNewUser(User user){
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if(userOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        userRepository.save(user);
    }

    public void deleteUser(Integer userId) {
        if(!userRepository.existsById(userId)){
            throw new IllegalStateException("user with id " + userId + " does not exist");
        }
        userRepository.deleteById(userId);
    }


    public void updateUser(Integer id,
                           String name,
                           String surname,
                           String email,
                           String password,
                           String job,
                           String adress,
                           String phone,
                           int age) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                        "user with id " + id + " does not exist"
                ));

        if (name != null &&
                name.length() > 0 &&
                !name.equals(user.getName())) {
            user.setName(name);
        }

        if (surname != null &&
                surname.length() > 0 &&
                !surname.equals(user.getSurname())) {
            user.setSurname(surname);
        }

        if (email != null &&
                email.length() > 0 &&
                !email.equals(user.getEmail())) {
            Optional<User> userOptional = userRepository.findByEmail(email);
            if(userOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            user.setEmail(email);
        }

        if (password != null &&
                password.length() > 0 &&
                !password.equals(user.getPassword())) {
            user.setPassword(password);
        }

        if (job != null &&
                job.length() > 0 &&
                !job.equals(user.getJob())) {
            user.setJob(job);
        }

        if (adress != null &&
                adress.length() > 0 &&
                !adress.equals(user.getAddress())) {
            user.setAddress(adress);
        }

        if (phone != null &&
                phone.length() > 0 &&
                !phone.equals(user.getPhone())) {
            user.setPhone(phone);
        }

        if (age > 0 &&
                age != user.getAge()) {
            user.setAge(age);
        }

        userRepository.save(user);
    }
}
