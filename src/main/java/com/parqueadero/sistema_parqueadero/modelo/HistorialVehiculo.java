package com.parqueadero.sistema_parqueadero.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class HistorialVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String placa;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime horaIngreso;

    @Column(nullable = true) // Puede ser null si no hay hora de salida.
    private LocalDateTime horaSalida;

    @NotNull
    @Column(nullable = false)
    private String tipoVehiculo;

    @Column
    private Double costoTotal; // Opcional para registrar el costo.

    // Constructor vacío
    public HistorialVehiculo() {}

    // Constructor completo
    public HistorialVehiculo(String placa, LocalDateTime horaIngreso, LocalDateTime horaSalida, String tipoVehiculo, Double costoTotal) {
        this.placa = placa;
        this.horaIngreso = horaIngreso;
        this.horaSalida = horaSalida;
        this.tipoVehiculo = tipoVehiculo;
        this.costoTotal = costoTotal;
    }

    // Constructor sin horaSalida ni costoTotal
    public HistorialVehiculo(String placa, LocalDateTime horaIngreso, String tipoVehiculo) {
        this.placa = placa;
        this.horaIngreso = horaIngreso;
        this.tipoVehiculo = tipoVehiculo;
    }

    // Constructor sin costoTotal
    public HistorialVehiculo(String placa, LocalDateTime horaIngreso, LocalDateTime horaSalida, String tipoVehiculo) {
        this.placa = placa;
        this.horaIngreso = horaIngreso;
        this.horaSalida = horaSalida;
        this.tipoVehiculo = tipoVehiculo;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public LocalDateTime getHoraIngreso() { return horaIngreso; }
    public void setHoraIngreso(LocalDateTime horaIngreso) { this.horaIngreso = horaIngreso; }

    public LocalDateTime getHoraSalida() { return horaSalida; }
    public void setHoraSalida(LocalDateTime horaSalida) { this.horaSalida = horaSalida; }

    public String getTipoVehiculo() { return tipoVehiculo; }
    public void setTipoVehiculo(String tipoVehiculo) { this.tipoVehiculo = tipoVehiculo; }

    public Double getCostoTotal() { return costoTotal; }
    public void setCostoTotal(Double costoTotal) { this.costoTotal = costoTotal; }

    // toString para depuración
    @Override
    public String toString() {
        return "HistorialVehiculo{" +
                "id=" + id +
                ", placa='" + placa + '\'' +
                ", horaIngreso=" + horaIngreso +
                ", horaSalida=" + horaSalida +
                ", tipoVehiculo='" + tipoVehiculo + '\'' +
                ", costoTotal=" + costoTotal +
                '}';
    }
}
