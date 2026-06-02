package com.Equipo.Futbol.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "results")
@Data
/**
 * Clase que representa los resultados de un entrenamiento de 
 * un jugador. Contiene atributos como la potencia de disparo,
 * velocidad del jugador y pases efectivos, así como referencias
 * a las entidades Player y Training.
 */
public class Result {
    /**
     * Identificador único para cada resultado, 
     * generado automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shooting_power", nullable = false)
    private int shootingPower;

    @Column(name = "speed_player", nullable = false)
    private int speedPlayer;

    @Column(name = "effective_passes", nullable = false)
    private int effectivePasses;

    @Column(name = "result_score", nullable = false)
    private double resultScore;

    @ManyToOne
    @JoinColumn(name = "id_player")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "id_training", nullable = false)
    private Training training;
}
