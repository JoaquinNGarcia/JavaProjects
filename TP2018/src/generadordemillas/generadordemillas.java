package generadordemillas;

import java.io.Serializable;

import Viajero.viajero;
import reportes.estadisticas;

@SuppressWarnings("serial")
public abstract class generadordemillas implements Serializable{
	private String id;
	private String nomb;
	private String desc;
	private Est estado;
	private double factor;

	public generadordemillas(String i, String n, String d, double f, Est e){
		id = i;
		nomb = n;
		desc = d;
		factor = f;
		estado = e;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNomb() {
		return nomb;
	}

	public void setNomb(String nomb) {
		this.nomb = nomb;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Est getEstado() {
		return estado;
	}

	public void setEstado(Est estado) {
		this.estado = estado;
	}

	public double getFactor() {
		return factor;
	}

	public void setFactor(double factor) {
		this.factor = factor;
	}
	
	public abstract double gananciamillas(double valor, viajero via, estadisticas es);
	
	@Override
	public String toString(){
		return (id + " " + nomb  + " " + desc + " " + estado.toString() + " " + factor);
	}
}
