package com.nelumbo.api.converter;

import com.nelumbo.api.dto.ParqueaderoDTO;
import com.nelumbo.api.dto.RolDTO;
import com.nelumbo.api.entity.Parqueadero;
import com.nelumbo.api.entity.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParqueaderoConverter {

    @Autowired
    VehiculoConverter vehiculoConverter;
    public Parqueadero parqueaderoDtoToParqueadero(ParqueaderoDTO parqueaderoDTO) {
        Parqueadero parqueadero = new Parqueadero();
        if (parqueaderoDTO != null) {
            parqueadero.setId(parqueaderoDTO.getId());
            parqueadero.setNombre(parqueaderoDTO.getNombre());
            parqueadero.setRegistro(parqueaderoDTO.getRegistro());
            parqueadero.setCantidadVehiculos(parqueaderoDTO.getCantidadVehiculos());
            parqueadero.setEstado(parqueaderoDTO.getEstado());
        }
        return parqueadero;
    }
    public ParqueaderoDTO parqueaderoToParqueaderoDTO(Parqueadero parqueadero) {
        ParqueaderoDTO parqueaderoDTO = new ParqueaderoDTO();
        if (parqueadero != null) {
            parqueaderoDTO.setId(parqueadero.getId());
            parqueaderoDTO.setNombre(parqueadero.getNombre());
            parqueaderoDTO.setCantidadVehiculos(parqueadero.getCantidadVehiculos());
            parqueaderoDTO.setVehiculos(parqueadero.getVehiculos());
            parqueaderoDTO.setRegistro(parqueadero.getRegistro());
        }
        return parqueaderoDTO;
    }

}
