package com.parqueadero.sistema_parqueadero.repositorio;

import com.parqueadero.sistema_parqueadero.modelo.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    Optional<Vehiculo> findByPlaca(String placa);
}

