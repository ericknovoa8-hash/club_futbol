package com.Equipo.Futbol.dto.response;

import lombok.Data;

@Data
/**
 * DTO para la respuesta de refresco de token, que incluye el nuevo JWT y un mensaje opcional.
 */
public class RefreshTokenResponseDTO {
    
    private String jwt;

    private String message;
    
}
