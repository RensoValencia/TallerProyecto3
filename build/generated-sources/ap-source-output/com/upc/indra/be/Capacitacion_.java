package com.upc.indra.be;

import com.upc.indra.be.DetalleSolicitud;
import com.upc.indra.be.Parametros;
import com.upc.indra.be.PlanCapacitacion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-19T12:12:51")
@StaticMetamodel(Capacitacion.class)
public class Capacitacion_ { 

    public static volatile SingularAttribute<Capacitacion, DetalleSolicitud> idDetSol;
    public static volatile SingularAttribute<Capacitacion, Parametros> estado;
    public static volatile SingularAttribute<Capacitacion, Date> fechaInicio;
    public static volatile SingularAttribute<Capacitacion, PlanCapacitacion> idPlanCapacitacion;
    public static volatile SingularAttribute<Capacitacion, Integer> id;
    public static volatile SingularAttribute<Capacitacion, Date> fechaFin;

}