package Producto;

import java.io.Serializable;
import Viajero.viajero;
import Canjeable.canjeable;

@SuppressWarnings("serial")
public class producto implements Serializable, canjeable{
	private String nomb, desc;
	private double costomillas;
	
	public producto(String n, String d, double c){
		nomb = n;
		desc = d;
		costomillas = c;
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

	public double getCostomillas() {
		return costomillas;
	}

	public void setCostomillas(double costomillas) {
		this.costomillas = costomillas;
	}
	
	public boolean Canjear(viajero pasajero){
		double desc = (getCostomillas() * (pasajero.getDescuento() / 100));
		double total = (getCostomillas() - desc);
		if(pasajero.getMillasdisp() >= total){
			pasajero.setMillasdisp(pasajero.getMillasdisp() - total);
			pasajero.DescAcumulable(getCostomillas());
			pasajero.agregaprod(this);
			pasajero.setMillascanjeadas(pasajero.getMillascanjeadas() + getCostomillas());
			return true;
		}
		else
			return false;

	}
	
	@Override
	public String toString(){
		return (nomb + "   " + desc + "   " + costomillas);
	}
}
