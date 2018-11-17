package com.upc.indra.enu;
/**
 *
 * @author RENSO
 */
public enum EstadoCapacitacion {

    PENDIENTE("1"), ENVIADO("2"), APROBADO("3"), RECHAZADO("4"), EN_PROCESO("5");
    
    private final String value;

    EstadoCapacitacion(String value) {
        this.value = value;
    }

    public Integer getValue() {
        return new Integer(value);
    }    
}