package com.upc.indra.be;

import com.upc.indra.be.Capacitacion;
import com.upc.indra.be.Parametros;
import com.upc.indra.be.RecursoCapacitacion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-19T12:12:51")
@StaticMetamodel(PlanCapacitacion.class)
public class PlanCapacitacion_ { 

    public static volatile SingularAttribute<PlanCapacitacion, Parametros> estado;
    public static volatile SetAttribute<PlanCapacitacion, RecursoCapacitacion> recursoCapacitacionSet;
    public static volatile SingularAttribute<PlanCapacitacion, Integer> periodo;
    public static volatile SingularAttribute<PlanCapacitacion, Date> fechaAprobacion;
    public static volatile SingularAttribute<PlanCapacitacion, Date> fechaEjecucion;
    public static volatile SingularAttribute<PlanCapacitacion, Integer> id;
    public static volatile SetAttribute<PlanCapacitacion, Capacitacion> capacitacionSet;
    public static volatile SingularAttribute<PlanCapacitacion, String> observacion;
    public static volatile SingularAttribute<PlanCapacitacion, Date> fechaElaboracion;

}