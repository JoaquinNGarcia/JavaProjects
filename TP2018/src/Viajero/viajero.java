package Viajero;
import java.util.LinkedList;
import generadordemillas.generadordemillas;
import Viaje.viaje;
import Producto.producto;

import java.io.PrintStream;
import java.io.Serializable;
import java.text.DecimalFormat;

public abstract class viajero implements Serializable{
	private String nom;
	private int dni, descuento;
	private LinkedList<generadordemillas> listagenerador = new LinkedList<>();
	private LinkedList<viaje> listaviaje = new LinkedList<>();
	private LinkedList<producto> listaprod = new LinkedList<>();
	private double millasdisp, millascanjeadas, millasobtenidas, millasComb, millasHotel, millasCons, millasViaje;
	
	public viajero(String n, int d, double mnc){
		nom = n;
		dni = d;
		millasdisp = mnc;
		descuento = 0; 
		millasobtenidas = 0; 
		millasComb = 0; 
		millasHotel = 0; 
		millasCons = 0;
		millasViaje = 0;
	}
	
	public void setDescuento(int descuento) {
		this.descuento += descuento;
		if(this.descuento > 30)
			this.descuento = 30;
	}

	public abstract void DescAcumulable(double millas);
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public double getMillasdisp() {
		return millasdisp;
	}

	public void setMillasdisp(double millasdisp) {
		this.millasdisp = millasdisp;
	}

	public int getDescuento() {
		return descuento;
	}
	
	public double getMillascanjeadas() {
		return millascanjeadas;
	}

	public void setMillascanjeadas(double millascanjeadas) {
		this.millascanjeadas = millascanjeadas;
	}

	public LinkedList<generadordemillas> getListagenerador() {
		return listagenerador;
	}

	public void setListagenerador(LinkedList<generadordemillas> listagenerador) {
		this.listagenerador = listagenerador;
	}

	public LinkedList<viaje> getListaviaje() {
		return listaviaje;
	}

	public void setListaviaje(LinkedList<viaje> listaviaje) {
		this.listaviaje = listaviaje;
	}

	public LinkedList<producto> getListaprod() {
		return listaprod;
	}

	public void setListaprod(LinkedList<producto> listaprod) {
		this.listaprod = listaprod;
	}

	public void agregaprod(producto prod){
		listaprod.add(prod);
	}
	
	public void agregaviaje(viaje via){
		listaviaje.add(via);
	}
	
	public void setMillasobtenidas(double millasobtenidas) {
		this.millasobtenidas += millasobtenidas;
	}

	public double getMillasobtenidas() {
		return millasobtenidas;
	}
	
	public double getMillasComb() {
		return millasComb;
	}

	public void setMillasComb(double millasComb) {
		this.millasComb += millasComb;
	}

	public double getMillasHotel() {
		return millasHotel;
	}

	public void setMillasHotel(double millasHotel) {
		this.millasHotel += millasHotel;
	}

	public double getMillasCons() {
		return millasCons;
	}

	public void setMillasCons(double millasCons) {
		this.millasCons += millasCons;
	}

	public double getMillasViaje() {
		return millasViaje;
	}

	public void setMillasViaje(double millasViaje) {
		this.millasViaje += millasViaje;
	}

	@Override
	public String toString(){
		DecimalFormat df = new DecimalFormat("#.##");
		return ("Nombre: " + nom + "      DNI: " + dni + "      Disp: " + millasdisp + "     Obtenidas: " + millasobtenidas + "     MillasViaje: " + millasViaje + "     MillasHotel: " + df.format(millasHotel) + "     MillasComb: " + millasComb + "     MillasCons: " + millasCons);
	}
}
