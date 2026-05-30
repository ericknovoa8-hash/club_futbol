package com.Equipo.Futbol.dto.response;

import lombok.Data;

@Data
public class ResultResponseDTO {
    
    private Long idResult;

    private Long idPlayer;

    private Long idTraining;

    private double shootingPower;

    private double speedPlayer;

    private Integer effectivePasses;

}
