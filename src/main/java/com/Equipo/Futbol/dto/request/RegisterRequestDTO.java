package com.Equipo.Futbol.dto.request;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String username;
    private String password;
    private Long rol;
    
}
