package Archivos;

import Archivos.archivoviaje;
import generadordemillas.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Archivogeneradores {
	
	private static void cargaconsumo(LinkedList<generadordemillas> lista){
		String id, nom, des;
		double factor;
		Est e;
		File f = new File("genconsumo.txt");
		Scanner s;
		try{
			s = new Scanner(f);
			while (s.hasNextLine()){
				String linea = s.nextLine();
				Scanner sl = new Scanner(linea);
				sl.useDelimiter("\\s*-\\s*");
				id = sl.next();
				nom = sl.next();
				des = sl.next();
				e = Est.valueOf(sl.next());
				factor = Double.parseDouble(sl.next());
				consumo con = new consumo(id, nom, des, factor, e);
				lista.add(con);
				sl.close();
			}
			s.close();
		}
		catch (FileNotFoundException ex){
			ex.printStackTrace();
		}
	}
	
	private static void cargacomb(LinkedList<generadordemillas> lista){
		String id, nom, des;
		double factor;
		Est e;
		File f = new File("gencombustible.txt");
		Scanner s;
		try{
			s = new Scanner(f);
			while (s.hasNextLine()){
				String linea = s.nextLine();
				Scanner sl = new Scanner(linea);
				sl.useDelimiter("\\s*-\\s*");
				id = sl.next();
				nom = sl.next();
				des = sl.next();
				e = Est.valueOf(sl.next());
				factor = Double.parseDouble(sl.next());
				combustible com = new combustible(id, nom, des, factor, e);
				lista.add(com);
				sl.close();
			}
			s.close();
		}
		catch (FileNotFoundException ex){
			ex.printStackTrace();
		}
	}
	
	private static void cargahotel(LinkedList<generadordemillas> lista){
		String id, nom, des;
		double factor, precionoche;
		int cat, cant;
		Est e;
		File f = new File("genhotel.txt");
		Scanner s;
		try{
			s = new Scanner(f);
			while (s.hasNextLine()){
				String linea = s.nextLine();
				Scanner sl = new Scanner(linea);
				sl.useDelimiter("\\s*-\\s*");
				id = sl.next();
				nom = sl.next();
				des = sl.next();
				e = Est.valueOf(sl.next());
				factor = Double.parseDouble(sl.next());
				cat = Integer.parseInt(sl.next());
				precionoche = Double.parseDouble(sl.next());
				cant = Integer.parseInt(sl.next());
				hotel hot = new hotel(id, nom, des, factor, e, cat, precionoche, cant);
				lista.add(hot);
				sl.close();
			}
			s.close();
		}
		catch (FileNotFoundException ex){
			ex.printStackTrace();
		}
	}
	
	public static LinkedList<generadordemillas> cargalista(){
		LinkedList<generadordemillas> lista = new LinkedList<>();
		lista = archivoviaje.cargalistagen();
		cargaconsumo(lista);
		cargacomb(lista);
		cargahotel(lista);
		return lista;
	}
}
