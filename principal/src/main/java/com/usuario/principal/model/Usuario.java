package com.usuario.principal.model;

import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(exclude = "contraseña")
@Data
@NoArgsConstructor
public class Usuario {
    private int id ;
    private String nombreUsuario;
    private String contraseña;
    private String correo;
    private String telefono;
    private LocalDate fechaRegistro;
    private EstadoCuenta estadoCuenta;
    
    
    public Usuario(int id, String nombreUsuario, String contraseña, String correo,
            String telefono, LocalDate fechaRegistro, EstadoCuenta estadoCuenta) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.correo = correo;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
        this.estadoCuenta = estadoCuenta;
    }

    

}
