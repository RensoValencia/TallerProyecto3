package com.upc.indra.dao;

import com.upc.indra.be.Capacitacion;
import com.upc.indra.be.Parametros;
import com.upc.indra.be.PlanCapacitacion;
import com.upc.indra.be.RecursoCapacitacion;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

/**
 * @author RENSO
 * @date 06-nov-2018
 */
@Stateless
public class RecursoCapacitacionFacade extends AbstractFacade<RecursoCapacitacion> {

    @Inject private ConstanteSingleton constanteSingleton;
    @PersistenceContext(unitName = "TallerProyecto3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecursoCapacitacionFacade() {
        super(RecursoCapacitacion.class);
    }
    
        public void eliminar(RecursoCapacitacion ds) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaDelete<RecursoCapacitacion> delete = cb.createCriteriaDelete(RecursoCapacitacion.class);
        Root e = delete.from(RecursoCapacitacion.class);
        delete.where(cb.equal(e.get("id"), ds.getId()));
        this.em.createQuery(delete).executeUpdate();
    }
    
    public void guardarPlanAndRecursoCapa(RecursoCapacitacion recCap) {
        em.persist(recCap);
    }
    
    public void actualizarPerfiles(List<Capacitacion> listada) {
    
        System.out.println("listada: " + listada);
        
        for(Capacitacion rrrr: listada) {
        
            if(null != rrrr.getIdRecurso()) {
                CriteriaBuilder criteriaBuilder = this.em.getCriteriaBuilder();
                CriteriaUpdate<RecursoCapacitacion> updateSedePaquete = null;
                Root entitySedePaquete = null;

                updateSedePaquete = criteriaBuilder.createCriteriaUpdate(RecursoCapacitacion.class);
                entitySedePaquete = updateSedePaquete.from(RecursoCapacitacion.class);

                updateSedePaquete.set("valor", rrrr.getValor());
                updateSedePaquete.set("idObjeto", rrrr.getPerfilCapacitador().getId());

                updateSedePaquete.where(criteriaBuilder.equal(entitySedePaquete.get("id"), rrrr.getIdRecurso()));
                this.em.createQuery(updateSedePaquete).executeUpdate();
            } else {
                RecursoCapacitacion ff = new RecursoCapacitacion();
                ff.setIdPlanificacion(rrrr.getIdPlanCapacitacion());
                ff.setIdObjeto(rrrr.getPerfilCapacitador().getId());
                ff.setCantidad(1);
                ff.setValor(rrrr.getValor());
                ff.setIdTipoRecurso(constanteSingleton.getTipoRecursoPerfilCapacitador());
                em.persist(ff);
            }
            
        }
        
    }
    
    public void guardarPerfiles(PlanCapacitacion plan, List<Capacitacion> listada) {
        
        for(Capacitacion caaa: listada) {
            RecursoCapacitacion ff = new RecursoCapacitacion();
            ff.setIdPlanificacion(plan);
            ff.setIdObjeto(caaa.getPerfilCapacitador().getId());
            ff.setCantidad(1);
            ff.setValor(caaa.getValor());
            ff.setIdTipoRecurso(constanteSingleton.getTipoRecursoPerfilCapacitador());
            em.persist(ff);
        }
    }
    
    public List<RecursoCapacitacion> findIdPlanCapa(PlanCapacitacion pc) {
         List<RecursoCapacitacion> listParametros = null;
        try {
            Query query = em.createNamedQuery("RecursoCapacitacion.findIdPlanCapa");
            query.setParameter("idPlani", pc);
            
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
    
    public List<RecursoCapacitacion> findByIdPlanAndIdTipoRecurso(PlanCapacitacion idPlan, Parametros idTipoRecurso) {
         List<RecursoCapacitacion> listParametros = null;
        try {
            Query query = em.createNamedQuery("RecursoCapacitacion.findByIdPlanAndIdTipoRecurso");
            query.setParameter("idPlan", idPlan);
            query.setParameter("idTipoRecurso", idTipoRecurso);
            
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
    
    public void actualizar(List<RecursoCapacitacion> listRecursoCapacitacion) {
        
        for(RecursoCapacitacion rc: listRecursoCapacitacion) {
            CriteriaBuilder criteriaBuilder = this.em.getCriteriaBuilder();
            CriteriaUpdate<RecursoCapacitacion> updateSedePaquete = null;
            Root entitySedePaquete = null;

            updateSedePaquete = criteriaBuilder.createCriteriaUpdate(RecursoCapacitacion.class);
            entitySedePaquete = updateSedePaquete.from(RecursoCapacitacion.class);

            updateSedePaquete.set("valor", rc.getValor());
            updateSedePaquete.set("cantidad", rc.getCantidad());
            updateSedePaquete.set("idTipoRecurso", rc.getIdTipoRecurso());
            updateSedePaquete.set("idObjeto", rc.getIdObjeto());
            

            updateSedePaquete.where(criteriaBuilder.equal(entitySedePaquete.get("id"), rc.getId()));
            this.em.createQuery(updateSedePaquete).executeUpdate();
                    }
    }

}