package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.dto.requests.LoginRequest;
import id.co.mii.serverapp.models.dto.requests.RegistrationRequest;
import id.co.mii.serverapp.models.dto.responses.LoginResponse;
import id.co.mii.serverapp.models.dto.responses.RegistrationResponse;
import id.co.mii.serverapp.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
  private AuthService authService;

  @PostMapping("/registration")
  public ResponseEntity<RegistrationResponse> registration(@RequestBody RegistrationRequest registrationRequest) {
    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(authService.registration(registrationRequest));
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> registration(@RequestBody LoginRequest loginRequest) {
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(authService.login(loginRequest));
  }
}
