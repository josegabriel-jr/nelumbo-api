package com.nelumbo.api.service.impl;

import com.nelumbo.api.converter.SocioClienteConverter;
import com.nelumbo.api.dto.SocioClienteDTO;
import com.nelumbo.api.entity.Rol;
import com.nelumbo.api.entity.SocioCliente;
import com.nelumbo.api.entity.Usuario;
import com.nelumbo.api.exception.ResourceNotFoundException;
import com.nelumbo.api.repository.RolRepository;
import com.nelumbo.api.repository.SocioClienteRepository;
import com.nelumbo.api.repository.UsuarioRepository;
import com.nelumbo.api.service.SocioClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SocioClienteServicioImpl implements SocioClienteService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    SocioClienteRepository socioClienteRepository;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    SocioClienteConverter socioClienteConverter;

    @Override
    public SocioClienteDTO asociarClienteConSocios(Long idSocio, Long idCliente) {
        Optional<Usuario> optionalUsuarioSocio = usuarioRepository.findById(idSocio);
        if (!optionalUsuarioSocio.isPresent()) {
            throw new ResourceNotFoundException("Socio", "id", idSocio);
        }

        Usuario usuarioSocio = optionalUsuarioSocio.get();
        Optional<Rol> optionalRolSocio = rolRepository.findById(usuarioSocio.getRol().getId());
        Rol rolSocio = optionalRolSocio.get();
        if (!optionalRolSocio.isPresent() || !"SOCIO".equals(rolSocio.getNombre())) {
            throw new IllegalArgumentException("El Usuario no es Socio");
        }

        Optional<Usuario> optionalUsuarioCliente = usuarioRepository.findById(idCliente);
        if (!optionalUsuarioSocio.isPresent()) {
            throw new ResourceNotFoundException("Cliente", "id", idCliente);
        }

        Usuario usuarioCliente = optionalUsuarioCliente.get();
        Optional<Rol> optionalRolCliente = rolRepository.findById(usuarioCliente.getRol().getId());
        Rol rolCliente= optionalRolCliente.get();
        if (!optionalRolCliente.isPresent() || !"CLIENTE".equals(rolCliente.getNombre())) {
            throw new IllegalArgumentException("El Usuario no es Cliente");
        }

        Optional<SocioCliente> socioCliente = socioClienteRepository.findBySocioIdAndClienteId(idSocio,idCliente);
        if (socioCliente.isPresent()) {
            throw new IllegalArgumentException("El cliente ya esta asociado a un socio");
        }

        SocioCliente nuevoSocioCliente = new SocioCliente();
        nuevoSocioCliente.setSocio(optionalUsuarioSocio.get());
        nuevoSocioCliente.setCliente(optionalUsuarioCliente.get());
        nuevoSocioCliente = socioClienteRepository.save(nuevoSocioCliente);
        SocioClienteDTO socioClienteDTO = socioClienteConverter.socioClienteToSocioClienteDTO(nuevoSocioCliente);

        return socioClienteDTO;
    }
}
