package com.parqueadero.sistema_parqueadero.controlador;

import com.parqueadero.sistema_parqueadero.modelo.Vehiculo;
import com.parqueadero.sistema_parqueadero.servicio.GestorParqueadero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
        // Llamar al método del servicio que ya devuelve un Map con la respuesta JSON
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


//    @GetMapping("/entrada/{placa}")
//    public Map<String, String> mostrar(@PathVariable String placa) {
//        // Aquí puedes agregar lógica para mostrar detalles adicionales si es necesario
//        Map<String, String> response = new HashMap<>();
//        response.put("placa", placa);
//        response.put("mensaje", "Detalles del vehículo");
//
//        return response;
//    }
}
