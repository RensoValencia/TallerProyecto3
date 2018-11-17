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
public enum TipoPlanPlanificacion {
    
    INTERNO("1"),
    EXTERNO("2");
    
    private final String value;

    TipoPlanPlanificacion(String value) {
        this.value = value;
    }

    public Integer getValue() {
        return new Integer(value);
    }
}
