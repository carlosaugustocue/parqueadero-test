package com.parqueadero.sistema_parqueadero.repositorio;

import com.parqueadero.sistema_parqueadero.modelo.HistorialVehiculo;
import com.parqueadero.sistema_parqueadero.modelo.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransaccionRepositorio extends JpaRepository<Transaccion, Long> {
    @Query("SELECT HOUR(t.horaIngreso) as hora, COUNT(t) as ingresos FROM Transaccion t WHERE t.horaIngreso IS NOT NULL GROUP BY HOUR(t.horaIngreso) ORDER BY hora")
    List<Object[]> obtenerIngresosPorHora();

    @Query("SELECT t.tipoVehiculo, COUNT(t) FROM Transaccion t GROUP BY t.tipoVehiculo")
    List<Object[]> contarPorTipoVehiculo();

    @Query("SELECT FUNCTION('DAYNAME', t.horaIngreso), COUNT(t) FROM Transaccion t GROUP BY FUNCTION('DAYNAME', t.horaIngreso)")
    List<Object[]> obtenerDiasMasConcurridos();
}

