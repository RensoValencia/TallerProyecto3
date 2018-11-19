package com.upc.indra.be;

import com.upc.indra.be.DetSolParticipante;
import com.upc.indra.be.Rol;
import com.upc.indra.be.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-19T12:12:51")
@StaticMetamodel(Empleado.class)
public class Empleado_ { 

    public static volatile SingularAttribute<Empleado, String> apellidos;
    public static volatile SingularAttribute<Empleado, Date> fechaBaja;
    public static volatile SingularAttribute<Empleado, Character> estado;
    public static volatile SingularAttribute<Empleado, Rol> idRol;
    public static volatile SingularAttribute<Empleado, Date> fechaAlta;
    public static volatile SingularAttribute<Empleado, Date> fechaNacimiento;
    public static volatile SetAttribute<Empleado, DetSolParticipante> detSolParticipanteSet;
    public static volatile SingularAttribute<Empleado, Integer> id;
    public static volatile SingularAttribute<Empleado, Character> flgCapacitacion;
    public static volatile SetAttribute<Empleado, Usuario> usuarioSet;
    public static volatile SingularAttribute<Empleado, String> nombres;

}