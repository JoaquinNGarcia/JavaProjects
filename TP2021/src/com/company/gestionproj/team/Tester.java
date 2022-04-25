package com.company.gestionproj.team;

import com.company.gestionproj.project.Proyecto;

import java.util.Scanner;

public class Tester extends Integrante{
    private String tipo;
    private final Double horaBase = 2000.0;
    private Double valorTotal;
    private int alcance;

    public Tester(Proyecto proy, String nombre, String rol, int antiguedad, String tipo, int alcance){
        super(proy, nombre, rol, antiguedad);

        this.tipo = tipo;
        this.alcance = alcance;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setAlcance(int alcance) {
        this.alcance = alcance;
    }

    public String getTipo() {
        return tipo;
    }

    public int getAlcance() {
        return alcance;
    }

    public Double getValorTotal() {
        Double sum;
        sum = (horaBase*0.005)* getAntiguedad() ;
        //Todo : agragar 2% al valor de la hora por alcance testeado.
        for(int i = 0 ; i <= getAlcance() ; i++){
            sum = sum * 1.02;
        }
        valorTotal = sum*getHorasTrabajadas();
        return valorTotal;
    }
}
