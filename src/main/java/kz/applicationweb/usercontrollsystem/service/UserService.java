package kz.applicationweb.usercontrollsystem.service;


import kz.applicationweb.usercontrollsystem.Model.User;
import kz.applicationweb.usercontrollsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    public void addNewUser(User user)
    {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if(userOptional.isPresent())
        {
            throw new IllegalStateException("Email is already taken");
        }
        userRepository.save(user);
    }

    public void deleteUser(int userId) {
        boolean exists = userRepository.existsById(userId);
        if(!exists)
        {
            throw new IllegalStateException("User with id " + userId + " does not exist");
        }
        userRepository.deleteById(userId);
    }

    public void updateUserById(Integer Id, String name, String surname, String email){
        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalStateException("User with email " + email + " does not exist"));


        if(name != null && name.length() > 0 && !user.getName().equals(name))
        {
            user.setName(name);

            Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
            if(userOptional.isPresent())
            {
                throw new IllegalStateException("Email is already taken");
            }
            user.setEmail(email);
        }
        userRepository.save(user);
    }
}
