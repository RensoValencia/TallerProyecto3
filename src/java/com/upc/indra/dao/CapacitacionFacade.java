package com.upc.indra.dao;

import com.upc.indra.be.Capacitacion;
import com.upc.indra.be.Parametros;
import com.upc.indra.be.PlanCapacitacion;
import com.upc.indra.be.RecursoCapacitacion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

/**
 * @author RENSO
 * @date 24-abr-2018
 */
@Stateless
public class CapacitacionFacade extends AbstractFacade<Capacitacion> {

    @PersistenceContext(unitName = "TallerProyecto3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CapacitacionFacade() {
        super(Capacitacion.class);
    }
    
    public void actualizarTodos(List<Capacitacion> listCapacitacion) {
        for(Capacitacion ca: listCapacitacion) {
            CriteriaBuilder criteriaBuilder = this.em.getCriteriaBuilder();
            CriteriaUpdate<Capacitacion> updateSedePaquete = null;
            Root entitySedePaquete = null;

            updateSedePaquete = criteriaBuilder.createCriteriaUpdate(Capacitacion.class);
            entitySedePaquete = updateSedePaquete.from(Capacitacion.class);

            updateSedePaquete.set("fechaInicio", ca.getFechaInicio());
            updateSedePaquete.set("fechaFin", ca.getFechaFin());

            updateSedePaquete.where(criteriaBuilder.equal(entitySedePaquete.get("id"), ca.getId()));
            this.em.createQuery(updateSedePaquete).executeUpdate();
        }
    }
    
    public void eliminar(Capacitacion capacitacion) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaDelete<Capacitacion> delete = cb.createCriteriaDelete(Capacitacion.class);
        Root e = delete.from(Capacitacion.class);
        delete.where(cb.equal(e.get("id"), capacitacion.getId()));
        this.em.createQuery(delete).executeUpdate();
    }
    
    public List<Capacitacion> findByIdPlanCapacitacion(PlanCapacitacion planCapacitacion) {
         List<Capacitacion> listParametros = null;
        try {
            Query query = em.createNamedQuery("Capacitacion.findByIdPlanCapacitacion");
            query.setParameter("planCapacitacion", planCapacitacion);
            
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
    
    public List<Capacitacion> findByIdEsp(Parametros tipoFormacion) {
         List<Capacitacion> listParametros = null;
        try {
            Query query = em.createNamedQuery("Capacitacion.findByIdEsp");
            query.setParameter("tipoFormacion", tipoFormacion);
            
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
    
    //@NamedQuery(name = "Capacitacion.findByIdEsp1", 
    //query = "SELECT c FROM Capacitacion c WHERE c.idDetSol.idFormacion.tipoFormacion = :tipoFormacion AND c.idPlanCapacitacion = :idPlan"),
   
    public List<Capacitacion> findByIdEsp1(Parametros tipoFormacion, PlanCapacitacion planCap) {
         List<Capacitacion> listParametros = null;
        try {
            Query query = em.createNamedQuery("Capacitacion.findByIdEsp1");
            query.setParameter("tipoFormacion", tipoFormacion);
            query.setParameter("idPlan", planCap);
            
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
    
    
}