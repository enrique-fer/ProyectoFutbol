package futbol.jugadores;

import enumerados.TipoEquipo;
import excepciones.jugadores.ErrorDorsal;
import excepciones.jugadores.ErrorNombre;

/**
 * @author Enrique Fernandez Santiago
 * @version 1.0
 */

/**
 * Clase Atacante
 * METODOS
 * 	+valoracion: int
 * 	+toString: String
 */

/**
 * Atacante es una clase heredada de JugadorCampo, en ella se especifican
 * opciones y acciones de los atacantes del equipo
 */
public class Atacante extends JugadorCampo {		
	public Atacante() {
		super();
	}
	public Atacante(String nombre, int numDorsal, TipoEquipo equipo) throws ErrorDorsal, ErrorNombre {
		super(nombre, numDorsal, equipo);
	}
	
	/**
	 * El metodo valoracion sobreescribe el metodo de la clase JugadorCampo
	 * con los valores apropiados para el atacante
	 */
	@Override
	public int valoracion() {
		int val = 0;
		val += (super.getNumGoles() * 30) + (super.getNumPasesExito() * 1) + (super.getBolasRecu() * 3);
		return val;
	}
	@Override
	public String toString() {
		return "Atacante: " + super.toString() + "\n\tValoracion: " + this.valoracion();
	}
}