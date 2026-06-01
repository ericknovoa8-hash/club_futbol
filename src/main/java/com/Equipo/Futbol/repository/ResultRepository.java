package com.Equipo.Futbol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Equipo.Futbol.entity.Result;

@Repository
/**
 * Repositorio para la entidad Result, que extiende JpaRepository
 *  para proporcionar métodos CRUD y de consulta.
 */
public interface ResultRepository  extends JpaRepository<Result, Long> {
    
}
