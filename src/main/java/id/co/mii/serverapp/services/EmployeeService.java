
package id.co.mii.serverapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        User user = userRepository.findById(id).orElse(null);
        employee.setUser(user);
        return employeeRepository.save(employee);
    } 
       
    }



