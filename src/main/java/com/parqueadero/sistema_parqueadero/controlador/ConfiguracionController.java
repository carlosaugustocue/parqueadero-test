    package com.parqueadero.sistema_parqueadero.controlador;
    
    import com.parqueadero.sistema_parqueadero.modelo.ConfiguracionGeneral;
    import com.parqueadero.sistema_parqueadero.modelo.Tarifa;
    import com.parqueadero.sistema_parqueadero.repositorio.ConfiguracionGeneralRepository;
    import com.parqueadero.sistema_parqueadero.repositorio.TarifaRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    
    import java.util.List;
    
    @RestController
    @RequestMapping("/api/configuracion")
    public class ConfiguracionController {

        @Autowired
        private ConfiguracionGeneralRepository configuracionGeneralRepository;

        @Autowired
        private TarifaRepository tarifaRepository;

        // Obtener nombre del parqueadero
        @GetMapping("/nombre-parqueadero")
        public ConfiguracionGeneral obtenerNombreParqueadero() {
            return configuracionGeneralRepository.findAll().stream().findFirst().orElse(null);
        }

        @PostMapping("/nombre-parqueadero")
        public ConfiguracionGeneral actualizarNombreParqueadero(@RequestBody ConfiguracionGeneral configuracion) {
            ConfiguracionGeneral existente = configuracionGeneralRepository.findAll().stream().findFirst().orElse(null);
            if (existente != null) {
                existente.setNombreParqueadero(configuracion.getNombreParqueadero());
                return configuracionGeneralRepository.save(existente);
            } else {
                return configuracionGeneralRepository.save(configuracion);
            }
        }



        // Obtener todas las tarifas
        @GetMapping("/tarifas")
        public List<Tarifa> obtenerTarifas() {
            return tarifaRepository.findAll();
        }

        // Crear o actualizar una tarifa
        @PostMapping("/tarifas")
        public ResponseEntity<?> guardarTarifa(@RequestBody Tarifa tarifa) {
            try {
                if (tarifa.getPrecioPorHora() <= 0 || tarifa.getPrecioPorMinuto() <= 0) {
                    throw new IllegalArgumentException("Los valores de las tarifas deben ser mayores a 0.");
                }
                tarifaRepository.save(tarifa);
                return ResponseEntity.ok(tarifaRepository.findAll());
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor.");
            }
        }




        // Eliminar una tarifa
        @DeleteMapping("/tarifas/{id}")
        public List<Tarifa> eliminarTarifa(@PathVariable Long id) {
            if (!tarifaRepository.existsById(id)) {
                throw new IllegalArgumentException("La tarifa con el ID especificado no existe.");
            }
            tarifaRepository.deleteById(id);
            return tarifaRepository.findAll(); // Retornar la lista actualizada
        }


    }
