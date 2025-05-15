package com.usuario.principal.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usuario.principal.model.EstadoCuenta;
import com.usuario.principal.model.Profesor;
import com.usuario.principal.model.Dto.ProfesorDto;
import com.usuario.principal.model.entity.ProfesorEntity;
import com.usuario.principal.repository.ProfesorRepository;

@Service
public class ProfesorService {
    @Autowired
    private ProfesorRepository profesorRepository;

    public String crearProfesor (Profesor profesor){
        try {
            boolean estado = profesorRepository.existsByCorreo(profesor.getCorreo());
            if (!estado) {
                ProfesorEntity nuevoProfesor = new ProfesorEntity();
                nuevoProfesor.setNombreUsuario(profesor.getNombreUsuario());
                nuevoProfesor.setContraseña(profesor.getContraseña());
                nuevoProfesor.setCorreo(profesor.getCorreo());
                nuevoProfesor.setTelefono(profesor.getTelefono());
                nuevoProfesor.setFechaRegistro(profesor.getFechaRegistro());
                nuevoProfesor.setEstadoCuenta(profesor.getEstadoCuenta());
                nuevoProfesor.setMaterias(profesor.getMateria());
                nuevoProfesor.setAñosDeExperiencia(profesor.getAñosDeExperiencia());
                if (nuevoProfesor.getFechaRegistro() == null) {
                    nuevoProfesor.setFechaRegistro(LocalDate.now());                    
                }
                if (nuevoProfesor.getEstadoCuenta() == null) {
                    nuevoProfesor.setEstadoCuenta(EstadoCuenta.ACTIVO);
                }
                profesorRepository.save(nuevoProfesor);
                return "Profesor creado correctamente";
            }
            return "este correo ya tiene un profesor";      
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Hubo un problema al crear el profesor " + e.getMessage();
        }
    }

    public ProfesorDto obtenerProfesor (String correo){
        try {
            ProfesorEntity profesor = profesorRepository.findByCorreo(correo);
            if (profesor != null){
                ProfesorDto profe = new ProfesorDto(profesor.getNombreUsuario(),profesor.getCorreo()
                ,profesor.getTelefono(),profesor.getEstadoCuenta(),profesor.getMaterias(),
                profesor.getAñosDeExperiencia());
                return profe;
            }
            return null;
            
        } catch (Exception e) {
            return null;
        }
    }

    public List<Long> obtenerMaterias(int profeId) {
        try {
            Optional<ProfesorEntity> profesorOptional = profesorRepository.findById(profeId);
            if (profesorOptional.isPresent()) {
                ProfesorEntity profesor = profesorOptional.get();
                return profesor.getMaterias();
            }
            return Collections.emptyList();
            
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

   
}
