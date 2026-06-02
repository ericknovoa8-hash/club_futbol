package com.Equipo.Futbol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Equipo.Futbol.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}
