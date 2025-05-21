package com.usuario.principal.model.Dto;



import com.usuario.principal.model.EstadoCuenta;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProfesorDto {
    private String nombreUsuario;
    private String correo;
    private EstadoCuenta estadoCuenta;
    private int añosDeExperiencia;
    
    public ProfesorDto( String nombreUsuario, String correo,
                        EstadoCuenta estadoCuenta,int añosExperiencia) {
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.estadoCuenta = estadoCuenta;
        this.añosDeExperiencia = añosExperiencia;
    }
}
