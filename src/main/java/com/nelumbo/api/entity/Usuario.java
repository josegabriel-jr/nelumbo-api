package com.nelumbo.api.entity;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
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
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(nullable = false, name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String pass;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="rol_id")
    private Rol rol;

    @CreationTimestamp
    @Column(name = "fecharegistro")
    private LocalDateTime fechaRegistro;

    @UpdateTimestamp
    @Column(name = "fechaactualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;

}
