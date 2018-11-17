package com.upc.indra.be;

import com.upc.indra.be.GriMarca;
import com.upc.indra.be.GriTipoRecurso;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-17T12:07:30")
@StaticMetamodel(GriRecurso.class)
public class GriRecurso_ { 

    public static volatile SingularAttribute<GriRecurso, String> descripcion;
    public static volatile SingularAttribute<GriRecurso, String> tipoExpiracion;
    public static volatile SingularAttribute<GriRecurso, String> procesador;
    public static volatile SingularAttribute<GriRecurso, GriTipoRecurso> idGriTipoRecurso;
    public static volatile SingularAttribute<GriRecurso, String> pulgadas;
    public static volatile SingularAttribute<GriRecurso, GriMarca> idGriMarca;
    public static volatile SingularAttribute<GriRecurso, Date> fechaExpiracion;
    public static volatile SingularAttribute<GriRecurso, String> memoriaRam;
    public static volatile SingularAttribute<GriRecurso, String> discoDuro;
    public static volatile SingularAttribute<GriRecurso, String> serie;
    public static volatile SingularAttribute<GriRecurso, Integer> id;
    public static volatile SingularAttribute<GriRecurso, Integer> cantidad;
    public static volatile SingularAttribute<GriRecurso, String> so;
    public static volatile SingularAttribute<GriRecurso, String> capacidad;

}