package com.upc.indra.be;

import com.upc.indra.be.Area;
import com.upc.indra.be.Empleado;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-19T12:28:05")
@StaticMetamodel(Rol.class)
public class Rol_ { 

    public static volatile SingularAttribute<Rol, Character> estado;
    public static volatile SingularAttribute<Rol, Date> fechaModificacion;
    public static volatile SingularAttribute<Rol, Area> idArea;
    public static volatile SingularAttribute<Rol, Integer> idUsuarioCreacion;
    public static volatile SetAttribute<Rol, Empleado> empleadoSet;
    public static volatile SingularAttribute<Rol, Integer> idUsuarioModificacion;
    public static volatile SingularAttribute<Rol, String> pcModificacion;
    public static volatile SingularAttribute<Rol, Date> fechaCreacionHora;
    public static volatile SingularAttribute<Rol, String> nombre;
    public static volatile SingularAttribute<Rol, String> pcCreacion;
    public static volatile SingularAttribute<Rol, Date> fechaCreacion;
    public static volatile SingularAttribute<Rol, Date> fechaModificacionHora;
    public static volatile SingularAttribute<Rol, Integer> id;
    public static volatile SingularAttribute<Rol, Integer> idSede;
    public static volatile SingularAttribute<Rol, Integer> nivel;

}