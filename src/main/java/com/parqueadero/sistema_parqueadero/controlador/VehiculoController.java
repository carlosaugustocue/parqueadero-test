package com.parqueadero.sistema_parqueadero.controlador;

import com.parqueadero.sistema_parqueadero.modelo.Vehiculo;
import com.parqueadero.sistema_parqueadero.servicio.GestorParqueadero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    private final GestorParqueadero gestor;

    @Autowired
    public VehiculoController(GestorParqueadero gestor) {
        this.gestor = gestor;
    }

    @PostMapping("/entrada")
    public Map<String, String> registrarVehiculo(@RequestBody Vehiculo vehiculo) {
        String mensaje = gestor.registrarEntrada(vehiculo);

        Map<String, String> response = new HashMap<>();
        response.put("placa", vehiculo.getPlaca());
        response.put("mensaje", mensaje);

        return response;
    }

    @PutMapping("/salida/{placa}")
    public Map<String, Object> registrarSalida(@PathVariable String placa, @RequestParam boolean cobrarPorMinuto) {
        Map<String, Object> response = gestor.registrarSalida(placa, cobrarPorMinuto);
        return response;
    }

    @GetMapping("/detalles/{placa}")
    public Map<String, Object> obtenerDetallesVehiculo(@PathVariable String placa, @RequestParam boolean cobrarPorMinuto) {
        return gestor.obtenerDetallesVehiculo(placa, cobrarPorMinuto);
    }

    @GetMapping("/detalles/minutos/{placa}")
    public Map<String, Object> obtenerDetallesVehiculoConMinutos(@PathVariable String placa) {
        return gestor.obtenerDetallesVehiculoConMinutos(placa);
    }

    // **Endpoint para obtener todos los vehículos**
    @GetMapping
    public List<Vehiculo> obtenerTodosLosVehiculos() {
        return gestor.obtenerTodosLosVehiculos();
    }
}
