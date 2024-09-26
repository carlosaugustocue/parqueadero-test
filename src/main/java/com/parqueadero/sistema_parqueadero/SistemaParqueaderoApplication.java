package com.parqueadero.sistema_parqueadero;



import com.parqueadero.sistema_parqueadero.modelo.Tarifa;
import com.parqueadero.sistema_parqueadero.repositorio.TarifaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SistemaParqueaderoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaParqueaderoApplication.class, args);
	}

	// 1. Configuración para CORS
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**")
						.allowedOrigins("http://localhost:3000")  // Permite el frontend desde este dominio
						.allowedMethods("GET", "POST", "PUT", "DELETE")  // Métodos permitidos
						.allowedHeaders("*");  // Permite todos los headers
			}
		};
	}

	// 2. Cargar datos iniciales al arrancar la aplicación (Tarifas)
	@Bean
	CommandLineRunner initDatabase(TarifaRepository tarifaRepository) {
		return args -> {
			// Carga tarifas iniciales para carros y motos
			if (tarifaRepository.count() == 0) {  // Solo si la tabla está vacía
				tarifaRepository.save(new Tarifa("carro", 5.0));  // Carro tarifa por hora 5.0
				tarifaRepository.save(new Tarifa("moto", 3.0));   // Moto tarifa por hora 3.0
				System.out.println("Tarifas iniciales cargadas en la base de datos.");
			} else {
				System.out.println("Tarifas ya están cargadas en la base de datos.");
			}
		};
	}
}

