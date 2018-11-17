/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upc.indra.enu;
/**
 *
 * @author RENSO
 */
public enum EstadoPlanCapacitacion {

    PENDIENTE("1"), EN_PROCESO("2"), APROBADO("3"), OBSERVADO("4");
    
    private final String value;

    EstadoPlanCapacitacion(String value) {
        this.value = value;
    }

    public Integer getValue() {
        return new Integer(value);
    }
}