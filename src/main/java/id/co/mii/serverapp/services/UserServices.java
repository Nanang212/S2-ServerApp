package id.co.mii.serverapp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.UserDTO;
import id.co.mii.serverapp.models.request.CreateUserRequest;
import id.co.mii.serverapp.models.request.UpdateUserRequest;
import id.co.mii.serverapp.repositories.UserRepositories;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServices {
    private UserRepositories userRepositories;

    public List<UserDTO> getAll() {
        List<User> user = userRepositories.findAll();
        return user.stream()
                .map(users -> {
                    UserDTO userDTO = new UserDTO();
                    BeanUtils.copyProperties(users, userDTO);
                    return userDTO;
                })
                .collect(Collectors.toList());
    }

    // get by id reguler
    public User getById(Integer id) {
        return userRepositories.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
    

    // get by id melalui dto
    public UserDTO getByIdWithDTO(Integer id) {
        User user = userRepositories.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    // public UserDTO createUser(CreateUserRequest request) {
    //     User user = new User();
    //     user.setUsername(request.getUsername());
    //     user.setPassword(request.getPassword()); // Atur password dari request
    //     userRepositories.save(user);
    //     UserDTO userDTO = new UserDTO();
    //     BeanUtils.copyProperties(user, userDTO);
    //     return userDTO;
    // }

    public UserDTO createUser(CreateUserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        userRepositories.save(user);
    
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
    
        return userDTO;
    }
    

    public UserDTO updateUser(Integer id, UpdateUserRequest request){
        User user = getById(id);
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        userRepositories.save(user);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }



    public void delete(Integer id) {
        User user = userRepositories.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        userRepositories.delete(user);
    }


}
