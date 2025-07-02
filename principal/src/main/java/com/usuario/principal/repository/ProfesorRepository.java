package com.usuario.principal.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.usuario.principal.model.entity.ProfesorEntity;

//funciones que interactuan con la base de datos en la tabla alumno
public interface ProfesorRepository extends JpaRepository<ProfesorEntity,Integer>{

    ProfesorEntity findById (int idProfe);
    ProfesorEntity findByCorreo (String correo);
    boolean existsByCorreo (String correo);
 
}
