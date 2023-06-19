package com.nelumbo.api.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioDTO {
    private Long id;
    @NotNull
    @NotBlank
    private String nombre;

    @NotNull
    @Email
    private String email;

    @NotNull
    @NotBlank
    private String pass;

    @NotNull
    private RolDTO rol;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaActualizacion;

}
