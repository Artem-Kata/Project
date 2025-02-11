package com.bank.authorization.Controllers;

import com.bank.authorization.Security.JWTTokenProvider;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/authorization/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JWTTokenProvider tokenProvider;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JWTTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            log.info("Attempting login for profileId: {}", loginRequest.getProfileId());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getProfileId(),
                            loginRequest.getPassword()
                    )
            );
            // Здесь мы используем profileId как "username"
            String jwt = tokenProvider.generateToken(loginRequest.getProfileId());
            Map<String, String> response = new HashMap<>();
            response.put("token", jwt);
            log.info("Login successful for profileId: {}", loginRequest.getProfileId());
            return ResponseEntity.ok(response);
        } catch (AuthenticationException ex) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid credentials");
            errorResponse.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

    // Вложенный DTO для логина
    @Getter
    @Setter
    public static class LoginRequest {
        private String profileId;
        private String password;
    }
}
