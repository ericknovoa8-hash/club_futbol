package com.Equipo.Futbol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Equipo.Futbol.entity.Player;
/**
 * Repositorio para la entidad Player, 
 * que extiende JpaRepository para proporcionar 
 * métodos CRUD y de consulta.
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>{
    
}
