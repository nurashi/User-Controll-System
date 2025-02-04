package kz.applicationweb.usercontrollsystemoop.model;


import kz.applicationweb.usercontrollsystemoop.model.validation.CustomValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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

    public void addNewUser(User user) {
        StringBuilder errorMessages = new StringBuilder();

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            errorMessages.append("Email is already taken. ");
        }

        if (userRepository.findByPhone(user.getPhone()).isPresent()) {
            errorMessages.append("Phone number is already taken. ");
        }

        if (errorMessages.length() > 0) {
            throw new CustomValidationException(errorMessages.toString().trim());
        }

        userRepository.save(user);
    }


    public void deleteUser(Integer userId) {
        if(!userRepository.existsById(userId)){
            throw new IllegalStateException("user with id " + userId + " does not exist");
        }
        userRepository.deleteById(userId);
    }


    public void updateUser(Integer id, String name, String surname, int age, String email, String password, String job, String phone, String address) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("User with ID " + id + " does not exist"));

        if (name != null && !name.isEmpty()) {
            existingUser.setName(name);
        }
        if (surname != null && !surname.isEmpty()) {
            existingUser.setSurname(surname);
        }
        if (age > 0) {
            existingUser.setAge(age);
        }
        if (email != null && !email.isEmpty()) {
            Optional<User> userOptional = userRepository.findByEmail(email);
            if (userOptional.isPresent() && !userOptional.get().getId().equals(id)) {
                throw new IllegalStateException("Email is already taken");
            }
            existingUser.setEmail(email);
        }
        if (password != null && !password.isEmpty()) {
            existingUser.setPassword(password);
        }
        if (job != null && !job.isEmpty()) {
            existingUser.setJob(job);
        }
        if (phone != null && !phone.isEmpty()) {
            Optional<User> userWithPhone = userRepository.findByPhone(phone);
            if (userWithPhone.isPresent() && !userWithPhone.get().getId().equals(id)) {
                throw new IllegalStateException("Phone number is already taken");
            }
            existingUser.setPhone(phone);
        }
        if (address != null && !address.isEmpty()) {
            existingUser.setAddress(address);
        }

        userRepository.save(existingUser);
    }


    public List<User> searchUsers(String name, String address) {
        if ((name == null || name.isEmpty()) && (address == null || address.isEmpty())) {
            throw new IllegalArgumentException("at list one parameter should be provided");
        }

        return userRepository.searchUsersByNameOrAddress(name, address);
    }
}
