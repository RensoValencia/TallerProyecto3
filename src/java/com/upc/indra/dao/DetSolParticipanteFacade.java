package com.upc.indra.dao;

import com.upc.indra.be.DetSolParticipante;
import com.upc.indra.be.DetalleSolicitud;
import com.upc.indra.be.Empleado;
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
public class DetSolParticipanteFacade extends AbstractFacade<DetSolParticipante> {

    @PersistenceContext(unitName = "TallerProyecto3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetSolParticipanteFacade() {
        super(DetSolParticipante.class);
    }
    
    public void eliminarParticipante(DetSolParticipante dsp, DetalleSolicitud detSol) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaDelete<DetSolParticipante> delete = cb.createCriteriaDelete(DetSolParticipante.class);
        delete = cb.createCriteriaDelete(DetSolParticipante.class);
        Root e = delete.from(DetSolParticipante.class);
        delete.where(cb.equal(e.get("id"), dsp.getId()));
        this.em.createQuery(delete).executeUpdate();
        
        detSol.setNumeroParticipantes(detSol.getNumeroParticipantes() - 1);
        
        em.merge(detSol);        
    }
    
    public List<DetSolParticipante> findByDetSol(DetalleSolicitud ds) {
        List<DetSolParticipante> listParametros = null;
        try {
            Query query = em.createNamedQuery("DetSolParticipante.findByDetSol");
            query.setParameter("detSolicitud", ds);
            
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
    
    public void guardar(DetalleSolicitud ds, List<Empleado> listParticipante) {
        
        for(Empleado dd: listParticipante) {
            DetSolParticipante ddd = new DetSolParticipante();
            ddd.setIdDetSolicitud(ds);
            ddd.setIdEmpleado(dd); 
            em.persist(ddd);
        }
        
        ds.setNumeroParticipantes(ds.getNumeroParticipantes() + listParticipante.size());
        em.merge(ds);
    }

}