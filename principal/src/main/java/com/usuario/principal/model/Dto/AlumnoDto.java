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
    private String telefono;
    private EstadoCuenta estadoCuenta;
    private List<Long> cursosInscritos;

    public AlumnoDto( String nombreUsuario, String correo,String telefono,
                        EstadoCuenta estadoCuenta,List<Long> cursosInscritos) {
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.telefono = telefono;
        this.estadoCuenta = estadoCuenta;
        this.cursosInscritos = cursosInscritos;
    }    
    
}
