package co.id.ms.mii.serverapp.services;

import co.id.ms.mii.serverapp.models.User;
import co.id.ms.mii.serverapp.models.User;
import co.id.ms.mii.serverapp.models.User;
import co.id.ms.mii.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public List<User> getall(){
        return userRepository.findAll();
    }

    public User getById(Integer id){
        return userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"id User is not found!!!")
        );
    }

    public User create (User user){
        if(userRepository.existsByUsername(user.getUsername())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Username already exists!!!");
        }

        return userRepository.save(user);
    }

    public User update(User user, Integer id){
        if(userRepository.existsByUsername(user.getUsername())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Name User already exists!!!");
        }
        getById(id);
        user.setId(id);
        return userRepository.save(user);
    }
    public User delete(Integer id) {
        User user = getById(id);
        userRepository.delete(user);
        return user;
    }
}
