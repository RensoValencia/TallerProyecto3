package com.upc.indra.be;

import com.upc.indra.be.Capacitacion;
import com.upc.indra.be.Formacion;
import com.upc.indra.be.PlanCapacitacion;
import com.upc.indra.be.RecursoCapacitacion;
import com.upc.indra.be.SolicitudCapacitacion;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-19T12:12:51")
@StaticMetamodel(Parametros.class)
public class Parametros_ { 

    public static volatile SingularAttribute<Parametros, String> descripcion;
    public static volatile SetAttribute<Parametros, Formacion> formacionSet;
    public static volatile SingularAttribute<Parametros, Integer> codigo;
    public static volatile SetAttribute<Parametros, RecursoCapacitacion> recursoCapacitacionSet;
    public static volatile SetAttribute<Parametros, SolicitudCapacitacion> solicitudCapacitacionSet1;
    public static volatile SetAttribute<Parametros, PlanCapacitacion> planCapacitacionSet1;
    public static volatile SingularAttribute<Parametros, Integer> grupo;
    public static volatile SetAttribute<Parametros, SolicitudCapacitacion> solicitudCapacitacionSet;
    public static volatile SetAttribute<Parametros, Formacion> formacionSet1;
    public static volatile SetAttribute<Parametros, Formacion> formacionSet2;
    public static volatile SetAttribute<Parametros, Capacitacion> capacitacionSet;
    public static volatile SingularAttribute<Parametros, Integer> id;

}