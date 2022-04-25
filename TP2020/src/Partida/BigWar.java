package Partida;
import java.util.ArrayList;
import Jugador.Jugador;

/**
 * Clase Bigwar que hereda de la clase partida, es una clase definida para un tipo de juego en este caso en 2 equipos de 50%-50%
 * @author 
 *
 */
//OK
public class BigWar extends Partida {
	//"region" debería ser de tipo "Region" en lugar de tipo "String"
	public BigWar( Integer cantJugadores, String region) {
		super(cantJugadores, region, 2, (int)Math.ceil((float)cantJugadores/2));
	}
	
	/**
	 *Procedimiento que genera la matriz recibida de manera ordenada por puntos y seleciona de a uno para cada equipo nuevo
	 */
	public void armarMatrices(int fila_tope, int columna_tope, int maxjugadores, ArrayList<Jugador> Jugadores){
		int fila = 0;
		int columna = 0;
		System.out.println("tope " + fila_tope);
		for (Jugador j: Jugadores) {
			matrizMatados[fila][columna] = 0; 
			matrizSobrevivientes[fila][columna] = 1;
			matrizJugadores[fila][columna] = j;
			fila++;
			if(fila == fila_tope) {
				fila = 0;
				columna++;
				System.out.println("Entra"); //Acoplamiento de interfaz
			}
		}
		System.out.println("\nLista de jugadores Ordenada: \n\nPuntos\tNickname"); //Acoplamiento de interfaz
		Jugadores.forEach(jugador->System.out.println(jugador.getPuntosGlobal() + "\t" + jugador.getNick()));		
	}
	
	/**
	 * Procedimiento que simula el juego
	 */
	public void jugar(){
		System.out.println("--------------------------------------------");
		System.out.println("-------------INICIANDO PARTIDA--------------");
		System.out.println("--------------------------------------------");
		int ronda = 0;
		while(!hayGanador()) {
			ronda++;
			System.out.println("RONDA: " + ronda);
			encuentro();
		}
		System.out.println("---------------------------------");
		getGanador();
		System.out.println("--------------------------------------------");
		System.out.println("-------------PARTIDA FINALIZADA-------------");
		System.out.println("--------------------------------------------");
	}	
	
	/**
	 * Procedimiento que obtiene un equipo ganador
	 */
	public void getGanador() {	
		boolean esganador = false;
		int cont = 0;
		int i = 0;
		int equipo = 0;	
		while(i < filas && !esganador) {
			cont = 0;
			for (int j = 0; j < columnas; j++) {
				if(matrizSobrevivientes[i][j] != null && matrizSobrevivientes[i][j] == 1)
					cont++;
			}
			if(cont > 0) {
				esganador = true;
				equipo=i;
			}
			i++;
		}
		System.out.println("La partida termino porque solo un equipo queda en pie..."); //Acoplamiento de interfaz
		System.out.println("			"); //Acoplamiento de interfaz
		System.out.println("Gano el equipo numero " + i); //Acoplamiento de interfaz
		if(cont == 1)
			System.out.println("Del cual sobrevivio: "); //Acoplamiento de interfaz
		else
			System.out.println("Del cual sobrevivieron: "); //Acoplamiento de interfaz
		
		for (int j = 0; j < columnas; j++) {
			if(matrizSobrevivientes[equipo][j] != null && matrizSobrevivientes[equipo][j] == 1){
				System.out.println( "	- " + matrizJugadores[equipo][j].getNick()); //Acoplamiento de interfaz
			}
		}
		setFilaGanadora(equipo);
	}
	
	//Remover métodos innecesarios
	public void sumaPuntos() {
	 //????
	}
}
