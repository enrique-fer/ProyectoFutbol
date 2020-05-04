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
 * METODOS
 * 	+valoracion: int
 * 	+toString: String
 */

/**
 * Defensor es una clase heredada de JugadorCampo, en ella se especifican
 *  opciones y acciones de los defensores del equipo
 */
public class Defensor extends JugadorCampo{
	public Defensor() {
		super();
	}
	public Defensor(String nombre, int numDorsal, TipoEquipo equipo) throws ErrorDorsal, ErrorNombre {
		super(nombre, numDorsal, equipo);
	}
	
	/**
	 * El metodo valoracion sobreescribe el metodo de la clase JugadorCampo
	 * con los valores apropiados para el defensor
	 */
	@Override
	public int valoracion() {
		int val = 0;
		val += (super.getNumGoles() * 20) + (super.getNumPasesExito() * 1) + (super.getBolasRecu() * 4);
		return val;
	}
	@Override
	public String toString() {
		return "Defensor: " + super.toString() + "\n\tValoracion: " + this.valoracion();
	}
}
