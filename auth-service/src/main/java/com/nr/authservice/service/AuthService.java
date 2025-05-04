package com.nr.authservice.service;

import com.nr.authservice.dto.LoginRequestDTO;
import com.nr.authservice.exception.IncorrectLoginCredentials;
import com.nr.authservice.exception.UserNotFoundException;
import com.nr.authservice.model.User;
import com.nr.authservice.repository.UserRepository;
import com.nr.authservice.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public String login(@Valid LoginRequestDTO loginRequestDTO) throws UserNotFoundException, IncorrectLoginCredentials {
        User user = userRepository.findByUserName(loginRequestDTO.userName())
                .orElseThrow(
                        () -> {
                            String message = String.format("User with name %s not found", loginRequestDTO.userName());
                            log.error(message);
                            return new UserNotFoundException(message);
                        }
                );
        if (!loginRequestDTO.password().equals(user.getPassword())) {
            throw new IncorrectLoginCredentials("Either username or password is incorrect");
        }
        return jwtUtil.generateToken(user.getId().toString(), user.getUserName(), user.getRoles());
    }
}
