package futbol.jugadores;

import enumerados.TipoEquipo;
import excepciones.jugadores.ErrorDorsal;
import excepciones.jugadores.ErrorNombre;

/**
 * @author Enrique Fernandez Santiago
 * @version 1.0
 */

/**
 * Clase Portero
 * ATRIBUTOS
 * 	-numParadas: int
 * 
 * GETTERS
 * 	+getNumParadas: int
 * 
 * METODOS
 * 	+paradaExito: void
 * 	+valoracion: int
 * 	+toString: String
 */

/**
 * Portero es una clase heredada de Jugador, en ella se especifican
 * cualidades y opciones de los porteros de un equipo
 */
public class Portero extends Jugador{
	private int numParadas;
	
	public Portero() {
		super();
		this.numParadas = -1;
	}
	public Portero(String nombre, int numDorsal, TipoEquipo equipo) throws ErrorDorsal, ErrorNombre {
		super(nombre, numDorsal, equipo);
		this.numParadas = 0;
	}
	
	public int getNumParadas() {
		return numParadas;
	}
	
	
	public void paradaExito() {
		this.numParadas++;
	}
	
	/**
	 * El metodo valoracion sobreescribe el metodo de la clase Jugador
	 * con los valores apropiados para el portero
	 */
	@Override
	public int valoracion() {
		int val = 0;
		val += (super.getNumGoles() * 10) + (this.numParadas * 5);
		return val;
	}
	@Override
	public String toString() {
		return "Portero: " + super.toString() + " Paradas: " + this.numParadas +
				"\n\tValoracion: " + this.valoracion();
	}
}

