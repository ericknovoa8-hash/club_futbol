package com.Equipo.Futbol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Equipo.Futbol.entity.Training;

@Repository
/**
 * Repositorio para la entidad Training, 
 * extiende JpaRepository para proporcionar métodos CRUD y consultas personalizadas.
 */
public interface TrainingRepository extends JpaRepository<Training, Long>{
    
}
