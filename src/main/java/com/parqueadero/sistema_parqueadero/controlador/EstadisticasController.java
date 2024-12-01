package com.parqueadero.sistema_parqueadero.controlador;

import com.parqueadero.sistema_parqueadero.dto.EstadisticasDTO;
import com.parqueadero.sistema_parqueadero.servicio.EstadisticasServicio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estadisticas")
public class EstadisticasController {

    private final EstadisticasServicio estadisticasServicio;

    public EstadisticasController(EstadisticasServicio estadisticasServicio) {
        this.estadisticasServicio = estadisticasServicio;
    }

    @GetMapping
    public EstadisticasDTO obtenerEstadisticas() {
        return estadisticasServicio.obtenerEstadisticas();
    }
}
