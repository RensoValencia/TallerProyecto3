package com.upc.indra.be;

import com.upc.indra.be.MarcaHardware;
import com.upc.indra.be.TipoHardware;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-29T21:44:12")
@StaticMetamodel(Hardware.class)
public class Hardware_ { 

    public static volatile SingularAttribute<Hardware, MarcaHardware> idMarcaHw;
    public static volatile SingularAttribute<Hardware, Character> estado;
    public static volatile SingularAttribute<Hardware, TipoHardware> idTipoHw;
    public static volatile SingularAttribute<Hardware, String> color;
    public static volatile SingularAttribute<Hardware, String> material;
    public static volatile SingularAttribute<Hardware, Double> tamanio;
    public static volatile SingularAttribute<Hardware, Double> peso;
    public static volatile SingularAttribute<Hardware, Integer> id;
    public static volatile SingularAttribute<Hardware, Integer> cantidad;
    public static volatile SingularAttribute<Hardware, Integer> stock;
    public static volatile SingularAttribute<Hardware, String> modelo;

}