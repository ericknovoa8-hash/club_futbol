package com.Equipo.Futbol.dto.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TrainingResponseDTO {
    private Long idTraining;

    private LocalDate training;

    private String descriptionTraining;

}
