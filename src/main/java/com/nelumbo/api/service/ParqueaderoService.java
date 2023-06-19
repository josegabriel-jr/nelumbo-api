package com.nelumbo.api.service;

import com.nelumbo.api.dto.ParqueaderoDTO;
import com.nelumbo.api.dto.VehiculoDTO;
import com.nelumbo.api.entity.Vehiculo;

import java.util.List;

public interface ParqueaderoService {

    ParqueaderoDTO crearParqueaderoDto(ParqueaderoDTO parqueaderoDTO);
    ParqueaderoDTO buscarParqueaderoPorId(Long id);
    List<ParqueaderoDTO> listaParqueaderoDtos();
    ParqueaderoDTO eliminarParqueaderoPorId(Long id);

    ParqueaderoDTO asociarParqueaderoConSocio(Long idUsuario, Long idParqueadero);

    int saberCantidadVehiculosIngresado(Long idParqueadero);

    List<VehiculoDTO> obtenerVehiculosEnParqueadero(Long idParqueadero);

    ParqueaderoDTO obtenerParqueaderoSocio(Long idSocio);

    ParqueaderoDTO registrarEntradaVehiculo(Long parqueaderoId,Long idVehiculo);

    ParqueaderoDTO registrarSalidaVehiculo(Long parqueaderoId,Long idVehiculo);

    List<VehiculoDTO> listadoVehiculosParqueaderos();


}
