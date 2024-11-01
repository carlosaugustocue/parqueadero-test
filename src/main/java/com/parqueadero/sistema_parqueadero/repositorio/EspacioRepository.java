package com.parqueadero.sistema_parqueadero.repositorio;


import com.parqueadero.sistema_parqueadero.modelo.EspacioParqueadero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EspacioRepository extends JpaRepository<EspacioParqueadero, Long> {
    List<EspacioParqueadero> findByOcupado(boolean ocupado);
}


