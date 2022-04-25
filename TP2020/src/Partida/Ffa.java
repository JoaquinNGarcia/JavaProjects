package Partida;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import Jugador.Jugador;
import Servidor.Servidor;
import Funciones.Funciones;

/**
 * Clase Ffa que hereda de la clase partida, es una clase definida para un tipo de juego en este caso
 * cada jugador pelea contra todos los demas que esten en la partida
 * @author
 */
//OK
public class Ffa extends Partida {
	//"region" debería ser de tipo "Region", no de tipo "String"
	public Ffa(Integer cantJugadores ,String region,Integer  tiempo, Integer tiempoConfig) {
		super( cantJugadores, region,cantJugadores,1);
		this.tiempoRestante = tiempo;
		this.tiempoMaxConfig = tiempoConfig;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 *Procedimiento que muestra la matriz.
	 */
	//Acoplamiento de interfaz!
	public void muestraMatriz() {
		System.out.println("		");
		System.out.println("CARGANDO JUGADORES...");
		for (int i = 0; i < filas; i++) {
			System.out.println("		");
			System.out.println("Jugador N "+ (i+1) + " :");
			for (int j = 0; j < columnas; j++) {
				if(matrizJugadores[i][j] != null) {
					System.out.println("  - " + matrizJugadores[i][j].getNick());
				}
			}
		}
		System.out.println("		");
	}
	
	/**
	 * Procedimiento que obtiene un jugador ganador
	 */
	public void getGanador(int duracion) {	
		int i = 0, jGanador = -1;
		if (duracion < 1) {
			System.out.println("La partida termino porque el tiempo se termino..."); //Acoplamiento de interfaz
			int maxKills = 0;
			for (i = 0; i < filas; i++){
				if(matrizSobrevivientes[i][0] != null && matrizSobrevivientes[i][0] == 1) {
					if(matrizMatados[i][0] > maxKills) {
						jGanador = i;
						maxKills = matrizMatados[i][0];
					}
				}
			}
			if (maxKills == 0) 
				System.out.println("Ningun jugador gano la partida..."); //Acoplamiento de interfaz
			else {
				if(maxKills == 1)
					System.out.println(matrizJugadores[jGanador][0].getNick() + " gano la partida con " + maxKills + " kill!"); //Acoplamiento de interfaz
				else
					System.out.println(matrizJugadores[jGanador][0].getNick() + " gano la partida con " + maxKills + " kills!"); //Acoplamiento de interfaz
			}
		}
		else {
			System.out.println("La partida termino porque un jugador logro derrotar a sus oponentes..."); //Acoplamiento de interfaz
			boolean esganador = false;
			while(i < filas && !esganador) {
				if( matrizSobrevivientes[i][0] != null && matrizSobrevivientes[i][0] == 1) {
					esganador = true;
					jGanador = i;
				}
				i++;
			}
			System.out.println(matrizJugadores[jGanador][0].getNick() + " GANO LA PARTIDA!"); //Acoplamiento de interfaz
		}
		if(jGanador != -1)
			setFilaGanadora(jGanador);
	}

	/**
	 *Obtiene los puntos conseguidos en la partida
	 */
	public int getPuntos(Boolean ganador, Integer pos) {
		int sumapuntos = ganador ? 5 : 0; //Definir los puntos en constantes al menos, para evitar que queden perdidos en el código
		if( matrizMatados[pos][0] != null && matrizMatados[pos][0] != 0 )
			sumapuntos += matrizMatados[pos][0];
		return sumapuntos;
	}
}
