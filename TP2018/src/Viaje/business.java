package Viaje;

import java.util.Scanner;
import Tabladestino.tabladestino;
import Viajero.viajero;
import Tabladestino.ExceptionNoHayDestino;
import generadordemillas.Est;
import reportes.estadisticas;

public class business extends viaje{
	adicionales vec[];
	
	public business(String i, String n, String d, double f, Est e, String o, String des){
		super(i,n,d,f,e,o,des);
		vec= new adicionales[2];
	}
	
	@Override
	public void agregaadi(String adi){
		String nom;
		double cost;
		int i=0;
		Scanner s = new Scanner(adi);
		s.useDelimiter("\\s*,\\s*");
		nom = s.next();
		while (!nom.equals("/")){
			cost = Double.parseDouble(s.next());
			vec[i] = new adicionales(nom, cost);
			i++;
			nom = s.next();
		}
		s.close();
	}
	
	@Override
	public String veradi(){
		int i=0;
		String adis = "";
		while ((i < 2) && (vec[i] != null)){
			adis = (adis + vec[i].toString() + ", ");
			i++;
		}
		return adis;
	}
	
	@Override
	public double gananciamillas(double valor, viajero via, estadisticas es){
		double millas=0;
		try {
			millas = tabladestino.BuscaGanancia(getOrigen(),getDestino()) * getFactor() * 1.5;
			via.setMillasViaje(millas);
		} catch (ExceptionNoHayDestino e) {
			e.printStackTrace();
		}
		tabladestino.sumades(getDestino());
		es.setAcumvia(es.getAcumvia() + millas);
		es.setContvia(es.getContvia() + 1);
		return millas;
	}
	
	@Override
	public double SumaAdicionales(){
		int i=0;
		double suma=0;
		while(i<2 && vec[i] != null){
			suma += vec[i].getCostomillas();
			i++;
		}
		return suma;
	}
	
	@Override
	public double costmillas(){
		double millas=0;
		try {
			millas = tabladestino.BuscaCosto(getOrigen(),getDestino()) * getFactor() * 2 + SumaAdicionales();
		} catch (ExceptionNoHayDestino e) {
			e.printStackTrace();
		}
		return millas;
	}
	
	@Override
	public String toString(){
		return (super.toString() + this.veradi());
	}
}