package com.Equipo.Futbol.dto.response;

import lombok.Data;

@Data
/**
 * DTO para la respuesta de login, que incluye el JWT generado y un mensaje opcional.
 */
public class LoginResponseDTO {

    private String jwt;

    private String message;

    
}
