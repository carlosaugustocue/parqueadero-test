package com.parqueadero.sistema_parqueadero.controlador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parqueadero.sistema_parqueadero.servicio.MapService;

import java.util.List;
import java.util.Map;

@RestController
public class MapController {

    private final MapService MapService;

    public MapController(MapService MapService, com.parqueadero.sistema_parqueadero.servicio.MapService mapService) {
        this.MapService = mapService;
    }

    @GetMapping("/api/parqueaderos")
    public List<Map<String, Object>> obtenerParqueaderos(
            @RequestParam double latitud,
            @RequestParam double longitud,
            @RequestParam(defaultValue = "5") double radioKm) {
        return MapService.obtenerParqueaderos(latitud, longitud, radioKm);
    }
}
