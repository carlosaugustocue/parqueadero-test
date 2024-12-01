package com.parqueadero.sistema_parqueadero.repositorio;

import com.parqueadero.sistema_parqueadero.modelo.HistorialVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HistorialVehiculoRepository extends JpaRepository<HistorialVehiculo, Long> {

    @Query("SELECT HOUR(h.horaIngreso) as hora, COUNT(h) as ingresos FROM HistorialVehiculo h GROUP BY HOUR(h.horaIngreso) ORDER BY hora")
    List<Object[]> findIngresosPorHora();

    @Query("SELECT h.tipoVehiculo as tipo, COUNT(h) as cantidad FROM HistorialVehiculo h GROUP BY h.tipoVehiculo ORDER BY cantidad DESC")
    List<Object[]> findTiposDeVehiculos();

    @Query("SELECT DATE(h.horaIngreso) as dia, COUNT(h) as cantidad FROM HistorialVehiculo h GROUP BY DATE(h.horaIngreso) ORDER BY cantidad DESC")
    List<Object[]> findDiasMasConcurridos();
}
