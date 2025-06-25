package com.usuario.principal.model.Dto;

import com.usuario.principal.model.EstadoCuenta;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
//clase que muestra siertos atributos de alumno
public class AlumnoDto {
    private String nombreUsuario;
    private String correo;
    private EstadoCuenta estadoCuenta;

    public AlumnoDto( String nombreUsuario, String correo,
                        EstadoCuenta estadoCuenta) {
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.estadoCuenta = estadoCuenta;
    }    
    
}
