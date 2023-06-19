package com.nelumbo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RolDTO {
    private Long id;
    private String nombre;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaActualizacion;
}
