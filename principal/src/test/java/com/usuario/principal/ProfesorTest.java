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

import com.usuario.principal.model.EstadoCuenta;
import com.usuario.principal.model.Profesor;
import com.usuario.principal.model.Dto.ProfesorDto;
import com.usuario.principal.model.entity.ProfesorEntity;
import com.usuario.principal.repository.ProfesorRepository;
import com.usuario.principal.service.ProfesorService;

public class ProfesorTest {

    @Mock
    private ProfesorRepository profesorRepository;

    @Mock 
    private RestTemplate restTemplate;

    @InjectMocks
    private ProfesorService profesorService;

    private Profesor profesor;

    private ProfesorEntity profesorEntity;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        profesor = new Profesor();
        profesor.setId(1);
        profesor.setNombreUsuario("pepePrueva");
        profesor.setContraseña("PruevaPepe");
        profesor.setCorreo("pepeprueva@gmail.com");
        profesor.setTelefono("+453232323");
        profesor.setFechaRegistro(LocalDate.now());
        profesor.setEstadoCuenta(EstadoCuenta.ACTIVO);
        profesor.setAñosDeExperiencia(2);

        profesorEntity = new ProfesorEntity();
        profesorEntity.setIdUsuario(1);
        profesorEntity.setNombreUsuario("pepePrueva");
        profesorEntity.setCorreo("pepeprueva@gmail.com");
        profesorEntity.setTelefono("+453232323");
        profesorEntity.setFechaRegistro(LocalDate.now());
        profesorEntity.setAñosDeExperiencia(2);
        profesorEntity.setEstadoCuenta(EstadoCuenta.ACTIVO);

    }

    @Test
    public void testCrearProfeExitoso(){
        System.out.println("INICIANDO TEST testCrearProfeExitoso");
        when(profesorRepository.existsByCorreo(any())).thenReturn(false);
        when(profesorRepository.save(any())).thenReturn(profesorEntity);

        String resultado = profesorService.crearProfesor(profesor);
        
        System.out.println("Resultado exacto: [" + resultado + "]");
        assertNotNull(resultado);
        assertEquals("Profesor creado correctamente",resultado);
        System.out.println("FIN DEL TEST (antes del assert)");
    }

    @Test
    public void testCrearProfeExistente(){
        when(profesorRepository.existsByCorreo(any())).thenReturn(true);

        String resultado = profesorService.crearProfesor(profesor);
        
        System.out.println("Resultado exacto: [" + resultado + "]");
        assertNotNull(resultado);
        assertEquals("Este correo ya tiene un profesor", resultado);
    }

    @Test
    public void testLlamarProfesorExitoso(){
        when(profesorRepository.findById(profesor.getId())).thenReturn(profesorEntity);

        ProfesorDto resultado = profesorService.obtenerProfesor2(profesor.getId());

        
        System.out.println("Resultado exacto: [" + resultado + "]");
        assertNotNull(resultado);    
        assertEquals("pepePrueva", resultado.getNombreUsuario());
        assertEquals("pepeprueva@gmail.com", resultado.getCorreo());
        assertEquals(profesorEntity.getEstadoCuenta(), resultado.getEstadoCuenta());
        assertEquals(profesorEntity.getAñosDeExperiencia(), resultado.getAñosDeExperiencia());

    }

    @Test
    public void testLlamarProfesorError(){
        when(profesorRepository.findById(anyInt())).thenThrow(new RuntimeException
        ("Error al llamar al profesor"));

        ProfesorDto resultado = profesorService.obtenerProfesor2(profesor.getId());

        System.out.println("Resultado exacto: [" + resultado + "]");
        assertNull(resultado);

    }

    @Test
    public void testLlamarProfesorInactivo(){
        when(profesorRepository.findById(profesor.getId())).thenReturn(profesorEntity);

        profesorEntity.setEstadoCuenta(EstadoCuenta.SUSPENDIDO);

        ProfesorDto resultado = profesorService.obtenerProfesor2(profesor.getId());

        System.out.println("Resultado exacto: [" + resultado + "]");
        assertNull(resultado);

    }

    @Test 
    public void testCambiarNombreProfesorExito(){
        
        when(profesorRepository.existsById(profesor.getId())).thenReturn(true);
        when(profesorRepository.findById(profesor.getId())).thenReturn(profesorEntity);

        String nombreNuevo = "pepeNuevo";
        String resultado = profesorService.cambiarNombreProfe(nombreNuevo, profesor.getId());

        System.out.println("Resultado exacto: [" + resultado + "]");
        assertNotNull(resultado);
        assertTrue(resultado.contains("pepePrueva"));
        assertTrue(resultado.contains("pepeNuevo"));
        assertEquals("El nombre de usuario del profesor pepePrueva fue cambiado a pepeNuevo", resultado);

    }

    @Test 
    public void testCambiarNombreProfesorNoExistente(){
        
        when(profesorRepository.existsById(profesor.getId())).thenReturn(false);

        String nombreNuevo = "pepeNuevo";
        String resultado = profesorService.cambiarNombreProfe(nombreNuevo, profesor.getId());

        System.out.println("Resultado exacto: [" + resultado + "]");
        assertNotNull(resultado);
        assertEquals("NotFound: No se a encontrado el id "+ profesor.getId(), resultado);

    }

    @Test
    public void testCambiarNombreProfesorError(){
        when(profesorRepository.existsById(anyInt())).thenThrow(new RuntimeException
        ("Error al cambiar el nombre al profesor"));

        String nombreNuevo = "pepeNuevo";
        String resultado = profesorService.cambiarNombreProfe(nombreNuevo, profesor.getId());

        System.out.println("Resultado exacto: [" + resultado + "]");
        assertNotNull(resultado);
        assertTrue(resultado.contains("Error: Hubo un problema al cambiar el nombre del profesor "));
        assertTrue(resultado.contains("Error al cambiar el nombre al profesor"));

    }

    @Test
    public void testCambiarNumeroProfesorExito(){

        when(profesorRepository.existsById(profesor.getId())).thenReturn(true);
        when(profesorRepository.findById(profesor.getId())).thenReturn(profesorEntity);

        String nombreProfe = profesorEntity.getNombreUsuario();

        String numeroNuevo = "+504545454";
        String resultado = profesorService.cambiarNumeroProfe(numeroNuevo, profesor.getId());

        System.out.println("Resultado exacto: [" + resultado + "]");
        assertNotNull(resultado);
        assertTrue(resultado.contains("504545454"));
        assertEquals("El telefono del profesor "+nombreProfe+" fue cambiado a "+numeroNuevo, resultado);

    }

    @Test
    public void testCambiarNumeroProfesorNoExiste(){

        when(profesorRepository.existsById(profesor.getId())).thenReturn(false);
        when(profesorRepository.findById(profesor.getId())).thenReturn(profesorEntity);

        String numeroNuevo = "+504545454";
        String resultado = profesorService.cambiarNumeroProfe(numeroNuevo, profesor.getId());

        System.out.println("Resultado exacto: [" + resultado + "]");
        assertNotNull(resultado);
        assertEquals("NotFound: El id "+profesor.getId()+" no se a encontrado", resultado);

    }

    @Test
    public void testCambiarNumeroProfesorError(){

        when(profesorRepository.existsById(anyInt())).thenThrow(new RuntimeException
        ("Error al cambiar el numero del profesor"));

        String numeroNuevo = "+504545454";
        String resultado = profesorService.cambiarNumeroProfe(numeroNuevo, profesor.getId());

        System.out.println("Resultado exacto: [" + resultado + "]");
        assertNotNull(resultado);
        assertTrue(resultado.contains("Error: Hubo un problema al cambiar el telefono del profesor "));
        assertTrue(resultado.contains("Error al cambiar el numero del profesor"));

    }   
   
}
