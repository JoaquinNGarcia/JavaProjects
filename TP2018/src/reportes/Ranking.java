package reportes;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import Viajero.viajero;

import java.lang.*;
import obtenciondemillas.Obtenciondemillas;

public class Ranking {
	
	@SuppressWarnings("unchecked")
	public static String RankingMillas(LinkedList<viajero> listaviajero){
		viajero via;
		int i=1;
		String linea = "";
		LinkedList<viajero> viajeros = (LinkedList<viajero>)listaviajero.clone();
		Collections.sort(viajeros, new comparaMillas());
		Iterator<viajero> it = viajeros.iterator();
		while (it.hasNext()){
			via = it.next();
			linea = linea + i + "_  ";
			linea = (linea + via.toString() + "\n\n");
			i++;
		}
		if (linea != "")
			return linea;
		else
			return "No hay viajeros";
	}
	
	static class comparaMillas implements Comparator<viajero>{
		@Override
		public int compare(viajero v1, viajero v2){
			int r = Double.compare(v1.getMillasobtenidas(), v2.getMillasobtenidas());
			if (r <= 0)
				return 1;
			else
				return -1;
		}
	}
}
