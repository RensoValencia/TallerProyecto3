package com.upc.indra.enu;
/**
 * @author RENSO
 */
public enum GrupoParametrosEnum {
    
    /*
    -- Grupo 1 (Estado de solicitud de capacitacion)
-- Grupo 2 (Tipo de formación);
-- Grupo 3 (Tipos de sala);
-- Grupo 4 (Tipos de capacitación)
-- Grupo 5 (Detalle de solicitud nivel)
-- Grupo 6 (Tipo de plan de planificacion)
-- Grupo 7 (Estado plan de planificacion)
-- Grupo 8 (Estados de capacitacion)
-- Grupo 9 (Tipo de recursos)
-- Grupo 10 (Formación academica)
-- Grupo 11 (Grado academico)
    */
    ESTADO_SOLICITUD_CAPACITACION("1"),
    TIPO_FORMACION("2"),
    TIPO_SALA("3"),
    TIPO_MODALIDAD("4"),
    DETALLE_SOLICITUD_NIVEL("5"),
    TIPO_PLAN_PLANIFICACION("6"),
    ESTADO_PLAN_CAPACITACION("7"),
    ESTADO_CAPACITACION("8"),
    TIPO_RECURSO("9"),
    FORMACION_ACADEMICA("10"),
    GRADO_ACADEMICO("11");
    
    private final String value;

    GrupoParametrosEnum(String value) {
        this.value = value;
    }

    public Integer getValue() {
        return new Integer(value);
    }
}