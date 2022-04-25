package Archivos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import Tabladestino.*;


public class ArchivoTablaDestino {
	
	public static LinkedList<parlugares> cargaListaTabDes(){
		String origen, destino;
		double costo,ganancia;
		LinkedList<parlugares> lista = new LinkedList<>();
		File f = new File("TablaDestinos.txt");
		Scanner s;
		try{
			s = new Scanner(f);
			while (s.hasNextLine()){
				String linea = s.nextLine();
				Scanner sl = new Scanner(linea);
				sl.useDelimiter("\\s*-\\s*");
				origen = sl.next();
				destino = sl.next();
				costo = Double.parseDouble(sl.next());
				ganancia = Double.parseDouble(sl.next());
				parlugares dest = new parlugares(ganancia, costo, origen, destino);
				lista.add(dest);
				sl.close();
			}
			s.close();
		}
		catch(FileNotFoundException e){
			System.out.println("El archivo no fue encotrado");
		}
		return lista;
	}
	
	public static LinkedList<lugar> cargalugares(){
		String nom, des;
		LinkedList<lugar> lista = new LinkedList<>();
		File f = new File("Lugares.txt");
		Scanner s;
		try{
			s = new Scanner(f);
			while (s.hasNextLine()){
				String linea = s.nextLine();
				Scanner sl = new Scanner(linea);
				sl.useDelimiter("\\s*-\\s*");
				nom = sl.next();
				des = sl.next();
				lugar lug = new lugar(nom, des);
				lista.add(lug);
				sl.close();
			}
			s.close();
		}
		catch(FileNotFoundException e){
			System.out.println("El archivo no fue encontrado");
		}
		return lista;
	}
}
