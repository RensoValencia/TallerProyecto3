package com.upc.indra.be;

import com.upc.indra.be.Parametros;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-24T11:57:17")
@StaticMetamodel(PerfilCapacitador.class)
public class PerfilCapacitador_ { 

    public static volatile SingularAttribute<PerfilCapacitador, String> descripcion;
    public static volatile SingularAttribute<PerfilCapacitador, String> cursos;
    public static volatile SingularAttribute<PerfilCapacitador, String> conocimiento;
    public static volatile SingularAttribute<PerfilCapacitador, String> experienciaEspecifica;
    public static volatile SingularAttribute<PerfilCapacitador, Parametros> idGradoAcademico;
    public static volatile SingularAttribute<PerfilCapacitador, Date> fechaCreacion;
    public static volatile SingularAttribute<PerfilCapacitador, Integer> id;
    public static volatile SingularAttribute<PerfilCapacitador, String> nombre;
    public static volatile SingularAttribute<PerfilCapacitador, String> experienciaGeneral;
    public static volatile SingularAttribute<PerfilCapacitador, String> competencia;
    public static volatile SingularAttribute<PerfilCapacitador, Parametros> idFormacionAcademica;

}