package com.usuario.principal.model.entity;

import java.time.LocalDate;
import java.util.List;

import com.usuario.principal.model.EstadoCuenta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class AlumnoEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;
    
    @Column
    private String nombreUsuario;
    private String contraseña;
    private String correo;
    @Column(nullable = true)
    private String telefono;
    private LocalDate fechaRegistro;
    @Enumerated(EnumType.STRING)
    private EstadoCuenta estadoCuenta; 
    @Column
    private List<Long> cursosInscritos;
    
}
