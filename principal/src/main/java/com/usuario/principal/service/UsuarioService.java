package com.usuario.principal.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usuario.principal.model.EstadoCuenta;
import com.usuario.principal.model.Usuario;
import com.usuario.principal.model.entity.UsuarioEntity;
import com.usuario.principal.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public String crearUsuario (Usuario usuario){
        try {
            boolean estado = usuarioRepository.existsByCorreo(usuario.getCorreo());
            if (!estado){
                UsuarioEntity usuarioNuevo = new UsuarioEntity();
                usuarioNuevo.setIdUsuario(usuario.getId());
                usuarioNuevo.setNombreUsuario(usuario.getNombreUsuario());
                usuarioNuevo.setContraseña(usuario.getContraseña());
                usuarioNuevo.setCorreo(usuario.getCorreo());
                usuarioNuevo.setTelefono(usuario.getTelefono());
                usuarioNuevo.setFechaRegistro(LocalDate.now());
                usuarioNuevo.setEstadoCuenta(EstadoCuenta.ACTIVO);
                usuarioRepository.save(usuarioNuevo);
                return"usuario creado correctamente";
            }return "el correo ya existe";
            
        } catch (Exception e) {
            return "error al crea el usuario";
        }
    }
    

    public Usuario obtenerUsuario(int id) {
    try {
        Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            UsuarioEntity usuario = usuarioOptional.get();
            Usuario user = new Usuario(
                usuario.getIdUsuario(),
                usuario.getNombreUsuario(),
                usuario.getContraseña(),
                usuario.getCorreo(),
                usuario.getTelefono(),
                usuario.getFechaRegistro(),
                usuario.getEstadoCuenta());
            return user;
        }
        return null;
    } catch (Exception e) {
        return null;
    }
}
    
}

