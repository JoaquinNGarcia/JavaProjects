package Archivos;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import Viajero.*;

public class archivoviajero {
	public static LinkedList<viajero> cargalistaviajero() throws FileNotFoundException{
		String nombre;
		int DNI;
		double millasnocanjeadas;
		LinkedList<viajero> listaviajero = new LinkedList<>();
		File f = new File("viajeros.txt");
		Scanner s = new Scanner(f);
		while(s.hasNextLine()){
			String linea = s.nextLine();
			Scanner sl = new Scanner(linea);
			sl.useDelimiter("\\s*-\\s*");
			nombre = sl.next();
			DNI = Integer.parseInt(sl.next());
			millasnocanjeadas = Double.parseDouble(sl.next());
			simple sim = new simple(nombre,DNI,millasnocanjeadas);
			listaviajero.add(sim);
			sl.close();
		}
		s.close();
		return listaviajero;
	}
}
