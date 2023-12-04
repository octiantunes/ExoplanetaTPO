package com.exoplaneta.model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import com.exoplaneta.util.PointEntryComparator;

public class Exoplaneta {

	private String nombre;
	private double radio;
	private String masa;
	private double gravedad;
	private int satelites;

	private static Point[] interseccionCirculo(double x1, double y1, double r1, double x2, double y2, double r2) {
		double d = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

		if (d > r1 + r2 || d < Math.abs(r1 - r2)) {
			// Circulos separados o dentro de otro
			return new Point[0];
		} else if (d == 0 && r1 == r2) {
			// Circulos identicos
			return new Point[] { new Point(x1 + r1, y1) };
		} else {
			// Interseccion
			double a = (Math.pow(r1, 2) - Math.pow(r2, 2) + Math.pow(d, 2)) / (2 * d);
			double h = Math.sqrt(Math.pow(r1, 2) - Math.pow(a, 2));
			double x3 = x1 + a * (x2 - x1) / d;
			double y3 = y1 + a * (y2 - y1) / d;
			double x4 = x3 + h * (y2 - y1) / d;
			double y4 = y3 - h * (x2 - x1) / d;

			Point intersection1 = new Point(x4, y4);
			Point intersection2 = new Point(x3 - h * (y2 - y1) / d, y3 + h * (x2 - x1) / d);

			return new Point[] { intersection1, intersection2 };
		}
	}

//	public static void main(String[] args) {
//		double x1 = 0, y1 = 0, r1 = 3; // Circ 1
//		double x2 = 1, y2 = 2, r2 = 3; // Circ 2
//		double x3 = 2, y3 = 0, r3 = 3; // Circ 3
//		Point test = obtenerCoordenadaExoplaneta(x1, y1, r1, x2, y2, r2, x3, y3, r3);
//		String[] mensajes1 = { "bienvenidos", "a", "", "orientada", "al", "" };
//		String[] mensajes2 = { "", "bienvenidos", "a", "", "orientada", "al", "objeto" };
//		String[] mensajes3 = { "", "", "programacion", "", "", "" };
//		String resultado = componerMensaje(mensajes1, mensajes2, mensajes3);
//		System.out.println(resultado);
//	}

	/**
	 * Compone un mensaje String en base a los arrays obtenidos de los satelites.
	 * Obtiene promedio de longitud para la correccion de defasaje de los strings en
	 * cada uno de los arrays y luego arama el mensaje con los arrays del mismo
	 * tamano
	 * 
	 * @param mensajes1
	 * @param mensajes2
	 * @param mensajes3
	 * @return mensaje final
	 */
	public static String componerMensaje(String[] mensajes1, String[] mensajes2, String[] mensajes3) {
		int long1 = mensajes1.length;
		int long2 = mensajes2.length;
		int long3 = mensajes3.length;
		int prom = (long1 + long2 + long3) / 3;

		mensajes1 = corregirDesfasaje(mensajes1, prom);
		mensajes2 = corregirDesfasaje(mensajes2, prom);
		mensajes3 = corregirDesfasaje(mensajes3, prom);

		return armarPalabra(mensajes1, mensajes2, mensajes3);
	}

	/**
	 * Arma la frase con los arrays de entrada verificando que no sea vacio el valor
	 * de cada palabra en la iteracion de sus posiciones
	 * 
	 * @param mensajes1
	 * @param mensajes2
	 * @param mensajes3
	 * @return La frase final en tipo String
	 */
	private static String armarPalabra(String[] mensajes1, String[] mensajes2, String[] mensajes3) {
		String resultado = "";
		for (int i = 0; i < mensajes1.length; i++) {
			if (!mensajes1[i].equals("")) {
				resultado += mensajes1[i] + " ";
			} else if (!mensajes2[i].equals("")) {
				resultado += mensajes2[i] + " ";
			} else {
				resultado += mensajes3[i] + " ";
			}
		}
		return resultado.trim();
	}

	/**
	 * Corrige defasaje en caso que haya un mensaje vacio al principio del array de
	 * entrada. Verifica que la longitud del array sea mayor al promedio para
	 * realizar un nuevo array y mover los valores validos a esa nueva variable
	 * 
	 * @param mensajes
	 * @param prom
	 * @return
	 */
	private static String[] corregirDesfasaje(String[] mensajes, int prom) {
		if (mensajes.length > prom && mensajes[0].equals("")) {
			String[] aux = new String[mensajes.length - 1];
			for (int i = 1; i < mensajes.length; i++) {
				aux[i - 1] = mensajes[i];
			}
			return aux;
		}
		return mensajes;
	}

	/**
	 * Obtiene las coordenadas del exoplaneta buscando loss puntos en comun entre
	 * las circunferencias. Los datos de entrada son las posiciones x, y de cada
	 * satelite y su distancia. Con eso busca la interseccion de los tres
	 * circunferencias generadas. Una vez obtenidos los puntos, acumula la cantidad
	 * de repeticiones de un punto en un mapa con clave en el punto y como valor el
	 * acumulador de repeticiones. Aquel que aparezca 3 veces es el que tiene la
	 * ubicacion del exoplaneta. En caso de no haber tantas repeticiones, es porque
	 * las circunferencias no tienen un triple punto en comun en cuyo caso devuelve
	 * nulo
	 * 
	 * @param x1
	 * @param y1
	 * @param r1
	 * @param x2
	 * @param y2
	 * @param r2
	 * @param x3
	 * @param y3
	 * @param r3
	 * @return
	 */
	public static Point obtenerUbicacion(double x1, double y1, double r1, double x2, double y2, double r2,
			double x3, double y3, double r3) {
		Point[] intersec12 = interseccionCirculo(x1, y1, r1, x2, y2, r2);
		Point[] intersec13 = interseccionCirculo(x1, y1, r1, x3, y3, r3);
		Point[] intersec23 = interseccionCirculo(x3, y3, r3, x2, y2, r2);

		Map<Point, Integer> contador = new HashMap<>();
		for (Point punto : intersec12) {
			Integer cantidad = contador.getOrDefault(punto, 0);
			contador.put(punto, cantidad++);

			System.out.println("(" + punto.x + ", " + punto.y + ")");
		}
		for (Point punto : intersec23) {
			Integer cantidad = contador.getOrDefault(punto, 0);
			contador.put(punto, cantidad++);
		}
		for (Point punto : intersec13) {
			Integer cantidad = contador.getOrDefault(punto, 0);
			contador.put(punto, cantidad++);
		}

		Comparator<Map.Entry<Point, Integer>> comparadorPuntos = new PointEntryComparator();
		Optional<Entry<Point, Integer>> puntoEnComun = contador.entrySet().stream().max(comparadorPuntos);
		if (puntoEnComun.isPresent()) {
			Point pos = puntoEnComun.get().getKey();
			Integer cant = puntoEnComun.get().getValue();
			if (cant.intValue() == 3) {
				System.out.println("La posicion buscada es " + pos);
				return pos;
			} else {
				System.out.println("Los satelites no coinciden en sus mediciones para encontrar exoplaneta");
			}
		}
		return null;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getRadio() {
		return radio;
	}

	public void setRadio(double radio) {
		this.radio = radio;
	}

	public String getMasa() {
		return masa;
	}

	public void setMasa(String masa) {
		this.masa = masa;
	}

	public double getGravedad() {
		return gravedad;
	}

	public void setGravedad(double gravedad) {
		this.gravedad = gravedad;
	}

	public int getSatelites() {
		return satelites;
	}

	public void setSatelites(int satelites) {
		this.satelites = satelites;
	}

}
