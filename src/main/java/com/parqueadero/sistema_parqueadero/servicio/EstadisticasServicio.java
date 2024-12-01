package com.parqueadero.sistema_parqueadero.servicio;

import com.parqueadero.sistema_parqueadero.dto.EstadisticasDTO;
import com.parqueadero.sistema_parqueadero.repositorio.HistorialVehiculoRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class EstadisticasServicio {

    private final HistorialVehiculoRepository historialVehiculoRepository;

    public EstadisticasServicio(HistorialVehiculoRepository historialVehiculoRepository) {
        this.historialVehiculoRepository = historialVehiculoRepository;
    }

    public EstadisticasDTO obtenerEstadisticas() {
        EstadisticasDTO estadisticasDTO = new EstadisticasDTO();

        // Ingresos por hora
        Map<Integer, Long> hourlyStats = new LinkedHashMap<>();
        for (Object[] row : historialVehiculoRepository.findIngresosPorHora()) {
            hourlyStats.put((Integer) row[0], (Long) row[1]);
        }
        estadisticasDTO.setHourlyStats(hourlyStats);

        // Tipos de vehículos
        Map<String, Long> vehicleTypes = new LinkedHashMap<>();
        for (Object[] row : historialVehiculoRepository.findTiposDeVehiculos()) {
            vehicleTypes.put((String) row[0], (Long) row[1]);
        }
        estadisticasDTO.setVehicleTypes(vehicleTypes);

        // Días más concurridos
        Map<String, Long> daysData = new LinkedHashMap<>();
        for (Object[] row : historialVehiculoRepository.findDiasMasConcurridos()) {
            daysData.put(row[0].toString(), (Long) row[1]);
        }
        estadisticasDTO.setDaysData(daysData);

        return estadisticasDTO;
    }
}
