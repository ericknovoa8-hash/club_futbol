package com.Equipo.Futbol.dto.response;

import java.time.LocalDate;

import lombok.Data;

@Data
/**
 * DTO de respuesta para la entidad Training, que se utiliza para enviar información sobre los entrenamientos a los clientes.
 * Contiene el ID del entrenamiento, la fecha del entrenamiento y una descripción del mismo.
 */
public class TrainingResponseDTO {
    private Long idTraining;

    private LocalDate trainingDate;

    private String descriptionTraining;

}
