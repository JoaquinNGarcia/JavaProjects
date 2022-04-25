package Tabladestino;

import java.io.Serializable;

public class parlugares implements Serializable{
	private String origen, destino;
	private double ganancia, costo;
	
	public parlugares(double g, double c, String o, String d){
		ganancia=g;
		costo=c;
		origen=o;
		destino=d;
	}
	
	
	
	public String getOrigen() {
		return origen;
	}



	public void setOrigen(String origen) {
		this.origen = origen;
	}



	public String getDestino() {
		return destino;
	}



	public void setDestino(String destino) {
		this.destino = destino;
	}



	public double getGanancia() {
		return ganancia;
	}



	public void setGanancia(double ganancia) {
		this.ganancia = ganancia;
	}



	public double getCosto() {
		return costo;
	}



	public void setCosto(double costo) {
		this.costo = costo;
	}



	@Override
	public String toString(){
		return (origen + " " + destino + " " + ganancia + " " + costo);
	}
}
