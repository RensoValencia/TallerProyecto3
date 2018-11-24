package com.upc.indra.be;

import com.upc.indra.be.Area;
import com.upc.indra.be.DetalleSolicitud;
import com.upc.indra.be.Parametros;
import com.upc.indra.be.PerfilCapacitador;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-24T11:57:17")
@StaticMetamodel(Formacion.class)
public class Formacion_ { 

    public static volatile SingularAttribute<Formacion, Integer> maximoParticipantes;
    public static volatile SingularAttribute<Formacion, PerfilCapacitador> idPerfilCapacitador;
    public static volatile SetAttribute<Formacion, DetalleSolicitud> detalleSolicitudSet;
    public static volatile SingularAttribute<Formacion, Area> idArea;
    public static volatile SingularAttribute<Formacion, String> proposito;
    public static volatile SingularAttribute<Formacion, String> temario;
    public static volatile SingularAttribute<Formacion, Integer> numeroHoras;
    public static volatile SingularAttribute<Formacion, String> nombre;
    public static volatile SingularAttribute<Formacion, Parametros> idTipoModalidad;
    public static volatile SingularAttribute<Formacion, Parametros> tipoSala;
    public static volatile SingularAttribute<Formacion, Parametros> idTipoFormacion;
    public static volatile SingularAttribute<Formacion, Date> fechaCreacion;
    public static volatile SingularAttribute<Formacion, Integer> id;

}