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
public enum EstadoSolicitudCapacitacionEnum {

    PENDIENTE("1"),
    OBSERVADA("2"),
    APROBADO("3"),
    RECHAZADO("4"),
    EN_PROCESO("5");
    
    private final String value;

    EstadoSolicitudCapacitacionEnum(String value) {
        this.value = value;
    }

    public Integer getValue() {
        return new Integer(value);
    }
}
