package com.Equipo.Futbol.dto.request;

import lombok.Data;
/**
 * DTO para recibir los datos de un usuario en una solicitud HTTP.
 */
@Data
public class UserRequestDTO {

    private String username;

    private String password;

    private String role;
    
}
