package Viaje;

import java.io.Serializable;

public class adicionales implements Serializable{ 
	private String nomb;
	private double costomillas;
	
	public adicionales(String n, double c){
		nomb=n;
		costomillas=c;
	}
	
	public String getNomb() {
		return nomb;
	}

	public double getCostomillas() {
		return costomillas;
	}

	public void setCostomillas(double costomillas) {
		this.costomillas = costomillas;
	}
	
	@Override
	public String toString(){
		return (nomb);
	}
}
