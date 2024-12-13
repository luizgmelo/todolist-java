package com.example.todolist.controller;

import com.example.todolist.dtos.LoginRequestDTO;
import com.example.todolist.dtos.LoginResponseDTO;
import com.example.todolist.dtos.RegisterRequestDTO;
import com.example.todolist.dtos.RegisterResponseDTO;
import com.example.todolist.models.User;
import com.example.todolist.repositories.UserRepository;
import com.example.todolist.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserRepository userRepository;

    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password());

        var auth = authenticationManager.authenticate(usernamePassword);

        var user = (User) auth.getPrincipal();
        var token = tokenService.generateToken(user);
        var response = new LoginResponseDTO(token);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody @Valid RegisterRequestDTO registerDTO) {
        if (userRepository.findUserByEmail(registerDTO.email()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        User newUser = new User(registerDTO.name(), registerDTO.email(), encryptedPassword, registerDTO.role());
        userRepository.save(newUser);

        var token = tokenService.generateToken(newUser);
        var response = new RegisterResponseDTO(token);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
