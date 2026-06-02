package com.Equipo.Futbol.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Equipo.Futbol.dto.request.LoginRequestDTO;
import com.Equipo.Futbol.dto.request.RegisterRequestDTO;
import com.Equipo.Futbol.dto.response.LoginResponseDTO;
import com.Equipo.Futbol.dto.response.MessageResponseDTO;
import com.Equipo.Futbol.dto.response.RefreshTokenResponseDTO;
import com.Equipo.Futbol.entity.User;
import com.Equipo.Futbol.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class AuthService {

    
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public MessageResponseDTO register(RegisterRequestDTO request) {
        MessageResponseDTO response = new MessageResponseDTO();
        response.setMessage("Registro exitoso");

        if (userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new RuntimeException("Este usuario ya esta en uso");

        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRole(String.valueOf(request.getRol()));
        userRepository.save(user);
        return response;
    }

    public LoginResponseDTO login(LoginRequestDTO request) {
        LoginResponseDTO response = new LoginResponseDTO();
        Optional<User> user = userRepository.findByUsername(request.getUsername());

        if (user.isEmpty() && request.getUsername() !=null) {
            response.setMessage("Este usuario no se encontro en el registro");
            return response;
        }
        User userFound = user.get(); // Obtenemos el usuario encontrado, si el usuario no existe se lanza una excepción indicando que el usuario no se encuentra registrado
        if (!passwordEncoder.matches(request.getPassword(), userFound.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }
        String jwt = jwtService.generateToken(userFound.getId(), Long.valueOf(userFound.getRole()), userFound.getUsername());
        response.setMessage("Inicio sesión Exitoso ");
        response.setJwt(jwt);
        return response;

    }
    public RefreshTokenResponseDTO refreshToken(String token) throws Exception {
        String jwt = jwtService.refreshToken(token);
        RefreshTokenResponseDTO response = new RefreshTokenResponseDTO();
        response.setMessage("Ok");
        response.setJwt(jwt);
        return response;
    }
    
}
