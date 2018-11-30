package com.upc.indra.be;

import com.upc.indra.be.MarcaMaterial;
import com.upc.indra.be.TipoMaterial;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-29T21:44:12")
@StaticMetamodel(MaterialesEscritorio.class)
public class MaterialesEscritorio_ { 

    public static volatile SingularAttribute<MaterialesEscritorio, String> descripcion;
    public static volatile SingularAttribute<MaterialesEscritorio, String> codigo;
    public static volatile SingularAttribute<MaterialesEscritorio, Integer> id;
    public static volatile SingularAttribute<MaterialesEscritorio, MarcaMaterial> idMarca;
    public static volatile SingularAttribute<MaterialesEscritorio, TipoMaterial> idTipoMat;

}