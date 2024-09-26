package com.parqueadero.sistema_parqueadero.controlador;

import com.parqueadero.sistema_parqueadero.modelo.Vehiculo;
import com.parqueadero.sistema_parqueadero.servicio.GestorParqueadero;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    private final GestorParqueadero gestor;

    public VehiculoController(GestorParqueadero gestor) {
        this.gestor = gestor;
    }

    @PostMapping
    public Vehiculo registrarVehiculo(@RequestBody Vehiculo vehiculo) {
        return gestor.registrarEntrada(vehiculo);
    }

    @PutMapping("/salida/{placa}")
    public double registrarSalida(@PathVariable String placa) {
        return gestor.registrarSalida(placa);
    }
}

