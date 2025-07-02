package com.usuario.principal.service;

import java.time.LocalDate;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usuario.principal.model.Alumno;
import com.usuario.principal.model.EstadoCuenta;
import com.usuario.principal.model.Dto.AlumnoDto;
import com.usuario.principal.model.entity.AlumnoEntity;
//import com.usuario.principal.model.entity.ProfesorEntity;
import com.usuario.principal.repository.AlumnoRepository;

//clase que contiene las funciones de la clase alumno
@Service
public class AlumnoService {
    //coneccion con alumno repository
    @Autowired
    private AlumnoRepository alumnoRepository;

    //funcion que crea un alumno y lo guarda en la base de datos
    public String crearAlumno (Alumno alumno){
        try {
            //comprueba en la BD si el correo ingresado ya existe
            boolean estado = alumnoRepository.existsByCorreo(alumno.getCorreo());
            //si estado es falso el correo no existe y crea al alumno
            if (!estado){
                AlumnoEntity alumnoNuevo = new AlumnoEntity();
                alumnoNuevo.setNombreUsuario(alumno.getNombreUsuario());
                alumnoNuevo.setContraseña(alumno.getContraseña());
                alumnoNuevo.setCorreo(alumno.getCorreo());
                alumnoNuevo.setTelefono(alumno.getTelefono());
                alumnoNuevo.setFechaRegistro(alumno.getFechaRegistro());
                alumnoNuevo.setEstadoCuenta(alumno.getEstadoCuenta());
                //si la fecha de registro es nula o invalida coloca la actual
                if (alumnoNuevo.getFechaRegistro() == null) {
                    alumnoNuevo.setFechaRegistro(LocalDate.now());
                }
                //si el estado es nulo o invalido coloca el estado activo por defecto
                if (alumnoNuevo.getEstadoCuenta() == null) {
                    alumnoNuevo.setEstadoCuenta(EstadoCuenta.ACTIVO);
                }
                //guarda el alumno en la BD
                alumnoRepository.save(alumnoNuevo);
                return"Alumno creado correctamente";
            }
            //el correo ya existe por lo que no se puede crear el usuario
            return "Este correo ya existe"; 
          //erro que presenta cuando se proboca un fallo            
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Hubo un problema al crear el alumno " + e.getMessage();
        }
    }

    //llama al alumno DTO 
    public AlumnoDto obtenerAlumno (String correo){
        try {
            //verifica que el correo del alumno exista
            AlumnoEntity alumno = alumnoRepository.findByCorreo(correo);
            if (alumno != null){
                //el correo existe y se crea el DTO del alumno
                AlumnoDto alum = new AlumnoDto(alumno.getNombreUsuario()
                ,alumno.getCorreo(),
                alumno.getEstadoCuenta());
                return alum;
            }
            return null;
            
        } catch (Exception e) {
            return null;
        }
    }

    public AlumnoDto obtenerAlumno2 (int alumnoId){
        try {
            AlumnoEntity alumno = alumnoRepository.findById(alumnoId);
            EstadoCuenta estado = alumno.getEstadoCuenta();
            if (alumno != null){
                if(estado != EstadoCuenta.ACTIVO){
                    System.out.println("el profesor esta fuera de servicio");
                    return null;
   
                }AlumnoDto alumnoDto = new AlumnoDto(alumno.getNombreUsuario(),alumno.getCorreo()
                ,alumno.getEstadoCuenta());
                return alumnoDto; 

            }
            return null;
            
        } catch (Exception e) {
            return null;
        }
    }

    //public List<Long> obtenerMaterias(int alumnoId) {
    //    try {
    //        Optional<AlumnoEntity> alumnoOptional = alumnoRepository.findById(alumnoId);
    //        if (alumnoOptional.isPresent()) {
    //            AlumnoEntity alumno = alumnoOptional.get();
    //            return alumno.getCursosInscritos();
    //        }
    //        return Collections.emptyList();
            
    //    } catch (Exception e) {
    //        return Collections.emptyList();
    //    }
    //} 
    
    public String cambiarNombreAlumno (String nuevoNombre , int alumnoId){
        try {
            //verifica que el id del alumno exista
            boolean estado = alumnoRepository.existsById(alumnoId);
            if(estado){
                //trae al alumno con la id señalada
                AlumnoEntity alumno= alumnoRepository.findById(alumnoId);
                //trae y guarda el nombre anterior al cambio
                String nombreViejo = alumno.getNombreUsuario();
                //cambia el nombre al nombre nuevo
                alumno.setNombreUsuario(nuevoNombre);
                alumnoRepository.save(alumno);
                return "El nombre de usuario del alumno "+nombreViejo+" fue cambiado a "+alumno.getNombreUsuario();
            }return"NotFound: El id "+ alumnoId+" no se a encontrado";     
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Hubo un problema al cambiar el nombre del alumno " + e.getMessage();
        }
    }
    
    public String cambiarNumeroAlumno (String nuevoNumero , int alumnoId){
        try {
            boolean estado = alumnoRepository.existsById(alumnoId);
            if(estado){
               AlumnoEntity alumno= alumnoRepository.findById(alumnoId);
               alumno.setTelefono(nuevoNumero);
               alumnoRepository.save(alumno);
               return "El telefono del alumno "+alumno.getNombreUsuario()+" fue cambiado a "+alumno.getTelefono();
            }return"NotFound: El id "+alumnoId+" no se a encontrado";     
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Hubo un problema al cambiar el telefono del alumno " + e.getMessage();
        }
    }

    public String cambiarEstadoCuenta(int alumnoId , EstadoCuenta nuevoEstadoCuenta){
        try {
            boolean estado = alumnoRepository.existsById(alumnoId);
            if(estado){
               AlumnoEntity alumno= alumnoRepository.findById(alumnoId);
               alumno.setEstadoCuenta(nuevoEstadoCuenta);
               alumnoRepository.save(alumno);
               return "el estado de la cuenta del alumno "+alumno.getNombreUsuario()+" fue cambiado a "+alumno.getEstadoCuenta();
            }return"el id "+alumnoId+" no se a encontrado";     
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Hubo un problema al cambiar el Estado de cuenta del alumno " + e.getMessage();
        }
    }
}
