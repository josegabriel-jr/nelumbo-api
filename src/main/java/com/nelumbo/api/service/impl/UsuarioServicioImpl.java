package com.nelumbo.api.service.impl;

import com.nelumbo.api.converter.UsuarioConverter;
import com.nelumbo.api.dto.UsuarioDTO;
import com.nelumbo.api.entity.Rol;
import com.nelumbo.api.entity.Usuario;
import com.nelumbo.api.exception.ResourceNotFoundException;
import com.nelumbo.api.repository.RolRepository;
import com.nelumbo.api.repository.UsuarioRepository;
import com.nelumbo.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImpl implements UsuarioService {
    @Autowired
    private UsuarioConverter usuarioConverter;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;

    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        //Convertimos a Entidad
        Usuario usuario = usuarioConverter.usuarioDtoToUsuario(usuarioDTO);

        Rol rol = rolRepository.findById(usuarioDTO.getRol().getId())
                .orElseThrow(() -> new RuntimeException("El rol especificado no existe"));
        usuario.setRol(rol);

        Usuario nuevoUsuario = usuarioRepository.save(usuario);

        //Convertimos a DTO
        UsuarioDTO nuevoUsuarioDTO = usuarioConverter.usuarioToUsuarioDTO(nuevoUsuario);

        return nuevoUsuarioDTO;
    }

    @Override
    public List<UsuarioDTO> listUsuario() {
        List<Usuario> listUsuario = usuarioRepository.findAll();
        List<UsuarioDTO> listUsuarioDTO = new ArrayList<>();

        for (Usuario usuario : listUsuario) {
            listUsuarioDTO.add(usuarioConverter.usuarioToUsuarioDTO(usuario));
        }
        return listUsuarioDTO;
    }

    @Override
    public UsuarioDTO obtenerUsuarioPorId(long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
        return usuarioConverter.usuarioToUsuarioDTO(usuario);
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con email " + email + " no existe"));
    }


}
