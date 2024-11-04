package com.parqueadero.sistema_parqueadero.modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class HistorialVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String placa;
    private LocalDateTime horaIngreso;
    private LocalDateTime horaSalida;

    // Constructor vacío
    public HistorialVehiculo() {}

    // Constructor con parámetros
    public HistorialVehiculo(String placa, LocalDateTime horaIngreso, LocalDateTime horaSalida) {
        this.placa = placa;
        this.horaIngreso = horaIngreso;
        this.horaSalida = horaSalida;
    }

    // Getters y setters
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
    public LocalDateTime getHoraIngreso() { return horaIngreso; }
    public void setHoraIngreso(LocalDateTime horaIngreso) { this.horaIngreso = horaIngreso; }
    public LocalDateTime getHoraSalida() { return horaSalida; }
    public void setHoraSalida(LocalDateTime horaSalida) { this.horaSalida = horaSalida; }
}

