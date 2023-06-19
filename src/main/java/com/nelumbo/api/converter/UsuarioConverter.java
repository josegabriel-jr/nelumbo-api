package com.nelumbo.api.converter;

import com.nelumbo.api.dto.UsuarioDTO;
import com.nelumbo.api.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioConverter {
    @Autowired
    private  RolConverter rolConverter;
    public Usuario usuarioDtoToUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        if (usuarioDTO != null) {
            usuario.setId(usuarioDTO.getId());
            usuario.setEmail(usuarioDTO.getEmail());
            usuario.setNombre(usuarioDTO.getNombre());
            usuario.setPass(usuarioDTO.getPass());
        }
        return usuario;
    }
    public UsuarioDTO usuarioToUsuarioDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        if (usuario != null) {
            usuarioDTO.setId(usuario.getId());
            usuarioDTO.setEmail(usuario.getEmail());
            usuarioDTO.setNombre(usuario.getNombre());
            usuarioDTO.setPass(usuario.getPass());
            usuarioDTO.setRol(rolConverter.rolToRolDto(usuario.getRol()));
            usuarioDTO.setFechaRegistro(usuario.getFechaRegistro());
            usuarioDTO.setFechaActualizacion(usuario.getFechaActualizacion());
        }
        return usuarioDTO;
    }

}
