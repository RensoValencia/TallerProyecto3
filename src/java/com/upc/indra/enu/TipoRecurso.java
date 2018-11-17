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
public enum TipoRecurso {
 
    MATERIAL_ESCRITORIO("1"),
    RECURSO_INFORMATICO("2"),
    PERFIL("3");
    
    private final String value;

    TipoRecurso(String value) {
        this.value = value;
    }

    public Integer getValue() {
        return new Integer(value);
    }
}
