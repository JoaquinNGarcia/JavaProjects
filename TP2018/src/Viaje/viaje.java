package Viaje;

import generadordemillas.Est;
import generadordemillas.generadordemillas;
import Tabladestino.ExceptionNoHayDestino;
import Tabladestino.tabladestino;
import Viajero.viajero;
import Canjeable.canjeable;

@SuppressWarnings("serial")
public abstract class viaje extends generadordemillas implements canjeable{
	private String origen, destino;
	public viaje(String i, String n, String d, double f, Est e, String o, String des){
		super(i,n,d,f, e);
		origen = o;
		destino = des;
	}
	
	public void agregaadi(String adi){}
	
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
	
	public double costmillas(){
		try {
			return (tabladestino.BuscaCosto(getOrigen(), getDestino()))*this.getFactor();
		} catch (ExceptionNoHayDestino e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public double SumaAdicionales(){
		return 0;
	}
	
	public boolean Canjear(viajero pasajero){
		double descuento=costmillas()*(pasajero.getDescuento()/100);
		double costo=(costmillas()-descuento);
		if(pasajero.getMillasdisp() >= costo){
			pasajero.setMillasdisp(pasajero.getMillasdisp()-costo);
			pasajero.DescAcumulable(costmillas());
			pasajero.agregaviaje(this);
			pasajero.setMillascanjeadas(pasajero.getMillascanjeadas()+costmillas());
			tabladestino.sumades(destino);
			return true;
		}
		else
			return false;
	}
	
	public String veradi(){
		return "";
	}
	
	@Override
	public String toString(){
		return (super.toString() + " " + origen + " " + destino);
	}
}

