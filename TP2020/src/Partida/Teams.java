package Partida;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import Jugador.Jugador;
import Funciones.Funciones;

//OK
public class Teams extends Partida{	
	//"region" debería ser de tipo "Region", no de tipo "String"
	public Teams(Integer cantJugadores, String region, Integer tiempo, Integer tiempoConfig) {
		super(cantJugadores, region,(int)Math.ceil((float)cantJugadores/2),2);
		this.tiempoRestante = tiempo;
		this.tiempoMaxConfig = tiempoConfig;
		// TODO Auto-generated constructor stub
	}
	/**
	 * Procedimiento que obtiene un jugador ganador
	 */
	public void getGanador(int duracion) {	
		int cont = 0;
		int i = 0;
		int j = 0;
		int eGanador = -1;
		if (duracion < 1) {
			System.out.println("La partida termino porque el tiempo se ha acabado..."); //Acoplamiento de interfaz
			int maxKills = 0;
			for (i = 0; i < filas; i++){
				if( matrizSobrevivientes[i][0]!= null && matrizSobrevivientes[i][1]!=null &&
					(matrizSobrevivientes[i][0]==1 && matrizSobrevivientes[i][1]==1 ) ){
					int teamkills = matrizMatados[i][0]+matrizMatados[i][1];
					if(teamkills > maxKills) {
						eGanador = i;
						maxKills = teamkills;
					}
				}
			}
			if (maxKills == 0)
				System.out.println("Ningun equipo gano la partida..."); //Acoplamiento de interfaz
			else {
				System.out.println("El equipo de:"); //Acoplamiento de interfaz
				for (j = 0; j < columnas; j++){
					if( matrizJugadores[eGanador][j]!=null) {
						System.out.println("	- "+matrizJugadores[eGanador][j].getNick()); //Acoplamiento de interfaz
					}
				}
				if(maxKills == 1)
					System.out.println("Gano la partida con " + maxKills + " kill!"); //Acoplamiento de interfaz
				else
					
					System.out.println("Gano la partida con " + maxKills + " kills!"); //Acoplamiento de interfaz
			}
			
		}
		else {
			System.out.println("La partida termino porque solo un equipo queda en pie..."); //Acoplamiento de interfaz
			boolean esGanador= false;
			while(i < filas && !esGanador) {
				while(j < columnas && !esGanador) {
					if( matrizSobrevivientes[i][0] != null && matrizSobrevivientes[i][0] == 1) {
						esGanador = true;
						eGanador = i;
					}
					j++;
				}
				i++;
			}
			System.out.println("El equipo de:");
			for (j = 0; j < columnas; j++){
				if( matrizJugadores[eGanador][j]!=null) {
					System.out.println("	- "+matrizJugadores[eGanador][j].getNick());
				}
			}
			System.out.println("GANO LA PARTIDA!"); //Acoplamiento de interfaz
		}
		if(eGanador != -1)
			setFilaGanadora(eGanador);
	}
	
	/**
	 *Obtiene los puntos conseguidos en la partida
	*/	
	public int getPuntos(Boolean ganador, Integer pos) {
		int sumapuntos= ganador?3:0; //Definir este tipo de valores como constantes!
		int cont_matados=0;
		for (int i = 0; i < columnas; i++) {
			if( matrizMatados[pos][i] != null && matrizMatados[pos][i]!=0 ) {
				System.out.println("matdos "+matrizMatados[pos][i]); //Acoplamiento de interfaz
				cont_matados+=matrizMatados[pos][i];
			}
		}
		cont_matados=(int)Math.floor((float)cont_matados/2);
		return sumapuntos+cont_matados;
	}
}
