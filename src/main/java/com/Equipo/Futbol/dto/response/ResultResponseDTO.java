package com.Equipo.Futbol.dto.response;

import lombok.Data;

@Data
/**
 * DTO de respuesta para los resultados de entrenamiento de un jugador.
 * Contiene información sobre el resultado de un entrenamiento específico, incluyendo:
 */
public class ResultResponseDTO {
    
    private Long idResult;

    private Long idPlayer;

    private Long idTraining;

    private Integer shootingPower;

    private Integer speedPlayer;

    private Integer effectivePasses;

    private Double resultScore;

}
