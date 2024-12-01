package com.parqueadero.sistema_parqueadero.controlador;

import com.parqueadero.sistema_parqueadero.dto.EstadisticasDTO;
import com.parqueadero.sistema_parqueadero.modelo.HistorialVehiculo;
import com.parqueadero.sistema_parqueadero.servicio.EstadisticasServicio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EstadisticasController {

    private final EstadisticasServicio estadisticasServicio;

    public EstadisticasController(EstadisticasServicio estadisticasServicio) {
        this.estadisticasServicio = estadisticasServicio;
    }

    @GetMapping("/estadisticas")
    public EstadisticasDTO obtenerEstadisticas() {
        return estadisticasServicio.obtenerEstadisticas();
    }

    @GetMapping("/ultimos-vehiculos")
    public List<HistorialVehiculo> obtenerUltimosVehiculos(
            @RequestParam(defaultValue = "5") int limite) {
        return estadisticasServicio.obtenerUltimosVehiculos(limite);
    }
}
