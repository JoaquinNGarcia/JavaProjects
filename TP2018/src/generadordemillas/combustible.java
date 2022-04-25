package generadordemillas;

import Viajero.viajero;
import reportes.estadisticas;

@SuppressWarnings("serial")
public class combustible extends generadordemillas{
	private double litros;
	public combustible(String i, String n, String d, double f, Est e){
		super (i,n,d,f, e);
		litros=0;
	}
	public double getLitros() {
		return litros;
	}
	public void setLitros(double litros) {
		this.litros = litros;
	}
	
	@Override
	public double gananciamillas(double valor, viajero via, estadisticas es){
		double millas=0;
		millas = valor/getFactor();
		via.setMillasComb(millas);
		es.setAcumcom(es.getAcumcom() + millas);
		es.setContcom(es.getContcom() + 1);
		return millas;
	}
	
	@Override
	public String toString(){
		return (super.toString() + "Litros: " + litros);
	}
	
}
