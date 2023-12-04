package com.exoplaneta.util;

import com.exoplaneta.model.Point;

/**
 * Respuesta para integrar la ubicacion del exoplaneta y el mensaje obtenido
 *
 */
public class Response {

	private Point position;
	private String message;

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
