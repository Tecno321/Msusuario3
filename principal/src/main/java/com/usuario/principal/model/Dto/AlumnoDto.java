package com.usuario.principal.model.Dto;

import java.util.List;

import com.usuario.principal.model.EstadoCuenta;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AlumnoDto {
    private String nombreUsuario;
    private String correo;
    private EstadoCuenta estadoCuenta;
    private List<Long> cursosInscritos;

    public AlumnoDto( String nombreUsuario, String correo,
                        EstadoCuenta estadoCuenta,List<Long> cursosInscritos) {
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.estadoCuenta = estadoCuenta;
        this.cursosInscritos = cursosInscritos;
    }    
    
}
