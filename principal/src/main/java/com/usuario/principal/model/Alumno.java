package com.usuario.principal.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Alumno extends Usuario {
    private List<Long> cursosInscritos;

    public Alumno(int id, String nombreUsuario, String contraseña, String correo, 
                    String telefono, LocalDate fechaRegistro, 
                    EstadoCuenta estadoCuenta, List<Long> cursosInscritos){
                        super(id, nombreUsuario, contraseña, correo, telefono
                        , fechaRegistro, estadoCuenta);
                        this.cursosInscritos = cursosInscritos;
                    }  

}
