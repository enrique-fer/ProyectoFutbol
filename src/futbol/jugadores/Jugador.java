package futbol.jugadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import enumerados.TipoEquipo;
import excepciones.jugadores.ErrorDorsal;
import excepciones.jugadores.ErrorNombre;

/**
 * @author Enrique Fernandez Santiago
 * @version 1.0
 */

/**
 * Clase Jugador
 * ATRIBUTOS
 * 	-nombre: String
 * 	-numDorsal: int
 * 	-equipo: TipoEquipo
 * 	-numGoles: int
 * 
 * GETTERS
 * 	+getNombre: String
 * 	+getNumDorsal: int
 * 	+getEquipo: TipoEquipo
 * 	+getNumGoles: int
 * 
 * SETTERS
 *  -setNombre(String): void
 *	-setNumDorsal(int): void
 * 
 * METODOS
 * 	+marcaGol: void
 *  +valoracion: int
 *  +compareTo(Jugador)
 *  +toString: String
 */

/**
 * Clase que guarda las pricipales cracteristicas de un jugador
 */
public abstract class Jugador implements Comparable<Jugador> {
	private String nombre;
	private int numDorsal;
	private TipoEquipo equipo;
	private int numGoles;
	
	public Jugador() {
		this.nombre = "Desconocido";
		this.numDorsal = 0;
		this.equipo = null;
		this.numGoles = -1;
	}
	public Jugador(String nombre, int numDorsal, TipoEquipo equipo) throws ErrorDorsal, ErrorNombre {
		setNombre(nombre);
		setNumDorsal(numDorsal);
		this.equipo = equipo;
		this.numGoles = 0;
	}
	
	public String getNombre() {
		return nombre;
	}
	public int getNumDorsal() {
		return numDorsal;
	}
	public TipoEquipo getEquipo() {
		return equipo;
	}
	public int getNumGoles() {
		return numGoles;
	}
	//Comprobar porque da error al introducir letras
	private void setNombre(String nombre) throws ErrorNombre {
		Pattern p = Pattern.compile("[a-zA-Z]*");
		Matcher m = p.matcher(nombre);
		if (m.matches()) {
			this.nombre = nombre;
		} else {
			throw new ErrorNombre("Nombre invalido");

		}
	}
	
	private void setNumDorsal(int numDorsal) throws ErrorDorsal {
		if (numDorsal >= 1 && numDorsal <= 30) {
			this.numDorsal = numDorsal;
		} else {
			throw new ErrorDorsal("Valor del dorsal no valido");
		}
	}

	public void marcaGol() {
		this.numGoles++;
	}
	
	public abstract int valoracion();
	
	@Override
	public int compareTo(Jugador j) {
		int num = 0;
		if (this.numGoles < j.getNumGoles()) {
			num = -1;
		}
		if (this.numGoles > j.getNumGoles()) {
			num = 1;
		}
		return num;
	}
	@Override
	public String toString() {
		return getNumDorsal() + " " + getNombre() + " " + "\n\tGoles: " + getNumGoles(); 
	}
}
