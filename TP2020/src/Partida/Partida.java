package Partida;

import java.util.ArrayList;
import java.util.Scanner;
//import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;
import Jugador.Jugador;
import Jugador.Historial;
import Region.Region;
import Servidor.Servidor;
import Funciones.Funciones;

/**
 * /**
 * Clase que contiene los tres tipos de juegos y las funcionalidades principales que utizan cada uno.
 * @author
 */
//Si bien es creativo el uso de matrices no termina solucionando el manejo de los tipos de partida e inclusive hace más complejo el código
//En lugar de las matrices se podrían haber utilizado listas simples y apostar por la virtualidad en métodos como "encuentro", de forma que cada tipo de partida (sub-clases) implementen las particularidades, en lugar de que estas particularidades dependan de la configuración de una matriz
//La creación de la partida desde "Juego" por ejemplo no llega a ser completamente polimórfica porque "Juego" tiene que preocuparse por cómo configurar la matriz (filas y columnas) en lugar de simplemente crear una nueva partida de un tipo concreto que ya maneje sus jugadores de forma apropiada internamente
public class Partida { //"Partida" debería ser abstracta, dado que NO se pueden crear instancias de tipo "Partida", siempre que se arme una partida será una instancia de algunos de sus sub-tipos!
	private String region; //No! No utilizar foreign-keys, utilizar composición ("region" debería ser de tipo "Region").
	protected Jugador[][] matrizJugadores;
	protected Integer[][] matrizMatados;
	protected Integer[][] matrizSobrevivientes;
	protected Integer columnas;
	protected Integer filas;
	private Integer filaGanadora;

	//"regio" debería ser de tipo "Region", no "String"
	public Partida(Integer cantJugadores, String regio, int filas, int columnas) {
		this.filas = filas;
		this.columnas = columnas;
		this.matrizMatados = new Integer[filas][columnas];
		this.matrizSobrevivientes = new Integer[filas][columnas];
		this.matrizJugadores = new Jugador[filas][columnas];
		this.region = region;
		this.cantJugadores = cantJugadores;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
	public void setFilaGanadora(Integer f) {
		this.filaGanadora = f;
	}
	public Integer getFilaGanadora() {
		return filaGanadora;
	}
	
	
	/**
	 * Procedimiento que informa los estados de cada logro para cada participante
	 */
	//Y si queremos agregar más logros? La idea con los logros era que fuesen una jerarquía de clases y cada uno tenga su propia lógica para desbloquearse, armando un sistema que sea flexible y escalable.
	//Si se agregasen más logros este método va a seguir creciendo por cada lógica nueva a considerar.
	//Estamos analizando tipos de partida dentro de un método de una clase base...eso está muy mal! En todo caso si el desbloqueo se va a realizar por medio de un método este, al menos, debería ser polimórfico!!
	public void desbloqueoLogros() {
		int ganadas_consecutivas = 0;
		int max_consecutivas = 0;
		boolean p_ffa = false;
		boolean p_bwar = false;
		int matados_t = 0;
		ArrayList<Historial> aux_historial = null;
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				if(matrizJugadores[i][j] != null ) {
					if( !matrizJugadores[i][j].logroDesbloqueado(1) && matrizMatados[i][j] >= 5) {
						matrizJugadores[i][j].desbloquearLogro(1);
					}
					ganadas_consecutivas = 0;
					max_consecutivas = 0;
					p_ffa = false;
					p_bwar = false;
					matados_t = 0;
					aux_historial = matrizJugadores[i][j].getHistorialJugador();
					for (Historial h: aux_historial) {
						if( h.getVictoria() ){
							ganadas_consecutivas++;
						}else {
							if(max_consecutivas < ganadas_consecutivas)
								max_consecutivas = ganadas_consecutivas;
						}
						matados_t += h.getEliminaciones();
						if(h.getTipoPartida() == "FFA" && !p_ffa)
							p_ffa = true;
						if(h.getTipoPartida() == "Big War" && !p_bwar)
							p_bwar = true;
					}
					if(max_consecutivas < ganadas_consecutivas)
						max_consecutivas = ganadas_consecutivas;
					
					if(!matrizJugadores[i][j].logroDesbloqueado(0) && matados_t >= 5) {
						matrizJugadores[i][j].desbloquearLogro(0);
					}
					if(!matrizJugadores[i][j].logroDesbloqueado(2) && max_consecutivas >= 5) {
						matrizJugadores[i][j].desbloquearLogro(2);
					}
					if(!matrizJugadores[i][j].logroDesbloqueado(3) && p_bwar) {
						matrizJugadores[i][j].desbloquearLogro(3);
					}
					if(!matrizJugadores[i][j].logroDesbloqueado(4) && p_ffa) {
						matrizJugadores[i][j].desbloquearLogro(4);
					}
				}
			}
		}
	}

	/**
	 * Procedimiento que genera las matrices necesarias para cada tipo de juego
	 * @param fila_tope
	 * @param columna_tope
	 * @param maxjugadores
	 * @param Jugadores
	 */
	//La idea es que cada tipo de partida maneje su lógica naturalmente de forma polimórfica, no en base a la configuración de una matriz...
	public void armarMatrices(int fila_tope, int columna_tope, int maxjugadores , ArrayList<Jugador> Jugadores){
		int fila = 0;
		int columna = 0;
		for (Jugador j: Jugadores) {
			matrizMatados[fila][columna] = 0;
			matrizSobrevivientes[fila][columna] = 1;  
			matrizJugadores[fila][columna] = j;
			columna++;
			if(columna == columna_tope) {
				columna = 0;
				fila++;
			}
		}
	}

	public void muestraMatriz() {
		System.out.println("		"); //Acoplamiento de interfaz
		System.out.println("CARGANDO EQUIPOS..."); //Acoplamiento de interfaz
		for (int i = 0; i < filas; i++) {
			System.out.println("		"); //Acoplamiento de interfaz
			System.out.println("Equipo N "+(i+1)+" :"); //Acoplamiento de interfaz
			for (int j = 0; j < columnas; j++) {
				if(matrizJugadores[i][j] != null) {
					System.out.println("  - "+  matrizJugadores[i][j].getNick()); //Acoplamiento de interfaz
				}
			}
		}
		System.out.println("		");
	}

	/**
	 * Procedimiento que simula la jugabilidad de los tipos de juego y es compartida en ffa y teams
	 */
	//Excesivo acoplamiento de interfaz-lógica de negocios!
	public void jugar() { 
		int duracion = 0;
		String numero = "";
		do {
			System.out.println("Indica duracion de partida: "); //Acoplamiento de interfaz
			Scanner render = new Scanner(System.in); //Acoplamiento de interfaz (lectura!)
			numero = render.nextLine();
		} while (!Funciones.validaNumeros(numero));

		duracion = Integer.parseInt(numero);
		System.out.println("--------------------------------------------------------------");
		System.out.println("INICIANDO PARTIDA");
		System.out.println("--------------------------------------------------------------");
		while(duracion>0 && !hayGanador()) {
			System.out.println("TIEMPO RESTANTE: " + duracion + " seg.");
			encuentro();
			duracion = duracion - 5;
		}
		System.out.println("--------------------------------------------------------------");
		getGanador(duracion);
		System.out.println("--------------------------------------------------------------");
		System.out.println("PARTIDA FINALIZADA");
		System.out.println("--------------------------------------------------------------");
		
	}

	//Eliminar métodos innecesarios
	public void getGanador(int duracion) {
		//????
	}
	
	//"encuentro" debería ser virtual y contener la funcionalidad específica de cada tipo de partida (selección de jugadores a partir de equipos o de un único listado) para llamarse polimórficamente desde otras clases  
	//Mediante las matrices y la implementación actual se revisa la configuración definida para determinar cómo debe ser la selección de jugadores, lo cual es innecesario si esta funcionalidad estuviese implementada para soportar polimorfismo y está mal desde el punto de vista de que "Partida" esta guardando información puntual acerca de qué tipo de partida es
	//Demasiado acoplamiento de interfaz!!!
	public void encuentro(){ 
		int jugador1Fila = Funciones.getRamdomNumber(filas);
		int jugador1Columna = 0; 
		int jugador2Columna = 0;
		if( columnas != 1) {
			jugador2Columna = Funciones.getRamdomNumber(columnas);
			jugador1Columna = Funciones.getRamdomNumber(columnas);
		}
		while (matrizSobrevivientes[jugador1Fila][jugador1Columna] == null || matrizSobrevivientes[jugador1Fila][jugador1Columna] != 1 ) {
			jugador1Fila = Funciones.getRamdomNumber(filas);
			if( columnas != 1)
				jugador1Columna = Funciones.getRamdomNumber(columnas);
		}
		int jugador2Fila = Funciones.getRamdomNumber(filas);
		while (jugador2Fila == jugador1Fila ||  matrizSobrevivientes[jugador2Fila][jugador2Columna] == null ||
				matrizSobrevivientes[jugador2Fila][jugador2Columna] != 1 ){
			jugador2Fila = Funciones.getRamdomNumber(filas);
			if( columnas != 1)
				jugador2Columna = Funciones.getRamdomNumber(columnas);
		}
		System.out.println(matrizJugadores[jugador1Fila][jugador1Columna].getNick() );
		System.out.println("    VS    ");
		System.out.println( matrizJugadores[jugador2Fila][jugador2Columna].getNick() );
		int chances = Funciones.getRamdomNumber(100) + 1;
		int j1_chances = getChances(matrizJugadores[jugador1Fila][jugador1Columna].getPuntosGlobal());
		int j2_chances = j1_chances + (getChances(matrizJugadores[jugador2Fila][jugador2Columna].getPuntosGlobal())) + 1;

		if (chances <= j1_chances) {
			matrizSobrevivientes[jugador2Fila][jugador2Columna] = 0;
			matrizMatados[jugador1Fila][jugador1Columna] += 1;
			System.out.println("- " + matrizJugadores[jugador1Fila][jugador1Columna].getNick() + "(" +
					Funciones.getRango(matrizJugadores[jugador1Fila][jugador1Columna].getPuntosGlobal()) +  ") elimina a " +
					matrizJugadores[jugador2Fila][jugador2Columna].getNick() + "(" +
					Funciones.getRango(matrizJugadores[jugador2Fila][jugador2Columna].getPuntosGlobal()) + ")");
		} else {
			if (chances <= j2_chances) {
				matrizSobrevivientes[jugador1Fila][jugador1Columna] = 0;
				matrizMatados[jugador2Fila][jugador2Columna] += 1;
				System.out.println("- " + matrizJugadores[jugador2Fila][jugador2Columna].getNick() + "(" +
						Funciones.getRango(matrizJugadores[jugador2Fila][jugador2Columna].getPuntosGlobal()) +  ") elimina a " +
						matrizJugadores[jugador1Fila][jugador1Columna].getNick() + "(" +
						Funciones.getRango(matrizJugadores[jugador1Fila][jugador1Columna].getPuntosGlobal()) + ")");
			} else {
				System.out.println("- Ningun jugador elimina a otro...");
			}
		}
		System.out.println("---------------------------------");
	}

	//Está mal generar getters/setters de todos los atributos. Solo se deben generar en el caso de que sean necesarios!!
	
	public Jugador[][] getmatrizJugadores() {
		return matrizJugadores;
	}

	public void setmatrizJugadores(Jugador[][] matrizJugadores) {
		this.matrizJugadores = matrizJugadores;
	}

	public Integer[][] getmatrizMatados() {
		return matrizMatados;
	}

	public void setMatrizMatados(Integer[][] matrizMatados) {
		this.matrizMatados = matrizMatados;
	}

	public Integer[][] getMatrizSobrevivientes() {
		return matrizSobrevivientes;
	}

	public void setMatrizSobrevivientes(Integer[][] matrizSobrevivientes) {
		this.matrizSobrevivientes = matrizSobrevivientes;
	}

	//Manejar las franjas de puntuación en constantes/Enums, no de esta forma! (poca flexibilidad)
	public Integer categoriaNumero(int puntos) {
		if (puntos <= 5)
			return 5;
		if (puntos <= 15)
			return 15;
		if (puntos <= 30)
			return 30;
		return 40;
	}

	enum categoria {
		novice, silver, gold, platinum
	}

	/**
	 * Funcion que devuelve un valor aleatorio
	 * @param max
	 * @param conCero
	 * @return
	 */
	public int random(int max, boolean conCero) {
		if (conCero)
			return (int) (Math.random() * max);
		else
			return (int) ((Math.random() * max) + 1);
	}

	//Manejar las franjas de categorías y de probabilidades en constantes y/o Enums!
	public int getChances(int puntos) {
		if (puntos <= 5)
			return 10;
		else {
			if (puntos <= 15)
				return 20;
			else {
				if (puntos <= 30)
					return 30;
				else
					return 40;
			}
		}
	}

	/**
	 * Informa si hay ganador
	 * @return
	 */
	public Boolean hayGanador() {
		int equiposVivos = 0;
		int i = 0, j = 0;
		boolean hayVivos = false;
		while(i < filas && equiposVivos < 2 ) {
			hayVivos = false;
			j = 0;
			while(j < columnas && !hayVivos) {
				if( matrizSobrevivientes[i][j] != null && matrizSobrevivientes[i][j] == 1)
					hayVivos=true;
				j++;
			}
			if(hayVivos)
				equiposVivos++;
			i++;
		}
		return equiposVivos<2;
	}
	
	public void actualizarHistorial(String tipoJuego) {
		boolean finDeTiempo = true;
		int i = 0, j = 0, mayor = 0, ganador = -1, contVivos = 0, sumaEliminaciones = 0, contEquiposVivos = 0, ganadorBatalla = 0;
		while(i < filas ) {
			j = 0;
			contVivos = 0;
			sumaEliminaciones = 0;
			while(j < columnas) {
				if( matrizSobrevivientes[i][j] != null && matrizSobrevivientes[i][j] == 1) {
					contVivos++;
				}
				if( matrizMatados[i][j] != null && matrizMatados[i][j] != 0) {
					sumaEliminaciones += matrizMatados[i][j];
				}
				j++;
			}
			if(sumaEliminaciones > mayor ) { //Devuelve quien gano fuera de tiempo (suma muerto o vivo)
				ganador = i;
				mayor = sumaEliminaciones;
			}
			if(contVivos != 0) { //Almacena la ultima fila con participantes vivos, si es > 1 termino por tiempo y no por falta de jugadores
				contEquiposVivos++;
				ganadorBatalla = i;
			}
			i++;
		}
		if(contEquiposVivos == 1) {
			ganador = ganadorBatalla;
			finDeTiempo = false;
		}
		for( int ii = 0; ii < filas; ii++ ) {
			for (int jj = 0; jj < columnas; jj++) {
				if(matrizJugadores[ii][jj] != null) {
					matrizJugadores[ii][jj].setHistorialJugador(tipoJuego, matrizMatados[ii][jj], ii==ganador, finDeTiempo);
				}
			}
		}
	}
	
	public int getPuntos(Boolean ganador, Integer pos) {
		return 0;
	}
	
	public void actualizarPuntos() {
		int puntos_juego=0;
		if(filaGanadora != null ) {
			for( int i = 0; i < filas; i++) {
				if(matrizJugadores[i][0] != null){
					puntos_juego = getPuntos(filaGanadora==i,i);
					if(puntos_juego != 0) {
						for (int j = 0; j < columnas; j++) {
							if(matrizJugadores[i][j] != null) {
								matrizJugadores[i][j].setPuntosGlobal(puntos_juego);
							}
						}
					}
				}
			}
		}else {
			System.out.println("No se actualiza el historia porque no hubo ganadores"); //Acoplamiento de interfaz
		}
	}
}
