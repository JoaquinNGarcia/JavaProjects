package Sistemacanjemillas;
import java.util.LinkedList;

import javax.swing.DefaultListModel;

import Archivos.Archivogeneradores;
import Archivos.Obdemillas;
import Archivos.archivoproductos;
import Archivos.archivoviaje;
import Archivos.archivoviajero;
import generadordemillas.*;
import obtenciondemillas.Obtenciondemillas;
import reportes.Ranking;
import reportes.Reportelugar;
import reportes.estadisticas;
import serializacion.Sergenerador;
import serializacion.Serviajecan;
import serializacion.Serviajero;
import serializacion.serestadisticas;
import Viaje.viaje;
import Viajero.*;
import Tabladestino.*;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Iterator; 
import Producto.producto;

public class sistemacanjemillas {
	private LinkedList<generadordemillas> listgenerador = new LinkedList<>();
	private LinkedList<viaje> listviajecan = new LinkedList<>();
	private LinkedList<producto> listprod = new LinkedList<>();
	private LinkedList<viajero> listaviajero = new LinkedList<>();
	private static sistemacanjemillas sis;
	private estadisticas es = new estadisticas();
	 private boolean haylista1 = false;
	 private boolean haylista2 = false;
	 private boolean haylista3 = false;
	 private boolean haylistagen = false;
	 private boolean haylistavia = false;
	
	private sistemacanjemillas(){}
	
	public static sistemacanjemillas creasistema(){
		if (sis == null)
			sis = new sistemacanjemillas();
		return sis;
	}
	
	public void serializartodo(){
		Sergenerador.serializa(listgenerador);
		Serviajecan.serializa(listviajecan);
		Serviajero.serializa(listaviajero);
		serestadisticas.serializa(es);
		tabladestino.serializar();
	}
	
	public void deserializartodo(){
		listgenerador = Sergenerador.deserializa();
		listviajecan = Serviajecan.deserializa();
		listaviajero = Serviajero.deserializa();
		es = serestadisticas.deserializa();
		tabladestino.deserializar();
	}
	
	
	public void registramillas(){
		LinkedList<Obtenciondemillas> lista = Obdemillas.cargaob();
		Iterator<Obtenciondemillas> listaob = lista.iterator();
		Obtenciondemillas ob;
		while (listaob.hasNext()){
			ob=listaob.next();
			boolean idencontrado = false;
			boolean dniencontrado = false;
			int dni=ob.getDni();
			String id = ob.getId();
			double valor = ob.getValor();
			Iterator<generadordemillas> it = listgenerador.iterator();
			Iterator<viajero> iter = listaviajero.iterator();
			double ganancia = 0;
			generadordemillas gen;
			viajero via;
			while (it.hasNext() && !idencontrado){
				gen = it.next();
				if (id.equals(gen.getId())){
					if (gen.getEstado().equals(Est.Disponible)){
						while (iter.hasNext() && !dniencontrado){
							via = iter.next();
							if (dni == via.getDni()){
								ganancia = gen.gananciamillas(valor,via, es);
								via.setMillasobtenidas(ganancia);
								via.setMillasdisp(via.getMillasdisp() + ganancia);
								dniencontrado = true;
							}
						}
					}
					else
						ganancia = 0;
					idencontrado = true;
				}
			}
		}
	}
	
	public void cargalistagen(){
		listgenerador = Archivogeneradores.cargalista();
	}
	
	public void cargalistaproductos(){
		listprod = archivoproductos.cargalistaprod();
	}
	
	public void cargaviajecanj(){
		listviajecan = archivoviaje.cargalistacanj();
	}
	
	public void muestragen(){
		Iterator<generadordemillas> it = listgenerador.iterator();
		while (it.hasNext())
			System.out.println(it.next().toString() + "\n");
	}
	
	public boolean creaviajegen(viaje v){
		Iterator<generadordemillas> it = listgenerador.iterator();
		boolean exito = true;
		while (it.hasNext() && exito){
			if (it.next().getId().equals(v.getId()))
				exito = false;
		}
		if (exito)
			listgenerador.add(v);
		return exito;
	}
	
	public boolean creaviajecan(viaje v){
		Iterator<viaje> it = listviajecan.iterator();
		boolean exito = true;
		while (it.hasNext() && exito){
			if (it.next().getId().equals(v.getId()))
				exito = false;
		}
		if (exito)
			listviajecan.add(v);
		return exito;
	}
	
	public boolean creaviajero(String nom, int dni){
		Iterator<viajero> it = listaviajero.iterator();
		while (it.hasNext()){
			if (it.next().getDni() == dni)
				return false;
		}
		simple s = new simple(nom, dni, 0);
		listaviajero.add(s);
		return true;
	}
	
	public boolean modifviajero(String nom, int dni){
		viajero v;
		Iterator<viajero> it = listaviajero.iterator();
		while (it.hasNext()){
			v = it.next();
			if (v.getDni() == dni){
				v.setNom(nom);
				return true;
			}
		}
		return false;
	}
	
	public boolean bajaviajero(int dni){
		viajero v;
		Iterator<viajero> it = listaviajero.iterator();
		while (it.hasNext()){
			v = it.next();
			if (v.getDni() == dni){
				it.remove();
				return true;
			}
		}
		return false;
	}
	
	public boolean altaparlugar (String ori, String des, double g, double c){
		return (tabladestino.agrega(ori, des, c, g));
	}
	
	public boolean bajaparlugar (String ori, String des){
		return (tabladestino.elimina(ori, des));
	}
	
	public boolean modificaparlugar (String ori, String des, double g, double c){
		return (tabladestino.modifica(ori, des, c, g));
	}
	
	public boolean modificaviaj (String id, String nomb, String desc, double fact, Est e){
		viaje via;
		boolean encontrado = false;
		Iterator<viaje> it = listviajecan.iterator();
		while (it.hasNext() && !encontrado){
			via = it.next();
			if (via.getId().equals(id)){
				via.setNomb(nomb);
				via.setDesc(desc);
				via.setFactor(fact);
				via.setEstado(e);
				encontrado = true;
			}
		}
		return encontrado;
	}
	
	public boolean modificaviajero (int dni, String nom){
		viajero via;
		boolean encontrado = false;
		Iterator<viajero> it = listaviajero.iterator();
		while (it.hasNext() && !encontrado){
			via = it.next();
			if (via.getDni() == dni){
				via.setNom(nom);
				encontrado = true;
			}
		}
		return encontrado;
	}
	
	public boolean creahotel(String id, String nomb, String desc, double fact, Est e, int c, double pn, int cn){
		Iterator<generadordemillas> it = listgenerador.iterator();
		while (it.hasNext()){
			if (it.next().getId() == id)
				return false;
		}
		hotel h = new hotel(id,nomb,desc,fact,e,c,pn,cn);
		listgenerador.add(h);
		return true;
	}
	
	public boolean creaconsumo(String id, String nomb, String desc, double fact, Est e){
		Iterator<generadordemillas> it = listgenerador.iterator();
		while (it.hasNext()){
			if (it.next().getId() == id)
				return false;
		}
		consumo cons = new consumo(id,nomb,desc,fact,e);
		listgenerador.add(cons);
		return true;
	}
	
	public boolean creacombustible(String id, String nomb, String desc, double fact, Est e){
		Iterator<generadordemillas> it = listgenerador.iterator();
		while (it.hasNext()){
			if (it.next().getId() == id)
				return false;
		}
		combustible comb = new combustible(id,nomb,desc,fact,e);
		listgenerador.add(comb);
		return true;
	}

	public boolean modifhotel(String id,String nomb, String desc, double fact,Est est, int cat, double pc, int cant){
		generadordemillas g;
		boolean encontrado = false;
		Iterator<generadordemillas> it = listgenerador.iterator();
		while (it.hasNext() && !encontrado){
			g = it.next();
			if(g.getId().equals(id)){
				g.setDesc(desc);
				g.setFactor(fact);
				g.setEstado(est);
				g.setNomb(nomb);
				if(g instanceof hotel){
					((hotel)g).setPrecionoche(pc);
					((hotel)g).setCat(cat);
					((hotel)g).setCantnoches(cant);	
				}
				encontrado = true;
			}
		}
		return encontrado;
	}
	
	public boolean modificagen(String id, String nom, String des, double fact, Est e){
		generadordemillas g;
		boolean encontrado = false;
		Iterator<generadordemillas> it = listgenerador.iterator();
		while (it.hasNext() && !encontrado){
			g = it.next();
			if (g.getId().equals(id)){
				encontrado = true;
				g.setDesc(des);
				g.setNomb(nom);
				g.setFactor(fact);
				g.setEstado(e);
			}
		}
		return encontrado;
	}
	
	public boolean bajagen(String id){
		generadordemillas gen;
		boolean encontrado = false;
		Iterator<generadordemillas> it = listgenerador.iterator();
		while (it.hasNext()){
			gen = it.next();
			if (gen.getId().equals(id)){
				it.remove();
				encontrado = true;
			}
		}
		return encontrado;
	}
	
	public boolean bajaviaje(String id){
		viaje via;
		boolean encontrado = false;
		Iterator<viaje> it = listviajecan.iterator();
		while (it.hasNext()){
			via = it.next();
			if (via.getId().equals(id)){
				it.remove();
				encontrado = true;
			}
		}
		return encontrado;
	}
	
	public boolean canjearproducto(int dni, String nom){
		boolean b=false;
		viajero via;
		producto prod;
		Iterator<viajero> it = listaviajero.iterator();
		via = it.next();
		while (via.getDni() != dni)
				via =it.next();
		Iterator<producto> it2 = listprod.iterator();
		prod = it2.next();
		while (!(prod.getNomb().equals(nom)))
			prod = it2.next();
		if(prod.Canjear(via)){
			it2.remove();
			b = true;
		}
		return b;
	}
	
	public boolean CanjeaViaje(int DNI, String id){
		boolean b=false;
		viaje via;
		viajero pasajero;
			Iterator<viaje> it = listviajecan.iterator();
			via= it.next();
			while(via.getId() != id)
				via = it.next();
			Iterator<viajero> it2 = listaviajero.iterator();
			pasajero= it2.next();
			while(pasajero.getDni() != DNI)
				pasajero = it2.next();
		if(via.Canjear(pasajero)){
			it.remove();
			b=true;
		}
		return b;
	}
	
	public boolean cambiatipo(viajero via){
		boolean b = false;
		if (via.getMillascanjeadas() > 10000 && !(via instanceof frecuente)){
			b = true;
			viajero aux;
			frecuente f = new frecuente(via.getNom(), via.getDni(), via.getMillasdisp());
			f.setDescuento(via.getDescuento());
			f.setListagenerador(via.getListagenerador());
			f.setListaprod(via.getListaprod());
			f.setListaviaje(via.getListaviaje());
			Iterator<viajero> it = listaviajero.iterator();
			aux = it.next();
			while (aux != via)
				aux = it.next();
			it.remove();
			listaviajero.add(f);
		}
		return b;
	}
	
	public void muestraviajero(){
		Iterator<viajero> it = listaviajero.iterator();
		while (it.hasNext())
			System.out.println(it.next().toString());
	}
	
	public String ranking(){
		return Ranking.RankingMillas(listaviajero);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DefaultListModel generalista1(){
		boolean alguno = false;
		DefaultListModel dlm = new DefaultListModel();
		DecimalFormat df = new DecimalFormat("#.##");
		Iterator<viajero> it = listaviajero.iterator();
		viajero via;
		while (it.hasNext()){
			via = it.next();
			dlm.addElement(via.getDni() + "   " + via.getNom() + "    " + df.format(via.getMillasdisp()));
			alguno = true;
		}
		if (!alguno){
			dlm.addElement("No hay Ningun Viajero");
			haylista1 = false;
		}
		else
			haylista1 = true;
		return dlm;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DefaultListModel generalista2(){
		boolean alguno = false;
		DefaultListModel dlm = new DefaultListModel();
		Iterator<viaje> it = listviajecan.iterator();
		viaje via;
		while (it.hasNext()){
			via = it.next();
			if (via.getEstado().equals(Est.Disponible)){
				dlm.addElement(via.getId() + "   " + via.getDesc() + "      " + via.getEstado() + "    " + via.veradi());
				alguno = true;
			}
		}
		if (!alguno){
			dlm.addElement("No hay Ningun Viaje");
			haylista2 = false;
		}
		else
			haylista2 = true;
		return dlm;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DefaultListModel generalistacanj(){
		boolean alguno = false;
		DefaultListModel dlm = new DefaultListModel();
		Iterator<viaje> it = listviajecan.iterator();
		viaje via;
		while (it.hasNext()){
			via = it.next();
			dlm.addElement(via.getId() + "   " + via.getDesc() + "      " + via.getEstado() + "    " + via.veradi());
			alguno = true;
		}
		if (!alguno){
			dlm.addElement("No hay Ningun Viaje");
			haylista2 = false;
		}
		else
			haylista2 = true;
		return dlm;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DefaultListModel generalista3(){
		boolean alguno = false;
		DefaultListModel dlm = new DefaultListModel();
		Iterator<producto> it = listprod.iterator();
		producto prod;
		while (it.hasNext()){
			prod = it.next();
			dlm.addElement(prod.getNomb() + "   " + prod.getDesc());
			alguno = true;
		}
		if (!alguno){
			dlm.addElement("No hay Ningun producto");
			haylista3 = false;
		}
		else
			haylista3 = true;
		return dlm;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DefaultListModel generalistagen(){
		boolean alguno = false;
		DefaultListModel dlm = new DefaultListModel();
		Iterator<generadordemillas> it = listgenerador.iterator();
		generadordemillas gen;
		while (it.hasNext()){
			gen = it.next();
			dlm.addElement(gen.getId() + "   " + gen.getNomb() + "   " + gen.getDesc() + "   " + gen.getEstado().toString());
			alguno = true;
		}
		if (!alguno){
			dlm.addElement("No hay Ningun Generador");
			haylistagen = false;
		}
		else
			haylistagen = true;
		return dlm;
	}
	
	
	
	public boolean Haylista1() {
		return haylista1;
	}

	public boolean Haylista2() {
		return haylista2;
	}
	
	public boolean Haylista3() {
		return haylista3;
	}
	
	

	public boolean getHaylistagen() {
		return haylistagen;
	}

	public boolean getHaylistavia() {
		return haylistavia;
	}

	public void cargalistaviajero() throws FileNotFoundException{
		listaviajero = archivoviajero.cargalistaviajero();
	}

	public estadisticas getEs() {
		return es;
	}
	
	public viajero getvia(String dni){
		viajero via = null;
		boolean encontrado = false;
		Iterator<viajero> it = listaviajero.iterator();
		while (it.hasNext() && !encontrado){
			via = it.next();
			if (via.getDni() == Integer.parseInt(dni))
				encontrado = true;
		}
		return via;
	}
	
	public viaje getviaje(String id){
		viaje via = null;
		boolean encontrado = false;
		Iterator<viaje> it = listviajecan.iterator();
		while (it.hasNext() && !encontrado){
			via = it.next();
			if (via.getId().equals(id))
				encontrado = true;
		}
		return via;
	}
	
	public producto getprod(String nom){
		producto prod = null;
		boolean encontrado = false;
		Iterator<producto> it = listprod.iterator();
		while (it.hasNext() && !encontrado){
			prod = it.next();
			if (prod.getNomb().equals(nom))
				encontrado = true;
		}
		return prod;
	}
	
	public generadordemillas getgen(String id){
		generadordemillas gen = null;
		boolean encontrado = false;
		Iterator<generadordemillas> it = listgenerador.iterator();
		while (it.hasNext() && !encontrado){
			gen = it.next();
			if (gen.getId().equals(id))
				encontrado = true;
		}
		return gen;
	}

}



