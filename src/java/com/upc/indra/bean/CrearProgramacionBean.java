package com.upc.indra.bean;

import static com.upc.indra.bean.util.ConstanteMensaje.MESSAGE_ERROR_INESPERADO;
import static com.upc.indra.bean.util.Constante.STR_GUION;
import com.upc.indra.bean.util.ConstanteMensaje;
import com.upc.indra.be.Area;
import com.upc.indra.be.Capacitacion;
import com.upc.indra.be.DetSolParticipante;
import com.upc.indra.be.DetalleSolicitud;
import com.upc.indra.be.Empleado;
import com.upc.indra.be.Formacion;
import com.upc.indra.be.Parametros;
import com.upc.indra.be.PlanCapacitacion;
import com.upc.indra.be.SolicitudCapacitacion;
import com.upc.indra.be.Usuario;
import com.upc.indra.bean.util.ControladorAbstracto;
import com.upc.indra.bean.util.JsfUtil;
import com.upc.indra.dao.ConstanteSingleton;
import com.upc.indra.dao.DetSolParticipanteFacade;
import com.upc.indra.dao.DetalleSolicitudFacade;
import com.upc.indra.dao.FormacionFacade;
import com.upc.indra.dao.ParametrosFacade;
import com.upc.indra.dao.SolicitudCapacitacionFacade;
import com.upc.indra.bean.util.Constante;
import static com.upc.indra.bean.util.Constante.STR_GUION;
import static com.upc.indra.bean.util.ConstanteMensaje.MESSAGE_ERROR_INESPERADO;
import com.upc.indra.bean.util.UtilDate;
import com.upc.indra.bean.util.UtilList;
import com.upc.indra.dao.CapacitacionFacade;
import com.upc.indra.dao.EmpleadoFacade;
import com.upc.indra.dao.PlanCapacitacionFacade;
import com.upc.indra.enu.GrupoParametrosEnum;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
/**
 * @author Gabriel Quiroz
 * @date 10/11/2018
 * @version 1.0
 * @description Crear todas las solicitudes de todas las areas que demanden una capacitacion
 */
@Named(value="crearProgramacionBean")
@ViewScoped
public class CrearProgramacionBean implements Serializable{

    @Inject private EmpleadoFacade empleadoFacade;
    @Inject private ConstanteSingleton constanteSingleton;
    @Inject private ParametrosFacade parametrosFacade;
    @Inject private FormacionFacade formacionFacade;
    @Inject private SolicitudCapacitacionFacade solicitudCapacitacionFacade;
    @Inject private DetalleSolicitudFacade detalleSolicitudFacade;
    @Inject private DetSolParticipanteFacade detSolParticipanteFacade;
    @Inject private PlanCapacitacionFacade planCapacitacionFacade;
    @Inject private CapacitacionFacade capacitacionFacade;

    @Getter @Setter private Integer anioSeleccionado;
    @Getter @Setter private Parametros estSolCapEnviada;
    @Getter @Setter private Parametros tipoCapacitacionSeleccionada;
    @Getter @Setter private Parametros idTipoModalidad;
    @Getter @Setter private Parametros estadoSolicitudCapacitacion;
    @Getter @Setter private Parametros tipoModalidadSeleccionada;
    @Getter @Setter private Parametros tipoModalidadElegida;
    @Getter @Setter private Formacion formacionSeleccionada;
    @Getter @Setter private Parametros tipoFormacionSeleccionada;
    @Getter @Setter private boolean desactivarExterno;
    @Getter @Setter private boolean desactivarComboTipCap;
    @Getter @Setter private boolean desactivarInductiva;
    @Getter @Setter private Parametros tipoModalidadInductivo;
    @Getter @Setter private SolicitudCapacitacion soliCap;
    
    @Getter @Setter private List<DetalleSolicitud> listSolicitud;
    @Getter @Setter private List<PlanCapacitacion> listPlanes;
    @Getter @Setter private List<Parametros> listTipoCapacitacion; 
    @Getter @Setter private List<Parametros> listTipoFormacion; 
    @Getter @Setter private List<Formacion> listFormacion; 
    @Getter @Setter private List<Formacion> listFormacionTemp; 
    @Getter @Setter private List<DetalleSolicitud> listDetalleSolicitud;
    @Getter @Setter private List<DetSolParticipante> listParticipante;
    @Getter @Setter private List<Empleado> listParticipanteArea;
    @Getter @Setter private List<Empleado> listParticipanteTemp;
    @Getter @Setter private List<Capacitacion> listCapacitacion;
    @Getter @Setter private Capacitacion regCapacitacion;
    @Getter @Setter private Date fecIniServicio;
    @Getter @Setter private Date fecFinServicio;
    
    private Usuario usuarioLogueado;
    private Area area;
    private DetalleSolicitud detalleSolicitudSeleccionada;
    private DetalleSolicitud ds;
    private Parametros tipoCapacitacion;
    private Calendar calendar;
    private Parametros tipoModalidadEspecializacion;
    
    public CrearProgramacionBean() {
    }
    
    @PostConstruct
    public void init() {
        
        try {
            cargarObjetos();
            listTipoCapacitacion = parametrosFacade.findByGrupo(GrupoParametrosEnum.TIPO_PLAN_PLANIFICACION.getValue());
            usuarioLogueado = (Usuario) ControladorAbstracto.getSessionProperty(Constante.USER);
            area = usuarioLogueado.getIdEmpleado().getIdRol().getIdArea();
            calendar = Calendar.getInstance();
            calendar.setTime(new Date()); 
            estSolCapEnviada = constanteSingleton.getEstadoSolicitudCapacitacionEnviado();
            tipoModalidadInductivo = constanteSingleton.getTipoModalidadInductivo();
            tipoModalidadEspecializacion = constanteSingleton.getTipoModalidadEspecializacion();
            
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(e.getMessage() + STR_GUION + MESSAGE_ERROR_INESPERADO);     
        }
    }
    
    public void buscarSolicitudCapacitacion() {
        
        try {
            if(null == estadoSolicitudCapacitacion) {
                 JsfUtil.addErrorMessage(ConstanteMensaje.ERR_SELECCIONE_TIP_ESTADO);     
                return;
            }

            if(null == tipoModalidadSeleccionada) {
                 JsfUtil.addErrorMessage(ConstanteMensaje.ERR_SELECCIONE_TIP_CAPA);     
                return;
            }

            if(null ==  anioSeleccionado || anioSeleccionado.equals(Constante.SIN_PERIODO)) {
                 JsfUtil.addErrorMessage(ConstanteMensaje.ERR_SELECCIONE_TIP_PERIODO);     
                return;
            }

            if(null == tipoFormacionSeleccionada) {
                JsfUtil.addErrorMessage(ConstanteMensaje.ERR_SELECCIONE_TIP_FORMACION);     
                return;
            }

            listSolicitud = detalleSolicitudFacade.findByEstadoTipoCapaAnioYCurso(estadoSolicitudCapacitacion, tipoModalidadSeleccionada, 
                    anioSeleccionado, tipoFormacionSeleccionada, area);

            if(listSolicitud.isEmpty()) {
                listSolicitud = new ArrayList<>();
                JsfUtil.addErrorMessage(ConstanteMensaje.ERR_RESULTADO_VACIO);     
                return;
            }
            ControladorAbstracto.updateComponent("frmBuscarPlanes:dtLstPlanes");
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(e.getMessage() + STR_GUION + MESSAGE_ERROR_INESPERADO);     
        }
    }

    public void buscarPlanes() {
        
        try {
            if(null ==  anioSeleccionado || anioSeleccionado.equals(Constante.SIN_PERIODO)) {
                 JsfUtil.addErrorMessage(ConstanteMensaje.ERR_SELECCIONE_TIP_PERIODO);     
                return;
            }

            listPlanes = planCapacitacionFacade.findByTipoPeriodo(anioSeleccionado);

            if(listPlanes.isEmpty()) {
                listPlanes = new ArrayList<>();
                JsfUtil.addErrorMessage(ConstanteMensaje.ERR_RESULTADO_VACIO);     
                return;
            }
            ControladorAbstracto.updateComponent("frmBuscarPlanes:dtLstPlanes");
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(e.getMessage() + STR_GUION + MESSAGE_ERROR_INESPERADO);     
        }
    }
    
    public void onRowSelect(SelectEvent event) {
        
        final String nombreMetodo = "onRowSelect";
        
        try {
            PlanCapacitacion planCapacitacion = (PlanCapacitacion) event.getObject();

            FacesMessage msgq = new FacesMessage(ConstanteMensaje.MSG_SOL_SELECCIONADA, String.valueOf(planCapacitacion.getId()));
            FacesContext.getCurrentInstance().addMessage(null, msgq);

            listDetalleSolicitud = detalleSolicitudFacade.findBySolCap(soliCap);
            listCapacitacion = capacitacionFacade.findByIdPlanCapacitacion(planCapacitacion);

            List<DetalleSolicitud> detSolTemp = new ArrayList<>();
            for(DetalleSolicitud ds: listDetalleSolicitud) {
                for(Capacitacion capa: listCapacitacion) {
                    if(ds.getIdFormacion().getId().compareTo(capa.getIdDetSol().getIdFormacion().getId()) == 0) {
                        detSolTemp.add(ds);
                    } 
                }
            }
            listDetalleSolicitud.removeAll(detSolTemp);
            ControladorAbstracto.updateComponent("frmBuscarPlanes:dtDetCapa"); 
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(nombreMetodo + STR_GUION + MESSAGE_ERROR_INESPERADO);
        }
    }   
    
    public void buscarFormacion() {
        
        try {
            listFormacion = formacionFacade.findByIdAreaIdTipoFormacionAndIdTipoModalidad(
                area.getId(), 
                tipoFormacionSeleccionada.getId(), 
                tipoModalidadSeleccionada.getId());
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(e.getMessage() + STR_GUION + MESSAGE_ERROR_INESPERADO);
        }
    }
    
    public void loadCrearProgramacion(Capacitacion registro){
        if (registro!=null) {
            regCapacitacion = registro;
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('wgvDlgFrmCrearProgramacion').show();");
            context.update("frmCrearProgramacion");
            
        }
    }
    
    public void closeCrearProgramacion() {
        regCapacitacion = null;
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('wgvDlgFrmCrearProgramacion').hide();");
    }
    
    public void buscarTipoFormacionYCurso() {
        
        try {
            desactivarInductiva = false;
            
            List<Parametros> listadoFormacion = new ArrayList<>();

            if(tipoModalidadEspecializacion.getId().compareTo(tipoModalidadSeleccionada.getId()) == 0) {
                listadoFormacion.add(parametrosFacade.findById(constanteSingleton.getTipoFormacionCurso().getId()));
                listTipoFormacion = listadoFormacion;
                listFormacion = formacionFacade.findByIdAreaIdTipoFormacionAndIdTipoModalidad(
                        area.getId(), 
                        constanteSingleton.getTipoFormacionCurso().getId(),
                        tipoModalidadSeleccionada.getId());

            } else if(constanteSingleton.getTipoModalidadCorrectivo().getId().compareTo(tipoModalidadSeleccionada.getId()) == 0) {
                listTipoFormacion = parametrosFacade.findByGrupo(GrupoParametrosEnum.TIPO_FORMACION.getValue());
                listFormacion = new ArrayList<>();
            } else if(constanteSingleton.getTipoModalidadInductivo().getId().compareTo(
                    tipoModalidadSeleccionada.getId()) == 0) {
                desactivarInductiva = true;
                listadoFormacion.add(parametrosFacade.findById(constanteSingleton.getTipoFormacionTaller().getId()));
                listTipoFormacion = listadoFormacion;            
                listFormacion = formacionFacade.findByIdAreaIdTipoFormacionAndIdTipoModalidad(
                        area.getId(), constanteSingleton.getTipoFormacionTaller().getId(),
                        tipoModalidadSeleccionada.getId()); 
            }
            
            ControladorAbstracto.updateComponent("frmListadoParticipante:btnAgregarPar");
            
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(e.getMessage() + STR_GUION + MESSAGE_ERROR_INESPERADO);
        }
    }
    
    public void seleccionarDetalleSolicitud() {
    
        final String nombreMethodo = "seleccionarDetalleSolicitud";
        
        try {
            tipoModalidadSeleccionada = soliCap.getIdTipoModalidad();
            
            if(tipoModalidadInductivo.getId().compareTo(tipoModalidadSeleccionada.getId()) == 0) {
                desactivarInductiva = true;
            } else {
                desactivarInductiva = false;
            }
            
            desactivarComboTipCap = true;
            tipoFormacionSeleccionada = detalleSolicitudFacade.findBySolCap(soliCap).get(0).getIdFormacion().getIdTipoFormacion();
            formacionSeleccionada = detalleSolicitudFacade.findBySolCap(soliCap).get(0).getIdFormacion();
            
            buscarTipoFormacionYCurso();
            
            listParticipante = new ArrayList<>();
            listDetalleSolicitud = detalleSolicitudFacade.findBySolCap(soliCap);
            
            ControladorAbstracto.updateComponent("frmCrearSolicitudCapacitacion:somTipoModalidadCrud", 
                    "frmCrearSolicitudCapacitacion:somTipoFormacionCrud", 
                    "frmCrearSolicitudCapacitacion:somFormacionCrud", 
                    "frmCrearSolicitudCapacitacion:dtListDetalleSolicitud", 
                    "frmListadoParticipante:dtListadoParticipante", "frmListadoParticipante:btnAgregarPar");
            
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(nombreMethodo + " - " + MESSAGE_ERROR_INESPERADO);
        }
    }
    
    public void agregarDetalleSolicitud() {
     
        final String nombreMethodo = "agregarDetalleSolicitud";
        
        try {
            if(tipoModalidadSeleccionada  == null) {
                JsfUtil.addErrorMessage(ConstanteMensaje.ERR_SELECCIONE_TIP_MODALIDAD);     
                return;
            }

            if(null == tipoFormacionSeleccionada) {
                JsfUtil.addErrorMessage(ConstanteMensaje.ERR_SELECCIONE_TIP_FORMACION);     
                return;
            }

            if(formacionSeleccionada == null) {
                JsfUtil.addErrorMessage(ConstanteMensaje.ERR_SELECCIONAR_FORMACION);     
                return;
            }

            if(soliCap == null || null == soliCap.getId()) {
                
                SolicitudCapacitacion solCapConsulta = solicitudCapacitacionFacade.findByAreaIdTipModAndPeriodo(area, 
                    tipoModalidadSeleccionada, UtilDate.getAnioActual() + 1);
            
                if(null != solCapConsulta) {
                    JsfUtil.addErrorMessage(ConstanteMensaje.ERR_TIP_MODALIDAD_X_ANIO);     
                    return;
                }

                if(tipoModalidadEspecializacion.getId().compareTo(tipoModalidadSeleccionada.getId()) == 0) {
                    tipoCapacitacion = constanteSingleton.getTipoPlanCapacitacionExterna();
                } else {
                    tipoCapacitacion = constanteSingleton.getTipoPlanCapacitacionInterna();
                }
                
                soliCap = new SolicitudCapacitacion();
                
              //  soliCap.setIdTipoCapacitacion(tipoCapacitacion);
                soliCap.setObservacion(ConstanteMensaje.OK_CREACION_SOLICITUD);
                soliCap.setIdArea(area); 
                soliCap.setIdEstado(constanteSingleton.getEstadoSolicitudCapacitacionPendiente());
                soliCap.setPeriodo(UtilDate.getAnioActual() + 1);
                soliCap.setIdTipoModalidad(tipoModalidadSeleccionada);

                ds = new DetalleSolicitud();
                ds.setIdFormacion(formacionSeleccionada);
                ds.setIdSolCap(soliCap);

                solicitudCapacitacionFacade.guardarSolicitud(soliCap, ds);

                desactivarComboTipCap = true;
                tipoModalidadElegida = tipoModalidadSeleccionada;
            } else {
                listDetalleSolicitud = detalleSolicitudFacade.findBySolCap(soliCap);

                if(listDetalleSolicitud.isEmpty()) {
                    desactivarComboTipCap = true;
                    tipoModalidadElegida = tipoModalidadSeleccionada;
                   // soliCap.setIdTipoCapacitacion(tipoModalidadElegida);
                    soliCap.setIdTipoModalidad(tipoModalidadSeleccionada);
                    solicitudCapacitacionFacade.actualizar(soliCap);
                }
                
                if(!listDetalleSolicitud.isEmpty()) {
                    for(DetalleSolicitud ddss: listDetalleSolicitud) {
                        if(ddss.getIdFormacion().getId().compareTo(formacionSeleccionada.getId()) == 0) {
                            JsfUtil.addErrorMessage(ConstanteMensaje.ERR_AGREGE_OTRA_FORMACION);
                            return;
                        }
                    }
                }
                
                if(listDetalleSolicitud.size() > Constante.MAXIMO_DETALLE_SOLICITUD) {
                    JsfUtil.addErrorMessage(ConstanteMensaje.ERR_MAXIMO_PARTICIPANTES);
                    return;
                }
                
                ds = new DetalleSolicitud();
                ds.setIdFormacion(formacionSeleccionada);
                ds.setIdSolCap(soliCap);
                detalleSolicitudFacade.guardarDetalleSolicitud(ds);
            }   

            JsfUtil.addSuccessMessage(ConstanteMensaje.OK_CREACION_DET_SOLICITUD);
            listDetalleSolicitud = detalleSolicitudFacade.findBySolCap(soliCap);
            ControladorAbstracto.updateComponent("frmCrearSolicitudCapacitacion:somTipoModalidadCrud", 
                    "frmCrearSolicitudCapacitacion:dtListDetalleSolicitud");
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(nombreMethodo + " - " + MESSAGE_ERROR_INESPERADO);
        }
    }
    
    public void eliminarCurso(DetalleSolicitud ds) {
        
        final String nombreMethodo = "eliminarCurso";
        
        try {
            List<DetSolParticipante> part = detSolParticipanteFacade.findByDetSol(ds);

            detalleSolicitudFacade.borrarDetalleSolicitud(ds, part);
            listDetalleSolicitud = detalleSolicitudFacade.findBySolCap(soliCap);
            
            listParticipante = new ArrayList<>();
            desactivarComboTipCap = true;
            
            if(listDetalleSolicitud.isEmpty()) {
                solicitudCapacitacionFacade.eliminar(soliCap);
                soliCap = null;
                desactivarComboTipCap = false;
            }
            
            ControladorAbstracto.updateComponent("frmCrearSolicitudCapacitacion:somTipoModalidadCrud", 
                    "frmCrearSolicitudCapacitacion:dtListDetalleSolicitud", 
                    "frmListadoParticipante:dtListadoParticipante");
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(nombreMethodo + " - " + MESSAGE_ERROR_INESPERADO);
        }
    }
    
    public void eliminarParticipante(DetSolParticipante dsp) {
        
        final String nombreMethodo = "eliminarCurso";
        
        try {
            detSolParticipanteFacade.eliminarParticipante(dsp, detalleSolicitudSeleccionada);
            listParticipante.remove(dsp);
            listDetalleSolicitud = detalleSolicitudFacade.findBySolCap(soliCap);
            ControladorAbstracto.updateComponent("frmCrearSolicitudCapacitacion:dtListDetalleSolicitud", 
                    "frmListadoParticipante:dtListadoParticipante");
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(nombreMethodo + " - " + MESSAGE_ERROR_INESPERADO);
        }
    }
    
    public void agregarListadoParticipantes(DetalleSolicitud ds) {
        
        final String nombreMethodo = "agregarListadoParticipantes";
        
        try {
            detalleSolicitudSeleccionada = ds; 
            listParticipante = detSolParticipanteFacade.findByDetSol(detalleSolicitudSeleccionada);
            
            if(UtilList.isEmpty(listSolicitud)) {
                JsfUtil.addErrorMessage(ConstanteMensaje.ERR_LST_PARTICIPANTES_VACIO);
            }

            ControladorAbstracto.updateComponent("frmListadoParticipante:dtListadoParticipante");
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(nombreMethodo + STR_GUION + MESSAGE_ERROR_INESPERADO);
        }
    }
    
    public void agregarListadoParticipantes2() {
        
        final String nombreMethodo = "agregarListadoParticipantes2";
        
        try {
            List<Empleado> eliminarTodo = new ArrayList<>();
            listParticipanteArea = empleadoFacade.findByIdArea(area);
            listParticipante = detSolParticipanteFacade.findByDetSol(detalleSolicitudSeleccionada);
            for(Empleado par1: listParticipanteArea) {
                for(DetSolParticipante det11: listParticipante) {
                    if(par1.getId().compareTo(det11.getIdEmpleado().getId()) == 0) {
                        eliminarTodo.add(par1);
                    }
                }
            }
            listParticipanteArea.removeAll(eliminarTodo);

            ControladorAbstracto.updateComponent("frmListadoParticipanteSeleccionar:dtListadoParticipanteSeleccionar");
            ControladorAbstracto.executeJavascript("PF('wgvDlgSeleccionarParticipantesSeleccionar').show();");
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(nombreMethodo + STR_GUION + MESSAGE_ERROR_INESPERADO);
        }
    }
    
    public void guardarParticipante() {    
        
        final String nombreMethodo = "guardarParticipante";
        
        try {
            if(listParticipanteTemp.isEmpty()) {
                JsfUtil.addErrorMessage(ConstanteMensaje.ERR_SELECCIONAR_PARTICIPANTES);
                return;
            }

            if((detalleSolicitudSeleccionada.getNumeroParticipantes() + listParticipanteTemp.size()) > detalleSolicitudSeleccionada.getIdFormacion().getMaximoParticipantes()) {
                JsfUtil.addErrorMessage(ConstanteMensaje.ERR_MAXIMO_PERMITIDO);
                return;
            }

            detSolParticipanteFacade.guardar(detalleSolicitudSeleccionada, listParticipanteTemp);
            agregarListadoParticipantes(detalleSolicitudSeleccionada);
            listParticipanteTemp = new ArrayList<>();
            listDetalleSolicitud = detalleSolicitudFacade.findBySolCap(soliCap);

            JsfUtil.addSuccessMessage(ConstanteMensaje.OK_AGREGAR_PARTICIPANTE);
            ControladorAbstracto.updateComponent("frmCrearSolicitudCapacitacion:dtListDetalleSolicitud"); 
            ControladorAbstracto.updateComponent("frmListadoParticipante:dtListadoParticipante"); 
            ControladorAbstracto.executeJavascript("PF('wgvDlgSeleccionarParticipantesSeleccionar').hide();");
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(nombreMethodo + STR_GUION + MESSAGE_ERROR_INESPERADO);
        }
    }
    
    public void nuevaSolicitudCapacitacion() {
        cargarObjetos();
        ControladorAbstracto.updateComponent("frmCrearSolicitudCapacitacion:somTipoModalidadCrud");
        ControladorAbstracto.updateComponent("frmCrearSolicitudCapacitacion:somTipoFormacionCrud");
        ControladorAbstracto.updateComponent("frmCrearSolicitudCapacitacion:somFormacionCrud");
        ControladorAbstracto.updateComponent("frmCrearSolicitudCapacitacion:dtListDetalleSolicitud");
        ControladorAbstracto.updateComponent("frmListadoParticipante:dtListadoParticipante");
    }
    
    public void actualizarSolicitud() {
        
        final String nombreMethodo = "actualizarSolicitud";
        
        try {
            soliCap.setFechaDocumento(new Date());
            soliCap.setIdEstado(constanteSingleton.getEstadoSolicitudCapacitacionEnviado());
            soliCap.setObservacion(ConstanteMensaje.OK_ACTUALIZAR_SOLICITUD);
            solicitudCapacitacionFacade.actualizar(soliCap);
            
            ControladorAbstracto.executeJavascript("PF('wgvDlgCrearSolicitudCapacitacion').hide();");
            JsfUtil.addSuccessMessage(ConstanteMensaje.OK_ACTUALIZAR_SOL_CAPACITACION);
            
            listSolicitud = new ArrayList<>();
            ControladorAbstracto.updateComponent("frmBuscarPlanes:dtLstPlanes");
            
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(nombreMethodo + STR_GUION + MESSAGE_ERROR_INESPERADO);
        }
    }
    
    public void guardarSolicitud() {
        
        final String nombreMethodo = "guardarSolicitud";
        
        try {
            if(null == tipoModalidadSeleccionada) {
                JsfUtil.addErrorMessage(ConstanteMensaje.ERR_SELECCIONE_TIP_MODALIDAD);     
                return;
            }

            if(null == tipoFormacionSeleccionada) {
                JsfUtil.addErrorMessage(ConstanteMensaje.ERR_SELECCIONE_TIP_FORMACION);     
                return;
            }

            if(null == formacionSeleccionada) {
                JsfUtil.addErrorMessage(ConstanteMensaje.ERR_SELECCIONAR_FORMACION);     
                return;
            }
            
            SolicitudCapacitacion solCapConsulta = solicitudCapacitacionFacade.findByAreaIdTipModAndPeriodo(area, 
                    tipoModalidadSeleccionada, calendar.get(Calendar.YEAR));
            
            if(null == soliCap.getId()) {
                if(null != solCapConsulta) {
                    JsfUtil.addErrorMessage(ConstanteMensaje.ERR_TIP_MODALIDAD_X_ANIO);     
                    return;
                }
            }

            if(null == soliCap) {
                JsfUtil.addErrorMessage(ConstanteMensaje.ERR_SOL_NO_EXITE);     
                return;
            }

            if(null == listDetalleSolicitud || listDetalleSolicitud.isEmpty()) {
                JsfUtil.addErrorMessage(ConstanteMensaje.ERR_NO_AGREGA_DET_SOL);     
                return;
            }

            for(DetalleSolicitud detSol: listDetalleSolicitud) {
                if(tipoModalidadInductivo.getId().compareTo(detSol.getIdFormacion().getIdTipoModalidad().getId()) != 0 &&  
                        detSol.getNumeroParticipantes() <= Constante.LIMITE_NO_PERMITIDO) {
                    JsfUtil.addErrorMessage(ConstanteMensaje.ERR_SELECCIONAR_PARTICIPANTES);     
                    return;
                } else {
                    if(tipoModalidadInductivo.getId().compareTo(detSol.getIdFormacion().getIdTipoModalidad().getId()) == 0 && 
                            detSol.getNumeroParticipantes() <= Constante.LIMITE_NO_PERMITIDO) {
                        JsfUtil.addErrorMessage(ConstanteMensaje.ERR_ESCRIBIR_PARTICIPANTES);     
                        return;
                    } else {
                        if(detSol.getNumeroParticipantes() > detSol.getIdFormacion().getMaximoParticipantes()) {
                            JsfUtil.addErrorMessage(ConstanteMensaje.ERR_MAXIMO_PERMITIDO);     
                            return;
                        }
                        detalleSolicitudFacade.actualizar(detSol);
                    }
                }
            }
            ControladorAbstracto.executeJavascript("PF('wgvDlgCrearSolicitudCapacitacion').hide();");
            JsfUtil.addSuccessMessage(ConstanteMensaje.OK_CREACION_SOLICITUD);
            
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(nombreMethodo + STR_GUION + MESSAGE_ERROR_INESPERADO);
        }
    }
    
    private void cargarObjetos() {
        soliCap = new SolicitudCapacitacion();
        listFormacionTemp = new ArrayList<>();
        listDetalleSolicitud = new ArrayList<>();
        listParticipanteTemp = new ArrayList<>();
        listParticipante = new ArrayList<>();
        desactivarComboTipCap = false;
        desactivarInductiva = false;
        listTipoFormacion = new ArrayList<>(); 
        listFormacion = new ArrayList<>(); 
    }
}