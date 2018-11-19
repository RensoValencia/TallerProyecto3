package com.upc.indra.bean;

import static com.upc.indra.bean.util.ConstanteMensaje.MESSAGE_ERROR_INESPERADO;
import static com.upc.indra.bean.util.Constante.STR_GUION;
import static com.upc.indra.bean.util.Constante.STR_VACIO;
import com.upc.indra.bean.util.ConstanteMensaje;
import com.upc.indra.be.Capacitacion;
import com.upc.indra.be.DetalleSolicitud;
import com.upc.indra.be.GriMarca;
import com.upc.indra.be.GriRecurso;
import com.upc.indra.be.GriTipoRecurso;
import com.upc.indra.be.IndicadorArea;
import com.upc.indra.be.MarcaMaterial;
import com.upc.indra.be.MaterialesEscritorio;
import com.upc.indra.be.Parametros;
import com.upc.indra.be.PerfilCapacitador;
import com.upc.indra.be.PlanCapacitacion;
import com.upc.indra.be.RecursoCapacitacion;
import com.upc.indra.be.SolicitudCapacitacion;
import com.upc.indra.be.TipoMaterial;
import com.upc.indra.be.Usuario;
import com.upc.indra.bean.util.Constante;
import com.upc.indra.bean.util.ControladorAbstracto;
import com.upc.indra.bean.util.JsfUtil;
import com.upc.indra.bean.util.UtilDate;
import com.upc.indra.bean.util.UtilList;
import com.upc.indra.dao.CapacitacionFacade;
import com.upc.indra.dao.ConstanteSingleton;
import com.upc.indra.dao.DetalleSolicitudFacade;
import com.upc.indra.dao.GriMarcaFacade;
import com.upc.indra.dao.GriRecursoFacade;
import com.upc.indra.dao.GriTipoRecursoFacade;
import com.upc.indra.dao.IndicadorAreaFacade;
import com.upc.indra.dao.MarcaMaterialFacade;
import com.upc.indra.dao.MaterialesEscritorioFacade;
import com.upc.indra.dao.ParametrosFacade;
import com.upc.indra.dao.PerfilCapacitadorFacade;
import com.upc.indra.dao.PlanCapacitacionFacade;
import com.upc.indra.dao.RecursoCapacitacionFacade;
import com.upc.indra.dao.SolicitudCapacitacionFacade;
import com.upc.indra.dao.TipoMaterialFacade;
import com.upc.indra.enu.GrupoParametrosEnum;
import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import org.primefaces.event.SelectEvent;
/**
 * @author Jerson
 * @date 14/11/2018
 * @version 1.0
 * @description Permite registrar todas las planificaciones para las solicitudes
 */
@Named("registrarPlanCapacitacionBean")
@ViewScoped
public class RegistrarPlanCapacitacionBean implements Serializable{

    @Inject private ParametrosFacade parametrosFacade;
    @Inject private SolicitudCapacitacionFacade solicitudCapacitacionFacade;
    @Inject private DetalleSolicitudFacade detalleSolicitudFacade;
    @Inject private TipoMaterialFacade tipoMaterialFacade;
    @Inject private MarcaMaterialFacade marcaMaterialFacade;
    @Inject private MaterialesEscritorioFacade materialesEscritorioFacade;
    @Inject private PlanCapacitacionFacade planCapacitacionFacade;
    @Inject private PerfilCapacitadorFacade perfilCapacitadorFacade;
    @Inject private IndicadorAreaFacade indicadorAreaFacade;
    @Inject private GriTipoRecursoFacade griTipoRecursoFacade;
    @Inject private GriMarcaFacade griMarcaFacade;
    @Inject private GriRecursoFacade griRecursoFacade;
    @Inject private ConstanteSingleton constanteSingleton;    
    @Inject private CapacitacionFacade capacitacionFacade;    
    @Inject private RecursoCapacitacionFacade recursoCapacitacionFacade;    

    @Getter private List<Parametros> lstEstadoAproObs;    
    @Getter @Setter private Parametros estadoPlanCapacitacionSeleccionada;
    @Getter @Setter private List<PlanCapacitacion> listPlanCapacitacion; 
    @Getter @Setter private List<RecursoCapacitacion> listRecursoCapacitacion; 
    @Getter @Setter private List<GriRecurso> listGriRecurso; 
    @Getter @Setter private List<IndicadorArea> listIndicadorArea; 
    @Getter @Setter private List<Parametros> listTipoCapacitacion; 
    @Getter @Setter private List<Parametros> listTipoRecurso; 
    @Getter @Setter private List<SolicitudCapacitacion> listSolicitudCapacitacion;
    @Getter @Setter private List<DetalleSolicitud> listDetalleSolicitud;
    @Getter @Setter private List<DetalleSolicitud> listDetalleSolicitudTemp;
    @Getter @Setter private List<SolicitudCapacitacion> solicitudCapacitacionSeleccionadas;
    @Getter @Setter private List<DetalleSolicitud> detalleSolicitudSeleccionadas;
    @Getter @Setter private List<DetalleSolicitud> detalleSolicitudSeleccionadasUltimo;
    @Getter @Setter private List<PerfilCapacitador> listPerfilCapacitador;
    @Getter @Setter private List<TipoMaterial> listTipoMaterial;
    @Getter @Setter private List<GriTipoRecurso> listGriTipoRecurso;
    @Getter @Setter private TipoMaterial tipoMaterialSeleccionado;
    @Getter @Setter private GriTipoRecurso griTipoRecursoSeleccionado;
    @Getter @Setter private List<MarcaMaterial> listMarcaMaterial;
    @Getter @Setter private List<GriMarca> listGriMarca;
    @Getter @Setter private List<MaterialesEscritorio> listMaterialEscritorio;
    @Getter @Setter private List<MaterialesEscritorio> listMaterialEscritorioTemp;
    @Getter @Setter private List<GriRecurso> listGriRecursoTemp;
    @Getter @Setter private List<MaterialesEscritorio> materialEscritorioSeleccionados;
    @Getter @Setter private List<GriRecurso> griRecursoSeleccionados;
    @Getter @Setter private MarcaMaterial marcaMaterialSeleccionado;
    @Getter @Setter private GriMarca griMarcaSeleccionado;
    @Getter @Setter private Parametros tipoCapacitacionSeleccionada;
    @Getter @Setter private Parametros tipoRecursoMaterialEscritorio;
    @Getter @Setter private Parametros tipoCapacitacion;
    @Getter @Setter private Parametros estadoPlanCapacitacion;
    @Getter @Setter private List<Capacitacion> listPerfiles;
    @Getter @Setter private List<Capacitacion> listPerfilesTempl;
    @Getter @Setter private List<Capacitacion> listCapacitacion;
    @Getter @Setter private Integer anioSeleccionado;
    @Getter @Setter private Integer anioSeleccionadoBuscar;
    @Getter @Setter private Date fechaEjecucion;
    @Getter @Setter private String fechaEjecucionInicio;
    @Getter @Setter private String nombreMaterial;
    @Getter @Setter private String nombreRecursoInformatico;
    @Getter @Setter private String nombreBoton;
    @Getter boolean hayEspecializacion = false;
    @Getter @Setter private Parametros estadoAprobadoSeleccionado;
    @Getter private PlanCapacitacion planCapa;
    
    @Getter String msg = "";
    @Getter @Setter private List<Integer> lstAnios;
    private Usuario usuarioLogueado;
    private PlanCapacitacion planCapacitacion;
    @Getter @Setter private String observacion;
    @Getter @Setter private double montoTotal;
    @Getter @Setter private double montoGeneral;
    @Getter @Setter private double montoTotal2;
    @Getter @Setter private int montoTotal3;
    private boolean editar;
    Calendar calen;
    
    public void RegistrarPlanCapacitacionBean() {
    }
    
    @PostConstruct
    public void init() {
        planCapacitacion = new PlanCapacitacion();
        iniciarListas();   
        iniciarObjetos();
        calen = Calendar.getInstance();
        calen.setTime(new Date());
        calen.add(Calendar.YEAR, 1);
        SimpleDateFormat sfd = new SimpleDateFormat(Constante.FORMATO_FECHA);
        fechaEjecucionInicio = sfd.format(calen.getTime());
    }
    
    private void iniciarObjetos() {
        
        final String nombreMetodo = "iniciarObjetos";
        
        try {
            editar = false;
            usuarioLogueado = (Usuario) ControladorAbstracto.getSessionProperty(Constante.USER);
            tipoCapacitacionSeleccionada = constanteSingleton.getTipoPlanCapacitacionInterna();
            listSolicitudCapacitacion = solicitudCapacitacionFacade.findByIdTipoPlanCapacitacionAndEstado(
                    UtilDate.getAnioActual() + 1, constanteSingleton.getEstadoSolicitudCapacitacionEnviado());
            listPerfilCapacitador = perfilCapacitadorFacade.findAll();
            tipoCapacitacion = constanteSingleton.getTipoModalidadEspecializacion();
            estadoPlanCapacitacion = constanteSingleton.getEstadoPlanCapacitacionPendiente();
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(nombreMetodo + STR_GUION + MESSAGE_ERROR_INESPERADO);
        }
    }
    
    private void iniciarListas() {
        
        final String nombreMetodo = "iniciarListas";
        
        try {
            listDetalleSolicitudTemp = new ArrayList<>();
            listMaterialEscritorioTemp = new ArrayList<>();
            listGriRecursoTemp = new ArrayList<>();
            lstAnios = new ArrayList<>();
            lstAnios.add(UtilDate.getAnioActual() + 1);
            anioSeleccionado = UtilDate.getAnioActual() + 1;
            
            lstEstadoAproObs = new ArrayList<>();
            lstEstadoAproObs.add(constanteSingleton.getEstadoPlanCapacitacionAprobado());
            lstEstadoAproObs.add(constanteSingleton.getEstadoPlanCapacitacionObservado());
            
            listTipoMaterial = tipoMaterialFacade.findAll();
            listMarcaMaterial = marcaMaterialFacade.findAll();
            listGriTipoRecurso = griTipoRecursoFacade.findAll();
            listGriMarca = griMarcaFacade.findAll();
            listTipoCapacitacion = parametrosFacade.findByGrupo(GrupoParametrosEnum.TIPO_PLAN_PLANIFICACION.getValue());
            listTipoRecurso = parametrosFacade.findByGrupo(GrupoParametrosEnum.TIPO_RECURSO.getValue());
            List<Parametros> lstParametros = new ArrayList<>();
            for(Parametros recu: listTipoRecurso) {
                if(recu.getId().compareTo(constanteSingleton.getTipoRecursoPerfilCapacitador().getId()) == 0) {
                    lstParametros.add(recu);
                }
            }
            listTipoRecurso.removeAll(lstParametros);
            tipoRecursoMaterialEscritorio = constanteSingleton.getTipoRecursoMaterialEscritorio();
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(nombreMetodo + STR_GUION + MESSAGE_ERROR_INESPERADO);
        }
    }
    
    public void agregarMaterialSolicitud(MaterialesEscritorio materialEscritorio) {

        final String nombreMetodo = "agregarMaterialSolicitud";
        
        try {
            RecursoCapacitacion rc = new RecursoCapacitacion();
            rc.setIdObjeto(materialEscritorio.getId());
            rc.setIdPlanificacion(planCapacitacion);
            rc.setIdTipoRecurso(constanteSingleton.getTipoRecursoMaterialEscritorio());
            rc.setTipo(materialEscritorio.getIdTipoMat().getNombre());
            rc.setMarca(materialEscritorio.getIdMarca().getNombre()); 
            rc.setNombre(ConstanteMensaje.MAT_ESCRITORIO); 
            rc.setDescripcion(materialEscritorio.getDescripcion());
            rc.setTipoMaterial(materialEscritorio); 

            recursoCapacitacionFacade.guardarPlanAndRecursoCapa(rc);
            
            List listRecursoCapacitacionPerfil = new ArrayList<>();
            listRecursoCapacitacion = recursoCapacitacionFacade.findIdPlanCapa(planCapacitacion);
            
            listMaterialEscritorio.remove(materialEscritorio);
            
            for(RecursoCapacitacion recCap: listRecursoCapacitacion) {
                
                if(recCap.getIdTipoRecurso().getId().compareTo(
                        constanteSingleton.getTipoRecursoMaterialEscritorio().getId()) == 0) {
                    MaterialesEscritorio mateEscri = materialesEscritorioFacade.findById(recCap.getIdObjeto());
                    recCap.setMarca(mateEscri.getIdMarca().getNombre()); 
                    recCap.setNombre(ConstanteMensaje.MAT_ESCRITORIO);
                    recCap.setTipo(mateEscri.getIdTipoMat().getNombre());
                    recCap.setDescripcion(mateEscri.getDescripcion());
                }
                
                if(recCap.getIdTipoRecurso().getId().compareTo(
                        constanteSingleton.getTipoRecursoMaterialRecursoInformatico().getId()) == 0) {
                    GriRecurso recursoo = griRecursoFacade.findById(recCap.getIdObjeto());
                    recCap.setMarca(recursoo.getIdGriMarca().getNombre()); 
                    recCap.setNombre(ConstanteMensaje.MAT_INFORMATICO);
                    recCap.setTipo(recursoo.getIdGriTipoRecurso().getNombre());
                    recCap.setDescripcion(recursoo.getDescripcion());
                }
                
                if(recCap.getIdTipoRecurso().getId().compareTo(
                        constanteSingleton.getTipoRecursoPerfilCapacitador().getId())== 0) {
                    listRecursoCapacitacionPerfil.add(recCap);
                }
            }
            listRecursoCapacitacion.removeAll(listRecursoCapacitacionPerfil);

            ControladorAbstracto.updateComponent("frmRegistrarPlanCapacitacion:dtMaterialEscritorio", 
                    "frmRegistrarPlanCapacitacion:dtMaterialInformatico2");
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(nombreMetodo + STR_GUION + MESSAGE_ERROR_INESPERADO);
        }
    }
    
    public void agregarMaterialInformatico(GriRecurso griRecurso) {
    
        final String nombreMetodo = "agregarMaterialInformatico";
        
        try {
            RecursoCapacitacion rc = new RecursoCapacitacion();
            rc.setIdObjeto(griRecurso.getId());
            rc.setIdPlanificacion(planCapacitacion);
            rc.setIdTipoRecurso(constanteSingleton.getTipoRecursoMaterialRecursoInformatico());
            rc.setTipo(griRecurso.getIdGriTipoRecurso().getNombre());
            rc.setMarca(griRecurso.getIdGriMarca().getNombre()); 
            rc.setNombre(ConstanteMensaje.MAT_INFORMATICO); 
            rc.setTipoMaterial(griRecurso); 
            rc.setDescripcion(griRecurso.getDescripcion());
            
            recursoCapacitacionFacade.guardarPlanAndRecursoCapa(rc);

            List listRecursoCapacitacionPerfil = new ArrayList<>();
            listRecursoCapacitacion = recursoCapacitacionFacade.findIdPlanCapa(planCapacitacion);
            
            for(RecursoCapacitacion recCap: listRecursoCapacitacion) {
                if(recCap.getIdTipoRecurso().getId().compareTo(
                        constanteSingleton.getTipoRecursoPerfilCapacitador().getId())== 0) {
                    listRecursoCapacitacionPerfil.add(recCap);
                }
            }
            listRecursoCapacitacion.removeAll(listRecursoCapacitacionPerfil);
            
            listGriRecurso.remove(griRecurso);
            ControladorAbstracto.updateComponent("frmRegistrarPlanCapacitacion:dtMaterialInformatico", 
                    "frmRegistrarPlanCapacitacion:dtMaterialInformatico2");
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(nombreMetodo + STR_GUION + MESSAGE_ERROR_INESPERADO);
        }      
    }
    
    public void eliminarMaterialInformatico(RecursoCapacitacion griRecurso) {
    
        final String nombreMetodo = "eliminarMaterialInformatico";
        
        try {
            recursoCapacitacionFacade.eliminar(griRecurso);

            List listRecursoCapacitacion22 = new ArrayList<>();
            listRecursoCapacitacion = recursoCapacitacionFacade.findIdPlanCapa(planCapacitacion);

            for(RecursoCapacitacion recCap: listRecursoCapacitacion) {
                if(recCap.getIdTipoRecurso().getId().compareTo(
                        constanteSingleton.getTipoRecursoPerfilCapacitador().getId())== 0) {
                    listRecursoCapacitacion22.add(recCap);
                }
            }
            listRecursoCapacitacion.removeAll(listRecursoCapacitacion22);
            
            if(griRecurso.getTipoMaterial() instanceof MaterialesEscritorio) {
                listMaterialEscritorio.add( (MaterialesEscritorio) griRecurso.getTipoMaterial() ); 
            } else if(griRecurso.getTipoMaterial() instanceof GriRecurso) {
                listGriRecurso.add( (GriRecurso) griRecurso.getTipoMaterial() ); 
            }

            ControladorAbstracto.updateComponent("frmRegistrarPlanCapacitacion:dtMaterialEscritorio", 
                    "frmRegistrarPlanCapacitacion:dtMaterialInformatico", 
                    "frmRegistrarPlanCapacitacion:dtMaterialInformatico2");
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(nombreMetodo + STR_GUION + MESSAGE_ERROR_INESPERADO);
        }
    }
    
    public void onRowSelect(SelectEvent event) {
        
        final String nombreMetodo = "onRowSelect";
        
        try {
            SolicitudCapacitacion soliCap = (SolicitudCapacitacion) event.getObject();

            FacesMessage msgq = new FacesMessage(ConstanteMensaje.MSG_SOL_SELECCIONADA, String.valueOf(soliCap.getId()));
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
            ControladorAbstracto.updateComponent("frmRegistrarPlanCapacitacion:dtDetSol"); 
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(nombreMetodo + STR_GUION + MESSAGE_ERROR_INESPERADO);
        }
    }   
    
    public void siguiente1() {
       
        final String nombreMetodo = "siguiente1";
        
        try {
            
            if(listCapacitacion == null || listCapacitacion.isEmpty()) {
                JsfUtil.addErrorMessage(ConstanteMensaje.ERR_AGREGAR_CURSO_CAPACITACION);
                return;
            }

            hayEspecializacion = false;
            msg="";
            nombreBoton = ConstanteMensaje.BTN_GUARDAR;
            for(Capacitacion ca: listCapacitacion) {
                if(tipoCapacitacion.getId().compareTo(ca.getIdDetSol().getIdFormacion().getIdTipoModalidad().getId()) == 0) {
                    hayEspecializacion = true;
                    nombreBoton = ConstanteMensaje.BTN_SIGUIENTE;
                    msg=ConstanteMensaje.ERR_TIENE_CURSO_ESPECIAL;
                }
                if(null  == ca.getFechaInicio()) {
                    JsfUtil.addErrorMessage(ConstanteMensaje.ERR_SELECCIONE_FEC_CURSO + 
                            ca.getIdDetSol().getIdFormacion().getNombre());
                    return;
                }

                if(null == ca.getFechaFin()) {
                    JsfUtil.addErrorMessage(ConstanteMensaje.ERR_SELECCIONE_FEC_FIN_CURSO + 
                            ca.getIdDetSol().getIdFormacion().getNombre());
                    return;
                }

                String select1 = String.valueOf(ca.getFechaInicio().getYear()).substring(1,3); 
                String select2 = String.valueOf(anioSeleccionado).substring(2,4);

                if(!select1.equals(select2)) {
                    JsfUtil.addErrorMessage(ConstanteMensaje.ERR_SELECCIONE_FEC_ANIO);
                    return;
                }

                if(ca.getFechaInicio().after(ca.getFechaFin())) {
                    JsfUtil.addErrorMessage(ConstanteMensaje.ERR_SELECCIONE_FEC_MAYOR);
                    return;
                }
                capacitacionFacade.actualizarTodos(listCapacitacion);
            }        
            ControladorAbstracto.updateComponent("frmRegistrarPlanCapacitacion:msg", 
                    "frmRegistrarPlanCapacitacion:btnSiguiente2");
            ControladorAbstracto.executeJavascript("$('#divPrimero').hide(); $('#divPrimero1').hide(); $('#divSegundo').show(); $('#divSegundo1').show();");
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(nombreMetodo + STR_GUION + MESSAGE_ERROR_INESPERADO);
        }
    }
    
    public void agregarDetalleSolicitud(DetalleSolicitud detalleSolicitud) {
        
        final String nombreMetodo = "agregarDetalleSolicitud";
        
        try {
        
            Capacitacion capa = null;
            if(planCapacitacion == null || planCapacitacion.getId() == null) {
                
                planCapacitacion = new PlanCapacitacion();
                
                planCapacitacion.setFechaElaboracion(new Date());
                planCapacitacion.setEstado(constanteSingleton.getEstadoPlanCapacitacionEnProceso());
                planCapacitacion.setPeriodo(anioSeleccionado);

                capa = new Capacitacion();
                capa.setIdDetSol(detalleSolicitud);
                capa.setEstado(constanteSingleton.getEstadoCapacitacionPendiente());
                capa.setIdPlanCapacitacion(planCapacitacion); 
                capa.setFechaInicio(calen.getTime());
                capa.setFechaFin(calen.getTime());

                planCapacitacionFacade.grabarPlanCapa(planCapacitacion, capa);

            } else {
                planCapacitacion.setPeriodo(anioSeleccionado);

                capa = new Capacitacion();
                capa.setEstado(constanteSingleton.getEstadoCapacitacionPendiente());
                capa.setIdDetSol(detalleSolicitud); 
                capa.setIdPlanCapacitacion(planCapacitacion);
                capa.setFechaInicio(calen.getTime());
                capa.setFechaFin(calen.getTime());
                
                planCapacitacionFacade.grabarCapa(capa);
            }

            listDetalleSolicitud.remove(detalleSolicitud);
            listCapacitacion.add(capa);

            ControladorAbstracto.updateComponent("frmRegistrarPlanCapacitacion:dtDetSol", 
                    "frmRegistrarPlanCapacitacion:dtDetSol2");
            
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(nombreMetodo + STR_GUION + MESSAGE_ERROR_INESPERADO);
        }   
    }
    
    public void eliminarDetalleSolicitud(Capacitacion detalleSolicitud) {
        
        final String nombreMetodo = "eliminarDetalleSolicitud";
        
        try {
        
            capacitacionFacade.eliminar(detalleSolicitud); 
            listCapacitacion = capacitacionFacade.findByIdPlanCapacitacion(planCapacitacion);
            
            if(listCapacitacion == null || listCapacitacion.isEmpty()) {
                planCapacitacionFacade.eliminar(planCapacitacion);
                planCapacitacion = null;
            }

            listCapacitacion.remove(detalleSolicitud);
            if(null != detalleSolicitud.getIdDetSol()) {
                listDetalleSolicitud.add(detalleSolicitud.getIdDetSol());
            }

            ControladorAbstracto.updateComponent("frmRegistrarPlanCapacitacion:dtDetSol", 
                    "frmRegistrarPlanCapacitacion:dtDetSol2");
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(nombreMetodo + STR_GUION + MESSAGE_ERROR_INESPERADO);
        }
    }
    
    public void buscarRecursoInformatico() {
        
        final String nombreMetodo = "buscarRecursoInformatico";
        
        try {
        
            if(null == griTipoRecursoSeleccionado) {
                JsfUtil.addErrorMessage(ConstanteMensaje.ERR_SELEC_TIP_REC_INF);
                return;
            }

            if(null == griMarcaSeleccionado) {
                JsfUtil.addErrorMessage(ConstanteMensaje.ERR_SELEC_TIP_REC_INF_MARCA);
                return;
            }

            if(null == nombreRecursoInformatico || nombreRecursoInformatico.equals(STR_VACIO)) {
                JsfUtil.addErrorMessage(ConstanteMensaje.ERR_SELEC_TIP_REC_INF_NOMBRE);
                return;
            }

            listGriRecurso = griRecursoFacade.findByTipoRecursoMarcaAndNombre(
                    griTipoRecursoSeleccionado.getId(), griMarcaSeleccionado.getId(), nombreRecursoInformatico);
            
            if(UtilList.isEmpty(listGriRecurso)) {
                JsfUtil.addErrorMessage(ConstanteMensaje.ERR_LIST_VACIO);
            }

            ControladorAbstracto.updateComponent("frmRegistrarPlanCapacitacion:dtMaterialInformatico");
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(nombreMetodo + STR_GUION + MESSAGE_ERROR_INESPERADO);
        }
    }
    
    public void buscarRecursoMaterial() {
        
        final String nombreMetodo = "buscarRecursoMaterial";
        
        try {
        
            if(null == tipoMaterialSeleccionado) {
                JsfUtil.addErrorMessage(ConstanteMensaje.ERR_SELEC_TIP_MAT);
                return;
            }

            if(null == marcaMaterialSeleccionado) {
                JsfUtil.addErrorMessage(ConstanteMensaje.ERR_SELEC_TIP_MAT_MARCA);
                return;
            }

            if(null == nombreMaterial || nombreMaterial.equals(Constante.STR_VACIO)) {
                JsfUtil.addErrorMessage(ConstanteMensaje.ERR_SELEC_TIP_MAT_NOMBRE);
                return;
            }

            listMaterialEscritorio = materialesEscritorioFacade.findByTipMatAndMarEsc(
                    tipoMaterialSeleccionado, marcaMaterialSeleccionado, nombreMaterial);

            if(UtilList.isEmpty(listMaterialEscritorio)) {
                JsfUtil.addErrorMessage(ConstanteMensaje.ERR_LIST_VACIO);
            }
            
            ControladorAbstracto.updateComponent("frmRegistrarPlanCapacitacion:dtMaterialEscritorio");
            
       } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(nombreMetodo + STR_GUION + MESSAGE_ERROR_INESPERADO);
        }       
    }
    
    public void siguiente2() {
    
        final String nombreMetodo = "siguiente2";
        
        try {
        
            if(listRecursoCapacitacion == null || listRecursoCapacitacion.isEmpty()) {
                JsfUtil.addErrorMessage(ConstanteMensaje.ERR_AGREGAR_RECURSO);
                return;
            }
            
            for(RecursoCapacitacion ccc: listRecursoCapacitacion) {
                if(ccc.getCantidad() < Constante.CANTIDAD_MINIMA) {
                    JsfUtil.addErrorMessage(ConstanteMensaje.ERR_CANT_VALIDA);
                    return;
                }
            
                if(ccc.getValor() < Constante.CANTIDAD_MINIMA) {
                    JsfUtil.addErrorMessage(ConstanteMensaje.ERR_VALOR_VALIDA);
                    return;
                }
            }
            
            recursoCapacitacionFacade.actualizar(listRecursoCapacitacion);
         
            listPerfiles = new ArrayList<>();    
            listPerfiles = capacitacionFacade.findByIdEsp1(tipoCapacitacion, planCapacitacion);
            
        if(editar) {    
            if(hayEspecializacion) {
                
                List<RecursoCapacitacion> recursCapa = recursoCapacitacionFacade.findByIdPlanAndIdTipoRecurso(this.planCapacitacion, 
                        constanteSingleton.getTipoRecursoPerfilCapacitador());
                
                int i = 0;
                for(Capacitacion capaa: listPerfiles) {
                    if(recursCapa.size() > i) {
                        System.out.println("i: " + i);
                        System.out.println("recursCapa.get(i): " + recursCapa.get(0));
                        RecursoCapacitacion recurso = recursCapa.get(i);
                        PerfilCapacitador perfil = perfilCapacitadorFacade.findById(recurso.getIdObjeto());
                        capaa.setIdRecurso(recurso.getId());
                        capaa.setPerfilCapacitador(perfil);
                        capaa.setValor(recurso.getValor());
                        capaa.setIdObjecto(recurso.getIdObjeto());
                        i++;
                    }
                }
                
                ControladorAbstracto.updateComponent("frmRegistrarPlanCapacitacion:dtPerfilCapacitador");
                ControladorAbstracto.executeJavascript("$('#divSegundo').hide(); $('#divSegundo1').hide(); $('#divTercero').show(); $('#divTercero1').show();");
            } else {
                JsfUtil.addSuccessMessage(ConstanteMensaje.OK_REGISTRO_PLAN);
                ControladorAbstracto.getExternalContext().redirect(Constante.PATH_PLAN_CAPA + Constante.PAGE_LST_PLAN_CAPA);
            }
        } else {
            if(hayEspecializacion) {
                ControladorAbstracto.updateComponent("frmRegistrarPlanCapacitacion:dtPerfilCapacitador");
                ControladorAbstracto.executeJavascript("$('#divSegundo').hide(); $('#divSegundo1').hide(); $('#divTercero').show(); $('#divTercero1').show();");
            } else {
                JsfUtil.addSuccessMessage(ConstanteMensaje.OK_REGISTRO_PLAN);
                ControladorAbstracto.getExternalContext().redirect(Constante.PATH_PLAN_CAPA + Constante.PAGE_LST_PLAN_CAPA);
            }
        }
        
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(nombreMetodo + STR_GUION + MESSAGE_ERROR_INESPERADO);
        }
    }
    
    public void siguiente3() {
        
        final String nombreMetodo = "siguiente3";
        
        try {
        if(listPerfiles.isEmpty()) {
            JsfUtil.addErrorMessage(ConstanteMensaje.ERR_PERFIL_CAPA);
            return;
        }
        
        for(Capacitacion detSol: listPerfiles) {
            
            if(null == detSol.getPerfilCapacitador()) {
                JsfUtil.addErrorMessage(ConstanteMensaje.ERR_PERFIL_CAPA_SELEC);
                return;
            }
            
            if(detSol.getValor() < Constante.CANTIDAD_MINIMA) {
                 JsfUtil.addErrorMessage(ConstanteMensaje.ERR_PERFIL_CAPA_PRECIO);
                return;
            }
        }
        
        if(editar) {
            recursoCapacitacionFacade.actualizarPerfiles(listPerfiles);
        } else {
            recursoCapacitacionFacade.guardarPerfiles(planCapacitacion, listPerfiles);
        }
        
        JsfUtil.addSuccessMessage(ConstanteMensaje.OK_REGISTRO_PLAN);
            ControladorAbstracto.getExternalContext().redirect(Constante.PATH_PLAN_CAPA+Constante.PAGE_LST_PLAN_CAPA);
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(nombreMetodo + STR_GUION + MESSAGE_ERROR_INESPERADO);
        }
    }
    
    public void cargarFechasPorTipo() {
    
        listSolicitudCapacitacion = solicitudCapacitacionFacade.findByIdTipoPlanCapacitacionAndEstado(
                UtilDate.getAnioActual(), constanteSingleton.getEstadoSolicitudCapacitacionEnviado());
       
        ControladorAbstracto.updateComponent("frmRegistrarPlanCapacitacion:dtSolicitudes");
    }
    
    public void mostrarVerMas() {
        
        String nombreMetodo = "mostrarVerMas";
        try {
            listIndicadorArea = indicadorAreaFacade.findAll();
            List<IndicadorArea> eliminar = new ArrayList<>();
            int year = Calendar.getInstance().get(Calendar.YEAR);
            for(IndicadorArea iiiaaa: listIndicadorArea) {
                if(year-1 != iiiaaa.getPeriodo()) {
                    eliminar.add(iiiaaa);
                }
            }
            listIndicadorArea.removeAll(eliminar);

            ControladorAbstracto.updateComponent("frmListadoIndicador:dtListadoIndicador");
            ControladorAbstracto.executeJavascript("PF('wgvDlgIndicador').show();");
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(nombreMetodo + STR_GUION + MESSAGE_ERROR_INESPERADO);
        }
    }
    
    public String nuevoPlanDeCapa() {
        
        String redireccion = "";
        
        PlanCapacitacion planCapa = planCapacitacionFacade.findByPeriodo(UtilDate.getAnioActual() + 1);
        
        if(null != planCapa) {
            JsfUtil.addErrorMessage(ConstanteMensaje.MAX_PLANES_ANIO);
        } else {
            ControladorAbstracto.executeJavascript("$(\"#NuevoRegistro a\").click()");
            redireccion = "/faces/actualizarPlanPlanificacion/frmListadoPlanCapacitacion";
        }
        
        return redireccion;
    }
    
    public void evaluarPlanCapacitacion(PlanCapacitacion planCapacitacion) {
        planCapa = planCapacitacion;
        
        ControladorAbstracto.updateComponent("frmActualizarObservacion:lblNroPlan");         
        ControladorAbstracto.executeJavascript("PF('wgvDlgAprobarPlan').show();");
    }
    
    public void listarCapacitaciones() {
        
        if(null == anioSeleccionadoBuscar || 0 == anioSeleccionadoBuscar.intValue()) {
            JsfUtil.addErrorMessage(ConstanteMensaje.ERR_ANIO_SELECCIONADO);
            return;
        }
        
        if(null == estadoPlanCapacitacionSeleccionada) {
            JsfUtil.addErrorMessage(ConstanteMensaje.ERR_TIP_ESTADO);
            return;
        }

        listPlanCapacitacion = planCapacitacionFacade.findByTipoPeriodoAndEstado(anioSeleccionadoBuscar, 
                estadoPlanCapacitacionSeleccionada.getId());
        
        if(UtilList.isEmpty(listPlanCapacitacion)) {
            JsfUtil.addErrorMessage(ConstanteMensaje.ERR_RESULTADO_VACIO);
            return;
        }
        ControladorAbstracto.updateComponent("frmListadoPlanCapacitacion:dtPlanesCapacitacion"); 
    }
    
    public void actualizarPlan() {
        
        try {
            if(null == estadoAprobadoSeleccionado) {
                JsfUtil.addErrorMessage(ConstanteMensaje.ERR_NO_EST_SELE);
                return;
            }

            if(observacion == null || observacion.equals(STR_VACIO)) {
                JsfUtil.addErrorMessage(ConstanteMensaje.ERR_NO_OBSERVACION);
                return;
            }

            planCapa.setFechaAprobacion(new Date());
            planCapa.setObservacion(observacion);
            planCapa.setEstado(estadoAprobadoSeleccionado);
            planCapacitacionFacade.modificar(planCapa);
            
            if(estadoAprobadoSeleccionado.getId().compareTo(constanteSingleton.getEstadoPlanCapacitacionAprobado().getId()) == 0) {
                List<Capacitacion> capacitaciones = capacitacionFacade.findByIdPlanCapacitacion(planCapa);
                solicitudCapacitacionFacade.actualizarSolicitudAprobado(capacitaciones);
            }

            listPlanCapacitacion = new ArrayList<>();
            JsfUtil.addSuccessMessage("Se " + estadoAprobadoSeleccionado.getDescripcion() + " satisfactoriamente el plan de capacitación");

            ControladorAbstracto.updateComponent("frmListadoPlanCapacitacion:dtPlanesCapacitacion");
            ControladorAbstracto.executeJavascript("PF('wgvDlgAprobarPlan').hide();");
            
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(e.getMessage() + STR_GUION + MESSAGE_ERROR_INESPERADO);
        }
    }
    
    public void modificarPlanCapacitacion(PlanCapacitacion planCapacitacion) {
        
        String nombreMetodo = "verPlanCapacitacion";
        
        try {
            listGriRecurso = new ArrayList<>();
            listMaterialEscritorio = new ArrayList<>();
            listDetalleSolicitud = new ArrayList<>();
            this.planCapacitacion = planCapacitacion;
            List<RecursoCapacitacion> listRecursoCapacitacion22 = new ArrayList<>();
            listCapacitacion = capacitacionFacade.findByIdPlanCapacitacion(planCapacitacion);

            montoTotal3 = 0;
            for(Capacitacion capacitacion: listCapacitacion) {
                montoTotal3 +=capacitacion.getIdDetSol().getNumeroParticipantes();
            }

            listPerfiles= new ArrayList<>();
            listRecursoCapacitacion = recursoCapacitacionFacade.findIdPlanCapa(planCapacitacion);
            editar = true;

            montoTotal = 0.00;
            for(RecursoCapacitacion recuCap: listRecursoCapacitacion) {
                if(recuCap.getIdTipoRecurso().getId().compareTo(
                        constanteSingleton.getTipoRecursoMaterialEscritorio().getId()) == 0) {
                    MaterialesEscritorio mateEscri = materialesEscritorioFacade.findById(recuCap.getIdObjeto());
                    recuCap.setMarca(mateEscri.getIdMarca().getNombre()); 
                    recuCap.setNombre(ConstanteMensaje.MAT_ESCRITORIO);
                    recuCap.setTipo(mateEscri.getIdTipoMat().getNombre());
                    recuCap.setDescripcion(mateEscri.getDescripcion());
                    montoTotal += recuCap.getCantidad() * recuCap.getValor();
                } else if(recuCap.getIdTipoRecurso().getId().compareTo(
                        constanteSingleton.getTipoRecursoMaterialRecursoInformatico().getId()) == 0) {
                    GriRecurso recursoo = griRecursoFacade.findById(recuCap.getIdObjeto());
                    recuCap.setMarca(recursoo.getIdGriMarca().getNombre()); 
                    recuCap.setNombre(ConstanteMensaje.MAT_INFORMATICO);
                    recuCap.setTipo(recursoo.getIdGriTipoRecurso().getNombre());
                    recuCap.setDescripcion(recursoo.getDescripcion());
                    montoTotal += recuCap.getCantidad() * recuCap.getValor();
                } else {
                    montoTotal2 += recuCap.getCantidad() * recuCap.getValor();
                    Capacitacion capaaa = new Capacitacion();
                    PerfilCapacitador perFilCa = perfilCapacitadorFacade.findById(recuCap.getIdObjeto());
                    capaaa.setPerfilCapacitador(perFilCa);
                    capaaa.setValor(recuCap.getValor());
                    listPerfiles.add(capaaa);
                    listRecursoCapacitacion22.add(recuCap);
                }
            }
            montoGeneral = montoTotal + montoTotal2;
            listRecursoCapacitacion.removeAll(listRecursoCapacitacion22);

            ControladorAbstracto.updateComponent("frmRegistrarPlanCapacitacion:dtDetSol2", 
                    "frmRegistrarPlanCapacitacion:dtMaterialInformatico2", 
                    "frmRegistrarPlanCapacitacion:dtPerfilCapacitador"); 
            ControladorAbstracto.executeJavascript("PF('wgvDlgEditarPlanCapacitacion').show();");
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(nombreMetodo + STR_GUION + MESSAGE_ERROR_INESPERADO);
        }
    }
    
    public void verPlanCapacitacion(PlanCapacitacion planCapacitacion) {
    
        String nombreMetodo = "verPlanCapacitacion";
        
        try {
            listCapacitacion=new ArrayList<>();
            List<RecursoCapacitacion> listRecursoCapacitacion22 = new ArrayList<>();
            listCapacitacion = capacitacionFacade.findByIdPlanCapacitacion(planCapacitacion);

            montoTotal = 0.0;
            montoTotal2 = 0.0;
            montoTotal3 = 0;
            montoGeneral = 0;
            for(Capacitacion capacitacion: listCapacitacion) {
                montoTotal3 +=capacitacion.getIdDetSol().getNumeroParticipantes();
            }

            listPerfiles= new ArrayList<>();
            listRecursoCapacitacion = new ArrayList<>();
            listRecursoCapacitacion = recursoCapacitacionFacade.findIdPlanCapa(planCapacitacion);
            editar = false;

            montoTotal = 0.00;
            for(RecursoCapacitacion recuCap: listRecursoCapacitacion) {
                if(recuCap.getIdTipoRecurso().getId().compareTo(
                        constanteSingleton.getTipoRecursoMaterialEscritorio().getId()) == 0) {
                    MaterialesEscritorio mateEscri = materialesEscritorioFacade.findById(recuCap.getIdObjeto());
                    recuCap.setMarca(mateEscri.getIdMarca().getNombre()); 
                    recuCap.setNombre(ConstanteMensaje.MAT_ESCRITORIO);
                    recuCap.setDescripcion(mateEscri.getDescripcion());
                    recuCap.setTipo(mateEscri.getIdTipoMat().getNombre());
                    montoTotal += recuCap.getCantidad() * recuCap.getValor();
                } else if(recuCap.getIdTipoRecurso().getId().compareTo(
                        constanteSingleton.getTipoRecursoMaterialRecursoInformatico().getId()) == 0) {
                    GriRecurso recursoo = griRecursoFacade.findById(recuCap.getIdObjeto());
                    recuCap.setMarca(recursoo.getIdGriMarca().getNombre()); 
                    recuCap.setNombre(ConstanteMensaje.MAT_INFORMATICO);
                    recuCap.setTipo(recursoo.getIdGriTipoRecurso().getNombre());
                    recuCap.setDescripcion(recursoo.getDescripcion());
                    montoTotal += recuCap.getCantidad() * recuCap.getValor();
                } else {
                    montoTotal2 += recuCap.getCantidad() * recuCap.getValor();
                    Capacitacion capaaa = new Capacitacion();
                    PerfilCapacitador perFilCa = perfilCapacitadorFacade.findById(recuCap.getIdObjeto());
                    capaaa.setPerfilCapacitador(perFilCa);
                    capaaa.setValor(recuCap.getValor());
                    listPerfiles.add(capaaa);
                    listRecursoCapacitacion22.add(recuCap);
                }
            }
            montoGeneral = montoTotal + montoTotal2;
            if(!listPerfiles.isEmpty()) {
                List<RecursoCapacitacion> recursCapa = recursoCapacitacionFacade.findByIdPlanAndIdTipoRecurso(planCapacitacion, 
                        constanteSingleton.getTipoRecursoPerfilCapacitador());
                
                List<Capacitacion> listCapa = capacitacionFacade.findByIdPlanCapacitacion(planCapacitacion);
                
                int i = 0;
                for(Capacitacion capaa: listPerfiles) {
                    if(recursCapa.size() > 0) {
                        RecursoCapacitacion recurso = recursCapa.get(i);
                        Capacitacion capa = listCapa.get(i);
                        PerfilCapacitador perfil = perfilCapacitadorFacade.findById(recurso.getIdObjeto());
                        capaa.setIdRecurso(recurso.getId());
                        capaa.setPerfilCapacitador(perfil);
                        capaa.setValor(recurso.getValor());
                        capaa.setIdObjecto(recurso.getIdObjeto());
                        capaa.setIdDetSol(capa.getIdDetSol());
                        i++;
                    }
                }
            }
            
            listRecursoCapacitacion.removeAll(listRecursoCapacitacion22);

            ControladorAbstracto.updateComponent("frmVerPlanCapacitacion:dtDetSol2", 
                    "frmVerPlanCapacitacion:dtMaterialInformatico2", 
                    "frmVerPlanCapacitacion:dtPerfilCapacitador",
                    "frmVerPlanCapacitacion:monto_total", 
                    "frmVerPlanCapacitacion:monto_total2",
                    "frmVerPlanCapacitacion:monto_total3",
                    "frmVerPlanCapacitacion:monto_total4"); 
            ControladorAbstracto.executeJavascript("PF('wgvDlgVerPlanCapacitacion').show();");
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(nombreMetodo + STR_GUION + MESSAGE_ERROR_INESPERADO);
        }
    }
}