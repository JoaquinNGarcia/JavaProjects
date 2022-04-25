package com.company.gestionproj.team;
//package com.company.gestionproj.project;

import com.company.gestionproj.project.Proyecto;

public abstract class IntegranteXproyecto {
    private Double HorasTrabajadas;

    public IntegranteXproyecto(){}

    public void sumaHoras(Double h, Proyecto pr){
        this.HorasTrabajadas = h;
        exportaHoras(pr);
    }

    public void exportaHoras(Proyecto pr) {
        pr.totalHs(HorasTrabajadas);
    }

    public Double getHorasTrabajadas() {
        return HorasTrabajadas;
    }
}
