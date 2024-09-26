package com.parqueadero.sistema_parqueadero.repositorio;


import com.parqueadero.sistema_parqueadero.modelo.EspacioParqueadero;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EspacioRepository extends JpaRepository<EspacioParqueadero, Long> {
    List<EspacioParqueadero> findByOcupado(boolean ocupado);
}

