package com.parqueadero.sistema_parqueadero.servicio;

import com.parqueadero.sistema_parqueadero.modelo.HistorialVehiculo;
import com.parqueadero.sistema_parqueadero.modelo.Vehiculo;
import com.parqueadero.sistema_parqueadero.modelo.EspacioParqueadero;
import com.parqueadero.sistema_parqueadero.repositorio.HistorialVehiculoRepository;
import com.parqueadero.sistema_parqueadero.repositorio.VehiculoRepository;
import com.parqueadero.sistema_parqueadero.repositorio.EspacioRepository;
import com.parqueadero.sistema_parqueadero.repositorio.TarifaRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
    import java.util.Map;
import java.util.Optional;

@Service
public class GestorParqueadero {

    private final VehiculoRepository vehiculoRepo;
    private final EspacioRepository espacioRepo;
    private final TarifaRepository tarifaRepo;
    private final HistorialVehiculoRepository historialRepo;
    private static final int LIMITE_CUPOS = 10; // Define el límite de cupos aquí

    private static final double TARIFA_POR_HORA = 5000; // Tarifa en COP
    private static final double TARIFA_POR_MINUTO = 100; // Tarifa en COP

    public GestorParqueadero(VehiculoRepository vehiculoRepo, EspacioRepository espacioRepo, TarifaRepository tarifaRepo, HistorialVehiculoRepository historialRepo) {
        this.vehiculoRepo = vehiculoRepo;
        this.espacioRepo = espacioRepo;
        this.tarifaRepo = tarifaRepo;
        this.historialRepo = historialRepo;
    }

    // Lógica para registrar la entrada de un vehículo
    public String registrarEntrada(Vehiculo vehiculo) {
        // Verificar si el vehículo ya está en el parqueadero
        Optional<Vehiculo> vehiculoExistente = vehiculoRepo.findByPlaca(vehiculo.getPlaca());
        if (vehiculoExistente.isPresent()) {
            return "El vehículo con placa " + vehiculo.getPlaca() + " ya está registrado en el parqueadero.";
        }


        List<Vehiculo> vehiculosEstacionados = vehiculoRepo.findAll();
        if (vehiculosEstacionados.size() >= LIMITE_CUPOS) {
            return "No hay espacio disponible en el parqueadero.";
        }
        vehiculo.setHoraIngreso(LocalDateTime.now());
        vehiculoRepo.save(vehiculo);
        return "Vehículo registrado con éxito.";
    }

    // Lógica para registrar la salida y calcular el costo
    public String registrarSalida(String placa, boolean cobrarPorMinuto) {
        Optional<Vehiculo> vehiculoOpt = vehiculoRepo.findByPlaca(placa);
        if (vehiculoOpt.isEmpty()) {
            return "Vehículo no encontrado.";
        }

        Vehiculo vehiculo = vehiculoOpt.get();
        vehiculo.setHoraSalida(LocalDateTime.now());
        Duration duracion = Duration.between(vehiculo.getHoraIngreso(), vehiculo.getHoraSalida());

        double costo;
        if (cobrarPorMinuto) {
            // Calcular costo por minuto
            costo = duracion.toMinutes() * TARIFA_POR_MINUTO;
        } else {
            // Calcular costo por hora completa
            costo = (duracion.toHours() + 1) * TARIFA_POR_HORA; // Redondeo hacia arriba
        }

        // Guardar registro en HistorialVehiculo
        HistorialVehiculo historial = new HistorialVehiculo(
                vehiculo.getPlaca(),
                vehiculo.getHoraIngreso(),
                vehiculo.getHoraSalida()
        );
        historialRepo.save(historial);

        // Eliminar el vehículo actual del estacionamiento
        vehiculoRepo.delete(vehiculo);

        return String.format("Vehículo retirado. Costo total: $%,.2f COP", costo);
    }

    public Map<String, Object> obtenerDetallesVehiculo(String placa, boolean cobrarPorMinuto) {
        Optional<Vehiculo> vehiculoOpt = vehiculoRepo.findByPlaca(placa);
        if (vehiculoOpt.isEmpty()) {
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Vehículo no encontrado.");
            return respuesta;
        }

        Vehiculo vehiculo = vehiculoOpt.get();
        Duration duracion = Duration.between(vehiculo.getHoraIngreso(), LocalDateTime.now());

        double costo;
        if (cobrarPorMinuto) {
            costo = duracion.toMinutes() * TARIFA_POR_MINUTO;
        } else {
            costo = (duracion.toHours() + 1) * TARIFA_POR_HORA; // Redondeo hacia arriba
        }

        Map<String, Object> detalles = new HashMap<>();
        detalles.put("placa", vehiculo.getPlaca());
        detalles.put("horaIngreso", vehiculo.getHoraIngreso());
        detalles.put("valorFacturado", String.format("$%,.2f COP", costo));

        return detalles;
    }

}

