package com.Equipo.Futbol.dto.request;

import lombok.Data;

@Data
/**
 * DTO para recibir los datos de un jugador en una solicitud HTTP.
 */
public class PlayerRequestDTO {

    private String name;

    private String positionPlayer;
    
    private int age;
    
}
