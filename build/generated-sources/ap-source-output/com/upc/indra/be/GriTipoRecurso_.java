package com.upc.indra.be;

import com.upc.indra.be.GriRecurso;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-19T12:28:05")
@StaticMetamodel(GriTipoRecurso.class)
public class GriTipoRecurso_ { 

    public static volatile SingularAttribute<GriTipoRecurso, String> codigo;
    public static volatile SetAttribute<GriTipoRecurso, GriRecurso> griRecursoSet;
    public static volatile SingularAttribute<GriTipoRecurso, Integer> id;
    public static volatile SingularAttribute<GriTipoRecurso, String> nombre;

}