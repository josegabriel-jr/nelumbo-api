package com.nelumbo.api.converter;

import com.nelumbo.api.dto.RolDTO;
import com.nelumbo.api.entity.Rol;
import org.springframework.stereotype.Component;

@Component
public class RolConverter {

    public Rol rolDtoToRol(RolDTO roltDTO) {
        Rol rol = new Rol();
        if (rol != null) {
            rol.setId(roltDTO.getId());
            rol.setNombre(roltDTO.getNombre());
            rol.setFechaRegistro(roltDTO.getFechaRegistro());
            rol.setFechaActualizacion(roltDTO.getFechaActualizacion());
        }
        return rol;
    }
    public RolDTO rolToRolDto(Rol rol) {
        RolDTO roltDTO = new RolDTO();
        if (roltDTO != null) {
            roltDTO.setId(rol.getId());
            roltDTO.setNombre(rol.getNombre());
            roltDTO.setFechaRegistro(rol.getFechaRegistro());
            roltDTO.setFechaActualizacion(rol.getFechaActualizacion());
        }
        return roltDTO;
    }
}
