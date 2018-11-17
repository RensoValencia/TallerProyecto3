package com.upc.indra.bean;

import com.upc.indra.be.Parametros;
import com.upc.indra.be.PlanCapacitacion;
import com.upc.indra.bean.util.ConstanteMensaje;
import com.upc.indra.bean.util.ControladorAbstracto;
import com.upc.indra.bean.util.JsfUtil;
import com.upc.indra.bean.util.UtilDate;
import com.upc.indra.dao.ConstanteSingleton;
import com.upc.indra.dao.ParametrosFacade;
import com.upc.indra.dao.PlanCapacitacionFacade;
import com.upc.indra.enu.GrupoParametrosEnum;
import com.upc.indra.enu.TipoPlanPlanificacion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author RENSO
 */
@Named(value="listadoPlanCapacitacion")
@ViewScoped
public class ListadoPlanCapacitacion implements Serializable{
    
    @Getter @Setter private Parametros tipoCapacitacionSeleccionada;
    @Getter @Setter private Parametros estadoPlanCapacitacionSeleccionada;
    @Getter @Setter private List<PlanCapacitacion> listPlanCapacitacion; 
    @Getter @Setter private Date fechaInicio; 
    @Getter @Setter private Date fechaFin; 
    @Inject private ParametrosFacade parametrosFacade;
    @Inject private PlanCapacitacionFacade planCapacitacionFacade;
    @Inject private ConstanteSingleton constanteSingleton;
    
    @Getter private List<Parametros> lstEstadoAproObs;
    @Getter @Setter private Parametros estadoAprobadoSeleccionado;
    @Getter @Setter private String observacion;
    
    @Getter @Setter private Integer anioSeleccionado;
    private PlanCapacitacion planCapa;

    public ListadoPlanCapacitacion() {
    }
    
    @PostConstruct
    public void init() {
        tipoCapacitacionSeleccionada = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.TIPO_PLAN_PLANIFICACION.getValue(), TipoPlanPlanificacion.INTERNO.getValue());
        
        fechaInicio = new Date();
        fechaFin = new Date();
        
        lstEstadoAproObs = new ArrayList<>();
        lstEstadoAproObs.add(constanteSingleton.getEstadoPlanCapacitacionAprobado());
        lstEstadoAproObs.add(constanteSingleton.getEstadoPlanCapacitacionObservado());
        
    }    
}