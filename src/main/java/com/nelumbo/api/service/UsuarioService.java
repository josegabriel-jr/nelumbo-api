package com.nelumbo.api.service;

import com.nelumbo.api.dto.UsuarioDTO;
import com.nelumbo.api.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO);
    List<UsuarioDTO> listUsuario();
    UsuarioDTO obtenerUsuarioPorId(long id);

}
