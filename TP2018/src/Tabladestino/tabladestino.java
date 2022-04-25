package Tabladestino;
import java.util.LinkedList;

import javax.swing.DefaultListModel;

import java.util.Iterator;
import Archivos.ArchivoTablaDestino;
import serializacion.Sertabladestino;
import reportes.Reportelugar;

public final class tabladestino{
	private static LinkedList<parlugares> listparlugares = new LinkedList<>(); 
	private static LinkedList<lugar> listlugares = new LinkedList<>();
	private static boolean haylistapar = false;
	
	private tabladestino(){
	}

	public static boolean agrega(String o, String d, double c, double g){
		parlugares par;
		Iterator<parlugares> it = listparlugares.iterator();
		while (it.hasNext()){
			par = it.next();
			if ((par.getOrigen().equals(o)) && (par.getDestino().equals(d)))
				return false;
		}
		par = new parlugares(g, c, o, d);
		listparlugares.add(par);
		return true;
	}
	
	public static boolean elimina(String o, String d){
		parlugares par;
		Iterator<parlugares> it = listparlugares.iterator();
		while (it.hasNext()){
			par = it.next();
			if ((par.getOrigen().equals(o)) && par.getDestino().equals(d)){
				it.remove();
				return true;
			}
		}
		return false;
	}
	
	public static boolean modifica (String o, String d, double c, double g){
		parlugares par;
		Iterator<parlugares> it = listparlugares.iterator();
		while(it.hasNext()){
			par = it.next();
			if ((par.getOrigen().equals(o)) && par.getDestino().equals(d)){
				par.setCosto(c);
				par.setGanancia(g);
				return true;
			}
		}
		return false;
	}
	
	public static void muestralistapar(){
		Iterator<parlugares> it = listparlugares.iterator();
		while (it.hasNext()){ 
			System.out.println(it.next().toString() + "\n");
		}
	}
	
	public static void muestralistalug(){
		Iterator<lugar> it = listlugares.iterator();
		while (it.hasNext()){
			System.out.println(it.next().toString() + "\n");
		}
	}
	
	public static void cargalugares(){
		listlugares = ArchivoTablaDestino.cargalugares();
	}
	
	public static void cargapares(){
		listparlugares = ArchivoTablaDestino.cargaListaTabDes();
	}
	
	public static void serializar(){
		Sertabladestino.serializaparlugar(listparlugares);
	}
	
	public static void deserializar(){
		listparlugares = Sertabladestino.deserializaparlugar();
	}
	
	public static parlugares getpar(String ori, String des){
		parlugares par=null;
		boolean encontrado = false;
		Iterator<parlugares> it = listparlugares.iterator();
		while (it.hasNext() && !encontrado){
			par = it.next();
			if (par.getOrigen().equals(ori) && par.getDestino().equals(des))
				encontrado = true;
		}
		return par;
	}
	
	public static double BuscaGanancia(String origen, String destino) throws ExceptionNoHayDestino{
		Iterator<parlugares> it = listparlugares.iterator();
		boolean encontro = false;
		double ganancia=0;
		while(it.hasNext()){
			parlugares aux=it.next();
			if(origen.equals(aux.getOrigen()) && destino.equals(aux.getDestino())){
				encontro = true;
				ganancia = aux.getGanancia();
			}
		}
		if (encontro)
			return ganancia;
		else
			throw new ExceptionNoHayDestino("No existe el destino");
	}
	
	public static double BuscaCosto(String origen, String destino) throws ExceptionNoHayDestino{
		Iterator<parlugares> it = listparlugares.iterator();
		boolean encontro = false;
		double costo=0;
		while(it.hasNext()){
			parlugares aux=it.next();
			if(origen.equals(aux.getOrigen()) && destino.equals(aux.getDestino())){
				encontro = true;
				costo = aux.getCosto();
			}
		}
		if (encontro)
			return costo;
		else
			throw new ExceptionNoHayDestino("No existe el destino");
	}
	
	public static LinkedList<lugar> reportelugares(){
		return Reportelugar.listadolugar(listlugares);
	}
	
	public static void sumades(String lugar){
		lugar lug;
		boolean encontrado = false;
		Iterator<lugar> it = listlugares.iterator();
		while (it.hasNext() && !encontrado){
			lug = it.next();
			if (lug.getNomb().equals(lugar)){
				lug.setDesveceselegido(lug.getDesveceselegido() + 1);
				encontrado = true;
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static DefaultListModel generalistapar(){
		boolean alguno = false;
		DefaultListModel dlm = new DefaultListModel();
		Iterator<parlugares> it = listparlugares.iterator();
		parlugares par;
		while (it.hasNext()){
			par = it.next();
			dlm.addElement(par.getOrigen() + "-" + par.getDestino());
			alguno = true;
		}
		if (!alguno){
			dlm.addElement("No hay Ningun Par de lugares");
			haylistapar = false;
		}
		else
			haylistapar = true;
		return dlm;
	}

	public static boolean getHaylistapar() {
		return haylistapar;
	}
	
	
	
}