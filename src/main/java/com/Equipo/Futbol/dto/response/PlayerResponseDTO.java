package com.Equipo.Futbol.dto.response;

import lombok.Data;

@Data
/**
 * DTO de respuesta para representar la información de un jugador.
 * Contiene los campos idPlayer, name, PositionPlayer y age.
 */
public class PlayerResponseDTO {
    
    private Long idPlayer;

    private String name;

    private String PositionPlayer;

    private int age;

}
