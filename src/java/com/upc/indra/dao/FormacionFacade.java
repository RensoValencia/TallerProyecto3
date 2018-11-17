package com.upc.indra.dao;

import com.upc.indra.be.Formacion;
import com.upc.indra.be.Parametros;
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
public class FormacionFacade extends AbstractFacade<Formacion> {

    @PersistenceContext(unitName = "TallerProyecto3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FormacionFacade() {
        super(Formacion.class);
    }

    public List<Formacion> findByIdArea(Integer idArea) {
        List<Formacion> listParametros = null;
        try {
            Query query = em.createNamedQuery("Formacion.findByIdArea");
            query.setParameter("idArea", idArea);
            
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
    
    public List<Formacion> findByIdAreaAndIdTipoFormacion(Integer idArea, Integer idTipoFormacion) {
        List<Formacion> listParametros = null;
        try {
            Query query = em.createNamedQuery("Formacion.findByIdAreaAndIdTipoFormacion");
            query.setParameter("idArea", idArea);
            query.setParameter("idTipoFormacion", idTipoFormacion);
            
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }

    public List<Formacion> findByIdAreaAndIdTipoModalidad(Integer idArea, Integer idTipoModalidad) {
        List<Formacion> listParametros = null;
        try {
            Query query = em.createNamedQuery("Formacion.findByIdAreaAndIdTipoModalidad");
            query.setParameter("idArea", idArea);
            query.setParameter("idTipoModalidad", idTipoModalidad);
            
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
    
    public List<Formacion> findByIdAreaIdTipoFormacionAndIdTipoModalidad(Integer idArea, Integer idTipoFormacion, Integer idTipoModalidad) {
        List<Formacion> listParametros = null;
        try {
            Query query = em.createNamedQuery("Formacion.findByIdAreaIdTipoFormacionAndIdTipoModalidad");
            query.setParameter("idArea", idArea);
            query.setParameter("idTipoFormacion", idTipoFormacion);
            query.setParameter("idTipoModalidad", idTipoModalidad);
            
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
    
    public List<Formacion> findByNombreAndIdTipoFormacion(String nombre, Parametros idTipoFormacion) {
        List<Formacion> listParametros = null;
        try {
            Query query = em.createNamedQuery("Formacion.findByNombreAndIdTipoFormacion");
            query.setParameter("nombre", "%" + nombre + "%");
            query.setParameter("idTipoFormacion", idTipoFormacion);
            
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
    
    public List<Formacion> findByIdTipoFormacion(Parametros idTipoFormacion) {
        List<Formacion> listParametros = null;
        try {
            Query query = em.createNamedQuery("Formacion.findByIdTipoFormacion");
            query.setParameter("idTipoFormacion", idTipoFormacion);
            
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
    
    public List<Formacion> findByNombre(String nombre) {
        List<Formacion> listParametros = null;
        try {
            Query query = em.createNamedQuery("Formacion.findByNombre");
            query.setParameter("nombre", "%" + nombre + "%");
            
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
    
    @Override
    public List<Formacion> findAll() {
        List<Formacion> listParametros = null;
        try {
            Query query = em.createNamedQuery("Formacion.findAll");
            
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
}