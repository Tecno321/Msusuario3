package com.usuario.principal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usuario.principal.model.entity.AlumnoEntity;

public interface AlumnoRepository extends JpaRepository<AlumnoEntity, Integer>{
    
    AlumnoEntity findByCorreo (String correo);
    boolean existsByCorreo (String correo);
    
}
