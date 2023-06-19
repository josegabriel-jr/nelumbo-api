package com.nelumbo.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class SocioCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "socio_id")
    private Usuario socio;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Usuario cliente;

    @CreationTimestamp
    @Column(name = "fecharegistro")
    private LocalDateTime fechaRegistro;

    @UpdateTimestamp
    @Column(name = "fechaactualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;

}
