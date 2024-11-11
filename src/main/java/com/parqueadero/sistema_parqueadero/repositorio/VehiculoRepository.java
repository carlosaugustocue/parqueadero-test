package com.parqueadero.sistema_parqueadero.repositorio;

import com.parqueadero.sistema_parqueadero.modelo.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repository interface for managing {@link Vehiculo} entities.
 * Extends {@link JpaRepository} to provide CRUD operations.
 */
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

    /**
     * Finds a vehicle by its license plate.
     *
     * @param placa the license plate of the vehicle to find
     * @return an Optional containing the found vehicle, or an empty Optional if no vehicle was found
     */
    Optional<Vehiculo> findByPlaca(String placa);
}





