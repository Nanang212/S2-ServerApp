package id.co.mii.serverapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.request.UserRequest;
import id.co.mii.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private RoleService roleService;

    public List<User> getAll(){
        return userRepository.findAll();
    }

     public User getById(Integer id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "tidak ditemukan id no :" + id));
    }

    public User insertDTO(UserRequest userRequest) {
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Nama pengguna sudah ada");
        }
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());

        List<Role> roles = new ArrayList<>();

        for (Integer roleid : userRequest.getRoleId()) {

            Role findrole = roleService.getById(roleid);
            roles.add(findrole);
        }
        user.setRoles(roles);

        return userRepository.save(user);
    }

    public User update(Integer id, User user){
        getById(id);
        user.setId(id);
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Nama pengguna sudah ada");
        }
        return userRepository.save(user);
    }

    public User delete(Integer id){
        User user = getById(id);
        userRepository.delete(user);
        return user;
    }
}
