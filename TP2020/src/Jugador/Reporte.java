package Jugador;

/**
 * Clase donde se informan los reportes obtenidos para cada jugador de acuerdo a su historial y sus ultimas partidas.
 * @author joaqu
 *
 */
//No sería mejor que "Reporte" no sea únicamente una clase de datos y que contenga la funcionalidad para realizar cada reporte? 
//De esta forma evitamos más lógica en la clase "Juego" y cada clase se encarga de implementar su propia funcionalidad.
public class Reporte {
	private String nick;
	private Integer globalpuntos;
	private Integer ganadas;
	private Integer perdidas;
	private double efectividad;
	public Reporte(String nick, Integer globalpuntos, Integer ganadas, Integer perdidas, double efectividad) {
		this.nick = nick;
		this.globalpuntos = globalpuntos;
		this.ganadas = ganadas;
		this.perdidas = perdidas;
		this.efectividad = efectividad;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public Integer getGlobalpuntos() {
		return globalpuntos;
	}
	public void setGlobalpuntos(Integer globalpuntos) {
		this.globalpuntos = globalpuntos;
	}
	public Integer getGanadas() {
		return ganadas;
	}
	public void setGanadas(Integer ganadas) {
		this.ganadas = ganadas;
	}
	public Integer getPerdidas() {
		return perdidas;
	}
	public void setPerdidas(Integer perdidas) {
		this.perdidas = perdidas;
	}
	public double getEfectividad() {
		return efectividad;
	}
	public void setEfectividad(Float efectividad) {
		this.efectividad = efectividad;
	}
	
}
