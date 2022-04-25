package com.company.gestionproj.team;

import com.company.gestionproj.project.Proyecto;

import java.util.Scanner;

public class Analista extends Integrante{
    private String tipo;
    private final Double horaBase = 3000.0;
    private Double valorTotal;


    public Analista(Proyecto proy, String nombre, String rol, int antiguedad, String tipo){
        super(proy, nombre, rol, antiguedad);
        this.tipo = tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public Double getValorTotal() {
        Double sum;
        sum = horaBase * (0.5*getAntiguedad()) ;
        if(getTipo().toLowerCase().equals("no funcional")){sum = sum * 1.05;}
        valorTotal = sum*getHorasTrabajadas();
        return valorTotal;
    }
}

