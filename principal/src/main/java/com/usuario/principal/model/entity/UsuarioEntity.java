package com.usuario.principal.model.entity;

import java.time.LocalDate;

import com.usuario.principal.model.EstadoCuenta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class UsuarioEntity {
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
    private EstadoCuenta estadoCuenta;
}
