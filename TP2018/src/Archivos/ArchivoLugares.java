package Archivos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import Tabladestino.lugar;

public class ArchivoLugares {
	public static LinkedList<lugar> CargaListaLugar() throws FileNotFoundException{
		String nombre, descripcion;
		LinkedList<lugar> lista = new LinkedList<>();
		File f = new File("Lugares.txt");
		Scanner s;
		s = new Scanner(f);
			while (s.hasNextLine()){
				String linea = s.nextLine();
				Scanner sl = new Scanner(linea);
				sl.useDelimiter("\\s*-\\s*");
				nombre = sl.next();
				descripcion = sl.next();
				lugar lug = new lugar(nombre, descripcion);
				lista.add(lug);
				sl.close();
			}
		s.close();
		return lista;
	}
}
