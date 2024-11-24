package com.parqueadero.sistema_parqueadero;

import com.parqueadero.sistema_parqueadero.modelo.ConfiguracionGeneral;
import com.parqueadero.sistema_parqueadero.repositorio.ConfiguracionGeneralRepository;
import com.parqueadero.sistema_parqueadero.modelo.Tarifa;
import com.parqueadero.sistema_parqueadero.repositorio.TarifaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate; // Asegúrate de importar RestTemplate
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
						.allowedOrigins("http://localhost:8080", "http://localhost:8081",
								"http://192.168.1.70:8081","http://192.168.1.77:8081", "http://localhost:3000")
						.allowedMethods("GET", "POST", "PUT", "DELETE")
						.allowedHeaders("*");

			}
		};
	}

	// 2. Definición del Bean RestTemplate
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	// 3. Cargar datos iniciales al arrancar la aplicación (Tarifas)
	@Bean
	CommandLineRunner initDatabase(TarifaRepository tarifaRepository, ConfiguracionGeneralRepository configuracionGeneralRepository) {
		return args -> {
			// Cargar el nombre del parqueadero si no existe
			if (configuracionGeneralRepository.count() == 0) {
				configuracionGeneralRepository.save(new ConfiguracionGeneral("Parqueadero Central"));
				System.out.println("Nombre del parqueadero inicial cargado en la base de datos.");
			}

			// Carga tarifas iniciales
			if (tarifaRepository.count() == 0) {
				tarifaRepository.save(new Tarifa("carro", 5.0, 0.0833));
				tarifaRepository.save(new Tarifa("moto", 3.0, 0.05));
				System.out.println("Tarifas iniciales cargadas en la base de datos.");
			}
		};
	}

}
