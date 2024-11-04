package com.parqueadero.sistema_parqueadero.repositorio;

import com.parqueadero.sistema_parqueadero.modelo.HistorialVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistorialVehiculoRepository extends JpaRepository<HistorialVehiculo, Long> {
}
