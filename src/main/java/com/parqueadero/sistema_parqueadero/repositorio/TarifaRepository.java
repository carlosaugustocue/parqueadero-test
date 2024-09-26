package com.parqueadero.sistema_parqueadero.repositorio;


import com.parqueadero.sistema_parqueadero.modelo.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Long> {

    // Método personalizado para encontrar la tarifa según el tipo de vehículo
    Optional<Tarifa> findByTipoVehiculo(String tipoVehiculo);
}

