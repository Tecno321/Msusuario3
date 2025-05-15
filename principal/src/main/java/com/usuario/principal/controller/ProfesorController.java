package com.usuario.principal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.principal.model.Profesor;
import com.usuario.principal.model.Dto.ProfesorDto;
import com.usuario.principal.service.ProfesorService;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @PostMapping("/crear")
    public ResponseEntity<String> crearProfesor(@RequestBody Profesor profesor) {
        String resultado = profesorService.crearProfesor(profesor);
        if (resultado.startsWith("Error")) {
            return ResponseEntity.status(400).body(resultado);
        }
        return ResponseEntity.ok(resultado);
    }


    @GetMapping("/{correo}")
    public ResponseEntity<ProfesorDto> obtenerProfesor(@PathVariable String correo) {
        if(profesorService.obtenerProfesor(correo) != null){
            return ResponseEntity.ok(profesorService.obtenerProfesor(correo));
        }
        return ResponseEntity.notFound().build();

        
    }

    @GetMapping("/{id}/materias")
    public List<Long> obtenerMaterias(@PathVariable int id) {
        return profesorService.obtenerMaterias(id);
    }
}
