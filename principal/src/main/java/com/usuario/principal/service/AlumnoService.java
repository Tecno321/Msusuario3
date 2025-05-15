package com.usuario.principal.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usuario.principal.model.Alumno;
import com.usuario.principal.model.EstadoCuenta;
import com.usuario.principal.model.Dto.AlumnoDto;
import com.usuario.principal.model.entity.AlumnoEntity;
import com.usuario.principal.repository.AlumnoRepository;

@Service
public class AlumnoService {
    @Autowired
    private AlumnoRepository alumnoRepository;

    public String crearAlumno (Alumno alumno){
        try {
            boolean estado = alumnoRepository.existsByCorreo(alumno.getCorreo());
            if (!estado){
                AlumnoEntity alumnoNuevo = new AlumnoEntity();
                alumnoNuevo.setNombreUsuario(alumno.getNombreUsuario());
                alumnoNuevo.setContraseña(alumno.getContraseña());
                alumnoNuevo.setCorreo(alumno.getCorreo());
                alumnoNuevo.setTelefono(alumno.getTelefono());
                alumnoNuevo.setFechaRegistro(alumno.getFechaRegistro());
                alumnoNuevo.setEstadoCuenta(alumno.getEstadoCuenta());
                alumnoNuevo.setCursosInscritos(alumno.getCursosInscritos());
                if (alumnoNuevo.getFechaRegistro() == null) {
                    alumnoNuevo.setFechaRegistro(LocalDate.now());
                }
                if (alumnoNuevo.getEstadoCuenta() == null) {
                    alumnoNuevo.setEstadoCuenta(EstadoCuenta.ACTIVO);
                }
                alumnoRepository.save(alumnoNuevo);
                return"Alumno creado correctamente";
            }
            return "el correo ya existe";           
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Hubo un problema al crear el alumno " + e.getMessage();
        }
    }

    public AlumnoDto obtenerAlumno (String correo){
        try {
            AlumnoEntity alumno = alumnoRepository.findByCorreo(correo);
            if (alumno != null){
                AlumnoDto alum = new AlumnoDto(alumno.getNombreUsuario()
                ,alumno.getCorreo(),alumno.getTelefono(),
                alumno.getEstadoCuenta(),alumno.getCursosInscritos());
                return alum;
            }
            return null;
            
        } catch (Exception e) {
            return null;
        }
    }

        public List<Long> obtenerMaterias(int alumnoId) {
        try {
            Optional<AlumnoEntity> alumnoOptional = alumnoRepository.findById(alumnoId);
            if (alumnoOptional.isPresent()) {
                AlumnoEntity alumno = alumnoOptional.get();
                return alumno.getCursosInscritos();
            }
            return Collections.emptyList();
            
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }  
}
