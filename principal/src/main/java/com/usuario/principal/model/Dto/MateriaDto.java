package com.usuario.principal.model.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MateriaDto {
    private Long id;
    private String nombre;
    private String descripcion;
    
}
