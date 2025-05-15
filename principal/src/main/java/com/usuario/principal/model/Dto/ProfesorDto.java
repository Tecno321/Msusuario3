package com.usuario.principal.model.Dto;

import java.util.List;

import com.usuario.principal.model.EstadoCuenta;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProfesorDto {
    private String nombreUsuario;
    private String correo;
    private String telefono;
    private EstadoCuenta estadoCuenta;
    private List<Long> materia;
    private int añosDeExperiencia;
    
    public ProfesorDto( String nombreUsuario, String correo,String telefono,
                        EstadoCuenta estadoCuenta,List<Long> materia,int añosExperiencia) {
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.telefono = telefono;
        this.estadoCuenta = estadoCuenta;
        this.materia = materia;
        this.añosDeExperiencia = añosExperiencia;
    }
}
