package com.nelumbo.api.converter;

import com.nelumbo.api.dto.ParqueaderoDTO;
import com.nelumbo.api.dto.SocioClienteDTO;
import com.nelumbo.api.entity.Parqueadero;
import com.nelumbo.api.entity.SocioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SocioClienteConverter {
    @Autowired
    private UsuarioConverter usuarioConverter;
    public SocioCliente socioClienteDtoToSocioCliente(SocioClienteDTO socioClienteDTO) {
        SocioCliente socioCliente = new SocioCliente();
        if (socioClienteDTO != null) {
            socioCliente.setId(socioClienteDTO.getId());
            socioCliente.setSocio(usuarioConverter.usuarioDtoToUsuario(socioClienteDTO.getSocio()));
            socioCliente.setCliente(usuarioConverter.usuarioDtoToUsuario(socioClienteDTO.getCliente()));
        }
        return socioCliente;
    }

    public SocioClienteDTO socioClienteToSocioClienteDTO(SocioCliente socioCliente) {
        SocioClienteDTO socioClienteDTO = new SocioClienteDTO();
        if (socioCliente != null) {
            socioClienteDTO.setId(socioCliente.getId());
            socioClienteDTO.setSocio(usuarioConverter.usuarioToUsuarioDTO(socioCliente.getSocio()));
            socioClienteDTO.setCliente(usuarioConverter.usuarioToUsuarioDTO(socioCliente.getCliente()));
            socioClienteDTO.setFechaRegistro(socioCliente.getFechaRegistro());
            socioClienteDTO.setFechaActualizacion(socioCliente.getFechaActualizacion());
        }
        return socioClienteDTO;
    }
}
