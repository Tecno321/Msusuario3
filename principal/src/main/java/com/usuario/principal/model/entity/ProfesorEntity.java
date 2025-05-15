package com.usuario.principal.model.entity;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class ProfesorEntity extends UsuarioEntity{

    @ElementCollection
    @CollectionTable(name = "materias_profesor", joinColumns = @JoinColumn(name = "profesor_id"))
    @Column(name = "materia_id")
    private List<Long> materias; 

    @Column
    private int añosDeExperiencia;
    
}
