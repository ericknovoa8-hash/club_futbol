package com.Equipo.Futbol.dto.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TrainingsRequestDTO {

    private LocalDate traingDate;

    private String descriptionTraining;
    
}
