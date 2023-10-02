
package id.co.mii.serverapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.repositories.EmployeeRepository;
import id.co.mii.serverapp.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;
    public  Employee createEmployee(Employee employee){
        return employeeRepository.save(employee);
        
    }
    public Employee getEmployeeById (Long id){
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        return employeeOptional.orElse(null);
    }
    public List<Employee>getAllEmployees(){
        return employeeRepository.findAll();
    }
    public Employee updatEmployee(Long id, Employee newEmployee){
      getEmployeeById(id);
      newEmployee.setId(id);
      return employeeRepository.save(newEmployee);
      


    }
    public Employee deleteEmployee(Long id){
        Employee  employee = getEmployeeById (id);
        employeeRepository.delete(employee);
        return employee;
    }


    
    public Employee createUserEmployee (Employee employee,Long id){
        User user = userRepository.findById(id).orElseThrow(() ->  new ResponseStatusException(HttpStatus.CONFLICT, "user employee already esist"));
  
        if(user.getEmployee() != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "user employee already esist");
        }

        employee.setUser(user);
        employeeRepository.save(employee);
        
        return employee;
     
    } 
       
    }



