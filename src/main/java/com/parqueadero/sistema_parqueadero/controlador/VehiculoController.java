package com.parqueadero.sistema_parqueadero.controlador;

import com.parqueadero.sistema_parqueadero.modelo.Vehiculo;
import com.parqueadero.sistema_parqueadero.servicio.GestorParqueadero;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    private final GestorParqueadero gestor;

    public VehiculoController(GestorParqueadero gestor) {
        this.gestor = gestor;
    }

    /*@PostMapping
    public Vehiculo registrarVehiculo(@RequestBody Vehiculo vehiculo) {
        System.out.println("Esto es una prueba");
        return gestor.registrarEntrada(vehiculo);
    }*/

    @PutMapping("/salida/{placa}")
    public double registrarSalida(@PathVariable String placa) {
        System.out.println("Actualizando el vehiculo de placa: "+ placa);
        return gestor.registrarSalida(placa);
    }

    /*@GetMapping("/entrada/{placa}")
    public String mostrar(@PathVariable String placa) {
        System.out.println("Actualizando el vehiculo de placa: "+ placa);
        return placa;
    }*/
    @GetMapping("/entrada/{placa}")
    public Map<String, String> mostrar(@PathVariable String placa) {
        System.out.println("Mostrando el vehículo con placa: " + placa);

        Map<String, String> response = new HashMap<>();
        response.put("placa", placa);
        response.put("mensaje", "Vehículo encontrado");
        response.put("fechaEntrada", "2021-10-10 10:00:00");

        return response;
    }

    @PostMapping("/entrada")
    public Map<String, Object> registrarVehiculo(@RequestBody Vehiculo vehiculo) {
        System.out.println("Registrando el vehículo con placa: " + vehiculo.getPlaca());

        Vehiculo vehiculoRegistrado = gestor.registrarEntrada(vehiculo);

        // Verificar si el vehículo fue registrado correctamente o no había espacios disponibles
        if (vehiculoRegistrado == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "No hay espacios disponibles en el parqueadero.");
            return response;
        }

        // Preparar la respuesta de éxito
        Map<String, Object> response = new HashMap<>();
        response.put("placa", vehiculoRegistrado.getPlaca());
        response.put("mensaje", "Vehículo registrado exitosamente");
        response.put("fechaEntrada", vehiculoRegistrado.getHoraIngreso());

        return response;
    }





}

