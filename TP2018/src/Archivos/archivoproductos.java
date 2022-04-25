package Archivos;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import Producto.*;

public class archivoproductos {
	public static LinkedList<producto> cargalistaprod(){
		String nom, des;
		double costo;
		LinkedList<producto> lista = new LinkedList<>();
		File f = new File("Productos.txt");
		Scanner s;
		try{
			s = new Scanner(f);
				while (s.hasNextLine()){
					String linea = s.nextLine();
					Scanner sl = new Scanner(linea);
					sl.useDelimiter("\\s*-\\s*");
					nom = sl.next();
					des = sl.next();
					costo = Double.parseDouble(sl.next());
					producto prod = new producto(nom, des, costo);
					lista.add(prod);
					sl.close();
			}
			s.close();
		}
		catch(FileNotFoundException e){
			System.out.println("No se encontró el archivo");
		}
		return lista;
	}
}
