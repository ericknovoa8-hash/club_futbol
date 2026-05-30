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

public class Results {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResults;

    @Column(name = "shooting_power")
    private int shootingpower;

    @Column(name = "speed_player")
    private int speedplayer;

    @Column(name = "effective_passes")
    private int effectivepasses;

    @ManyToOne
    @JoinColumn(name = "id_player")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "id_training")
    private Training training;
}
