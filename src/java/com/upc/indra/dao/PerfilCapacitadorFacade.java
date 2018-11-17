package com.upc.indra.dao;

import com.upc.indra.be.PerfilCapacitador;
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
public class PerfilCapacitadorFacade extends AbstractFacade<PerfilCapacitador> {

    @PersistenceContext(unitName = "TallerProyecto3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PerfilCapacitadorFacade() {
        super(PerfilCapacitador.class);
    }
    
    public PerfilCapacitador findById(Integer id) {
        PerfilCapacitador parametros = null;
        try {
            Query query = em.createNamedQuery("PerfilCapacitador.findById");
            query.setParameter("id", id);
            
            parametros = (PerfilCapacitador) query.getSingleResult();
            
        }catch(Exception e) {
            e.printStackTrace();
            parametros = null;
        }
        return parametros;
    }
    
    public List<PerfilCapacitador> findByNombreAndDescripcion(String nombre, String descripcion) {
        List<PerfilCapacitador> parametros = null;
        try {
            Query query = em.createNamedQuery("PerfilCapacitador.findByNombreAndDescripcion");
            query.setParameter("nombre", "%" + nombre + "%");
            query.setParameter("descripcion", "%" + descripcion + "%");
            
            parametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            parametros = null;
        }
        return parametros;
    }
    
    public List<PerfilCapacitador> findByDescripcion(String descripcion) {
        List<PerfilCapacitador> parametros = null;
        try {
            Query query = em.createNamedQuery("PerfilCapacitador.findByDescripcion");
            query.setParameter("descripcion", "%" + descripcion + "%");
            
            parametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            parametros = null;
        }
        return parametros;
    }
    
    public List<PerfilCapacitador> findByNombre(String nombre) {
        List<PerfilCapacitador> parametros = null;
        try {
            Query query = em.createNamedQuery("PerfilCapacitador.findByNombre");
            query.setParameter("nombre", "%" + nombre + "%");
            
            parametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            parametros = null;
        }
        return parametros;
    }
    
    @Override
    public List<PerfilCapacitador> findAll() {
        List<PerfilCapacitador> parametros = null;
        try {
            Query query = em.createNamedQuery("PerfilCapacitador.findAll");
            
            parametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            parametros = null;
        }
        return parametros;
    }
}