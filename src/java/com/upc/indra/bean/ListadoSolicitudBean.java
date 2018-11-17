package com.upc.indra.bean;

import com.upc.indra.be.Parametros;
import com.upc.indra.be.SolicitudCapacitacion;
import com.upc.indra.bean.util.ControladorAbstracto;
import com.upc.indra.bean.util.JsfUtil;
import com.upc.indra.dao.ConstanteSingleton;
import com.upc.indra.dao.ParametrosFacade;
import com.upc.indra.dao.SolicitudCapacitacionFacade;
import com.upc.indra.enu.EstadoPlanCapacitacion;
import com.upc.indra.enu.EstadoSolicitudCapacitacionEnum;
import com.upc.indra.enu.GrupoParametrosEnum;
import com.upc.indra.enu.TipoPlanPlanificacion;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author RENSO
 */
@Named(value="listadoSolicitudBean")
@ViewScoped
public class ListadoSolicitudBean implements Serializable{

    @Inject private ParametrosFacade parametrosFacade;
    @Inject private ConstanteSingleton constanteSingleton;
    @Inject private SolicitudCapacitacionFacade solicitudCapacitacionFacade; 
    
    @Getter @Setter private Parametros tipoCapacitacionSeleccionada;
    @Getter @Setter private Parametros estadoPlanCapacitacionSeleccionada;
    @Getter @Setter private List<Parametros> listEstadoSolicitudCapacitacion; 
    @Getter @Setter private List<Parametros> listTipoCapacitacion;
    @Getter @Setter private List<SolicitudCapacitacion> listSolicitudCapacitacion;
    
    @Getter @Setter private Integer anioSeleccionado;
    @Getter @Setter private Date fechaInicio; 
    @Getter @Setter private Date fechaFin; 
    
    
    public ListadoSolicitudBean() {
    }
    
    @PostConstruct
    public void init() {
        listTipoCapacitacion = parametrosFacade.findByGrupo(GrupoParametrosEnum.TIPO_PLAN_PLANIFICACION.getValue());
        listEstadoSolicitudCapacitacion = parametrosFacade.findByGrupo(GrupoParametrosEnum.ESTADO_SOLICITUD_CAPACITACION.getValue());
        tipoCapacitacionSeleccionada = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.TIPO_PLAN_PLANIFICACION.getValue(), TipoPlanPlanificacion.INTERNO.getValue());
        fechaInicio = new Date();
        fechaFin = new Date();
        estadoPlanCapacitacionSeleccionada = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.ESTADO_SOLICITUD_CAPACITACION.getValue(), EstadoSolicitudCapacitacionEnum.PENDIENTE.getValue());
        listSolicitudCapacitacion = solicitudCapacitacionFacade.findByIdEstadoPeriodo(
                    estadoPlanCapacitacionSeleccionada, new Integer("2018"), tipoCapacitacionSeleccionada);
    }
    
    public void cambiarEstado(SolicitudCapacitacion sc, int cantidad) {
        solicitudCapacitacionFacade.actualizarEstado(constanteSingleton.getEstadoSolicitudCapacitacionPendiente(), sc);
        sc.setIdEstado(constanteSingleton.getEstadoSolicitudCapacitacionPendiente());
        
        JsfUtil.addSuccessMessage("Se ha deshabilitado la opción para actualizar dado que ya se encuentra como pendiente.");
        ControladorAbstracto.updateComponent("frmListSolicitudCapacitacion:dtPlanesCapacitacion:"+cantidad+":btnEditar"); 
    }
    
    public void listarSolicitudes() {
    
        System.out.println("anioSeleccionado: " + anioSeleccionado);
        System.out.println("tipoCapacitacionSeleccionada: " + tipoCapacitacionSeleccionada);
        
        if(tipoCapacitacionSeleccionada.getId().compareTo(constanteSingleton.getTipoPlanCapacitacionInterna().getId()) == 0) {
            
            if(null == anioSeleccionado || 0 == anioSeleccionado ) {
                JsfUtil.addErrorMessage("Por favor, Ud debe seleccionar el periodo");
                return;
            }
            
            listSolicitudCapacitacion = solicitudCapacitacionFacade.findByIdEstadoPeriodo(
                    estadoPlanCapacitacionSeleccionada, anioSeleccionado, tipoCapacitacionSeleccionada);
            
        } else {
            listSolicitudCapacitacion = solicitudCapacitacionFacade.findByIdEstadoFechas(
                    estadoPlanCapacitacionSeleccionada, fechaInicio, fechaFin, tipoCapacitacionSeleccionada);
            
        }
        
        if(listSolicitudCapacitacion.isEmpty()) {
            JsfUtil.addErrorMessage("No se encontraron resultados con los filtros ingresados.");
            return;
        }
        
        ControladorAbstracto.updateComponent("frmListSolicitudCapacitacion:dtPlanesCapacitacion"); 
    }
}