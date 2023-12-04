package com.exoplaneta.util;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

import com.exoplaneta.model.Point;

/**
 * Compara dos entradas del mapa de puntos conteniendo ubicacion y cantidad y
 * devuelve el que tiene mayor cantidad de entradas
 *
 */
public class PointEntryComparator implements Comparator<Map.Entry<Point, Integer>> {

	@Override
	public int compare(Entry<Point, Integer> o1, Entry<Point, Integer> o2) {
		return o1.getValue() - o2.getValue();
	}

}
