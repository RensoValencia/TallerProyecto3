package com.upc.indra.dao;

import com.upc.indra.be.Parametros;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author RENSO
 * @date 24-abr-2018
 */
@Stateless
public class ParametrosFacade extends AbstractFacade<Parametros> {

    @PersistenceContext(unitName = "TallerProyecto3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParametrosFacade() {
        super(Parametros.class);
    }
    
    public Parametros findById(Integer id) {
    
        Parametros parametros = null;
        try {
            Query query = em.createNamedQuery("Parametros.findById");
            query.setParameter("id", id);
            
            parametros = (Parametros) query.getSingleResult();
            
        }catch(Exception e) {
            e.printStackTrace();
            parametros = null;
        }
        return parametros;
    }
    
    public List<Parametros> findByGrupo(Integer grupo) {
        List<Parametros> listParametros = null;
        try {
            Query query = em.createNamedQuery("Parametros.findByGrupo");
            query.setParameter("grupo", grupo);
            
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
    
    public Parametros findByGrupoCodigo(Integer grupo, Integer codigo) {
        Parametros parametros = null;
        try {
            Query query = em.createNamedQuery("Parametros.findByGrupoCodigo");
            query.setParameter("grupo", grupo);
            query.setParameter("codigo", codigo);
            
            parametros = (Parametros) query.getSingleResult();
            
        }catch(Exception e) {
            e.printStackTrace();
            parametros = null;
        }
        return parametros;
    }

}