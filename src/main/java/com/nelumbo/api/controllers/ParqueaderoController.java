package com.nelumbo.api.controllers;

import com.nelumbo.api.dto.ParqueaderoDTO;
import com.nelumbo.api.dto.UsuarioDTO;
import com.nelumbo.api.dto.VehiculoDTO;
import com.nelumbo.api.entity.Vehiculo;
import com.nelumbo.api.exception.ResourceNotFoundException;
import com.nelumbo.api.security.UserDetailServiceImpl;
import com.nelumbo.api.service.ParqueaderoService;
import com.nelumbo.api.service.RolService;
import com.nelumbo.api.utils.ErrorResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parqueadero")
public class ParqueaderoController {

    @Autowired
    private ParqueaderoService parqueaderoService;
    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private RolService rolService;


    @PostMapping
    public ResponseEntity<?> crearParqueadero(@Valid @RequestBody ParqueaderoDTO parqueaderoDTO, BindingResult bindingResult) {
        try{
            if (!bindingResult.hasErrors()) {
                String rolUsuarioActual = userDetailService.obtenerRolUsuarioActual();
                if ("ADMIN".equals(rolUsuarioActual)) {
                    ParqueaderoDTO nuevoParqueadero = parqueaderoService.crearParqueaderoDto(parqueaderoDTO);
                    return ResponseEntity.status(HttpStatus.CREATED).body(nuevoParqueadero);
                } else {
                    ErrorResponse errorResponse = new ErrorResponse("El usuario no tiene Permisos");
                    return ResponseEntity
                            .status(HttpStatus.FORBIDDEN)
                            .body(errorResponse);
                }
            } else {
                ErrorResponse errorResponse = new ErrorResponse("Campos Incompletos");
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(errorResponse);
            }
        }catch (IllegalArgumentException err){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err.getMessage());
        }catch (ResourceNotFoundException err) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.getMessage());
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<ParqueaderoDTO> buscarParqueaderoPorId(@PathVariable Long id) {
        ParqueaderoDTO parqueaderoDTO = parqueaderoService.buscarParqueaderoPorId(id);
        if (parqueaderoDTO != null) {
            return ResponseEntity.ok(parqueaderoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ParqueaderoDTO>> listaParqueaderoDtos() {
        List<ParqueaderoDTO> listaParqueaderos = parqueaderoService.listaParqueaderoDtos();
        return ResponseEntity.ok(listaParqueaderos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarParqueaderoPorId(@PathVariable Long id) {

        String rolUsuarioActual = userDetailService.obtenerRolUsuarioActual();
        if ("ADMIN".equals(rolUsuarioActual)) {
            ParqueaderoDTO parqueaderoEliminado = parqueaderoService.eliminarParqueaderoPorId(id);
            if (parqueaderoEliminado != null) {
                return ResponseEntity.ok(parqueaderoEliminado);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            ErrorResponse errorResponse = new ErrorResponse("El usuario no tiene Permisos");
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(errorResponse);
        }
    }

    @GetMapping("/asociar_parqueadero_socio")
    public ResponseEntity<?> asociarParqueaderoConSocio(
            @RequestParam(name = "idParqueadero") @NotNull Long idParqueadero,
            @RequestParam(name = "idSocio") @NotNull Long idSocio) {
       try {
           String rolUsuarioActual = userDetailService.obtenerRolUsuarioActual();
           if ("ADMIN".equals(rolUsuarioActual)) {
               ParqueaderoDTO parqueaderoAsociado = parqueaderoService.asociarParqueaderoConSocio(idSocio, idParqueadero);
               return ResponseEntity.ok(parqueaderoAsociado);
           } else {
               ErrorResponse errorResponse = new ErrorResponse("El usuario no tiene Permisos");
               return ResponseEntity
                       .status(HttpStatus.FORBIDDEN)
                       .body(errorResponse);
           }
       }catch (IllegalArgumentException err){
           return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err.getMessage());
       }catch (ResourceNotFoundException err) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.getMessage());
       }catch (Exception err){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err.getMessage());
       }
    }

    @GetMapping("/listado_detalle_parqueadero")
    public ResponseEntity<?> listaDetalleParqueadero(@RequestParam(name = "idParqueadero") @NotNull Long idParqueadero) {

        String rolUsuarioActual = userDetailService.obtenerRolUsuarioActual();
        if ("ADMIN".equals(rolUsuarioActual)) {
            List<VehiculoDTO> listVehiculoDetalle = parqueaderoService.obtenerVehiculosEnParqueadero(idParqueadero);
            return ResponseEntity.ok(listVehiculoDetalle);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("El usuario no tiene Permisos");
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(errorResponse);
        }
    }

    @GetMapping("/listado_detalle_parqueadero_socio")
    public ResponseEntity<?> listaDetalleParqueadero() {

        try{
            String rolUsuarioActual = userDetailService.obtenerRolUsuarioActual();
            UsuarioDTO usuarioDTO = userDetailService.obtenerUsuarioLogueado();
            if ("SOCIO".equals(rolUsuarioActual) && usuarioDTO != null) {
                ParqueaderoDTO parqueaderoDTO = parqueaderoService.obtenerParqueaderoSocio(usuarioDTO.getId());
                List<VehiculoDTO> listVehiculoDetalle = parqueaderoService.obtenerVehiculosEnParqueadero(parqueaderoDTO.getId());
                return ResponseEntity.ok(listVehiculoDetalle);
            } else {
                ErrorResponse errorResponse = new ErrorResponse("El usuario no tiene Permisos");
                return ResponseEntity
                        .status(HttpStatus.FORBIDDEN)
                        .body(errorResponse);
            }
        }catch (IllegalArgumentException err){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err.getMessage());
        }catch (ResourceNotFoundException err) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.getMessage());
        }catch (Exception err){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err.getMessage());
        }
    }



}

