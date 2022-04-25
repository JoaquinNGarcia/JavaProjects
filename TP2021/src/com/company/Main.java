package com.company;

import com.company.gestionproj.exception.RequerimientoYaTieneProyecto;
import com.company.gestionproj.project.Proyecto;
import com.company.gestionproj.ReadFile;

import java.io.IOException;
import java.lang.*;

public class Main {

    public static void main(String[] args) throws IOException, RequerimientoYaTieneProyecto {

        //LECTURA DESDE TXT / CREACION DE PROYECTOS CON SUS REQUERIMIENTOS E INTEGRANTES
        ReadFile rf = new ReadFile();
        Proyecto projA = (Proyecto)rf.readProyect("ProyectoA.txt");
        Proyecto projB = (Proyecto)rf.readProyect("ProyectoB.txt");

        //INGRESO DE HORAS POR INTEGRANTE
        System.out.println("PROYECTO A");
        System.out.println("Ingrese horas por Integrante: ");
        projA.registraHoras();
        System.out.println("PROYECTO B");
        System.out.println("Ingrese horas por Integrante: ");
        projB.registraHoras();

        //MUESTRA PROYECTOS
        System.out.println(projA);
        projA.muestraRequerimientos();
        projA.muestraIntegrantes();
        System.out.println("Total de horas: " + projA.getTotalHs());
        projA.calculaPresOriginal();
        System.out.println("Presupuesto Estimado: " + projA.getPresupuesto());
        System.out.println("Presupuesto Real: " + projA.getPresupuestoReal());

        System.out.println("---------------------------------------------------------------");

        System.out.println(projB);
        projB.muestraRequerimientos();
        projB.muestraIntegrantes();
        System.out.println("Total de horas: " + projB.getTotalHs());
        projB.calculaPresOriginal();
        System.out.println("Presupuesto Estimado: " + projB.getPresupuesto());
        System.out.println("Presupuesto Real: " + projB.getPresupuestoReal());

    }
}
