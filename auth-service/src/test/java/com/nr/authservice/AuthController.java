package com.nr.authservice;

import com.nr.authservice.dto.LoginRequestDTO;
import com.nr.authservice.dto.LoginResponseDTO;
import com.nr.authservice.exception.IncorrectLoginCredentials;
import com.nr.authservice.exception.UserNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) throws UserNotFoundException, IncorrectLoginCredentials {
        String token = authService.login(loginRequestDTO);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
