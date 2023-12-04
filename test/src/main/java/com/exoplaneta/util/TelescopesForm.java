package com.exoplaneta.util;

import com.exoplaneta.model.Telescope;

/**
 * Formulario basado en el json de ejemplo. Contiene array de telescopios y
 * satelites que se utilizaran para calcular la ubicacion del exoplaneta y armar
 * el mensaje
 *
 */
public class TelescopesForm {

	private Telescope[] telescopes;
	private Telescope[] satellites;

	public Telescope[] getTelescopes() {
		return telescopes;
	}

	public void setTelescopes(Telescope[] telescopes) {
		this.telescopes = telescopes;
	}

	public Telescope[] getSatellites() {
		return satellites;
	}

	public void setSatellites(Telescope[] satellites) {
		this.satellites = satellites;
	}

}
