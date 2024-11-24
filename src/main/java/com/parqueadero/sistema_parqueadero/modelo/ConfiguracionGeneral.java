package com.parqueadero.sistema_parqueadero.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ConfiguracionGeneral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreParqueadero;

    // Constructor por defecto
    public ConfiguracionGeneral() {
    }

    // Constructor con parámetros
    public ConfiguracionGeneral(String nombreParqueadero) {
        this.nombreParqueadero = nombreParqueadero;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreParqueadero() {
        return nombreParqueadero;
    }

    public void setNombreParqueadero(String nombreParqueadero) {
        this.nombreParqueadero = nombreParqueadero;
    }
}
