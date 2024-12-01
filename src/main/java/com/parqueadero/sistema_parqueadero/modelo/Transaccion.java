package com.parqueadero.sistema_parqueadero.modelo;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

@Entity
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String placa;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime horaIngreso;

    private LocalDateTime horaSalida;

    @NotNull
    @Column(nullable = false)
    private String tipoVehiculo; // e.g., "Automóvil", "Moto", etc.

    @Column
    private Double costoTotal;

    // Getters, setters y constructores
}

