package com.Equipo.Futbol.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.Equipo.Futbol.dto.request.LoginRequestDTO;
import com.Equipo.Futbol.dto.request.RegisterRequestDTO;
import com.Equipo.Futbol.dto.response.JwtResponseDTO;
import com.Equipo.Futbol.dto.response.LoginResponseDTO;
import com.Equipo.Futbol.dto.response.MessageResponseDTO;
import com.Equipo.Futbol.dto.response.RefreshTokenResponseDTO;
import com.Equipo.Futbol.entity.User;
import com.Equipo.Futbol.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

    public MessageResponseDTO register(RegisterRequestDTO request) {
        MessageResponseDTO response = new MessageResponseDTO();
        response.setMessage("Registro exitoso");

        if (userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new RuntimeException("Este usuario ya esta en uso");

        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRol());
        userRepository.save(user);
        return response;
    }

    public JwtResponseDTO login(LoginRequestDTO request) {
    

        Optional<User> user = userRepository.findByUsername(request.getUsername());

     
        if (user.isEmpty() && request.getUsername() !=null) {
            throw new RuntimeException("Este usuario no se encontro en el registro");
        }
        User userFound = user.get(); // Obtenemos el usuario encontrado, si el usuario no existe se lanza una excepción indicando que el usuario no se encuentra registrado
    
        if (!passwordEncoder.matches(request.getPassword(), userFound.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }
        String jwt = jwtService.generateToken(userFound.getId(), userFound.getRole(), userFound.getUsername());
       
        
        return new JwtResponseDTO(jwt, userFound.getRole(), userFound.getUsername());

    }
    public RefreshTokenResponseDTO refreshToken(String token) throws Exception {
        String jwt = jwtService.refreshToken(token);
        RefreshTokenResponseDTO response = new RefreshTokenResponseDTO();
        response.setMessage("Ok");
        response.setJwt(jwt);
        return response;
    }
    
}
