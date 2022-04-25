package main;

import java.util.ArrayList;
import Juego.Juego;
import Region.Region;
import Servidor.Servidor;
import Jugador.Jugador;
import Partida.Teams;

/**
 * Clase principal del sistema donde se crean los servidores, regiones y la carga de jugadores
 * @author
 *
 */
//NO CUMPLEN LA CONSIGNA!
//La carga de TODOS los datos debía realizarse desde un txt inicialmente y luego a partir del archivo serializado.
//Parte de la carga se hace desde el archivo serializado (jugadores) mientras que otra se hace desde código (regiones y servidores) mientras que otra se debe comentar para que no se siga realizando luego de la primera vez (jugadores). Mantener una coherencia para la carga!
public class Main {
	public static void main(String[] args) {
		ArrayList<Region> list_Region = new ArrayList<Region>();
		ArrayList<Servidor> listServidor = new ArrayList<Servidor>();
		Region RegionA =  new Region("NA", "Norte America"); //No! La carga de regiones debe ser desde txt la primera vez y luego desde el/los archivos serializados, no desde el código! Mal!
		Region RegionB =  new Region("EU", "Europa");
		Juego JuegoUnico = new Juego();
		JuegoUnico.cargaJugadores();
		
		//Debería cargar los datos desde txt inicialmente, no desde el código!!

//		Jugador jugador_a = new Jugador("CerealKiller", "cerealkiller_83@gmail.com", "NA","asd123",false,21);
//		jugador_a.setHistorialJugador("FFA", 5, true, false);
//		jugador_a.setHistorialJugador("2P", 3, true, true);
//		jugador_a.setHistorialJugador("FFA", 4, false, false);
//		jugador_a.setHistorialJugador("FFA", 4, true, false);
//		jugador_a.setHistorialJugador("FFA", 5, true, false);
//		Jugador jugador_b = new Jugador("-_Corleone_-", "corleone_gamer@gmail.com", "EU","asd123",false,4);
//		jugador_b.setHistorialJugador("FFA", 4, true, false);
//		Jugador jugador_c = new Jugador("Joker", "rinusficus@hotmail.com", "NA", "asd123",false,10);
//		jugador_c.setHistorialJugador("2P", 4, true, true);
//		jugador_c.setHistorialJugador("FFA", 4, true, false);
//		jugador_c.setHistorialJugador("2P", 2, false, true);
//		Jugador jugador_d = new Jugador("Rob77", "rob.t.streamer@gmail.com", "NA", "asd123",false,6);
//		jugador_d.setHistorialJugador("FFA", 2, true, true);
//		jugador_d.setHistorialJugador("FFA", 3, true, true);
//		jugador_d.setHistorialJugador("FFA", 1, false, true);
//		Jugador jugador_e = new Jugador("Master_Killah", "m.killah.twitch@gmail.com", "NA","asd123",false,0);
//		Jugador jugador_f = new Jugador("SIR_HEADSHOT", "coltwilkinson@gmail.com", "EU", "asd123",false,24);
//		jugador_f.setHistorialJugador("FFA", 4, true, false);
//		jugador_f.setHistorialJugador("FFA", 5, true, false);
//		jugador_f.setHistorialJugador("FFA", 6, true, false);
//		jugador_f.setHistorialJugador("FFA", 3, true, false);
//		jugador_f.setHistorialJugador("FFA", 6, true, false);
//		Jugador jugador_g = new Jugador("BaBySHARK", "ratkid_2006@gmail.com", "NA", "asd123",false,2);
//		jugador_g.setHistorialJugador("2P", 0, true, false);
//		jugador_g.setHistorialJugador("Big War", 2, false, false);
//		Jugador jugador_h = new Jugador("wintersoldier", "alexei89@hotmail.com", "EU", "asd123",false,0);
//		Jugador jugador_i = new Jugador("SENSEI2", "izi_pizi@gmail.com", "EU","asd123",false,2);
//		jugador_i.setHistorialJugador("FFA", 2, false, false);
//		Jugador jugador_j = new Jugador("oO_ROX_Oo", "mirage99@gmail.com", "NA", "asd123",false,6);
//		jugador_j.setHistorialJugador("FFA", 3, true, true);
//		jugador_j.setHistorialJugador("Big War", 3, true, false);
//		Jugador jugador_k = new Jugador("Stalin_revival", "alexander.godunov@gmail.ru", "EU", "asd123",false,14);
//		jugador_k.setHistorialJugador("2P", 4, true, false);
//		jugador_k.setHistorialJugador("FFA", 6, false, false);
//		jugador_k.setHistorialJugador("2P", 4, true, true);
//		Jugador jugador_l = new Jugador("DaNiStOnE", "ryan_dunn@gmail.com", "NA", "asd123",false,17);
//		jugador_l.setHistorialJugador("2P", 2, true, true);
//		jugador_l.setHistorialJugador("FFA", 6, true, false);
//		jugador_l.setHistorialJugador("FFA", 6, true, false);
//		jugador_l.setHistorialJugador("2P", 3, true, false);
//		Jugador jugador_m = new Jugador("PacoDeadShot", "paco_del_corral@gmail.com", "EU", "asd123",false,10);
//		jugador_m.setHistorialJugador("Big War", 4, true, false);
//		jugador_m.setHistorialJugador("2P", 0, true, true);
//		jugador_m.setHistorialJugador("FFA", 3, false, true);
//		jugador_m.setHistorialJugador("Big War", 3, true, false);
//		Jugador jugador_n = new Jugador("EL_MARIACHI", "arturo_g@gmail.com", "NA", "asd123",false,8);
//		jugador_n.setHistorialJugador("Big War", 4, true, false);
//		jugador_n.setHistorialJugador("2P", 4, false, false);
//		Jugador jugador_o = new Jugador("SpiderSense", "tom.saviour@gmail.com", "NA", "asd123",false,7);
//		jugador_o.setHistorialJugador("FFA", 4, true, false);
//		jugador_o.setHistorialJugador("FFA", 3, false, true);
//		JuegoUnico.setJugador(jugador_a);
//		JuegoUnico.setJugador(jugador_b);
//		JuegoUnico.setJugador(jugador_c);
//		JuegoUnico.setJugador(jugador_d);
//		JuegoUnico.setJugador(jugador_e);
//		JuegoUnico.setJugador(jugador_f);
//		JuegoUnico.setJugador(jugador_g);
//		JuegoUnico.setJugador(jugador_h);
//		JuegoUnico.setJugador(jugador_i);
//		JuegoUnico.setJugador(jugador_j);
//		JuegoUnico.setJugador(jugador_k);
//		JuegoUnico.setJugador(jugador_l);
//		JuegoUnico.setJugador(jugador_m);
//		JuegoUnico.setJugador(jugador_n);
//		JuegoUnico.setJugador(jugador_o);

		//No! Idem a las regiones! Porqué carga desde código cuando debería cargar desde txt y luego desde el archivo serializado??? Mal!
		Servidor servidor_a = new Servidor("23asdasq2", false);
		Servidor servidor_b = new Servidor("23asdasq3", false);
		Servidor servidor_c = new Servidor("233333", false);
		RegionB.setServidor(servidor_a);
		RegionB.setServidor(servidor_b);
		RegionA.setServidor(servidor_c);
		JuegoUnico.setRegion(RegionA);
		JuegoUnico.setRegion(RegionB);
		JuegoUnico.menu();	
	}
}
