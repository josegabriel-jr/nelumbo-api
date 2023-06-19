package com.nelumbo.api.service;

import com.nelumbo.api.dto.SocioClienteDTO;

public interface SocioClienteService {

    SocioClienteDTO asociarClienteConSocios(Long idSocio, Long idCliente);
}
