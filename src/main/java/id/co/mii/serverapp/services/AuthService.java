package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.requests.RegistrationRequest;
import id.co.mii.serverapp.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private UserRepo userRepo;
    private ModelMapper modelMapper;
    public User registration(RegistrationRequest registrationRequest) {
        User user = modelMapper.map(registrationRequest, User.class);
        Employee employee = modelMapper.map(registrationRequest, Employee.class);
        user.setEmployee(employee);
        employee.setUser(user);
        userRepo.save(user);
        return user;
    }
}
