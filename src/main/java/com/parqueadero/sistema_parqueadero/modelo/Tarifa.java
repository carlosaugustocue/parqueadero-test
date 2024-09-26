package com.parqueadero.sistema_parqueadero.modelo;

import jakarta.persistence.*;

@Entity
public class Tarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoVehiculo;
    private double precioPorHora;

    // Constructor por defecto
    public Tarifa() {
    }

    // Constructor con parámetros
    public Tarifa(String tipoVehiculo, double precioPorHora) {
        this.tipoVehiculo = tipoVehiculo;
        this.precioPorHora = precioPorHora;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public double getPrecioPorHora() {
        return precioPorHora;
    }

    public void setPrecioPorHora(double precioPorHora) {
        this.precioPorHora = precioPorHora;
    }
}

