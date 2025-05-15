package com.usuario.principal.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.usuario.principal.model.entity.ProfesorEntity;

public interface ProfesorRepository extends JpaRepository<ProfesorEntity,Integer>{

    ProfesorEntity findByCorreo (String correo);
    boolean existsByCorreo (String correo);

    
}
