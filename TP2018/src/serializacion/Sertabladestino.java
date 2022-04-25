package serializacion;

import Tabladestino.*;
import java.util.LinkedList;
import java.io.*;

public class Sertabladestino {
	
	public static void serializaparlugar(LinkedList<parlugares> lista){
		try{
			File f = new File("parlugares.ser");
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
	public static LinkedList<parlugares> deserializaparlugar(){
		LinkedList<parlugares> lista = new LinkedList<>();
		try{
			File f = new File("parlugares.ser");
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object stream = ois.readObject();
			lista = (LinkedList<parlugares>)stream;
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
