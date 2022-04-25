package com.company.gestionproj.project;

import com.company.gestionproj.team.Integrante;
import com.company.gestionproj.exception.RequerimientoYaTieneProyecto;
import com.company.gestionproj.tiempo.Estimable;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Scanner;

public class Proyecto implements Estimable {
    List<Requerimiento> requerimientos = new ArrayList<>();
    List<Integrante> integrantes = new ArrayList<Integrante>();

    String nombre;
    private Double presupuesto;
    private int presupuestoReal;
    private int totalHs;

    public Proyecto(String nombre, Double presu) { //Discriminar presupuesto por Area
        this.nombre = nombre ; this.presupuesto = presu;
    }

    @Override
    public Double estimate() {
        Double total = 0D;//es un double
        Iterator<Requerimiento> it = requerimientos.iterator();
        while(it.hasNext()){
            total += it.next().estimate();
        }
        return total;
    }

    public void muestraRequerimientos() {
        Iterator<Requerimiento> it = requerimientos.iterator();
        while(it.hasNext()){
            System.out.println(it.next());;
        }
    };
    public void muestraIntegrantes(){
        Iterator<Integrante> it = integrantes.iterator();
        while(it.hasNext()){
            System.out.println(it.next());;
        }
    };

    public void registraHoras(Proyecto this){
        Scanner sc = new Scanner(System.in);
        Iterator<Integrante> it = integrantes.iterator();
        while(it.hasNext()){
            Integrante person = it.next();
            System.out.println(person.getNombre() + ": ");
            //Acumula en horas trabajadas
            person.sumaHoras(sc.nextDouble(), this);
        }

        //Todo Si aca cierro el scanner, no me deja ejecutar el siguiente read. Cuando quiere hacer el sc.nextInt(),
        //Todo para los integrantes del proyecto B me tira la excepcion NoSuchElementException
    };

    public void calculaPresOriginal(){
        Iterator<Integrante> it = integrantes.iterator();
        while(it.hasNext()){
            Integrante persona = it.next();
            presupuestoReal += persona.getValorTotal();//Todo: porque no puedo sumar con += en un Double?
        }
    }

    public int getPresupuestoReal() {
        return presupuestoReal;
    }

    public Double getPresupuesto() {
        return presupuesto;
    }

    public void totalHs(Double total){
        totalHs += total;
    }

    public int getTotalHs(){
        return totalHs;
    }

    @Override
    public Double estimatePresupuesto(){ //
        return null;
    };

    public List<Requerimiento> getRequerimientos() {
        return requerimientos;
    }

    public String getNombre() {
        return nombre;
    }

    public void addRequerimiento(Requerimiento req) throws RequerimientoYaTieneProyecto {

        //Validar el requerimiento que llega como parametro, si esta en un proyecto.
        if( req.getProyecto() != null ) {
            throw new RequerimientoYaTieneProyecto( req );
        }
        //Si el requerimiento no tiene proyecto, asignarlo
        req.setProyecto(this);

        this.requerimientos.add(req);

    }

    public void addIntegranteProy(Integrante nuevo){
        integrantes.add(nuevo);
    }

    @Override
    public String toString() {
        return "Proyecto{" +
                "nombre='" + nombre + '\'' +
                "tiempo='" + this.estimate() + '\'' +
                '}';
    }
}
