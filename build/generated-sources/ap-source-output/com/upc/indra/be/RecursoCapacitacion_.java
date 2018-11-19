package com.upc.indra.be;

import com.upc.indra.be.Parametros;
import com.upc.indra.be.PlanCapacitacion;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-19T12:28:05")
@StaticMetamodel(RecursoCapacitacion.class)
public class RecursoCapacitacion_ { 

    public static volatile SingularAttribute<RecursoCapacitacion, PlanCapacitacion> idPlanificacion;
    public static volatile SingularAttribute<RecursoCapacitacion, Integer> idObjeto;
    public static volatile SingularAttribute<RecursoCapacitacion, Double> valor;
    public static volatile SingularAttribute<RecursoCapacitacion, Integer> id;
    public static volatile SingularAttribute<RecursoCapacitacion, Integer> cantidad;
    public static volatile SingularAttribute<RecursoCapacitacion, Parametros> idTipoRecurso;

}