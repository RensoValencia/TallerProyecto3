package com.upc.indra.be;

import com.upc.indra.be.MarcaSoftware;
import com.upc.indra.be.TipoSoftware;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-24T11:57:17")
@StaticMetamodel(Software.class)
public class Software_ { 

    public static volatile SingularAttribute<Software, String> descripcion;
    public static volatile SingularAttribute<Software, Character> estado;
    public static volatile SingularAttribute<Software, MarcaSoftware> idMarcaSw;
    public static volatile SingularAttribute<Software, Date> fechaExpiracion;
    public static volatile SingularAttribute<Software, String> codLicencia;
    public static volatile SingularAttribute<Software, String> tipoExpiracion;
    public static volatile SingularAttribute<Software, Integer> id;
    public static volatile SingularAttribute<Software, Double> cantidad;
    public static volatile SingularAttribute<Software, Integer> stock;
    public static volatile SingularAttribute<Software, TipoSoftware> idTipoSw;

}