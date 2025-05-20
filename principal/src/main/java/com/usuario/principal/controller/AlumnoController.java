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

import com.usuario.principal.model.Alumno;
import com.usuario.principal.model.Dto.AlumnoDto;
import com.usuario.principal.service.AlumnoService;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
    
    @Autowired
    private AlumnoService alumnoService;

    @PostMapping("/crear")
    public ResponseEntity<String> crearAlumno(@RequestBody Alumno alumno) {
        String resultado = alumnoService.crearAlumno(alumno);
        if (resultado.startsWith("Error")) {
            return ResponseEntity.status(400).body(resultado);
        }
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/{correo}")
    public ResponseEntity<AlumnoDto> obtenerProfesor(@PathVariable String correo) {
        if(alumnoService.obtenerAlumno(correo) != null){
            return ResponseEntity.ok(alumnoService.obtenerAlumno(correo));
        }
        return ResponseEntity.notFound().build();     
    }

    //@GetMapping("/{id}/materias")
    //public List<Long> obtenerMaterias(@PathVariable int id) {
    //    return alumnoService.obtenerMaterias(id);
    //}      
}