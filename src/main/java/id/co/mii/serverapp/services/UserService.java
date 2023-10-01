package id.co.mii.serverapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.repositories.UserRepository;

import java.util.List;
import java.util.Optional;



@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create User
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Read User by ID
    public User getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }
    public List<User>getAllUsers(){
        return userRepository.findAll();
    }

    // // Read All Users
    // public List<User> getAllUsers() {
    //     return userRepository.findAll();
    // }

    // Update User
    public User updateUser(Long id, User newUser) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            existingUser.setUsername(newUser.getUsername());
            existingUser.setPassword(newUser.getPassword());
            return userRepository.save(existingUser);
        }

        return null;
    }

    // Delete User
    public User deleteUser(Long id) {
       User user = getUserById (id);
        userRepository.delete(user);
        return user;
    }

}
