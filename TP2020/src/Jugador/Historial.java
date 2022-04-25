package Jugador;

import java.io.Serializable;

/**
 * Clase en la cual se cargan todo el hisorial del jugador
 * @author
 *
 */
//OK
//No implementar "setters" que sean necesarios. No siempre un atributo tiene que tener su getter/setter si no es necesario acceder y/o asignar su valor desde otras clases!
public class Historial implements Serializable {
	private static final long serialVersionUID = 1L;
	private String tipoPartida; //Utilizar "Enums", no String!!
	private Integer eliminaciones;
	private Boolean victoria;
	private Boolean fueraDeTiempo;
	//"tipoPartida" debería ser un Enum con los tipos de partida posibles y no un String
	public Historial(String tipoPartida, Integer eliminaciones, Boolean victoria, Boolean fueraDeTiempo) {
		this.tipoPartida = tipoPartida;
		this.eliminaciones = eliminaciones;
		this.victoria = victoria;
		this.fueraDeTiempo = fueraDeTiempo;
	}
	public String getTipoPartida() {
		return tipoPartida;
	}
	public void setTipoPartida(String tipoPartida) {
		this.tipoPartida = tipoPartida;
	}
	public Integer getEliminaciones() {
		return eliminaciones;
	}
	public void setEliminaciones(Integer eliminaciones) {
		this.eliminaciones = eliminaciones;
	}
	public Boolean getVictoria() {
		return victoria;
	}
	public void setVictoria(Boolean victoria) {
		this.victoria = victoria;
	}
	public Boolean getFueraDeTiempo() {
		return fueraDeTiempo;
	}
	public void setFueraDeTiempo(Boolean fueraDeTiempo) {
		this.fueraDeTiempo = fueraDeTiempo;
	}
}
