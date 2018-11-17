package com.upc.indra.dao;

import com.upc.indra.be.Area;
import com.upc.indra.be.DetSolParticipante;
import com.upc.indra.be.DetalleSolicitud;
import com.upc.indra.be.Parametros;
import com.upc.indra.be.SolicitudCapacitacion;
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
 * @date 06-nov-2018
 */
@Stateless
public class DetalleSolicitudFacade extends AbstractFacade<DetalleSolicitud> {

    @PersistenceContext(unitName = "TallerProyecto3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleSolicitudFacade() {
        super(DetalleSolicitud.class);
    }
    
    public void actualizar(DetalleSolicitud ds) {
        em.merge(ds);
    }
    
    public List<DetalleSolicitud> findBySolCap(SolicitudCapacitacion solCap) {
        List<DetalleSolicitud> listParametros = null;
        try {
            Query query = em.createNamedQuery("DetalleSolicitud.findBySolCap");
            query.setParameter("solCap", solCap);
            
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
    
    public List<DetalleSolicitud> findBySolExterna(SolicitudCapacitacion solCap, Integer idEsp) {
        List<DetalleSolicitud> listParametros = null;
        try {
            Query query = em.createNamedQuery("DetalleSolicitud.findBySolExterna");
            query.setParameter("solCap", solCap);
            query.setParameter("idArea", idEsp);
            
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
    
    public void guardarDetalleSolicitud(DetalleSolicitud ds) {
        em.persist(ds);
    }
    
    public void borrarDetalleSolicitud(DetalleSolicitud ds, List<DetSolParticipante> part) {
        
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        
        if(!part.isEmpty()) {
            for(DetSolParticipante rrrr: part) {
                CriteriaDelete<DetSolParticipante> delete2 = cb.createCriteriaDelete(DetSolParticipante.class);
                delete2 = cb.createCriteriaDelete(DetSolParticipante.class);
                Root e = delete2.from(DetSolParticipante.class);
                delete2.where(cb.equal(e.get("id"), rrrr.getId()));
                this.em.createQuery(delete2).executeUpdate();
            }
        }
        
        CriteriaDelete<DetalleSolicitud> delete = cb.createCriteriaDelete(DetalleSolicitud.class);
        delete = cb.createCriteriaDelete(DetalleSolicitud.class);
        Root e = delete.from(DetalleSolicitud.class);
        delete.where(cb.equal(e.get("id"), ds.getId()));
        this.em.createQuery(delete).executeUpdate();
    }
    
    public List<DetalleSolicitud> findByEstadoTipoCapaAnioYCurso(Parametros estado, Parametros idTipoCapacitacion, 
            Integer periodo, Parametros idTipForm, Area idArea) {
    
        List<DetalleSolicitud> listParametros = null;
        
        try {
            Query query = em.createNamedQuery("DetalleSolicitud.findByEstadoTipoCapaAnioYCurso");
            query.setParameter("estado", estado);
            query.setParameter("idTipCapa", idTipoCapacitacion);
            query.setParameter("periodo", periodo);
            query.setParameter("idTipForm", idTipForm);
            query.setParameter("idArea", idArea);
            
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;                
    }

}