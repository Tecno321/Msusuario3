package com.usuario.principal.model;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
//clase alumno extiende de usuario
public class Alumno extends Usuario {

//constructor de alumno    
    public Alumno(int id, String nombreUsuario, String contraseña, String correo, 
                    String telefono, LocalDate fechaRegistro, 
                    EstadoCuenta estadoCuenta){
                        super(id, nombreUsuario, contraseña, correo, telefono
                        , fechaRegistro, estadoCuenta);
                    }  

}
