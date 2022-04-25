package Servidor;

import java.io.Serializable;

/**
 * Clase donde se declara el servidor con sus propiedades y sus estados
 * @author
 *
 */
//OK
public class Servidor implements Serializable {
	public Servidor(String _id, boolean partida) {
		this._id = _id;
		this.partida = partida;
	}

	private String _id;
	private boolean partida;
	private Boolean enPartida;

	public String getId() {
		return _id;
	}

	public void setId(String _id) {
		this._id = _id;
	}

	public boolean isPartida() {
		return partida;
	}
	public void setestado( Boolean estado) {
		partida = estado;
	}

	public Boolean getEstado() {
		return partida;
	}

	public void setPartida(boolean partida) {
		this.partida = partida;
	}
	public Boolean estaDisponible() {
		return !partida;
	}

	public void muestra() {
		System.out.println("    id region : " + _id); //Acoplamiento de interfaz
		if(partida)
			System.out.println("    ocupado"); //Acoplamiento de interfaz
		else 
			System.out.println("    libre"); //Acoplamiento de interfaz
	}
}