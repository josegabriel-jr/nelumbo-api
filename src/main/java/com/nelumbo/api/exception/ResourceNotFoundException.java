package com.nelumbo.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private static final Long UID = 1L;
    private String nombreRecurso;
    private String nombreCampo;
    private Long valorCampo;

    public ResourceNotFoundException(){}
    public ResourceNotFoundException(String nombreRecurso, String nombreCampo, Long valorCampo) {
        super(String.format("%s No encontrado con : %s : '%s'", nombreRecurso, nombreCampo, valorCampo));
        this.nombreRecurso = nombreRecurso;
        this.nombreCampo = nombreCampo;
        this.valorCampo = valorCampo;
    }

}
