package com.Equipo.Futbol.dto.response;

import lombok.Data;

@Data
/**
 * DTO para enviar un mensaje de respuesta en una solicitud HTTP.
 */
public class MessageResponseDTO {
    
    private String message;
}
