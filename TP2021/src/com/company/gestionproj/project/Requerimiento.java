package com.company.gestionproj.project;

import com.company.gestionproj.exception.RequerimientoYaTieneProyecto;
import com.company.gestionproj.tiempo.Estimable;

public abstract class Requerimiento implements Estimable {
    Proyecto proyecto;
    String nombre;

    Requerimiento(String nombre, Proyecto proyecto) {

        this.nombre = nombre;
        //"proyecto" -puntero-> objeto de tipo Prpoecto

        //"this.proyecto" -puntero-> al mismo objeto de tipo Prpoecto que "proyecto"
        try {
            proyecto.addRequerimiento(this);
        } catch (RequerimientoYaTieneProyecto e) {
            e.printStackTrace();
        }
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    @Override
    public String toString() {
        return "Requerimiento{" +
                "proyecto=" + proyecto +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
