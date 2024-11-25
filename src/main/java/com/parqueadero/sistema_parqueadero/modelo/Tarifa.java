package com.parqueadero.sistema_parqueadero.modelo;

import jakarta.persistence.*;

@Entity
public class Tarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_vehiculo", nullable = false)
    private String tipoVehiculo;

    @Column(name = "precio_por_hora", nullable = false)
    private double precioPorHora;

    @Column(name = "precio_por_minuto", nullable = false)
    private double precioPorMinuto;

    // Constructor por defecto (necesario para JPA)
    public Tarifa() {
    }

    // Constructor con parámetros
    public Tarifa(String tipoVehiculo, double precioPorHora, double precioPorMinuto) {
        if (tipoVehiculo == null || tipoVehiculo.isEmpty()) {
            throw new IllegalArgumentException("El tipo de vehículo es obligatorio.");
        }
        if (precioPorHora <= 0) {
            throw new IllegalArgumentException("El precio por hora debe ser mayor a 0.");
        }
        if (precioPorMinuto <= 0) {
            throw new IllegalArgumentException("El precio por minuto debe ser mayor a 0.");
        }
        this.tipoVehiculo = tipoVehiculo;
        this.precioPorHora = precioPorHora;
        this.precioPorMinuto = precioPorMinuto;
    }

    // Getters y Setters con validaciones
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
        if (tipoVehiculo == null || tipoVehiculo.isEmpty()) {
            throw new IllegalArgumentException("El tipo de vehículo es obligatorio.");
        }
        this.tipoVehiculo = tipoVehiculo;
    }

    public double getPrecioPorHora() {
        return precioPorHora;
    }

    public void setPrecioPorHora(double precioPorHora) {
        if (precioPorHora <= 0) {
            throw new IllegalArgumentException("El precio por hora debe ser mayor a 0.");
        }
        this.precioPorHora = precioPorHora;
    }

    public double getPrecioPorMinuto() {
        return precioPorMinuto;
    }

    public void setPrecioPorMinuto(double precioPorMinuto) {
        if (precioPorMinuto <= 0) {
            throw new IllegalArgumentException("El precio por minuto debe ser mayor a 0.");
        }
        this.precioPorMinuto = precioPorMinuto;
    }

    // Método toString (opcional, útil para depuración)
    @Override
    public String toString() {
        return "Tarifa{" +
                "id=" + id +
                ", tipoVehiculo='" + tipoVehiculo + '\'' +
                ", precioPorHora=" + precioPorHora +
                ", precioPorMinuto=" + precioPorMinuto +
                '}';
    }
}
