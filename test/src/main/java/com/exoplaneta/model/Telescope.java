package com.exoplaneta.model;

/**
 * Representa un telescopio que contiene un nombre, mensajes obtenidos,
 * distancia al exoplaneta y su ubicacion
 *
 */

public class Telescope {

	private String name;
	private String[] message;
	private double distance;
	private Point location;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getMessage() {
		return message;
	}

	public void setMessage(String[] message) {
		this.message = message;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

}
