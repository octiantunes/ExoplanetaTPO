package com.exoplaneta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exoplaneta.model.Exoplaneta;
import com.exoplaneta.model.Point;
import com.exoplaneta.model.Telescope;
import com.exoplaneta.util.Response;
import com.exoplaneta.util.TelescopesForm;

/**
 * Controlador que recibe las peticiones /topsecret
 * 
 *
 */
@RestController
public class PlanetController {

	@Autowired
	private Exoplaneta exoplanet;

	/**
	 * Devuelve el objeto del exoplaneta con toda su informacion
	 * 
	 * @return Exoplaneta
	 */
	@GetMapping("/exoplaneta")
	public ResponseEntity<Exoplaneta> exoplaneta() {
		return ResponseEntity.ok(exoplanet);
	}

	/**
	 * Modifica el estado del objeto exoplaneta con toda su informacion y devuelve
	 * el mismo objeto con el que se modifico y un estado HTTP OK 200
	 * 
	 * @return Exoplaneta
	 */
	@PutMapping("/exoplaneta")
	public ResponseEntity<Exoplaneta> exoplanetaUpdate(@RequestBody Exoplaneta exoplaneta) {
		exoplanet = exoplaneta;
		return ResponseEntity.ok(exoplaneta);
	}

	/**
	 * Recibe un formulario con los datos de cada telescopio y utilizando las clases
	 * de utilidad puede obtener la ubicacion del exoplaneta y armar el mensaje
	 * obtenido Devuelve un OK (200) en caso de haber encontrado al planeta o
	 * NOT_FOUND (404) en caso que no y el mensaje construido
	 * 
	 * @param form
	 * @return ResponseEntity
	 */
	@PostMapping("/topsecret")
	public ResponseEntity<Response> topsecret(@RequestBody TelescopesForm form) {
		Telescope[] telescopes = form.getTelescopes();
		double dis1 = telescopes[0].getDistance();
		double x1 = telescopes[0].getLocation().getX();
		double y1 = telescopes[0].getLocation().getY();
		double dis2 = telescopes[1].getDistance();
		double x2 = telescopes[1].getLocation().getX();
		double y2 = telescopes[1].getLocation().getY();
		double dis3 = telescopes[2].getDistance();
		double x3 = telescopes[2].getLocation().getX();
		double y3 = telescopes[2].getLocation().getY();
		Point point = Exoplaneta.obtenerUbicacion(x1, y1, dis1, x2, y2, dis2, x3, y3, dis3);
		String palabra = Exoplaneta.componerMensaje(telescopes[0].getMessage(), telescopes[1].getMessage(),
				telescopes[2].getMessage());
		Response resp = new Response();
		resp.setMessage(palabra);
		resp.setPosition(point);
		if (point != null) {
			return new ResponseEntity<Response>(resp, HttpStatus.OK);
		} else {
			return new ResponseEntity<Response>(resp, HttpStatus.NOT_FOUND);
		}
	}

}
