package com.company.gestionproj.team;

import com.company.gestionproj.project.Proyecto;

public class Lider extends Integrante{
    private Double valorHora;
    private int antiguedad;
    private final Double horaBase = 5000.0;
    private Double valorTotal;

    public Lider(Proyecto proy, String nombre, String rol, int antiguedad){
        super(proy, nombre, rol, antiguedad);
    };

    public Double getValorTotal() {
        Double sum;
        sum = horaBase * (0.5*getAntiguedad()) ;
        valorTotal = sum*getHorasTrabajadas();
        return valorTotal;
    }
}
