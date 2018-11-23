package com.upc.indra.be;

import com.upc.indra.be.Formacion;
import com.upc.indra.be.IndicadorArea;
import com.upc.indra.be.Rol;
import com.upc.indra.be.SolicitudCapacitacion;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-22T22:44:39")
@StaticMetamodel(Area.class)
public class Area_ { 

    public static volatile SetAttribute<Area, Formacion> formacionSet;
    public static volatile SingularAttribute<Area, Character> estado;
    public static volatile SetAttribute<Area, Rol> rolSet;
    public static volatile SetAttribute<Area, IndicadorArea> indicadorAreaSet;
    public static volatile SetAttribute<Area, SolicitudCapacitacion> solicitudCapacitacionSet;
    public static volatile SingularAttribute<Area, Integer> id;
    public static volatile SingularAttribute<Area, String> nombre;

}