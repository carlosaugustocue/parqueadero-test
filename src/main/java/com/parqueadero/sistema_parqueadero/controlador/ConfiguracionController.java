package com.parqueadero.sistema_parqueadero.controlador;

import com.parqueadero.sistema_parqueadero.modelo.ConfiguracionGeneral;
import com.parqueadero.sistema_parqueadero.modelo.Tarifa;
import com.parqueadero.sistema_parqueadero.repositorio.ConfiguracionGeneralRepository;
import com.parqueadero.sistema_parqueadero.repositorio.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/configuracion")
public class ConfiguracionController {

    @Autowired
    private ConfiguracionGeneralRepository configuracionGeneralRepository;

    @Autowired
    private TarifaRepository tarifaRepository;

    // Obtener nombre del parqueadero
    @GetMapping("/nombre-parqueadero")
    public ConfiguracionGeneral obtenerNombreParqueadero() {
        return configuracionGeneralRepository.findAll().stream().findFirst().orElse(null);
    }

    // Actualizar nombre del parqueadero
    @PostMapping("/nombre-parqueadero")
    public ConfiguracionGeneral actualizarNombreParqueadero(@RequestBody ConfiguracionGeneral configuracion) {
        return configuracionGeneralRepository.save(configuracion);
    }

    // Obtener todas las tarifas
    @GetMapping("/tarifas")
    public List<Tarifa> obtenerTarifas() {
        return tarifaRepository.findAll();
    }

    // Crear o actualizar una tarifa
    @PostMapping("/tarifas")
    public Tarifa guardarTarifa(@RequestBody Tarifa tarifa) {
        return tarifaRepository.save(tarifa);
    }

    // Eliminar una tarifa
    @DeleteMapping("/tarifas/{id}")
    public void eliminarTarifa(@PathVariable Long id) {
        tarifaRepository.deleteById(id);
    }
}
