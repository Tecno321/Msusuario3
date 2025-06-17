package com.usuario.principal.controller;

import java.util.Map;

//import java.util.List;

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
        if (resultado.startsWith("Este")) {
            return ResponseEntity.status(409).body(resultado);
        }
        if (resultado.startsWith("Error")) {
            return ResponseEntity.status(400).body(resultado);
        }
        return ResponseEntity.ok(resultado);
    }


    @GetMapping("/{correo}")
    public ResponseEntity<ProfesorDto> obtenerProfesor(@PathVariable String correo) {
        ProfesorDto profe = profesorService.obtenerProfesor(correo);
        if(profe != null){
            return ResponseEntity.ok(profe);
        }
        return ResponseEntity.notFound().build();       
    }

    @GetMapping("/obtenerProfe/{profeId}")
    public ResponseEntity<ProfesorDto> obtenerProfesor(@PathVariable int profeId) {
        ProfesorDto profe = profesorService.obtenerProfesor2(profeId);
        if(profe != null){
            return ResponseEntity.ok(profe);
        }
        return ResponseEntity.notFound().build();       
    }

    //@GetMapping("/{id}/materias")
    //public List<Long> obtenerMaterias(@PathVariable int id) {
    //    return profesorService.obtenerMaterias(id);
    //}

    @PutMapping("/cambiarNombre/{id}")
    public ResponseEntity<String> cambiarNombre(@PathVariable int id ,@RequestBody Map<String ,String> datos){
        String nombreNuevo = datos.get("nombre");
        String resultado = profesorService.cambiarNombreProfe(nombreNuevo, id);
        if (resultado.startsWith("NotFound")) {
            return ResponseEntity.status(404).body(resultado);
        }
        if (resultado.startsWith("Error")) {
            return ResponseEntity.status(400).body(resultado);
        }
        return ResponseEntity.ok(resultado);
    }

    @PutMapping("/cambiarTelefono/{id}")
    public ResponseEntity<String> cambiarTelefono(@PathVariable int id ,@RequestBody Map<String ,String> datos){
        String telefonoNuevo = datos.get("telefono");
        String resultado = profesorService.cambiarNumeroProfe(telefonoNuevo, id);
        if (resultado.startsWith("NotFound")) {
            return ResponseEntity.status(404).body(resultado);
        }
        if (resultado.startsWith("Error")) {
            return ResponseEntity.status(400).body(resultado);
        }
        return ResponseEntity.ok(resultado);
    } 
    
    @PutMapping("/cambiarEstadoCuenta/{estado}/{id}")
    public ResponseEntity<String> cambiarEstado(@PathVariable EstadoCuenta estado ,@PathVariable int id){
        String resultado = profesorService.cambiarEstadoCuenta(id, estado);
        if (resultado.startsWith("Error")) {
            return ResponseEntity.status(400).body(resultado);
        }
        return ResponseEntity.ok(resultado);
    } 
}
