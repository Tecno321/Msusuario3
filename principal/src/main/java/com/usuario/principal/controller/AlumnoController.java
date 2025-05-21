package com.usuario.principal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.principal.model.Alumno;
import com.usuario.principal.model.EstadoCuenta;
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
    
    @PutMapping("/cambiarNombre/{nombre}/{id}")
    public ResponseEntity<String> cambiarNombre(@RequestBody String nombre ,int id){
        String resultado = alumnoService.cambiarNombreAlumno(nombre, id);
        if (resultado.startsWith("Error")) {
            return ResponseEntity.status(400).body(resultado);
        }
        return ResponseEntity.ok(resultado);
    }

    @PutMapping("/cambiarTelefono/{telefono}/{id}")
    public ResponseEntity<String> cambiarTelefono(@RequestBody String telefono ,int id){
        String resultado = alumnoService.cambiarNumeroAlumno(telefono, id);
        if (resultado.startsWith("Error")) {
            return ResponseEntity.status(400).body(resultado);
        }
        return ResponseEntity.ok(resultado);
    }

    @PutMapping("/cambiarEstadoCuenta/{estado}/{id}")
    public ResponseEntity<String> cambiarEstado(@RequestBody EstadoCuenta estado ,int id){
        String resultado = alumnoService.cambiarEstadoCuenta(id, estado);
        if (resultado.startsWith("Error")) {
            return ResponseEntity.status(400).body(resultado);
        }
        return ResponseEntity.ok(resultado);
    } 

}