package com.upc.indra.dao;

import com.upc.indra.be.Capacitacion;
import com.upc.indra.be.DetalleSolicitud;
import com.upc.indra.be.GriRecurso;
import com.upc.indra.be.MaterialesEscritorio;
import com.upc.indra.be.Parametros;
import com.upc.indra.be.PlanCapacitacion;
import com.upc.indra.be.RecursoCapacitacion;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;
/**
 * @author RENSO
 * @date 24-abr-2018
 */
@Stateless
public class PlanCapacitacionFacade extends AbstractFacade<PlanCapacitacion> {

    @PersistenceContext(unitName = "TallerProyecto3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public void eliminar(PlanCapacitacion pc) {
        
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        
        CriteriaDelete<PlanCapacitacion> delete = cb.createCriteriaDelete(PlanCapacitacion.class);
        delete = cb.createCriteriaDelete(PlanCapacitacion.class);
        Root e = delete.from(PlanCapacitacion.class);
        delete.where(cb.equal(e.get("id"), pc.getId()));
        this.em.createQuery(delete).executeUpdate();
    }

    public PlanCapacitacionFacade() {
        super(PlanCapacitacion.class);
    }
    
    public void modificar(PlanCapacitacion planCapa) {
        em.merge(planCapa);
    }
    
    public void grabarCapa(Capacitacion capa) {
        em.persist(capa);
    }
    
    public void grabarPlanCapa(PlanCapacitacion planCapa, Capacitacion capa) {
        em.persist(planCapa); 
        em.persist(capa); 
    }
    
    public List<PlanCapacitacion> findByTipoFechaElaboracionAndEstado(Integer tipoPlan, 
            Date fechaInicio, Date fechaFin, Integer estado) {
        
        List<PlanCapacitacion> listParametros = null;
        try {
            Query query = em.createNamedQuery("PlanCapacitacion.findByTipoFechaElaboracionAndEstado");
            query.setParameter("tipoPlan", tipoPlan);
            query.setParameter("fechaInicio", fechaInicio);
            query.setParameter("fechaFinal", fechaFin);
            query.setParameter("estado", estado);
            
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
            
    public PlanCapacitacion findByPeriodo(Integer periodo) {
        PlanCapacitacion listParametros = null;
        try {
            Query query = em.createNamedQuery("PlanCapacitacion.findByPeriodo");
            query.setParameter("periodo", periodo);
            
            listParametros = (PlanCapacitacion) query.getSingleResult();
            
        } catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
    
    
    public List<PlanCapacitacion> findByTipoPeriodoAndEstado(Integer periodo, Integer estado) {
        List<PlanCapacitacion> listParametros = null;
        try {
            Query query = em.createNamedQuery("PlanCapacitacion.findByTipoPeriodoAndEstado");
            query.setParameter("perido", periodo);
            query.setParameter("estado", estado);
            
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
    
    public void grabarPlanDeCapacitacion(List<MaterialesEscritorio> materialEscritorioSeleccionados, 
            List<DetalleSolicitud> detalleSolicitudSeleccionadas, PlanCapacitacion planCapacitacion, 
            Parametros tipoRecurso, List<GriRecurso> griRecursoSeleccionados) {
        
        em.persist(planCapacitacion);
        
        //Este es el detalle de capacitacion
            for(DetalleSolicitud detSoll: detalleSolicitudSeleccionadas) {
                Capacitacion capa = new Capacitacion();
                capa.setFechaInicio(detSoll.getFechaInicio());
                capa.setFechaFin(detSoll.getFechaFin());
                capa.setEstado(new Parametros(new Integer("10")));
                capa.setIdDetSol(detSoll);
                capa.setIdPlanCapacitacion(planCapacitacion);
                em.persist(capa);
            }
            
            for(MaterialesEscritorio me: materialEscritorioSeleccionados) {
                RecursoCapacitacion recCap = new RecursoCapacitacion();
                recCap.setCantidad(me.getCantidad());
                recCap.setValor(me.getValor());
                recCap.setIdTipoRecurso(new Parametros(new Integer("30")));
                recCap.setIdObjeto(me.getId());
                recCap.setIdPlanificacion(planCapacitacion);
                em.persist(recCap);
            } 
            
            for(GriRecurso me: griRecursoSeleccionados) {
                RecursoCapacitacion recCap = new RecursoCapacitacion();
                recCap.setCantidad(me.getCantidadTempl());
                recCap.setValor(me.getValor());
                recCap.setIdTipoRecurso(new Parametros(new Integer("31")));
                recCap.setIdObjeto(me.getId());
                recCap.setIdPlanificacion(planCapacitacion);
                em.persist(recCap);
            } 
    }
    
    public void grabarPlanDeCapacitacionYPerfil(List<MaterialesEscritorio> materialEscritorioSeleccionados, 
            List<DetalleSolicitud> detalleSolicitudSeleccionadas, 
            PlanCapacitacion planCapacitacion, List<DetalleSolicitud> listDetSol, Parametros tipoRecurso, 
            List<GriRecurso> griRecursoSeleccionados) {
        
        em.persist(planCapacitacion);
        
        //Este es el detalle de capacitacion
            for(DetalleSolicitud detSoll: detalleSolicitudSeleccionadas) {
                Capacitacion capa = new Capacitacion();
                capa.setFechaInicio(detSoll.getFechaInicio());
                capa.setFechaFin(detSoll.getFechaFin());
                capa.setEstado(new Parametros(new Integer("10")));
                capa.setIdDetSol(detSoll);
                capa.setIdPlanCapacitacion(planCapacitacion);
                em.persist(capa);
            }
            
            for(MaterialesEscritorio me: materialEscritorioSeleccionados) {
                RecursoCapacitacion recCap = new RecursoCapacitacion();
                recCap.setCantidad(me.getCantidad());
                recCap.setValor(me.getValor());
                recCap.setIdTipoRecurso(new Parametros(new Integer("30")));
                recCap.setIdObjeto(me.getId());
                recCap.setIdPlanificacion(planCapacitacion);
                em.persist(recCap);
            } 
            
            for(GriRecurso me: griRecursoSeleccionados) {
                RecursoCapacitacion recCap = new RecursoCapacitacion();
                recCap.setCantidad(me.getCantidadTempl());
                recCap.setValor(me.getValor());
                recCap.setIdTipoRecurso(new Parametros(new Integer("31")));
                recCap.setIdObjeto(me.getId());
                recCap.setIdPlanificacion(planCapacitacion);
                em.persist(recCap);
            } 
            
            for(DetalleSolicitud detSolll: listDetSol) {
                RecursoCapacitacion recCap2 = new RecursoCapacitacion();
                recCap2.setCantidad(1);
                recCap2.setValor(detSolll.getHonorarios());
                recCap2.setIdTipoRecurso(tipoRecurso);
                recCap2.setIdObjeto(detSolll.getPerfilCapacitador().getId());
                recCap2.setIdPlanificacion(planCapacitacion);
                em.persist(recCap2);
            }
    }
}