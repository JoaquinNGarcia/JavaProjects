package Archivos;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import Viaje.*;
import generadordemillas.*;

public class archivoviaje {
	static LinkedList<generadordemillas> cargalistagen(){
		String id, nom, des, origen, dest, tipo, adi;
		double factor;
		Est e;
		LinkedList<generadordemillas> lista = new LinkedList<>();
		viaje via = null;
		File f = new File("Viajegen.txt");
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
				origen = sl.next();
				dest = sl.next();
				tipo = sl.next();
				adi = sl.next();
				switch (tipo){
				case "Turista":{
					via = new turista(id, nom, des, factor, e, origen, dest);
					break;
				}
				case "Business":{
					via = new business(id, nom, des, factor, e, origen, dest);
					break;
				}
				case "Primera":{
					via = new primera(id, nom, des, factor, e, origen, dest);
					break;
				}
				}
				via.agregaadi(adi);
				lista.add(via);
				sl.close();
			}
			s.close();
		}
		catch(FileNotFoundException ex){
			ex.printStackTrace();
		}
		return lista;
	}
	
	public static LinkedList<viaje> cargalistacanj(){
		String id, nom, des, origen, dest, tipo, adi;
		double factor;
		Est e;
		LinkedList<viaje> lista = new LinkedList<>();
		viaje via = null;
		File f = new File("Viajecanj.txt");
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
				origen = sl.next();
				dest = sl.next();
				tipo = sl.next();
				adi = sl.next();
				switch (tipo){
				case "Turista":{
					via = new turista(id, nom, des, factor, e, origen, dest);
					break;
				}
				case "Business":{
					via = new business(id, nom, des, factor, e, origen, dest);
					break;
				}
				case "Primera":{
					via = new primera(id, nom, des, factor, e, origen, dest);
					break;
				}
				}
				via.agregaadi(adi);
				lista.add(via);
				sl.close();
			}
			s.close();
		}
		catch(FileNotFoundException ex){
			ex.printStackTrace();
		}
		return lista;
	}
}
