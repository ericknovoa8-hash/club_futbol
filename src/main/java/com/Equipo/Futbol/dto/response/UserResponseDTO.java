package com.Equipo.Futbol.dto.response;

import lombok.Data;
/**
 * DTO para enviar los datos de un usuario en una respuesta HTTP.
 */
@Data
public class UserResponseDTO {

    private Long id;

    private String username;

    private String password;

    private String role;

}
