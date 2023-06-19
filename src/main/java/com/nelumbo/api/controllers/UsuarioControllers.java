package com.nelumbo.api.controllers;

import com.nelumbo.api.dto.RolDTO;
import com.nelumbo.api.dto.UsuarioDTO;
import com.nelumbo.api.security.UserDetailServiceImpl;
import com.nelumbo.api.service.RolService;
import com.nelumbo.api.service.UsuarioService;
import com.nelumbo.api.utils.ErrorResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/usuario")
public class UsuarioControllers {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private RolService rolService;


    @PostMapping
    public ResponseEntity<?> registrarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO, BindingResult bindingResult) {
        try {
            if (!bindingResult.hasErrors()) {
                String rolUsuarioActual = userDetailService.obtenerRolUsuarioActual();
                RolDTO rolDTO = rolService.obtenerPorPorId(usuarioDTO.getRol().getId());
                usuarioDTO.setPass(new BCryptPasswordEncoder().encode(usuarioDTO.getPass()));

                if ("ADMIN".equals(rolUsuarioActual)) {
                    return new ResponseEntity<>(usuarioService.crearUsuario(usuarioDTO), HttpStatus.CREATED);
                } else if ("SOCIO".equals(rolUsuarioActual) && ("CLIENTE".equals(rolDTO.getNombre()))) {
                    return new ResponseEntity<>(usuarioService.crearUsuario(usuarioDTO), HttpStatus.CREATED);
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
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/lista")
    public ResponseEntity<List<UsuarioDTO>> ListadoUsuarios() {
        return new ResponseEntity<>(usuarioService.listUsuario(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(@NotNull @PathVariable(name = "id") long id) {
        return ResponseEntity.ok(usuarioService.obtenerUsuarioPorId(id));
    }


}
