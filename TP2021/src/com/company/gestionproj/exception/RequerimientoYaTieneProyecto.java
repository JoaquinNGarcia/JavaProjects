package com.company.gestionproj.exception;

import com.company.gestionproj.project.Proyecto;
import com.company.gestionproj.project.Requerimiento;

public class RequerimientoYaTieneProyecto extends Exception {
    public RequerimientoYaTieneProyecto( Requerimiento req ) {

            super ("El requerimiento " + req.getNombre() +
                    " ya se encuentra asiginado al proyecto " +  nombreProyecto( req.getProyecto() ) );

    }

    static String nombreProyecto(Proyecto proy ) {
        return ( proy != null ) ? proy.getNombre() : "";
    }
}
