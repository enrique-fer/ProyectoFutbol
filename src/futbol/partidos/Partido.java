package futbol.partidos;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import futbol.jugadores.Jugador;
import futbol.jugadores.JugadorCampo;
import futbol.jugadores.Portero;
import excepciones.jugadores.JugadorExistente;

import enumerados.*;

/**
 * @author Enrique Fernandez Santiago
 * @version 1..0
 */

/**
 * Clase Partido
 * ATRIBUTOS
 * 	-equipoVisitante: ArrayList-Jugador-
 *  -equipoLocal: Jugador[]
 * 
 * METODOS
 * 	+anadirJugador(TipoEquipo, Jugador): void
 * 	-anadirJugadorLocal(Jugador): void
 *  -anadirJugadorVisitante(Jugador): void
 *  
 *  +borrarJugador(TipoEquipo, int): void
 *  -borrarJugadorLocal(int): void
 *  -borrarJugadorVisitante(int): void
 *  
 *  +contabilizarGol(TipoEquipo, int): void
 *  -contabilizarGolLocal(int): void
 *  -contabilizarGolVisitante(int): void
 *  
 *  +contabilizarPaseExito(TipoEquipo, int): void
 *  -contabilizarPaseLocal(int): void
 *  -contabilizarPaseVisitante(int): void
 *  
 *  +contabilizarBolasRecu(TipoEquipo, int): void
 *  -contabilizarBolaLocal(int): void
 *  -contabilizarBolaVisitante(int): void
 *  
 *  +contabilizarParada(TipoEquipo, int): void
 *  -contabilizarParadaLocal(int): void
 *  -contabilizarParadaVisitante(int): void
 *  
 *  +mostrarJugador(TipoEquipo, int): Jugador
 *   
 *  -buscarJugadorLocal(int): boolean
 *  -buscarJugadorVisitante(int): boolean
 *  
 *  +jugConIntervencion(TipoEquipo): ArrayList-Jugador-
 *  -intervencionesLocal(): ArrayList-Jugador-
 *  -intervencionesVisitante(): ArrayList-Jugador-
 *  
 *  +golesLocal(): int
 *  +golesVisitante(): int
 *  
 *  +mediaPasesLocal(): double
 *  +mediaPasesVisitante(): double
 *  
 *  +ordenarLocalGoles(): void
 *  +ordenarLocalPases(): void
 *  +ordenarVisitanteGoles(): void
 *  +ordenarVisitantePases(): void
 */

/**
 * Contiene los datos y acciones relacinados con un partido
 */
public class Partido {
	private Jugador[] equipoLocal;
	private ArrayList<Jugador> equipoVisitante; 
	
	public Partido() {
		equipoVisitante = new ArrayList<Jugador>();
		equipoLocal = new Jugador[1];
	}
	
	
	//Añadir jugadores
	/**
	 * Metodo intermediario para introducir un jugador a un equipo
	 * @param te Tipo del equipo del jugador
	 * @param j El objeto jugador
	 * @throws JugadorExistente El jugador ya existe en alguno de los equipos
	 */
	public void anadirJugador(TipoEquipo te, Jugador j) throws JugadorExistente {
		if (te == TipoEquipo.LOCAL) {
			if (buscarJugadorLocal(j.getNumDorsal()) == false) {
				anadirJugadorLocal(j);
			} else {
				throw new JugadorExistente("Ya existe un jugador con ese dorsal");
			}
		} else {
			if (buscarJugadorVisitante(j.getNumDorsal()) == false) {
				anadirJugadorVisitante(j);
			} else {
				throw new JugadorExistente("Ya existe un jugador con ese dorsal");
			}
		}
	}
	
	/**
	 * Añade un jugador al equipo Local
	 * @param j El jugador a añadir
	 */
	private void anadirJugadorLocal(Jugador j) {
		int pos;
		Jugador[] aux;
		//Añade al jugador en la siguite posicion disponible
		if (this.equipoLocal[this.equipoLocal.length-1] == null) { 
			pos = 0;
			while (pos < this.equipoLocal.length) {
				if (this.equipoLocal[pos] == null) {
					this.equipoLocal[pos] = j;
					pos = this.equipoLocal.length;
				}
				pos++;
			}
		} else {
			//Con la ayuda de otro array se añade una posicion en el equipo y se añade el jugador
			aux = new Jugador[this.equipoLocal.length + 1];
			for (int i = 0; i < this.equipoLocal.length; i++) {
				aux[i] = this.equipoLocal[i];
			}
			
			aux[aux.length - 1] = j;
			this.equipoLocal = new Jugador[aux.length];
			
			for (int i = 0; i < this.equipoLocal.length; i++) {
				this.equipoLocal[i] = aux [i];
			}
		}
	}
	
	/**
	 * Añade un jugador al equipo Visitante
	 * @param j El jugador a añadir
	 */
	private void anadirJugadorVisitante(Jugador j) {
		this.equipoVisitante.add(j);
	}
	
	
	//Borrar jugador	
	/**
	 * Metodo intermediario para borrar a un jugador de un equipo
	 * @param te Tipo del equipo del jugador buscado
	 * @param dorsal Dorsal del jugador buscado
	 * @throws JugadorExistente El jugador buscado no existe
	 */
	public void borrarJugador(TipoEquipo te, int dorsal) throws JugadorExistente{
		if (te == TipoEquipo.LOCAL) {
			if (buscarJugadorLocal(dorsal)) {
				borrarJugadorLocal(dorsal);
			} else {
				throw new JugadorExistente("No existe ese jugador en el equipo");
			}
		} else {
			if (buscarJugadorVisitante(dorsal)) {
				borrarJugadorVisitante(dorsal);
			} else {
				throw new JugadorExistente("No existe ese jugador en el equipo");
			}	
		}
	}
	
	/**
	 * Borra un jugador del equipo Local
	 * @param dorsal Dorsal del jugador buscado
	 */
	private void borrarJugadorLocal(int dorsal) {
		for (int i = 0; i < this.equipoLocal.length; i++) {
			if (this.equipoLocal[i] != null) {
				if (this.equipoLocal[i].getNumDorsal() == dorsal) {
					this.equipoLocal[i] = null;
				}
			}
		}
	}
	
	/**
	 * Borra un jugador del equipo Visitante
	 * @param dorsal Dorsal del jugador a borrar
	 */
	private void borrarJugadorVisitante(int dorsal) {
		int pos = 0;
		while (pos < this.equipoVisitante.size()) {
			if (this.equipoVisitante.get(pos).getNumDorsal() == dorsal) {
				this.equipoVisitante.remove(pos);
			}
			pos++;
		}
	}
	
	
	//ContabilizarPuntos 
	/**
	 * Metodo intermedio para contabilizar goles a un jugador
	 * @param te Tipo del equipo del jugador
	 * @param dorsal Dorsal del jugador buscado
	 * @param goles Cantidad de goles marcados
	 */
	public void contabilizarGol(TipoEquipo te, int dorsal, int goles) {
		switch (te) {
			case LOCAL: {
				contabilizarGolLocal(dorsal, goles);
				break;
			}
			case VISITANTE: {
				contabilizarGolVisitante(dorsal, goles);
				break;
			}
		}
	}
	
	/**
	 * Contabiliza goles a un jugador Local
	 * @param dorsal Dorsal del jugador buscado
	 * @param goles Numero de goles marcados
	 */
	private void contabilizarGolLocal(int dorsal, int goles) {
		if (buscarJugadorLocal(dorsal)) {
			for (int i = 0; i < this.equipoLocal.length; i++) {
				if (this.equipoLocal[i] != null) {
					if (this.equipoLocal[i].getNumDorsal() == dorsal) {
						for (int j = 0; j < goles; j++) {
							this.equipoLocal[i].marcaGol();
						}
					}
				}
			}
		}
	}
	
	/**
	 * Contabiliza goles a un jugador Visitante
	 * @param dorsal Dorsal del jugador buscado
	 * @param goles Cantidad de goles marcados
	 */
	private void contabilizarGolVisitante(int dorsal, int goles) {
		if (buscarJugadorVisitante(dorsal)) {
			for (int i = 0; i < this.equipoVisitante.size(); i++) {
				if (this.equipoVisitante.get(i).getNumDorsal() == dorsal) {
					for (int j = 0; j < goles; j++) {
						this.equipoVisitante.get(i).marcaGol();
					}
				}
			}
		}
	}
	
	/**
	 * Metodo intermedio para contabilizar pases de un jugador
	 * @param te Tipo del equipo del jugador
	 * @param dorsal Dorsal del jugador buscado
	 * @param pases Numero de pases con exito realizados
	 */
	public void contabilizarPaseExito(TipoEquipo te, int dorsal, int pases) {
		switch (te) {
			case LOCAL: {
				contabilizarPaseLocal(dorsal, pases);
				break;
			}
			case VISITANTE: {
				contabilizarPaseVisitante(dorsal, pases);
				break;
			}
		}
	}
	
	/**
	 * Contabiliza pases con exito a un jugador Visitante
	 * @param dorsal Dorsal del jugador buscado
	 * @param pases Cantidad de pases realizados
	 */
	private void contabilizarPaseLocal(int dorsal, int pases) {
		if (buscarJugadorLocal(dorsal)) {
			for (int i = 0; i < this.equipoLocal.length; i++) {
				if (this.equipoLocal[i] != null) {
					if (this.equipoLocal[i] instanceof JugadorCampo) {
						if (this.equipoLocal[i].getNumDorsal() == dorsal) {
							for (int j = 0; j < pases; j++) {
								((JugadorCampo)this.equipoLocal[i]).sumarPase();
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Contabiliza pases con exito a un jugador Visitante
	 * @param dorsal Dorsal del jugador buscado
	 * @param pases Cantidad de pases realizados
	 */
	private void contabilizarPaseVisitante(int dorsal, int pases) {
		if (buscarJugadorVisitante(dorsal)) {
			for (int i = 0; i < this.equipoVisitante.size(); i++) {
				if (this.equipoVisitante.get(i) instanceof JugadorCampo) {
					if (this.equipoVisitante.get(i).getNumDorsal() == dorsal) {
						for (int j = 0; j < pases; j++) {
							((JugadorCampo)this.equipoVisitante.get(i)).sumarPase();
						}
					}
				}
			}
		}
	}
	
	/**
	 * Metodo intermedio para contabilizar bolas recuperadas de un jugador
	 * @param te Tipo del equipo del jugador
	 * @param dorsal Dorsal del jugador buscado
	 * @param recuas Numero de recupersaciones realizadas
	 */
	public void contabilizarBolaRecu(TipoEquipo te, int dorsal, int recus) {
		switch (te) {
			case LOCAL: {
				contabilizarBolaLocal(dorsal, recus);
				break;
			}
			case VISITANTE: {
				contabilizarBolaVisitante(dorsal, recus);	
				break;
			}
		}
	}
	
	/**
	 * Contabiliza bolas recuperadas a un jugador Local
	 * @param dorsal Dorsal del jugador buscado
	 * @param recus Numero de recuperacines realizadas
	 */
	private void contabilizarBolaLocal(int dorsal, int recus) {
		if (buscarJugadorLocal(dorsal)) {
			for (int i = 0; i < this.equipoLocal.length; i++) {
				if (this.equipoLocal[i] != null) {
					if (this.equipoLocal[i] instanceof JugadorCampo) {
						if (this.equipoLocal[i].getNumDorsal() == dorsal) {
							for (int j = 0; j < recus; j++) {
								((JugadorCampo)this.equipoLocal[i]).sumarBolaRecu();;
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Contabiliza bolas recuperadas a un jugador Visitante
	 * @param dorsal Dorsal del jugador buscado
	 * @param recus Numero de recuperacines realizadas
	 */
	private void contabilizarBolaVisitante(int dorsal, int recus) {
		if (buscarJugadorVisitante(dorsal)) {
			for (int i = 0; i < this.equipoVisitante.size(); i++) {
				if (this.equipoVisitante.get(i) instanceof JugadorCampo) {
					if (this.equipoVisitante.get(i).getNumDorsal() == dorsal) {
						for (int j = 0; j < recus; j++) {
							((JugadorCampo)this.equipoVisitante.get(i)).sumarBolaRecu();;
						}
					}
				}
			}
		}
	}
	
	/**
	 * Metodo intermedio para contabilizar paradas de un jugador
	 * @param te Tipo del equipo del jugador
	 * @param dorsal Dorsal del jugador buscado
	 * @param paradas Numero de paradas realizadas
	 */
	public void contabilizarParada(TipoEquipo te, int dorsal, int paradas) {
		switch (te) {
			case LOCAL: {	
				contabilizarParadaLocal(dorsal, paradas);
				break;
			}
			case VISITANTE: {
				contabilizarParadaVisitante(dorsal, paradas);
				break;
			}
		}
	}
	
	/**
	 * Contabilizar paradas a un jugador Local
	 * @param dorsal Dorsal del jugador buscado
	 * @param paradas Cantidad de paradas realizadas
	 */
	private void contabilizarParadaLocal(int dorsal, int paradas) {
		if (buscarJugadorLocal(dorsal)) {
			for (int i = 0; i < this.equipoLocal.length; i++) {
				if (this.equipoLocal[i] != null) {
					if (this.equipoLocal[i] instanceof Portero) {
						if (this.equipoLocal[i].getNumDorsal() == dorsal) {
							for (int j = 0; j < paradas; j++) {
								((Portero)this.equipoLocal[i]).paradaExito();
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Contabilizar paradas a un jugador Visitante
	 * @param dorsal Dorsal del jugador buscado
	 * @param paradas Cantidad de paradas realizadas
	 */
	private void contabilizarParadaVisitante(int dorsal, int paradas) {
		if (buscarJugadorVisitante(dorsal)) {
			for (int i = 0; i < this.equipoVisitante.size(); i++) {
				if (this.equipoVisitante.get(i) instanceof Portero) {
					if (this.equipoVisitante.get(i).getNumDorsal() == dorsal) {
						for (int j = 0; j < paradas; j++) {
							((Portero)this.equipoVisitante.get(i)).paradaExito();
						}
					}
				}
			}
		}
	}
	
	
	//Mostrar informacion del jugador
	/**
	 * Metodo para mostrar la informacion de un jugador
	 * @param te Tipo del equipo del jugador
	 * @param dorsal Dorsal del jugador buscado
	 * @return Un string con los datos del jugador buscado
	 * @throws JugadorExistente El jugador buscado no existe
	 */
	public String mostrarJugador(TipoEquipo te, int dorsal) throws JugadorExistente {
		String jugador = "";
		if (te == TipoEquipo.LOCAL) {
			if (buscarJugadorLocal(dorsal)) {
				for (int i = 0; i < this.equipoLocal.length; i++) {
					if (this.equipoLocal[i] != null) {
						if (this.equipoLocal[i].getNumDorsal() == dorsal) {
							jugador += this.equipoLocal[i];
						}
					}
				}
			} else {
				throw new JugadorExistente("No existe ese jugador");
			}
		} else {
			if (buscarJugadorVisitante(dorsal)) {
				for (int i = 0; i < this.equipoVisitante.size(); i++) {
					if (this.equipoVisitante.get(i).getNumDorsal() == dorsal) {
						jugador += this.equipoVisitante.get(i);
					}
				}
			} else {
				throw new JugadorExistente("No existe ese jugador");
			}
		}
		
		return jugador;
	}
	
	
	//Busqueda de jugadores 
	/**
	 * Permite saber si un dorsal ya existe en el equipo Local
	 * @param dorsal Dorsal de un jugador
	 * @return True si el dorsal se ha encontrado en el equipo, si no 
	 * 		   devuleve false
	 */
	private boolean buscarJugadorLocal(int dorsal) {
		boolean exist = false;
		for (int i = 0; i < this.equipoLocal.length; i++) {
			if (this.equipoLocal[i] != null) {
				if (this.equipoLocal[i].getNumDorsal() == dorsal) {
					exist = true;
				}
			}
		}
		
		return exist;
	}
	
	/**
	 * Permite saber si un dorsal ya existe en el equipo Visitante
	 * @param dorsal Dorsal de un jugador
	 * @return True si el dorsal se ha encontrado en el equipo, si no 
	 * 		   devuleve false
	 */
	private boolean buscarJugadorVisitante(int dorsal) {
		boolean exist = false;
		Iterator<Jugador> visIt = this.equipoVisitante.iterator();
		
		while (visIt.hasNext()) {
			if (visIt.next().getNumDorsal() == dorsal) {
				exist = true;
			}
		}
		
		return exist;
	}
	
	
	//Intervencion de jugadores (String builder-buscar)
	/**
	 * Metodo intermedio para obetener las intervenciones de un equipo
	 * @param te Tipo del equipo buscado
	 * @return Una lista con todos los jugadores del equipo que hayan 
	 * 		   intervenido
	 */
	public ArrayList<Jugador> jugConIntervencion(TipoEquipo te) {
		ArrayList<Jugador> lista = null;
		if (te == TipoEquipo.LOCAL) {
			lista = intervencionesLocal();
		} else {
			lista = intervencionesVisitante();
		}
		return lista;
	}
	
	/**
	 * Obtiene los jugadores del equipo Local que hayan intervenido en 
	 * el partido
	 * @return Un lista con los jugadores del equipo con intervencion
	 */
	private ArrayList<Jugador> intervencionesLocal() {
		int cont = 0;
		ArrayList<Jugador> listaJugadores = new ArrayList<Jugador>();
		
		while (cont < this.equipoLocal.length) {
			if (this.equipoLocal[cont] != null) {
				if (intervencionJugador(this.equipoLocal[cont])) {
					listaJugadores.add(this.equipoLocal[cont]);
				}
			}
			cont++;
		}
		
		return listaJugadores;
	}
	
	/**
	 * Obtiene los jugadores del equipo Visitante que hayan intervenido en 
	 * el partido
	 * @return Un lista con los jugadores del equipo con intervencion
	 */
	private ArrayList<Jugador> intervencionesVisitante() {
		ArrayList<Jugador> listaJugadores = new ArrayList<Jugador>();
		
		for (int i = 0; i < this.equipoVisitante.size(); i++) {
			if (intervencionJugador(this.equipoVisitante.get(i))) {
				listaJugadores.add(this.equipoVisitante.get(i));
			}
		}
		
		return listaJugadores;
	}
	
	/**
	 * Indica si un jugador a intervenido en el partido
	 * @param j Jugador del que se desea saber la intervencion	
	 * @return True si el jugador a intervenido, si no false
	 */
	private boolean intervencionJugador(Jugador j) {
		boolean inter = false;
		if (j.getNumGoles() > 0) {
			inter = true;
		} else {
			if (j instanceof JugadorCampo) {
				if (((JugadorCampo) j).getBolasRecu() > 0 || ((JugadorCampo) j).getNumPasesExito() > 0) {
					inter = true;
				}
			} else {
				if (((Portero) j).getNumParadas() > 0) {
					inter = true;
				}
			}
		}
		return inter;
	}
	
	
	//Marcador
	/**
	 * Obtiene los goles totales del equipo Local
	 * @return La cantidad total de goles
	 */
	public int golesLocal() {
		int cont = 0, i = 0;
		
		while (i < this.equipoLocal.length) {
			if (this.equipoLocal[i] != null) {
				cont += this.equipoLocal[i].getNumGoles();
			}
			i++;
		}
		
		return cont;
	}
	
	/**
	 * Obtiene los goles totales del equipo Visitante
	 * @return La cantidad total de goles
	 */
	public int golesVisitante() {
		int cont = 0, i = 0;
		
		while (i < this.equipoVisitante.size()) {
			cont += this.equipoVisitante.get(i).getNumGoles();
			i++;
		}
		
		return cont;
	}
	
	
	//Pases
	/**
	 * Obtiene la media de pases del equipo Local
	 * @return La media de pases
	 */
	public double mediaPasesLocal() {
		double pases = 0;
		for (int i = 0; i < this.equipoLocal.length; i++) {
			if (this.equipoLocal[i] != null && this.equipoLocal[i] instanceof JugadorCampo) {
				pases += ((JugadorCampo) this.equipoLocal[i]).getNumPasesExito();
			}
		}
		pases /= this.equipoLocal.length;
		
		return pases;
	}
	
	/**
	 * Obtiene la media de pases del equipo Visiatnte
	 * @return La media de pases
	 */
	public double mediaPasesVisitante() {
		double pases = 0;
		for (int i = 0; i < this.equipoVisitante.size(); i++) {
			if (this.equipoVisitante.get(i) instanceof JugadorCampo) {
				pases += ((JugadorCampo) this.equipoVisitante.get(i)).getNumPasesExito();
			}
		}
		pases /= this.equipoVisitante.size();
		
		return pases;
	}
	
	
	//Ordenar equipos
	/**
	 * Ordena el equipo Local segun los goles marcados
	 */
	public void ordenarLocalGoles() {
		Arrays.sort(this.equipoLocal);
	}
	
	/**
	 * Metodo para ordenar los jugadores del equipo Local mediante
	 * el logaritmo de insercion, siempre que algun jugador no sea 
	 * instancia de JugadorCampo, este jugador lo sera de Portero,
	 * por lo que se guardara en un array auxiliar para posteriormente
	 * cuando se hayan ordenado los jugadores, se añadiran al final
	 * de la lista de los jugadores
	 */
	public void ordenarLocalPases() {
		Jugador[] p = new Jugador[this.equipoVisitante.size()];
		Jugador[] j = new Jugador[1];
		int aux;
		int k = 0;
    	boolean hueco;
    	for (int i = 1; i < this.equipoLocal.length; i++)
    	{
			if (this.equipoLocal[i] instanceof JugadorCampo) {
				aux = ((JugadorCampo) this.equipoLocal[i]).getNumPasesExito();
	    		j[0] = this.equipoLocal[i];
	    		k = i - 1;
	    		hueco = false;
	    		while (k >= 0 && hueco == false)
	    		{
	    			if (this.equipoLocal[k] instanceof JugadorCampo) {
		    			if (((JugadorCampo) this.equipoLocal[k]).getNumPasesExito() > aux)
		    			{
		    				this.equipoLocal[k + 1] = this.equipoLocal[k];
		    				k -= 1;
		    			} else {
		    				hueco = true;
		    			}
	    			} else {
	    				if (0 < aux)
		    			{	
	    					this.equipoLocal[k + 1] = this.equipoLocal[k];
	    					k -= 1;
		    			} else {
		    				hueco = true;
		    			}
	    			}
		    	}
	    		this.equipoLocal[k + 1] = j[0];
			} else {
				for (int x = 0; x < p.length; x++) {
					if (p[x] == null) {
						p[x] = this.equipoLocal[i];
						this.equipoLocal[i] = null;
						x = p.length;
					}
				}
			}
    	}
    	
    	for (int i = 0; i < p.length; i++) {
    		for (int x = 0; x < this.equipoLocal.length; x++) {
    			if (p[i] != null) {
    				if (this.equipoLocal[x] == null) {
    					this.equipoLocal[x] = p[i];
    					x = this.equipoLocal.length;
    				}
    			}
    		}
    	}
	}
	
	/**
	 * Ordena el equipo Visitante segun el numero de goles
	 */
	public void ordenarVisitanteGoles() {
		Collections.sort(this.equipoVisitante);
	}
	
	/**
	 * Metodo para ordenar los jugadores del equipo Visitante mediante
	 * el logaritmo de insercion, siempre que algun jugador no sea 
	 * instancia de JugadorCampo, este jugador lo sera de Portero,
	 * por lo que se guardara en un array auxiliar para posteriormente
	 * cuando se hayan ordenado los jugadores, se añadiran al final
	 * de la lista de los jugadores
	 */
	public void ordenarVisitantePases() {
		Jugador[] p = new Jugador[this.equipoVisitante.size()];
		Jugador[] j = new Jugador[1];
		int aux;
		int k = 0;
    	boolean hueco;
    	for (int i = 1; i < this.equipoVisitante.size(); i++)
    	{
			if (this.equipoVisitante.get(i) instanceof JugadorCampo) {
				aux = ((JugadorCampo) this.equipoVisitante.get(i)).getNumPasesExito();
	    		j[0] = this.equipoVisitante.get(i);
	    		k = i - 1;
	    		hueco = false;
	    		while (k >= 0 && hueco == false)
	    		{
	    			if (this.equipoVisitante.get(k) instanceof JugadorCampo) {
		    			if (((JugadorCampo) this.equipoVisitante.get(k)).getNumPasesExito() > aux)
		    			{
		    				this.equipoVisitante.remove(k + 1);
		    				this.equipoVisitante.add(k + 1, this.equipoVisitante.get(k));
		    				k -= 1;
		    			} else {
		    				hueco = true;
		    			}
	    			} else {
	    				if (0 < aux)
		    			{
	    					
		    				this.equipoVisitante.remove(k + 1);
		    				this.equipoVisitante.add(k + 1, this.equipoVisitante.get(k));
		    				k -= 1;
		    			} else {
		    				hueco = true;
		    			}
	    			}
		    	}
	    		this.equipoVisitante.remove(k + 1);
	    		this.equipoVisitante.add(k + 1, j[0]);
			} else {
				for (int x = 0; x < p.length; x++) {
					if (p[x] == null) {
						p[x] = this.equipoVisitante.get(i);
						this.equipoVisitante.remove(i);
						x = p.length;
					}
				}
			}
    	}
    	
    	for (int i = 0; i < p.length; i++) {
    		if (p[i] != null) {
    			this.equipoVisitante.add(p[i]);
    		}
    	}
	} 
}