package serializacion;

import Viaje.viaje;
import java.util.LinkedList;
import java.io.*;

public class Serviajecan {
	
	public static void serializa(LinkedList<viaje> lista){
		try{
			File f = new File("viajes.ser");
			if (!f.exists())
				f.createNewFile();
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(lista);
			oos.close();
		}
		catch(IOException e){
			System.out.println("Error de IO en la serialización");
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static LinkedList<viaje> deserializa(){
		LinkedList<viaje> lista = new LinkedList<>();
		try{
			File f = new File("viajes.ser");
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object stream = ois.readObject();
			lista = (LinkedList<viaje>)stream;
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
		return lista;
	}
	
}
