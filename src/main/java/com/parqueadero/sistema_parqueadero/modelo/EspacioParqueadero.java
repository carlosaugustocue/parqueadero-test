package com.parqueadero.sistema_parqueadero.modelo;

import jakarta.persistence.*;

@Entity
public class EspacioParqueadero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numero;
    private boolean ocupado;

    // Constructor vacío (necesario para JPA)
    public EspacioParqueadero() {}

    // Constructor con parámetros
    public EspacioParqueadero(int numero, boolean ocupado) {
        this.numero = numero;
        this.ocupado = ocupado;
    }

    // Métodos para asignar y liberar espacio
    public void asignarEspacio() {
        this.ocupado = true;
    }

    public void liberarEspacio() {
        this.ocupado = false;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
}
