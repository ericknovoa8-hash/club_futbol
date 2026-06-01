package com.Equipo.Futbol.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "trainings")
@Data
/**
 * Clase que representa una sesión de entrenamiento en el sistema 
 * de gestión de fútbol.
 */
public class Training {
    /**
     * Identificador único de la sesión de entrenamiento.
     * 
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "training_date")
    private LocalDate trainingDate;

    @Column(name = "description_training")
    private String descriptionTraining;
    
    
}
