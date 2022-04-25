package reportes;

import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import Tabladestino.*;

public class Reportelugar {
	
   /**
    * Utiliza la interfaz Comparator para generar una lista de lugares ordenada
    * alfabeticamente a partir de un clon de una lista de lugares que se 
    * recibe como parametro, luego la retorna
    *  
    * @param listlugar La lista de lugares a ordenar
    * @return lista ordenada
    * @see Comparator, Comparable, Lugar 
    */
	public static LinkedList<lugar> listadolugar(LinkedList<lugar> listlugar){
		lugar lug;
		LinkedList<lugar> lugares = (LinkedList<lugar>)listlugar.clone();
		Collections.sort(lugares, new comparalugar());
		Iterator<lugar> itlug = lugares.iterator();
		return lugares;
	}
	
	static class comparalugar implements Comparator<lugar>{
		@Override
		public int compare(lugar l1, lugar l2){
			int r = l1.getNomb().compareTo(l2.getNomb());
			if (r <= 0)
				return -1;
			else
				return 1;
		}
	}

}
