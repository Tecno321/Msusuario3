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
            EstadoCuenta estado = profesor.getEstadoCuenta();
            if (profesor != null){
                if(estado != EstadoCuenta.ACTIVO){
                    System.out.println("el profesor esta fuera de servicio");
                    return null;
   
                }ProfesorDto profe = new ProfesorDto(profesor.getNombreUsuario(),profesor.getCorreo()
                ,profesor.getEstadoCuenta(),
                profesor.getAñosDeExperiencia());
                return profe; 

            }
            return null;
            
        } catch (Exception e) {
            return null;
        }
    }

    //public List<Long> obtenerMaterias(int profeId) {
    //    try {
    //        Optional<ProfesorEntity> profesorOptional = profesorRepository.findById(profeId);
    //        if (profesorOptional.isPresent()) {
    //            ProfesorEntity profesor = profesorOptional.get();
    //            return profesor.getMaterias();
    //        }
    //        return Collections.emptyList();
            
    //    } catch (Exception e) {
    //        return Collections.emptyList();
    //    }
    //}

    public String cambiarNombreProfe (String nuevoNombre , int profeId){
        try {
            boolean estado = profesorRepository.existsById(profeId);
            if(estado){
               ProfesorEntity profe= profesorRepository.findById(profeId);
               String nombreViejo = profe.getNombreUsuario();
               profe.setNombreUsuario(nuevoNombre);
               return "el nombre de usuario del profesor "+nombreViejo+" fue cambiado a "+profe.getNombreUsuario();
            }return"el id "+ profeId+" no se a encontrado";     
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Hubo un problema al cambiar el nombre del profesor " + e.getMessage();
        }
    }

    public String cambiarNumeroProfe (String nuevoNumero , int profeId){
        try {
            boolean estado = profesorRepository.existsById(profeId);
            if(estado){
               ProfesorEntity profe= profesorRepository.findById(profeId);
               profe.setTelefono(nuevoNumero);
               return "el telefono del profesor "+profe.getNombreUsuario()+" fue cambiado a "+profe.getTelefono();
            }return"el id "+profeId+" no se a encontrado";     
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Hubo un problema al cambiar el telefono del profesor " + e.getMessage();
        }
    }

    public String cambiarEstadoCuenta(int profeId , EstadoCuenta nuevoEstadoCuenta){
        try {
            boolean estado = profesorRepository.existsById(profeId);
            if(estado){
               ProfesorEntity profe= profesorRepository.findById(profeId);
               profe.setEstadoCuenta(nuevoEstadoCuenta);
               return "el estado de la cuenta del profesor "+profe.getNombreUsuario()+" fue cambiado a "+profe.getEstadoCuenta();
            }return"el id "+profeId+" no se a encontrado";     
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Hubo un problema al cambiar el Estado de cuenta del profesor " + e.getMessage();
        }
    }

   
}
