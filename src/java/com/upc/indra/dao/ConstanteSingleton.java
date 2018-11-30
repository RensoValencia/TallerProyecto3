package com.upc.indra.dao;

import com.upc.indra.be.Parametros;
import com.upc.indra.enu.EstadoCapacitacion;
import com.upc.indra.enu.EstadoPlanCapacitacion;
import com.upc.indra.enu.EstadoSolicitudCapacitacionEnum;
import com.upc.indra.enu.GrupoParametrosEnum;
import com.upc.indra.enu.TipoCapacitacion;
import com.upc.indra.enu.TipoFormacion;
import com.upc.indra.enu.TipoPlanPlanificacion;
import com.upc.indra.enu.TipoRecurso;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;

/**
 * @author RENSO
 * @date 02-may-2018
 */
@Singleton
public class ConstanteSingleton {

    @Inject public ParametrosFacade parametrosFacade;

    @Getter @Setter public Parametros tipoModalidadInductivo;
    @Getter @Setter public Parametros tipoModalidadCorrectivo;
    @Getter @Setter public Parametros tipoModalidadEspecializacion;
    
    @Getter @Setter public Parametros tipoFormacionCurso;
    @Getter @Setter public Parametros tipoFormacionTaller;
    
    @Getter @Setter public Parametros tipoPlanCapacitacionInterna;
    @Getter @Setter public Parametros tipoPlanCapacitacionExterna;
    @Getter @Setter public Parametros estadoSolicitudCapacitacionPendiente;
    @Getter @Setter public Parametros estadoSolicitudCapacitacionEnviado;
    @Getter @Setter public Parametros estadoPlanCapacitacionEnProceso;
    @Getter @Setter public Parametros estadoPlanCapacitacionPendiente;
    @Getter @Setter public Parametros estadoPlanCapacitacionAprobado;
    @Getter @Setter public Parametros estadoPlanCapacitacionObservado;
    
    
    @Getter @Setter public Parametros tipoRecursoMaterialEscritorio;
    @Getter @Setter public Parametros tipoRecursoMaterialRecursoInformatico;
    @Getter @Setter public Parametros tipoRecursoPerfilCapacitador;
    
    @Getter @Setter public Parametros estadoCapacitacionPendiente;
    @Getter @Setter public Parametros estadoCapacitacionProgramado;
    
    @Getter @Setter public Parametros estadoSolicitudCapacitacionEnProceso;
    @Getter @Setter public Parametros estadoSolicitudCapacitacionAprobado;
    
    @PostConstruct
    public void init() {
        
        tipoModalidadInductivo = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.TIPO_MODALIDAD.getValue(), 
                TipoCapacitacion.INDUCTIVO.getValue());
        
        tipoModalidadCorrectivo = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.TIPO_MODALIDAD.getValue(), 
                TipoCapacitacion.CORRECTIVO.getValue());
        
        tipoModalidadEspecializacion = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.TIPO_MODALIDAD.getValue(), 
                TipoCapacitacion.ESPECIALIZACION.getValue());
        
        
        tipoFormacionCurso = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.TIPO_FORMACION.getValue(), 
                TipoFormacion.CURSO.getValue());
        
        tipoFormacionTaller = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.TIPO_FORMACION.getValue(), 
                TipoFormacion.TALLER.getValue());
        
        tipoPlanCapacitacionInterna = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.TIPO_PLAN_PLANIFICACION.getValue(), 
                TipoPlanPlanificacion.INTERNO.getValue());
        tipoPlanCapacitacionExterna = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.TIPO_PLAN_PLANIFICACION.getValue(), 
                TipoPlanPlanificacion.EXTERNO.getValue());
        
        estadoSolicitudCapacitacionPendiente = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.ESTADO_SOLICITUD_CAPACITACION.getValue(), 
                EstadoSolicitudCapacitacionEnum.PENDIENTE.getValue());
        
        estadoSolicitudCapacitacionEnviado = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.ESTADO_SOLICITUD_CAPACITACION.getValue(), 
                EstadoSolicitudCapacitacionEnum.ENVIADO.getValue());
        
        estadoSolicitudCapacitacionEnProceso = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.ESTADO_SOLICITUD_CAPACITACION.getValue(), 
                EstadoSolicitudCapacitacionEnum.EN_PROCESO.getValue());

        estadoSolicitudCapacitacionAprobado = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.ESTADO_SOLICITUD_CAPACITACION.getValue(), 
                EstadoSolicitudCapacitacionEnum.APROBADO.getValue());        
        
        estadoPlanCapacitacionPendiente = parametrosFacade.findByGrupoCodigo(
                GrupoParametrosEnum.ESTADO_PLAN_CAPACITACION.getValue(), EstadoPlanCapacitacion.PENDIENTE.getValue());
        estadoPlanCapacitacionEnProceso = parametrosFacade.findByGrupoCodigo(
                GrupoParametrosEnum.ESTADO_PLAN_CAPACITACION.getValue(), 
                EstadoPlanCapacitacion.EN_PROCESO.getValue());
        
        estadoPlanCapacitacionAprobado = parametrosFacade.findByGrupoCodigo(
                GrupoParametrosEnum.ESTADO_PLAN_CAPACITACION.getValue(), 
                EstadoPlanCapacitacion.APROBADO.getValue());
        
        estadoPlanCapacitacionObservado = parametrosFacade.findByGrupoCodigo(
                GrupoParametrosEnum.ESTADO_PLAN_CAPACITACION.getValue(), 
                EstadoPlanCapacitacion.OBSERVADO.getValue());
        
        tipoRecursoMaterialEscritorio = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.TIPO_RECURSO.getValue(), TipoRecurso.MATERIAL_ESCRITORIO.getValue());
        tipoRecursoMaterialRecursoInformatico = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.TIPO_RECURSO.getValue(), TipoRecurso.RECURSO_INFORMATICO.getValue());
        tipoRecursoPerfilCapacitador = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.TIPO_RECURSO.getValue(), TipoRecurso.PERFIL.getValue());
        
        estadoCapacitacionPendiente = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.ESTADO_CAPACITACION.getValue(), EstadoCapacitacion.PENDIENTE.getValue());
        estadoCapacitacionProgramado = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.ESTADO_CAPACITACION.getValue(), EstadoCapacitacion.PROGRAMADO.getValue());
        
    }
    
    public ConstanteSingleton() {
    }
}