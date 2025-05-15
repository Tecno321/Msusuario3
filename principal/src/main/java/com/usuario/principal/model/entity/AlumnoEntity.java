package com.usuario.principal.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class AlumnoEntity extends UsuarioEntity{

    @Column
    private List<Long> cursosInscritos;
    
}
