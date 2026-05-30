package com.Equipo.Futbol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Equipo.Futbol.entity.Training;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long>{
    
}
