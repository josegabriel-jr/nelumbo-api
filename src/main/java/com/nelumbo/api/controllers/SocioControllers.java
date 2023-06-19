package com.nelumbo.api.controllers;

import com.nelumbo.api.dto.UsuarioDTO;
import com.nelumbo.api.exception.ResourceNotFoundException;
import com.nelumbo.api.security.UserDetailServiceImpl;
import com.nelumbo.api.service.SocioClienteService;
import com.nelumbo.api.utils.ErrorResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/socio")
public class SocioControllers {

    @Autowired
    private SocioClienteService socioClienteService;
    @Autowired
    private UserDetailServiceImpl userDetailService;

    @GetMapping("/asociar_cliente_socio")
    public ResponseEntity<?> asocirClienteASocios(@NotNull @RequestParam(name = "idSocio") long idSocio,
                                                  @NotNull @RequestParam(name = "idCliente") long idCliente) {
        try {
            String rolUsuarioActual = userDetailService.obtenerRolUsuarioActual();
            if ("ADMIN".equals(rolUsuarioActual)) {
                return ResponseEntity.ok(socioClienteService.asociarClienteConSocios(idSocio, idCliente));
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

    @GetMapping("/asociar_cliente")
    public ResponseEntity<?> asocirClienteASocio(@NotNull @RequestParam(name = "idCliente") long idCliente) {
        try{
            String rolUsuarioActual = userDetailService.obtenerRolUsuarioActual();
            UsuarioDTO  usuarioDTO = userDetailService.obtenerUsuarioLogueado();
            if ("SOCIO".equals(rolUsuarioActual) && usuarioDTO != null) {
                return ResponseEntity.ok(socioClienteService.asociarClienteConSocios(usuarioDTO.getId(), idCliente));
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
