package com.parqueadero.sistema_parqueadero.servicio;

import com.parqueadero.sistema_parqueadero.modelo.*;
import com.parqueadero.sistema_parqueadero.repositorio.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List; // <- Importamos List
import java.util.Optional;

@Service
public class GestorParqueadero {

    private final VehiculoRepository vehiculoRepo;
    private final EspacioRepository espacioRepo;
    private final TarifaRepository tarifaRepo;  // Agregamos el repositorio de Tarifa

    public GestorParqueadero(VehiculoRepository vehiculoRepo, EspacioRepository espacioRepo, TarifaRepository tarifaRepo) {
        this.vehiculoRepo = vehiculoRepo;
        this.espacioRepo = espacioRepo;
        this.tarifaRepo = tarifaRepo;
    }

    public Vehiculo registrarEntrada(Vehiculo vehiculo) {
        // Buscar un espacio libre
        EspacioParqueadero espacioLibre = espacioRepo.findByOcupado(false).stream().findFirst().orElse(null);
        if (espacioLibre != null) {
            espacioLibre.asignarEspacio();
            espacioRepo.save(espacioLibre);
            vehiculo.setHoraIngreso(LocalDateTime.now());
            return vehiculoRepo.save(vehiculo);
        }
        return null;  // No hay espacios libres
    }

    public double registrarSalida(String placa) {
        Optional<Vehiculo> optionalVehiculo = vehiculoRepo.findByPlaca(placa);
        if (optionalVehiculo.isPresent()) {
            Vehiculo vehiculo = optionalVehiculo.get();
            vehiculo.setHoraSalida(LocalDateTime.now());

            // Buscar la tarifa por tipo de vehículo
            Optional<Tarifa> optionalTarifa = tarifaRepo.findByTipoVehiculo(vehiculo.getTipoVehiculo());
            if (optionalTarifa.isPresent()) {
                Tarifa tarifa = optionalTarifa.get();
                double costo = vehiculo.calcularCosto(tarifa.getPrecioPorHora());
                vehiculoRepo.save(vehiculo);
                liberarEspacio(vehiculo);
                return costo;
            } else {
                throw new RuntimeException("No se encontró una tarifa para el tipo de vehículo: " + vehiculo.getTipoVehiculo());
            }
        }
        return 0;
    }

    private void liberarEspacio(Vehiculo vehiculo) {
        List<EspacioParqueadero> espacios = espacioRepo.findByOcupado(true);
        EspacioParqueadero espacio = espacios.stream().findFirst().orElse(null);
        if (espacio != null) {
            espacio.liberarEspacio();
            espacioRepo.save(espacio);
        }
    }
}
