package futbol.jugadores;

import enumerados.TipoEquipo;
import excepciones.jugadores.ErrorDorsal;
import excepciones.jugadores.ErrorNombre;

/**
 * @author Enrique Fernandez Santiago
 * @version 1.0
 */

/**
 * Clase JugadorCampo
 * ATRIBUTOS
 * 	-pasesExito: int
 *  -bolasRecu: int
 * 
 * GETTERS
 * 	+getNumPasesExito: int
 *  +getBolaRecu: int
 * 
 * METODOS
 * 	+sumarPase: void
 *  +sumarBolaRecu: void
 * 	+valoracion: int
 * 	+toString: String
 */

/**
 * JugadorCampo es una clase heredada de Jugador, en ella se especifican
 * cualidades y opciones de los jugadores de campo de un equipo
 */

public abstract class JugadorCampo extends Jugador {
	private int pasesExito;
	private int bolasRecu;
	
	public JugadorCampo() {
		super();
		this.pasesExito = -1;
		this.bolasRecu = -1;
	}
	public JugadorCampo(String nombre, int numDorsal, TipoEquipo equipo) throws ErrorDorsal, ErrorNombre {
		super(nombre, numDorsal, equipo);
		this.pasesExito = 0;
		this.bolasRecu = 0;
	}
	
	public int getNumPasesExito() {
		return pasesExito;
	}
	public int getBolasRecu() {
		return bolasRecu;
	}
	
	public void sumarPase() {
		this.pasesExito++;
	}
	
	public void sumarBolaRecu() {
		this.bolasRecu++;
	}
	
	public abstract int valoracion();
	
	@Override
	public String toString() {
		return super.toString() + " Pases: " + this.pasesExito +
			   " Recuperaciones: " + this.bolasRecu;
	}
}
