package Jugador;

import java.io.Serializable;
import java.util.ArrayList;
import Servidor.Servidor;

/**
 * Clase donde se obtienen y manejan los datos del jugador, sus logros, historial, puntos, etc.
 * @author
 *
 */
//Bajo ningún punto se deberían utilizar foreign-key (región) para la "relación" de clases entre un Jugador y su Region...en estos casos SIEMPRE se debe utilizar composición, es decir, "region" debe ser de tipo "Region" (un "Jugador" tiene una "Region")!
//Evitar getters/setters innecesarios!
public class Jugador implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nick;
	private String mail;
	private String password;
	private String region; //No!!! "region" debería ser de tipo "Region", no de tipo String! No trabajar con foreign-key, trabajar con composición de clases!!!!
	private Boolean enPartida;
	private Integer equipo;
	private Integer puntosGlobal;
	private ArrayList<Historial> historialJugador;
	private int[] logrosDesbloqueados; //No, porqué sería un arreglo de enteros??? Idem a "region", no trabajar con claves, trabajar con composición!!

	//El parámetro "region" debería ser de tipo "Region", no un String!
	public Jugador(String nick, String mail, String region, String password, Boolean enPartida, Integer puntosGlobal) {
		this.nick = nick;
		this.mail = mail;
		this.password = password;
		this.region = region;
		this.enPartida = enPartida;
		this.puntosGlobal = puntosGlobal;
		this.historialJugador = new ArrayList<Historial>();
		this.logrosDesbloqueados = new int[] { 0, 0, 0, 0, 0 };
	}

	public String getNick() {
		return nick;
	}
	
	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public boolean getEnPartida() {
		return enPartida;
	}

	public void setEnPartida(Boolean enPartida) {
		this.enPartida = enPartida;
	}

	public Integer getEquipo() {
		return equipo;
	}

	public void setEquipo(Integer equipo) {
		this.equipo = equipo;
	}

	public Integer getPuntosGlobal() {
		return puntosGlobal;
	}

	public Boolean logroDesbloqueado(Integer indice) {
		return logrosDesbloqueados[indice] == 1;
	}

	public void desbloquearLogro(Integer indice) {
		logrosDesbloqueados[indice] = 1;
	}

	public void setPuntosGlobal(Integer puntosGlobal) {
		this.puntosGlobal += puntosGlobal;
	}

	public void setHistorialJugador(String tipoPartida, Integer eliminaciones, Boolean victoria, Boolean fueraDeTiempo) {
		historialJugador.add(new Historial(tipoPartida, eliminaciones, victoria, fueraDeTiempo));
	}

	public void getLogrosJugadorText() {
		String[] descripcion = { "Eliminar 5 jugadores", "Eliminar 5 jugadores en una misma partida",
				"Ganar 5 partidas seguidas", "Ganar una partida BigWar", "Ganar una partida FFA" };
		String estadoLogro = "";
		for (int i = 0; i < logrosDesbloqueados.length; i++) {
			estadoLogro = "BLOQUEADO";
			if (logrosDesbloqueados[i] == 1)
				estadoLogro = "DESBLOQUEADO";
			System.out.println(descripcion[i] + " [[ " + estadoLogro + " ]]");
		}
	}

	public void getHistorialJugadorText() {
		System.out.println("Tipo\tEliminaciones\t\tResultado\tFinalizacion");
		String resultado = "", finalizacion = "", tipo;
		int eliminacion;
		for (int i = 0; i < historialJugador.size(); i++) {
			eliminacion = historialJugador.get(i).getEliminaciones();
			finalizacion = historialJugador.get(i).getFueraDeTiempo() ? "Tiempo" : "Eliminacion";
			resultado = historialJugador.get(i).getVictoria() ? "Ganador   " : "Derrotado";
			tipo = historialJugador.get(i).getTipoPartida();
			System.out.println(tipo + "\t\t" + eliminacion + "\t\t" + resultado + "\t" + finalizacion);
		}
	}

	public ArrayList<Historial> getHistorialJugador() {
		return historialJugador;
	}
}
