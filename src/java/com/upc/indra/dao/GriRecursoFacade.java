package com.upc.indra.dao;

import com.upc.indra.be.GriRecurso;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
/**
 * @author RENSO
 * @date 06-nov-2018
 */
@Stateless
public class GriRecursoFacade extends AbstractFacade<GriRecurso> {

    @PersistenceContext(unitName = "TallerProyecto3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GriRecursoFacade() {
        super(GriRecurso.class);
    }
    

    public List<GriRecurso> findByTipoRecursoMarcaAndNombre(Integer id1, Integer id2, String nombre) {
                List<GriRecurso> listParametros = null;
        try {
            Query query = em.createNamedQuery("GriRecurso.findByTipoRecursoMarcaAndNombre");
            query.setParameter("id1", id1);
            query.setParameter("id2", id2);
            query.setParameter("id3", "%" + nombre + "%");
            
            listParametros = (List<GriRecurso>) query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
    
    public GriRecurso findById(Integer id) {
        GriRecurso parametros = null;
        try {
            Query query = em.createNamedQuery("GriRecurso.findById");
            query.setParameter("id", id);
            
            parametros = (GriRecurso) query.getSingleResult();
            
        }catch(Exception e) {
            e.printStackTrace();
            parametros = null;
        }
        return parametros;
    }
}