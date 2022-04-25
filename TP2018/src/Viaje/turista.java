package Viaje;

import generadordemillas.Est;
import reportes.estadisticas;
import Tabladestino.ExceptionNoHayDestino;
import Tabladestino.tabladestino;
import Viajero.viajero;

@SuppressWarnings("serial")
public class turista extends viaje{
	public turista(String i, String n, String d, double f, Est e, String o, String des){
		super(i,n,d,f,e,o,des);
	}
	
	@Override
	public double gananciamillas(double valor, viajero via, estadisticas es){
		double millas=0;
		try {
			millas = (tabladestino.BuscaGanancia(getOrigen(),getDestino()) * getFactor());
			via.setMillasViaje(millas);
		} catch (ExceptionNoHayDestino e) {
			e.printStackTrace();
		}
		tabladestino.sumades(getDestino());
		es.setAcumvia(es.getAcumvia() + millas);
		es.setContvia(es.getContvia() + 1);
		return millas;
	}
	
	
}
