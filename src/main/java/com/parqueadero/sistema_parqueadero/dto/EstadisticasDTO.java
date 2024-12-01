package com.parqueadero.sistema_parqueadero.dto;

import java.util.Map;

public class EstadisticasDTO {

    private Map<Integer, Long> hourlyStats; // Hora -> Ingresos
    private Map<String, Long> vehicleTypes; // Tipo Vehículo -> Conteo
    private Map<String, Long> daysData;    // Día -> Conteo

    // Constructor vacío
    public EstadisticasDTO() {}

    // Constructor con todos los campos
    public EstadisticasDTO(Map<Integer, Long> hourlyStats, Map<String, Long> vehicleTypes, Map<String, Long> daysData) {
        this.hourlyStats = hourlyStats;
        this.vehicleTypes = vehicleTypes;
        this.daysData = daysData;
    }

    // Getters y setters
    public Map<Integer, Long> getHourlyStats() {
        return hourlyStats;
    }

    public void setHourlyStats(Map<Integer, Long> hourlyStats) {
        this.hourlyStats = hourlyStats;
    }

    public Map<String, Long> getVehicleTypes() {
        return vehicleTypes;
    }

    public void setVehicleTypes(Map<String, Long> vehicleTypes) {
        this.vehicleTypes = vehicleTypes;
    }

    public Map<String, Long> getDaysData() {
        return daysData;
    }

    public void setDaysData(Map<String, Long> daysData) {
        this.daysData = daysData;
    }

    // toString para depuración
    @Override
    public String toString() {
        return "EstadisticasDTO{" +
                "hourlyStats=" + hourlyStats +
                ", vehicleTypes=" + vehicleTypes +
                ", daysData=" + daysData +
                '}';
    }
}
