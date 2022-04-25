package serializacion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import generadordemillas.generadordemillas;
import reportes.estadisticas;

public class serestadisticas {
	public static void serializa(estadisticas es){
		try{
			File f = new File("estadisticas.ser");
			if (!f.exists())
				f.createNewFile();
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(es);
			oos.close();
		}
		catch(IOException e){
			System.out.println("Error de IO en la serialización");
			e.printStackTrace();
		}
	}
	
	public static estadisticas deserializa(){
		estadisticas es = new estadisticas();
		try{
			File f = new File("estadisticas.ser");
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object stream = ois.readObject();
			es = (estadisticas)stream;
			ois.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Archivo no encontrado");
			e.printStackTrace();
		}
		catch(IOException e){
			System.out.println("Error de IO en la deserializacion");
		}
		catch(ClassNotFoundException e){
			System.out.println("No se encuentra la clase");
		}
		return es;
	}
}
