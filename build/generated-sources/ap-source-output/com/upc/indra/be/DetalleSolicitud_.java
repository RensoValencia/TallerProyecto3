package com.upc.indra.be;

import com.upc.indra.be.Capacitacion;
import com.upc.indra.be.DetSolParticipante;
import com.upc.indra.be.Formacion;
import com.upc.indra.be.SolicitudCapacitacion;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-17T12:07:30")
@StaticMetamodel(DetalleSolicitud.class)
public class DetalleSolicitud_ { 

    public static volatile SingularAttribute<DetalleSolicitud, Integer> numeroParticipantes;
    public static volatile SingularAttribute<DetalleSolicitud, Formacion> idFormacion;
    public static volatile SingularAttribute<DetalleSolicitud, SolicitudCapacitacion> idSolCap;
    public static volatile SetAttribute<DetalleSolicitud, DetSolParticipante> detSolParticipanteSet;
    public static volatile SingularAttribute<DetalleSolicitud, Integer> id;
    public static volatile SetAttribute<DetalleSolicitud, Capacitacion> capacitacionSet;

}