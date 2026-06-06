package com.Equipo.Futbol.dto.response;

import lombok.Data;

@Data
public class JwtResponseDTO {

    private String jwt;

    private String role;
    private String username;

    public JwtResponseDTO(String jwt, String role, String username) {
        this.jwt = jwt;
        this.role = role;
        this.username = username;
    }
    
}
