package com.usuario.principal.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
//clase profesor extiende de usuario
public class Profesor extends Usuario{
    private int añosDeExperiencia;

//constructor de profesor    
    public Profesor(int id, String nombreUsuario, String contraseña, String correo, 
                    String telefono, LocalDate fechaRegistro, 
                    EstadoCuenta estadoCuenta, int añosDeExperiencia) {
        super(id, nombreUsuario, contraseña, correo, telefono, fechaRegistro, estadoCuenta);
        this.añosDeExperiencia = añosDeExperiencia;

    }
}
