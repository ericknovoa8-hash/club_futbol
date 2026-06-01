package com.Equipo.Futbol.dto.request;

import java.time.LocalDate;

import lombok.Data;

@Data
/**
 * DTO para recibir los datos de una nueva sesión de entrenamiento. 
 * Contiene la fecha del entrenamiento y una descripción de las 
 * actividades realizadas durante la sesión.
 */
public class TrainingsRequestDTO {

    private LocalDate trainingDate;

    private String descriptionTraining;
    
}
