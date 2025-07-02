package com.usuario.principal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import com.usuario.principal.model.Alumno;
import com.usuario.principal.model.EstadoCuenta;
import com.usuario.principal.model.Dto.AlumnoDto;
import com.usuario.principal.model.entity.AlumnoEntity;
import com.usuario.principal.repository.AlumnoRepository;
import com.usuario.principal.service.AlumnoService;

public class AlumnoTest {

    @Mock 
    AlumnoRepository alumnoRepository;
    
    @Mock 
    private RestTemplate restTemplate;
    
    @InjectMocks
    private AlumnoService alumnoService;

    private Alumno alumno;

    private AlumnoEntity alumnoEntity;

    @BeforeEach
    public void setup (){
        MockitoAnnotations.openMocks(this);
        alumno = new Alumno();
        alumno.setId(1);
        alumno.setNombreUsuario("LucasPrueva");
        alumno.setContraseña("Lucas12");
        alumno.setCorreo("lucas@gmail.com");
        alumno.setTelefono("+901221122");
        alumno.setFechaRegistro(LocalDate.now());
        alumno.setEstadoCuenta(EstadoCuenta.ACTIVO);
        
        alumnoEntity = new AlumnoEntity();
        alumnoEntity.setIdUsuario(1);
        alumnoEntity.setNombreUsuario("LucasPrueva");
        alumnoEntity.setContraseña("Lucas12");
        alumnoEntity.setCorreo("lucas@gmail.com");
        alumnoEntity.setTelefono("+901221122");
        alumnoEntity.setFechaRegistro(LocalDate.now());
        alumnoEntity.setEstadoCuenta(EstadoCuenta.ACTIVO);  

    }

    @Test
    public void testCrearAlumnoExito(){
        when(alumnoRepository.existsByCorreo(any())).thenReturn(false);
        when(alumnoRepository.save(any())).thenReturn(alumnoEntity);

        String resultado = alumnoService.crearAlumno(alumno);
        
        System.out.println("Resultado exacto: [" + resultado + "]");
        assertNotNull(resultado);
        assertEquals("Alumno creado correctamente",resultado);
    }
    
    @Test
    public void testCrearAlumnoExistente(){
        when(alumnoRepository.existsByCorreo(any())).thenReturn(true);

        String resultado = alumnoService.crearAlumno(alumno);
        
        System.out.println("Resultado exacto: [" + resultado + "]");
        assertNotNull(resultado);
        assertEquals("Este correo ya existe", resultado);
    }
    
    @Test
    public void testLlamarAlumnoExitoso(){
        when(alumnoRepository.findById(alumno.getId())).thenReturn(alumnoEntity);

        AlumnoDto resultado = alumnoService.obtenerAlumno2(alumno.getId());

        
        System.out.println("Resultado exacto: [" + resultado + "]");
        assertNotNull(resultado);    
        assertEquals("LucasPrueva", resultado.getNombreUsuario());
        assertEquals("lucas@gmail.com", resultado.getCorreo());
        assertEquals(alumnoEntity.getEstadoCuenta(), resultado.getEstadoCuenta());

    }
    
    @Test
    public void testLlamarAlumnoError(){
        when(alumnoRepository.findById(anyInt())).thenThrow(new RuntimeException
        ("Error al llamar al alumno"));

        AlumnoDto resultado = alumnoService.obtenerAlumno2(alumno.getId());

        System.out.println("Resultado exacto: [" + resultado + "]");
        assertNull(resultado);

    } 
    
    @Test
    public void testLlamarAlumnoInactivo(){
        when(alumnoRepository.findById(alumno.getId())).thenReturn(alumnoEntity);

        alumnoEntity.setEstadoCuenta(EstadoCuenta.SUSPENDIDO);

        AlumnoDto resultado = alumnoService.obtenerAlumno2(alumno.getId());

        System.out.println("Resultado exacto: [" + resultado + "]");
        assertNull(resultado);

    }
    
    @Test 
    public void testCambiarNombreAlumnoExito(){
        
        when(alumnoRepository.existsById(alumno.getId())).thenReturn(true);
        when(alumnoRepository.findById(alumno.getId())).thenReturn(alumnoEntity);

        String nombreNuevo = "pepeNuevo";
        String resultado = alumnoService.cambiarNombreAlumno(nombreNuevo, alumno.getId());

        System.out.println("Resultado exacto: [" + resultado + "]");
        assertNotNull(resultado);
        assertTrue(resultado.contains("LucasPrueva"));
        assertTrue(resultado.contains("pepeNuevo"));
        assertEquals("El nombre de usuario del alumno LucasPrueva fue cambiado a pepeNuevo",resultado);

    }

    @Test 
    public void testCambiarNombreAlumnoNoExistente(){
        
        when(alumnoRepository.existsById(alumno.getId())).thenReturn(false);

        String nombreNuevo = "pepeNuevo";
        String resultado = alumnoService.cambiarNombreAlumno(nombreNuevo, alumno.getId());

        System.out.println("Resultado exacto: [" + resultado + "]");
        assertNotNull(resultado);
        assertEquals("NotFound: El id "+ alumno.getId()+" no se a encontrado", resultado);

    }

    @Test
    public void testCambiarNombreAlumnoError(){
        when(alumnoRepository.existsById(anyInt())).thenThrow(new RuntimeException
        ("Error al cambiar el nombre al alumno"));

        String nombreNuevo = "pepeNuevo";
        String resultado = alumnoService.cambiarNombreAlumno(nombreNuevo, alumno.getId());

        System.out.println("Resultado exacto: [" + resultado + "]");
        assertNotNull(resultado);
        assertTrue(resultado.contains("Error: Hubo un problema al cambiar el nombre del alumno "));
        assertTrue(resultado.contains("Error al cambiar el nombre al alumno"));

    }

    @Test
    public void testCambiarNumeroAlumnoExito(){

        when(alumnoRepository.existsById(alumno.getId())).thenReturn(true);
        when(alumnoRepository.findById(alumno.getId())).thenReturn(alumnoEntity);

        String nombreAlumno = alumnoEntity.getNombreUsuario();

        String numeroNuevo = "+504545454";
        String resultado = alumnoService.cambiarNumeroAlumno(numeroNuevo, alumno.getId());

        System.out.println("Resultado exacto: [" + resultado + "]");
        assertNotNull(resultado);
        assertTrue(resultado.contains("504545454"));
        assertEquals("El telefono del alumno "+nombreAlumno+" fue cambiado a "+numeroNuevo, resultado);

    }

    @Test
    public void testCambiarNumeroAlumnoNoExiste(){

        when(alumnoRepository.existsById(alumno.getId())).thenReturn(false);
        when(alumnoRepository.findById(alumno.getId())).thenReturn(alumnoEntity);

        String numeroNuevo = "+504545454";
        String resultado = alumnoService.cambiarNumeroAlumno(numeroNuevo, alumno.getId());

        System.out.println("Resultado exacto: [" + resultado + "]");
        assertNotNull(resultado);
        assertEquals("NotFound: El id "+alumno.getId()+" no se a encontrado", resultado);

    }

    @Test
    public void testCambiarNumeroAlumnoError(){

        when(alumnoRepository.existsById(anyInt())).thenThrow(new RuntimeException
        ("Error al cambiar el numero del alumno"));

        String numeroNuevo = "+504545454";
        String resultado = alumnoService.cambiarNumeroAlumno(numeroNuevo, alumno.getId());

        System.out.println("Resultado exacto: [" + resultado + "]");
        assertNotNull(resultado);
        assertTrue(resultado.contains("Error: Hubo un problema al cambiar el telefono del alumno "));
        assertTrue(resultado.contains("Error al cambiar el numero del alumno"));

    }
}
