package com.Equipo.Futbol.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "players")
@Data
/**
 * Entidad Player que representa a un jugador de 
 * fútbol en la base de datos.
 */
public class Player {

    /**
     * ID del jugador, generado automáticamente por 
     * la base de datos.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "position_player")
    private String positionPlayer;

    @Column(name = "age")
    private int age;
    
}
