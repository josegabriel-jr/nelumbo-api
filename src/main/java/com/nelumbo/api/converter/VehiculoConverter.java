package com.nelumbo.api.converter;

import com.nelumbo.api.dto.UsuarioDTO;
import com.nelumbo.api.dto.VehiculoDTO;
import com.nelumbo.api.entity.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehiculoConverter {

    @Autowired
    ParqueaderoConverter parqueaderoConverter;

    public Vehiculo vehiculoDtoToVehiculo(VehiculoDTO vehiculoDTO) {
        Vehiculo vehiculo = new Vehiculo();
        if (vehiculoDTO != null) {
            vehiculo.setId(vehiculoDTO.getId());
            vehiculo.setModelo(vehiculoDTO.getModelo());
            vehiculo.setPlaca(vehiculoDTO.getPlaca());
            vehiculo.setFechaIngreso(vehiculoDTO.getFechaIngreso());
            vehiculo.setFechaSalida(vehiculoDTO.getFechaSalida());
            vehiculo.setFechaRegistro(vehiculoDTO.getFechaRegistro());
            vehiculo.setFechaActualizacion(vehiculoDTO.getFechaActualizacion());
            vehiculo.setParqueadero(parqueaderoConverter.parqueaderoDtoToParqueadero(vehiculoDTO.getParqueadero()));


        }
        return vehiculo;
    }
    public VehiculoDTO vehiculoToVehiculoDto(Vehiculo vehiculo) {
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        if (vehiculo != null) {
            vehiculoDTO.setId(vehiculo.getId());
            vehiculoDTO.setModelo(vehiculo.getModelo());
            vehiculoDTO.setPlaca(vehiculo.getPlaca());
            vehiculoDTO.setFechaIngreso(vehiculo.getFechaIngreso());
            vehiculoDTO.setFechaSalida(vehiculo.getFechaSalida());
            vehiculoDTO.setFechaRegistro(vehiculo.getFechaRegistro());
            vehiculoDTO.setFechaActualizacion(vehiculo.getFechaActualizacion());
            vehiculoDTO.setParqueadero(parqueaderoConverter.parqueaderoToParqueaderoDTO(vehiculo.getParqueadero()));
        }
        return vehiculoDTO;
    }
}
