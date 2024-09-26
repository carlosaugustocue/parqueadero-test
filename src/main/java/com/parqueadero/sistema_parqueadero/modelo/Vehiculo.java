package com.parqueadero.sistema_parqueadero.modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String placa;
    private String tipoVehiculo;
    private LocalDateTime horaIngreso;
    private LocalDateTime horaSalida;

    // Constructor vacío (requerido por JPA)
    public Vehiculo() {
    }

    // Constructor con parámetros
    public Vehiculo(String placa, String tipoVehiculo, LocalDateTime horaIngreso) {
        this.placa = placa;
        this.tipoVehiculo = tipoVehiculo;
        this.horaIngreso = horaIngreso;
    }

    // Métodos para calcular el tiempo de estancia y el costo
    public long calcularTiempoEstancia() {
        if (horaSalida != null && horaIngreso != null) {
            return java.time.Duration.between(horaIngreso, horaSalida).toHours();
        }
        return 0;
    }

    public double calcularCosto(double tarifaPorHora) {
        return calcularTiempoEstancia() * tarifaPorHora;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public LocalDateTime getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(LocalDateTime horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "id=" + id +
                ", placa='" + placa + '\'' +
                ", tipoVehiculo='" + tipoVehiculo + '\'' +
                ", horaIngreso=" + horaIngreso +
                ", horaSalida=" + horaSalida +
                '}';
    }
}
