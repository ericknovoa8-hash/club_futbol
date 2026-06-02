package com.Equipo.Futbol.dto.request;

import lombok.Data;

@Data
/**
 * DTO para recibir los datos de un nuevo resultado de entrenamiento.
 * Contiene el ID del jugador, el ID del entrenamiento, la potencia de disparo,
 */
public class ResultRequestDTO {
    
    private Long idPlayer;

    private Long idTraining;

    private Integer shootingPower;

    private Integer speedPlayer;

    private Integer effectivePasses;

    private Double resultScore;
    
    
}
