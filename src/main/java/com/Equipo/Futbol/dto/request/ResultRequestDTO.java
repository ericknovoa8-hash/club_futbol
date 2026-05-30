package com.Equipo.Futbol.dto.request;

import lombok.Data;

@Data
public class ResultRequestDTO {
    
    private Long idplayer;

    private Long idTraining;

    private Double shootingPower;

    private Double speedPlayer;

    private Integer effectivePasses;
    
    
}
