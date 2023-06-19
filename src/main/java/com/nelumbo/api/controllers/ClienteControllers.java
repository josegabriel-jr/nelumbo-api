package com.nelumbo.api.controllers;

import com.nelumbo.api.dto.ParqueaderoDTO;
import com.nelumbo.api.dto.UsuarioDTO;
import com.nelumbo.api.dto.VehiculoDTO;
import com.nelumbo.api.entity.Parqueadero;
import com.nelumbo.api.entity.Vehiculo;
import com.nelumbo.api.security.UserDetailServiceImpl;
import com.nelumbo.api.service.ParqueaderoService;
import com.nelumbo.api.utils.ErrorResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/cliente")
public class ClienteControllers {
    @Autowired
    UserDetailServiceImpl userDetailService;

    @Autowired
    ParqueaderoService parqueaderoService;

    @GetMapping("/list_detalle_parqueaderos/idparqueadero{idParqueadero}/idsocio{idSocio}")
    public ResponseEntity<?> listaDetalleParqueaderoPorSocio(@PathVariable(name = "idSocio") @NotNull Long idSocio) {
        String rolUsuarioActual = userDetailService.obtenerRolUsuarioActual();
        if ("CLIENTE".equals(rolUsuarioActual)) {
            ParqueaderoDTO parqueaderoDTO = parqueaderoService.obtenerParqueaderoSocio(idSocio);
            List<VehiculoDTO> listVehiculosDto = parqueaderoService.obtenerVehiculosEnParqueadero(parqueaderoDTO.getId());
            return ResponseEntity.ok(listVehiculosDto);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("El usuario no tiene Permisos");
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(errorResponse);
        }
    }

    @GetMapping("/listado_detalle_parqueadero_socio")
    public ResponseEntity<?> listaDetalleParqueadero() {

        String rolUsuarioActual = userDetailService.obtenerRolUsuarioActual();
        UsuarioDTO usuarioDTO = userDetailService.obtenerUsuarioLogueado();
        if ("CLIENTE".equals(rolUsuarioActual)) {
            List<VehiculoDTO> listVehiculosDTO = parqueaderoService.listadoVehiculosParqueaderos();
            return ResponseEntity.ok(listVehiculosDTO);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("El usuario no tiene Permisos");
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(errorResponse);
        }
    }

    @PostMapping("/{idParqueadero}/vehiculos/{idVehiculo}/entrada")
    public ResponseEntity<?> registrarEntradaVehiculo(
            @PathVariable Long idParqueadero, @PathVariable Long idVehiculo) {
        String rolUsuarioActual = userDetailService.obtenerRolUsuarioActual();
        UsuarioDTO usuarioDTO = userDetailService.obtenerUsuarioLogueado();
        if ("CLIENTE".equals(rolUsuarioActual)) {
            ParqueaderoDTO parqueaderoDTO = parqueaderoService.registrarEntradaVehiculo(idParqueadero, idVehiculo);
            return ResponseEntity.ok(parqueaderoDTO);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("El usuario no tiene Permisos");
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(errorResponse);
        }
    }

    @PostMapping("/{idParqueadero}/vehiculos/{idVehiculo}/salida")
    public ResponseEntity<?> registrarSalidaVehiculo(
            @PathVariable Long idParqueadero, @PathVariable Long idVehiculo) {
        String rolUsuarioActual = userDetailService.obtenerRolUsuarioActual();
        UsuarioDTO usuarioDTO = userDetailService.obtenerUsuarioLogueado();
        if ("CLIENTE".equals(rolUsuarioActual)) {
            ParqueaderoDTO parqueaderoDTO = parqueaderoService.registrarSalidaVehiculo(idParqueadero, idVehiculo);
            return ResponseEntity.ok(parqueaderoDTO);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("El usuario no tiene Permisos");
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(errorResponse);
        }

    }

}
