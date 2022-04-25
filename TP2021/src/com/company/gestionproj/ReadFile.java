package com.company.gestionproj;

import com.company.gestionproj.exception.RequerimientoYaTieneProyecto;
import com.company.gestionproj.project.Proyecto;
import com.company.gestionproj.project.RequerimientoFuncional;
import com.company.gestionproj.project.RequerimientoNoFuncional;
import com.company.gestionproj.team.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFile {

    String nombre, cad, rol, categoria, tipo;
    Double presu, estimacion;
    int antiguedad, alcance;

    public Object readProyect(String archivo) throws FileNotFoundException, RequerimientoYaTieneProyecto {

        Scanner scanner = new Scanner(new File(archivo));

        Proyecto proj = null;
        RequerimientoFuncional reqF = null;
        RequerimientoNoFuncional reqNF = null;
        Integrante in = null;

        while (scanner.hasNextLine()) {
            cad = scanner.nextLine().toLowerCase();
            switch (cad) {
                case "proyecto":
                    nombre = scanner.nextLine();
                    presu = Double.parseDouble(scanner.nextLine());
                    proj = new Proyecto(nombre, presu);
                    break;
                case "requerimiento funcional":
                    nombre = scanner.nextLine();
                    estimacion = Double.parseDouble(scanner.nextLine());
                    reqF = new RequerimientoFuncional(nombre, proj, estimacion);
                    break;
                case "requerimiento no funcional":
                    nombre = scanner.nextLine();
                    reqNF = new RequerimientoNoFuncional(nombre, proj);
                    break;
                case "subrequerimiento":
                    nombre = scanner.nextLine();
                    reqNF.agregarSubRequerimientoNF(nombre);
                    break;
                case "integrante":
                    nombre = scanner.nextLine();
                    rol = scanner.nextLine() ;
                    antiguedad = Integer.parseInt(scanner.nextLine());
                    rol = rol.toLowerCase() ;
                    switch (rol) {
                        case "desarrollador":
                            categoria = scanner.nextLine();
                            String[] arr= new String[20];
                            int i = 0;
                            String lang;
                            while(!(lang = scanner.nextLine()).equals("x")){
                                arr[i] = lang;
                                i++;
                            }
                            in = new Desarollador(proj, nombre, rol, antiguedad, categoria, arr);
                            break;
                        case "analista":
                            tipo = scanner.nextLine();
                            in = new Analista(proj, nombre, rol, antiguedad, tipo);
                            break;
                        case "lider":
                            in = new Lider(proj, nombre, rol, antiguedad);
                            break;
                        case "tester":
                            tipo = scanner.nextLine();
                            alcance = Integer.parseInt(scanner.nextLine());
                            in = new Tester(proj, nombre, rol, antiguedad, tipo, alcance);
                            break;
                    }
                    break;
            }
        }
        scanner.close();
        return proj;
    }
}


