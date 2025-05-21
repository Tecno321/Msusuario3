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

import com.usuario.principal.model.EstadoCuenta;
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

    //@GetMapping("/{id}/materias")
    //public List<Long> obtenerMaterias(@PathVariable int id) {
    //    return profesorService.obtenerMaterias(id);
    //}

    @PutMapping("/cambiarNombre/{nombre}/{id}")
    public ResponseEntity<String> cambiarNombre(@RequestBody String nombre ,int id){
        String resultado = profesorService.cambiarNombreProfe(nombre, id);
        if (resultado.startsWith("Error")) {
            return ResponseEntity.status(400).body(resultado);
        }
        return ResponseEntity.ok(resultado);
    }

    @PutMapping("/cambiarTelefono/{telefono}/{id}")
    public ResponseEntity<String> cambiarTelefono(@RequestBody String telefono ,int id){
        String resultado = profesorService.cambiarNumeroProfe(telefono, id);
        if (resultado.startsWith("Error")) {
            return ResponseEntity.status(400).body(resultado);
        }
        return ResponseEntity.ok(resultado);
    } 
    
    @PutMapping("/cambiarEstadoCuenta/{estado}/{id}")
    public ResponseEntity<String> cambiarEstado(@RequestBody EstadoCuenta estado ,int id){
        String resultado = profesorService.cambiarEstadoCuenta(id, estado);
        if (resultado.startsWith("Error")) {
            return ResponseEntity.status(400).body(resultado);
        }
        return ResponseEntity.ok(resultado);
    } 
}
