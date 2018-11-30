package com.upc.indra.dao;

import com.upc.indra.be.Capacitacion;
import com.upc.indra.be.DetalleSolicitud;
import com.upc.indra.be.GriRecurso;
import com.upc.indra.be.MaterialesEscritorio;
import com.upc.indra.be.Parametros;
import com.upc.indra.be.PlanCapacitacion;
import com.upc.indra.be.RecursoCapacitacion;
import com.upc.indra.bean.util.Constante;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
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

    
    @Inject private ConstanteSingleton constanteSingleton;
    
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
    
    public List<PlanCapacitacion> findByPeriodo2(Integer periodo) {
        List<PlanCapacitacion> listParametros = null;
        try {
            Query query = em.createNamedQuery("PlanCapacitacion.findByPeriodo");
            query.setParameter("periodo", periodo);
            
            listParametros = query.getResultList();
            
        } catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
    
    public List<PlanCapacitacion> findByEstado2(Integer estado) {
        List<PlanCapacitacion> listParametros = null;
        try {
            Query query = em.createNamedQuery("PlanCapacitacion.findByEstado");
            query.setParameter("estado", estado);
            
            listParametros = query.getResultList();
            
        } catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
    
    public PlanCapacitacion findByEstado(Integer estado) {
        PlanCapacitacion listParametros = null;
        try {
            Query query = em.createNamedQuery("PlanCapacitacion.findByEstado");
            query.setParameter("estado", estado);
            
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
    
    public List<PlanCapacitacion> findByTipoPeriodoAndEstado2(Integer periodo, Parametros estado) {
        List<PlanCapacitacion> listParametros = new ArrayList<>();
        
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT PC.ID, LPAD(PC.ID, 8, '0'), PC.PERIODO, PC.FECHA_ELABORACION, PC.FECHA_EJECUCION, PC.ESTADO ");
        sql.append(",EST.DESCRIPCION, PC.FECHA_APROBACION, PC.OBSERVACION ");
        sql.append("FROM CA_PLAN_CAPACITACION PC ");
        sql.append("INNER JOIN CA_PARAMETROS EST ON EST.ID = PC.ESTADO ");
        
        if(Constante.SIN_PERIODO.compareTo(periodo) != 0 || null != estado) {
            sql.append("WHERE 1 = 1 ");
        }

        if(Constante.SIN_PERIODO.compareTo(periodo) != 0) {
             sql.append(" AND PC.PERIODO = ").append(periodo).append(" ");
        }

        if (null != estado) {
            sql.append(" AND PC.ESTADO = ").append(estado.getId()).append(" ");
        } 
        
        sql.append("ORDER BY PC.FECHA_CREACION DESC ");
        
        try {
            Query query = em.createNativeQuery(sql.toString());
            List<Object[]> listResult = (List<Object[]>) query.getResultList();
            
            PlanCapacitacion planCapacitacion;
            for(Object[] result: listResult) {
                planCapacitacion = new PlanCapacitacion(
                        (Integer) result[0],
                        (String) result[1], 
                        (Integer) result[2],
                        (Date) result[3],
                        (Date) result[4],
                        (Integer) result[5],
                        (String) result[6],
                        (Date) result[7],
                        (String) result[8]
                );
                listParametros.add(planCapacitacion);
            }
            
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
        
        for(DetalleSolicitud detSoll: detalleSolicitudSeleccionadas) {
            Capacitacion capa = new Capacitacion();
            capa.setFechaInicio(detSoll.getFechaInicio());
            capa.setFechaFin(detSoll.getFechaFin());
            capa.setEstado(constanteSingleton.getEstadoCapacitacionPendiente());
            capa.setIdDetSol(detSoll);
            capa.setIdPlanCapacitacion(planCapacitacion);
            em.persist(capa);
        }
            
        for(MaterialesEscritorio me: materialEscritorioSeleccionados) {
            RecursoCapacitacion recCap = new RecursoCapacitacion();
            recCap.setCantidad(me.getCantidad());
            recCap.setValor(me.getValor());
            recCap.setIdTipoRecurso(constanteSingleton.getTipoRecursoMaterialEscritorio());
            recCap.setIdObjeto(me.getId());
            recCap.setIdPlanificacion(planCapacitacion);
            em.persist(recCap);
        } 
            
        for(GriRecurso me: griRecursoSeleccionados) {
            RecursoCapacitacion recCap = new RecursoCapacitacion();
            recCap.setCantidad(me.getCantidadTempl());
            recCap.setValor(me.getValor());
            recCap.setIdTipoRecurso(constanteSingleton.getTipoRecursoMaterialRecursoInformatico());
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
                capa.setEstado(constanteSingleton.getEstadoCapacitacionPendiente());
                capa.setIdDetSol(detSoll);
                capa.setIdPlanCapacitacion(planCapacitacion);
                em.persist(capa);
            }
            
            for(MaterialesEscritorio me: materialEscritorioSeleccionados) {
                RecursoCapacitacion recCap = new RecursoCapacitacion();
                recCap.setCantidad(me.getCantidad());
                recCap.setValor(me.getValor());
                recCap.setIdTipoRecurso(constanteSingleton.getTipoRecursoMaterialEscritorio());
                recCap.setIdObjeto(me.getId());
                recCap.setIdPlanificacion(planCapacitacion);
                em.persist(recCap);
            } 
            
            for(GriRecurso me: griRecursoSeleccionados) {
                RecursoCapacitacion recCap = new RecursoCapacitacion();
                recCap.setCantidad(me.getCantidadTempl());
                recCap.setValor(me.getValor());
                recCap.setIdTipoRecurso(constanteSingleton.getTipoRecursoMaterialRecursoInformatico());
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
    
    public List<PlanCapacitacion> findByTipoPeriodo(Integer periodo) {
        List<PlanCapacitacion> listParametros = null;
        try {
            //Query query = em.createNamedQuery("PlanCapacitacion.findByPeriodo");
            Query query = em.createQuery("SELECT p FROM PlanCapacitacion p where p.periodo = :periodo and p.estado.id = 36");
            query.setParameter("periodo", periodo);
            
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
}