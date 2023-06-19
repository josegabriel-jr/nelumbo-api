package com.nelumbo.api.service.impl;

import com.nelumbo.api.converter.RolConverter;
import com.nelumbo.api.dto.RolDTO;
import com.nelumbo.api.dto.UsuarioDTO;
import com.nelumbo.api.entity.Rol;
import com.nelumbo.api.entity.Usuario;
import com.nelumbo.api.exception.ResourceNotFoundException;
import com.nelumbo.api.repository.RolRepository;
import com.nelumbo.api.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServicioImpl implements RolService {
    @Autowired
    private RolConverter rolConverter;
    @Autowired
    private RolRepository rolRepository;

    @Override
    public RolDTO obtenerPorPorId(long id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol", "id", id));
        return rolConverter.rolToRolDto(rol);
    }
}
