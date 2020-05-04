package ejecutable;

import futbol.jugadores.*;
import futbol.partidos.Partido;

import java.util.Iterator;

import javax.swing.JOptionPane;

import enumerados.*;
import excepciones.jugadores.*;

/**
 * @author Enrique Fernandez Santiago
 * @version 1.0
 */

public class EstadisticasApp {
	/**
	 * En el main se ejecutaran todos los metodos de las diferentes clases
	 * @param args Argumentos del metodo 
	 */
	public static void main(String[] args) {
		int op = 0;
		Partido partido = new Partido();
		try {
			menuPrincipal(partido, op);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error de Caracter", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	//Printeo de menus
	/**
	 * Este metodo nos imprime el menu principal del programa
	 * @return Un string con las opciones del menu principal
	 */
	private static String printMenuPrincipal() {
		return "1 > Jugar partido" +
				"\n2 > Estadisticas partido" +
				"\n3 > Salir";
	}
	
	/**
	 * Este metodo nos imprime el menu principal del partido
	 * @return Un string con las opciones del menu principal
	 */
	private static String printMenuPartido () {
		return "1 > Jugadores" +
				"\n2 > Acciones partido" + 
				"\n3 > Salir";
	}
	
	/**
	 * Este metodo nos imprime el menu de los jugadores
	 * @return Un string con las opciones del menu de los jugadores
	 */
	private static String printMenuJugadores() {
		return  "1 > Anadir jugador" +
				"\n2 > Borrar jugador" +
				"\n3 > Muestra datos" + 
				"\n4 > Salir";
	}

	/**
	 * Este metodo nos imprime el menu de nuestro partido
	 * @return Un string con las opcines del partido
	 */
	private static String printAccionesPartido() {
		return "1 > Marcar gol" +
				"\n2 > Pase con exito" + 
				"\n3 > Recupera balon" +
				"\n4 > Parada con exito" +
				"\n5 > Salir";		
	}
	
	/**
	 * Este metodo nos imprime el menu de las estadisticas
	 * @return Un string con las opcines de las estadisticas
	 */
	private static String printMenuEstadistica() {
		return "1 > Jugadores con intervencion" + 
				"\n2 > Total de goles" + 
				"\n3 > Media de pases" +
				"\n4 > Ordenar equipos" +
				"\n5 > Salir";
	}
	
	
	//Menus de acciones
	/**
	 * Metodo que recoge las opciones de acceso a los diferentes 
	 * submenus
	 * @param partido Objeto de la clase Partido
	 * @param op Numero para las opciones del menu
	 */
	private static void menuPrincipal(Partido partido, int op) throws NumberFormatException{
		boolean continua = true;
		while (continua) {
			op = Integer.parseInt(JOptionPane.showInputDialog(null,
					printMenuPrincipal(), "Menu", JOptionPane.QUESTION_MESSAGE));
			switch (op) {
				case 1: { //Acceso al partido
					jugarPartido(partido, op);
					break;
				}
				case 2: { //Acceso a las estadisticas
					estadisticasPartido(partido, op);
					break;
				}
				case 3: { //Salir
					continua = false;
					break;
				}
				default: {
					JOptionPane.showMessageDialog(null,
							"VALOR INTRODUCIDO NO VALIDO", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	/**
	 * Metodo que recoge todas las acciones y errores en relacion 
	 * al periodo de juego del partido
	 * @param partido Objeto de la clase Partido
	 * @param op Numero para las opciones del menu
	 */
	private static void jugarPartido(Partido partido, int op) {
		boolean continuaP = true;
		while (continuaP) {
			try {
				op = Integer.parseInt(JOptionPane.showInputDialog(null,
						printMenuPartido(), "Menu Partido", JOptionPane.QUESTION_MESSAGE));
				continuaP = accionesMenuPartido(partido, op);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error de Caracter", JOptionPane.ERROR_MESSAGE);
			} catch (ErrorValor e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error de Valor", JOptionPane.ERROR_MESSAGE);
			} catch (JugadorExistente e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error de Jugador", JOptionPane.ERROR_MESSAGE);
			} catch (ErrorDorsal e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error de Dorsal", JOptionPane.ERROR_MESSAGE);
			} catch (ErrorNombre e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error de Nombre", JOptionPane.ERROR_MESSAGE);
			}

		}
	}
	
	/**
	 * Este metodo es un intermediario entre el main y el resto de metodos
	 * en el que se permite el acceso a submenus
	 * @param partido Objeto de la clase Partido
	 * @param op Numero para las opciones del menu
	 * @return Devuelve true si el usuario realiza una accion cualquiera, y devuelve
	 * 		   false cuando el usuario elige salir
	 * @throws NumberFormatException El formato del valor introducido no es valido
	 * @throws JugadorExistente El jugador no existe
	 * @throws ErrorDorsal El dorsal introducido no existe 
	 * @throws ErrorValor El valor introducido no es valido
	 * @throws ErrorNombre Algun caracter del nombre introducido no pertenece al alfabeto 
	 */
	private static boolean accionesMenuPartido(Partido partido, int op) throws NumberFormatException, JugadorExistente, ErrorDorsal, ErrorValor, ErrorNombre {
		boolean c = true, aJ = true, aP = true;
		
		switch (op) {
			case 1: { //Vista de jugadores
				while (aJ) {
					op = Integer.parseInt(JOptionPane.showInputDialog(null,
							printMenuJugadores(), "Menu Jugadores", JOptionPane.QUESTION_MESSAGE));
					aJ = accionesSobreJugadores(partido, op);
				}
				break;
			}
			case 2: {//Acciones del partido
				while (aP) {
					op = Integer.parseInt(JOptionPane.showInputDialog(null,
							printAccionesPartido(), "Menu Acciones", JOptionPane.QUESTION_MESSAGE));
					aP = accionesPartido(partido, op);
				}
				break;
			}
			case 3: {//Salir
				c = false;
				break;
			}
			default: {
				JOptionPane.showMessageDialog(null, "VALOR INTRODUCIDO NO VALIDO",
						"ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
		return c;
	}

	/**
	 * En este metodo se ejecutan todos los metodos relacionados con la creacion
	 * de los jugadores y el muestreo de informacion
	 * @param partido
	 * @param op
	 * @return Devuelve true si el usuario realiza una accion cualquiera, y devuelve
	 * 		   false cuando el usuario elige salir
	 * @throws NumberFormatException El formato del valor introducido no es valido
	 * @throws JugadorExistente El jugador no existe
	 * @throws ErrorDorsal El dorsal introducido no existe 
	 * @throws ErrorValor El valor introducido no es valido
	 * @throws ErrorNombre Algun caracter del nombre introducido no pertenece al alfabeto
	 */
	private static boolean accionesSobreJugadores(Partido partido, int op) throws NumberFormatException, JugadorExistente, ErrorDorsal, ErrorValor, ErrorNombre {
		boolean aJ = true;	
		switch (op) {
			case 1: { //Anadir Jugador
				crearJugador(partido);
				break;
			}
			case 2: { //Borrar Jugador
				partido.borrarJugador(elegirEquipo(), elegirDorsal());
				break;
			}
			case 3: { //Mostrar Jugador
				JOptionPane.showMessageDialog(null,
						partido.mostrarJugador(elegirEquipo(), elegirDorsal()), 
						"Jugador", JOptionPane.INFORMATION_MESSAGE);
						;
				break;
			}
			case 4: { //Salir
				aJ = false;
				break;
			}
			default: {
				JOptionPane.showMessageDialog(null, "VALOR INTRODUCIDO NO VALIDO",
						"ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
		return aJ;
	}
	
	/**
	 * En este metodo se ejecutan los metodos de acciones realizadas por los
	 * jugadores durante el partido
	 * @param partido
	 * @param op
	 * @return Devuelve true si el usuario realiza una accion cualquiera, y devuelve	 
	 * 		   false cuando el usuario elige salir
	 * @throws ErrorValor El valor introducido no es valido
	 * @throws NumberFormatException El formato del valor introducido no es valido
	 */
	private static boolean accionesPartido(Partido partido, int op) throws ErrorValor, NumberFormatException {
		boolean aP = true;
		switch (op) {
			case 1 : { // Marcar gol
				partido.contabilizarGol(elegirEquipo(), elegirDorsal(), elegirCantidad());
				break;
			}
			case 2: { //Pase con exito
				partido.contabilizarPaseExito(elegirEquipo(), elegirDorsal(), elegirCantidad());
				break;
			}
			case 3: { //Recupera el balon
				partido.contabilizarBolaRecu(elegirEquipo(), elegirDorsal(), elegirCantidad());
				break;
			}
			case 4: { //Parada con exito
				partido.contabilizarParada(elegirEquipo(), elegirDorsal(), elegirCantidad());
				break;
			}
			case 5: { //Salir
				aP = false;
				break;
			}
			default: {
				JOptionPane.showMessageDialog(null, "VALOR INTRODUCIDO NO VALIDO",
						"ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
		return aP;
	}
	
	/**
	 * Metodo que recoge todas las acciones y errores en relacion 
	 * a las estadisticas del partido
	 * @param partido Objeto de la clase Partido
	 * @param op Numero para las opciones del menu
	 */
	private static void estadisticasPartido(Partido partido, int op) {
		boolean continuaE = true;
		while (continuaE) {
			try {
				op = Integer.parseInt(JOptionPane.showInputDialog(null, 
						printMenuEstadistica(), "Menu", JOptionPane.QUESTION_MESSAGE));
				continuaE = accionesMenuEstadistica(partido, op);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),
						"Error de Caracter", JOptionPane.ERROR_MESSAGE);
			} catch (ErrorValor e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),
						"Error de Valor", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Este metodo ejecuta la parte de los metodos a los que se dan uso en el main,
	 * todos aquellos que tienen relacion con las estadisticas del partido,
	 * es una especie de metodo de transicion entre el main y el resto de metodos
	 * @param partido Objeto de la clase Partido
	 * @param op Numero para las opciones del menu
	 * @return Devuelve true si el usuario realiza una accion cualquiera, y devuelve
	 * 		   false cuando el usuario elige salir
 	 * @throws ErrorValor El valor introducido no es valido
	 * @throws NumberFormatException El formato del valor introducido no es valido
	 */
	private static boolean accionesMenuEstadistica(Partido partido, int op) throws ErrorValor, NumberFormatException {
		boolean c = true;
		switch (op) {
			case 1: { //Ver los jugadores que han intervenido                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
				intervencionesEquipo(partido, elegirEquipo());
				break;
			}
			case 2: { //Total de goles de un equipo
				golesEquipo(partido, elegirEquipo());
				break;
			}
			case 3: { //Media de pases de un equipo
				mediaPasesEquipo(partido, elegirEquipo());
				break;
			}
			case 4: { //Ordenar equipo por numero de goles
				ordenarEquipos(partido, elegirEquipo());
				break;
			}
			case 5: { //Salir
				c = false;
				break;
			}
			default: {
				JOptionPane.showMessageDialog(null, "VALOR INTRODUCIDO NO VALIDO",
						"ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
		return c;
	}

	
	//Metodos de eleccion
	/**
	 * Este metodo permite elegir el tipo de equipo que se desea crear
	 * @return El tipo del equipo a traves del valor de
	 * 		   un enumerado
 	 * @throws ErrorValor El valor introducido no es valido
	 * @throws NumberFormatException El formato del valor introducido no es valido
	 */
	private static TipoEquipo elegirEquipo() throws ErrorValor, NumberFormatException {
		TipoEquipo te = null;
		int op = Integer.parseInt(JOptionPane.showInputDialog(null,
				"1 > Local\n2 > Visitante", "Equipo", JOptionPane.QUESTION_MESSAGE));
		if (op == 1) {
			te = TipoEquipo.LOCAL;
		} else {
			if (op == 2) {
				te = TipoEquipo.VISITANTE;
			} else {
				throw new ErrorValor("El valor introducido no es valido");
			}
		}

		return te;
	}
	
	/**
	 * Este metodo permite elegir el tipo de jugador que se desea crear
	 * @return El tipo del jugador a traves del valor de
	 * 		   un enumerado
	 * @throws ErrorValor El valor introducido no es valido
	 * @throws NumberFormatException El formato del valor introducido no es valido
	 */
	private static TipoJugador elegirJugador() throws ErrorValor, NumberFormatException {
		TipoJugador tj = null;
		int op = Integer.parseInt(JOptionPane.showInputDialog(null,
				"1 > Atacante\n2 > Defensor\n3 > Portero", "Nuevo Jugador", JOptionPane.QUESTION_MESSAGE));
		if (op == 1) {
			tj = TipoJugador.ATACANTE;
		} else {
			if (op == 2) {
				tj = TipoJugador.DEFENSOR;
			} else {
				if (op == 3) {
					tj = TipoJugador.PORTERO;
				} else {
					throw new ErrorValor("El valor introducido no es valido");
				}
			}
		}

		return tj;
	}

	/**
	 * Metodo para recibir el nombre de un jugador por teclado
	 * @return El nombre del jugador deseado
	 */
	private static String elegirNombre() {
		return JOptionPane.showInputDialog(null, "¿Cual es el nombre del jugador?",
				"Jugador", JOptionPane.QUESTION_MESSAGE);
	}

	/**
	 * Metodo para recibir el numero de dorsal de un 
	 * jugador por teclado
	 * @return El numero de dorsal deseado
	 */
	private static int elegirDorsal() {
		return Integer.parseInt(JOptionPane.showInputDialog(null,
				"¿Cual es el dorsal del jugador?", "Jugador", JOptionPane.QUESTION_MESSAGE));
	}

	/**
	 * Metodo para elegir una cantidad a sumar a algun valor
	 * @return La cantidad decidida por el usuario
	 */
	private static int elegirCantidad() {
		return Integer.parseInt(JOptionPane.showInputDialog(null,
				"¿Cuantos quieres sumar?", "Cantidad", JOptionPane.QUESTION_MESSAGE));
	}
	
	
	//Crear Jugadores
	/**
	 * Metodo que sirve para recoger datos y volcarlos sobre la clase Partido, 
	 * a partir de las clases hijas Atacante, Defensor y Portero
	 * @param partido Objeto de la clase Partido
	 * @throws NumberFormatException Se introduce un valor no valido
	 * @throws JugadorExistente El jugador ya existe en la base
	 * @throws ErrorDorsal El dorsal ya existe en el equipo o es un valor no valido
	 * @throws ErrorValor 
	 * @throws ErrorNombre El nombre introducido contiene un caracter no valido
	 */
	private static void crearJugador(Partido partido) throws NumberFormatException, JugadorExistente, ErrorDorsal, ErrorValor, ErrorNombre {
		TipoEquipo te = elegirEquipo();
		TipoJugador tj = elegirJugador();
		switch (tj) {
			case ATACANTE: {
				partido.anadirJugador(te, new Atacante(elegirNombre(),
						elegirDorsal(),
						te));
				break;
			}
			case DEFENSOR: {
				partido.anadirJugador(te, new Defensor(elegirNombre(),
						elegirDorsal(),
						te));
				break;
			}
			case PORTERO: {
				partido.anadirJugador(te, new Portero(elegirNombre(),
						elegirDorsal(),
						te));
				break;
			}
		}
	}
	

	//Imprimir intervenciones
	/**
	 * Obtiene e imprime todos los jugadores del equipo pasado por parametro 
	 * que hayan intervenido en el partido
	 * @param partido Objeto de la clase Partido
	 * @param te Tipo del equipo de las intervenciones
	 */
	private static void intervencionesEquipo(Partido partido, TipoEquipo te) {
		Iterator<Jugador> jugadoresIt = partido.jugConIntervencion(te).iterator();

		while (jugadoresIt.hasNext()) {
			JOptionPane.showMessageDialog(null, jugadoresIt.next(),
					"Intervenciones", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	
	//Contador de goles
	/**
	 * Obtiene el total de goles del equipo pasado por parametro y lo imprime
	 * por pantalla
	 * @param partido Objeto de la clase Partido
	 * @param te Tipo del equipo de los goles
	 */
	private static void golesEquipo(Partido partido, TipoEquipo te) {
		if (te == TipoEquipo.LOCAL) {
			JOptionPane.showMessageDialog(null, partido.golesLocal() +
					" goles", "Goles Local", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, partido.golesVisitante() +
					" goles", "Goles Visitante", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	
	//Calculador de media
	/**
	 * Obtiene la media de pases del equipo pasado por parametro y lo imprime
	 * por pantalla
	 * @param partido Objeto de la clase Partido
	 * @param te Tipo del equipo de la meida
	 */
	private static void mediaPasesEquipo(Partido partido, TipoEquipo te) {
		if (te == TipoEquipo.LOCAL) {
			JOptionPane.showMessageDialog(null, partido.mediaPasesLocal() +
					" pases", "Media de pases Local", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, partido.mediaPasesVisitante() +
					" pases", "Media de pases Visitante", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	
	//Ordenar equipos
	/**
	 * Hace de intermediario entre la ordenacion de los distintos equipos
	 * @param partido Objeto de la clase Partido
	 * @param te Tipo del equipo a ordernar
	 */
	public static void ordenarEquipos(Partido partido, TipoEquipo te) {
		if (te == TipoEquipo.LOCAL) {
			ordenaLocal(partido);
		} else {
			ordenaVisitante(partido);
		}
	}

	/**
	 * Ordena el equipo local por goles o pases seguen elija el usuario
	 * @param partido Objeto de la clase Partido
	 */
	private static void ordenaLocal(Partido partido) {
		int op;
		op = Integer.parseInt(JOptionPane.showInputDialog(null,
				"1 > Goles\n2 > Pases", "Ordenar equipo Local", JOptionPane.INFORMATION_MESSAGE));
		switch (op) {
			case 1: { //Por goles
				partido.ordenarLocalGoles();
				break;
			}
			case 2: { //Por pases
				partido.ordenarLocalPases();
				break;
			}
			default: {
				JOptionPane.showMessageDialog(null,
						"VALOR INTRODUCIDO NO VALIDO", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Ordena el equipo visitante por goles o pases segun elija el usuario
	 * @param partido Objeto de la clase Partido
	 */
	private static void ordenaVisitante(Partido partido) {
		int op;
		op = Integer.parseInt(JOptionPane.showInputDialog(null,
				"1 > Goles\n2 > Pases", "Ordenar equipo Visitante", JOptionPane.INFORMATION_MESSAGE));
		switch (op) {
			case 1: { //Por goles
				partido.ordenarVisitanteGoles();
				break;
			}
			case 2: { //Por pases
				partido.ordenarVisitantePases();
				break;
			}
			default: {
				JOptionPane.showMessageDialog(null,
						"VALOR INTRODUCIDO NO VALIDO", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}