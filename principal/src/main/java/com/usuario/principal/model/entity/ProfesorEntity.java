package com.usuario.principal.model.entity;

import java.time.LocalDate;
import java.util.List;

import com.usuario.principal.model.EstadoCuenta;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Data;


@Entity
@Data
//crea la tabla de profesor en la base de datos
public class ProfesorEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;
    
    @Column
    private String nombreUsuario;
    private String contraseña;
    private String correo;
    private String telefono;
    private LocalDate fechaRegistro;
    @Enumerated(EnumType.STRING)
    private EstadoCuenta estadoCuenta;

    @ElementCollection
    @CollectionTable(name = "materias_profesor", joinColumns = @JoinColumn(name = "profesor_id"))
    @Column(name = "materia_id")
    private List<Long> materias;
    
    @Column
    private int añosDeExperiencia;

}
