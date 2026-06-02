package com.Equipo.Futbol.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Equipo.Futbol.entity.User;
/**
 * Repositorio para la entidad User que extiende JpaRepository, proporcionando métodos CRUD 
 * y consultas personalizadas para la gestión de usuarios en la base de datos.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    /**
     * Método para encontrar un usuario por su nombre de usuario.
     * @param username
     * @return
     */
    Optional<User> findByUsername(String username);
}
