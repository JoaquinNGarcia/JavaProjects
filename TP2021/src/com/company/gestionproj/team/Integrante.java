package com.company.gestionproj.team;

import com.company.gestionproj.project.Proyecto;

public class Integrante extends IntegranteXproyecto {

    private String Nombre;
    private String Rol;
    private int Antiguedad;
    private Double valorTotalHoras;

    public Integrante(Proyecto proy, String nombre, String rol, int antiguedad) {
        Nombre = nombre;
        Rol = rol;
        Antiguedad = antiguedad;
        rol = rol.toLowerCase();

        proy.addIntegranteProy(this);
    }

    public Integrante() {}

    public String getNombre(){
        return Nombre;
    }

    public int getAntiguedad() {
        return Antiguedad;
    }

    public String getRol() {
        return Rol;
    }

    public String getCategoria(){
        return null;
    }
    public Double getValorTotal(){
        return null;
    }

    @Override
    public String toString() {
        return "Integrante{" +
                "nombre='" + Nombre + "' Rol='" + Rol + "' Antiguedad='" + Antiguedad +
                '}';
    }
}
