package com.exoplaneta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import com.exoplaneta.model.Exoplaneta;

@SpringBootApplication
@RestController
public class PlanetApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanetApplication.class, args);
	}

	@Bean
	public Exoplaneta planet() {
		Exoplaneta planet = new Exoplaneta();
		planet.setGravedad(0.98);
		planet.setMasa("piedra");
		planet.setNombre("Exoplanet");
		planet.setRadio(1.20);
		planet.setSatelites(1);

		return planet;
	}

}
