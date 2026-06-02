package com.Equipo.Futbol.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Equipo.Futbol.dto.request.LoginRequestDTO;
import com.Equipo.Futbol.dto.request.RegisterRequestDTO;
import com.Equipo.Futbol.dto.response.LoginResponseDTO;
import com.Equipo.Futbol.dto.response.MessageResponseDTO;
import com.Equipo.Futbol.dto.response.RefreshTokenResponseDTO;
import com.Equipo.Futbol.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
/**
 * Controlador para manejar las operaciones de autenticación, incluyendo registro, inicio de sesión y renovación de tokens.
 * @param request
 * @return
 */
public class AuthController {
    /**
     * Servicio de autenticación que maneja la lógica de negocio relacionada con el registro, inicio de sesión y renovación de tokens.
     */
    private final AuthService authService;
    /**
     *  Maneja la solicitud de registro, creando un nuevo usuario en el sistema con los datos proporcionados en el cuerpo de la solicitud.
      * @param request
     */
    @PostMapping("/register")
    public ResponseEntity<MessageResponseDTO register(@RequestBody RegisterRequestDTO request) {
        try {
            MessageResponseDTO response = authService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    /**
     * Maneja la solicitud de inicio de sesión, validando las credenciales del usuario y generando un token JWT si son correctas.
     * @param request
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        /**
         * Intenta autenticar al usuario con las credenciales proporcionadas. Si la autenticación es exitosa, devuelve un token JWT en la respuesta. Si ocurre algún error durante el proceso, se captura la excepción y se devuelve una respuesta de error adecuada.
         */
        try {
            LoginResponseDTO response = authService.login(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    /**
     * Maneja la solicitud de renovación de token, verificando el token JWT proporcionado en el encabezado de autorización y generando un nuevo token si el original es válido pero ha expirado.
     * @param request
     * @return
     */
    @GetMapping("/refresh-token")
    public ResponseEntity<RefreshTokenResponseDTO> refreshToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        String token = authHeader.substring(7);
        RefreshTokenResponseDTO response = new RefreshTokenResponseDTO();

        try {
            response = authService.refreshToken(token);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (RuntimeException e) {
            response.setMessage("Token expired");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}