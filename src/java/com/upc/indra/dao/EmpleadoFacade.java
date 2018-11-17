package com.upc.indra.dao;

import com.upc.indra.be.Area;
import com.upc.indra.be.Empleado;
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
public class EmpleadoFacade extends AbstractFacade<Empleado> {

    @PersistenceContext(unitName = "TallerProyecto3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadoFacade() {
        super(Empleado.class);
    }
    
    public List<Empleado> findByIdArea(Area idArea) {
        List<Empleado> listEmpleado = null;
        try {
            Query query = em.createNamedQuery("Empleado.findByIdArea");
            query.setParameter("idArea", idArea);
            
            listEmpleado = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listEmpleado = null;
        }
        return listEmpleado;
    }
}