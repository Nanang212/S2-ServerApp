package id.co.mii.serverapp.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.dto.request.RegistrationRequest;
import id.co.mii.serverapp.services.EmployeeService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class RegistrationController {

    EmployeeService employeeService;

    @GetMapping("/register")
    public String createView(RegistrationRequest registrationRequest,
            @RequestParam(name = "uuid", required = false) String uuid, Model model) {
        Employee employee = employeeService.findByUuid(uuid);
        if (employee.getUser().getIsEnabled()) {
            return "404";
        }
        model.addAttribute("id", employee.getUser().getId());
        return "form";
    }

    @PostMapping("/register/{id}")
    public ResponseEntity<String> create(@ModelAttribute RegistrationRequest registrationRequest,
            @PathVariable String id) {
        // Proses form dan data yang diterima dari form di sini
        // Jika berhasil, kirim respons yang sesuai
        // Misalnya:

        employeeService.update(Integer.parseInt(id), registrationRequest);

        return ResponseEntity.ok("Registration successful!"); // Pesan sukses yang akan ditampilkan di halaman
    }
}
