package serializacion;

import generadordemillas.generadordemillas;
import java.util.LinkedList;
import java.io.*;

public class Sergenerador {
	public static void serializa(LinkedList<generadordemillas> lista){
		try{
			File f = new File("generador.ser");
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
	public static LinkedList<generadordemillas> deserializa(){
		LinkedList<generadordemillas> lista = new LinkedList<>();
		try{
			File f = new File("generador.ser");
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object stream = ois.readObject();
			lista = (LinkedList<generadordemillas>)stream;
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