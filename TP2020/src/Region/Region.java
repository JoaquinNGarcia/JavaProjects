package Region;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import Servidor.Servidor;

/**
 * Clase donde se defina la region y se manejan datos como el estado del servidor y sus cambios
 * @author
 *
 */
//No atar la lógica del sistema al lote de datos!
//Evitar getters/setters innecesarios...evitar la costumbre de hacer los getters y los setters de todos los atributos, únicamente hacerlos de los atributos que se vayan a leer/modificar desde otras clases!
public class Region implements Serializable {
	private String  regionName;
	private String descripcion;
	private ArrayList<Servidor> servidores;

	public Region(String name, String descripcion) {
		this.regionName = name;
		this.descripcion = descripcion;
		 
		this.servidores = new ArrayList<Servidor>();
	}

	public ArrayList<Servidor> getServidor() {
		return servidores;
	}

	public void setServidor(Servidor listServidor) {
		this.servidores.add(listServidor);
	}
	
	public void setServidorName(String name) {
		this.regionName = name;
	}
	
	public String getRegionKey() {
		return regionName;
	}
	
	public String getRegionDes() {
		return descripcion;
	}
	public Boolean servidorDisponible(){
		int disponibles = 0;
		for (Servidor s: servidores){
			if(s.estaDisponible())
				disponibles++;
		}
		return disponibles > 0;
	}
	
	//Este tipo de métodos es muy poco flexible! Se pueden tener N servidores por Region, está muy mal atar la lógica a los datos de un lote!
	//Debería, en todo caso, pasar un Servidor directamente para cambiar el estado del Servidor de la lista que, mediante Equals, sea igual al servidor por parámetro!
	public void cambiarEstadoPrimerServer(Boolean estado){
		Boolean listo=false;
		for (Servidor s: servidores){
			if(s.getEstado()!=estado &&  listo) {
				listo=true;
				s.setestado(estado);	
			}
		}
	}
	
	
	
	public void mostrar() {
		System.out.println("   Servidor " + regionName); //Acoplamiento de interfaz
		for (Servidor p: servidores){
			p.muestra();
		}
	}
	
}
