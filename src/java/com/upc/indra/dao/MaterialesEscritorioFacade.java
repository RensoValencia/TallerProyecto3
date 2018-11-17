package com.upc.indra.dao;

import com.upc.indra.be.MarcaMaterial;
import com.upc.indra.be.MaterialesEscritorio;
import com.upc.indra.be.TipoMaterial;
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
public class MaterialesEscritorioFacade extends AbstractFacade<MaterialesEscritorio> {

    @PersistenceContext(unitName = "TallerProyecto3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MaterialesEscritorioFacade() {
        super(MaterialesEscritorio.class);
    }

    public List<MaterialesEscritorio> findByTipMatAndMarEsc(TipoMaterial tipoMaterial, MarcaMaterial marcaMaterial, String descripcion) {
        List<MaterialesEscritorio> listParametros = null;
        try {
            Query query = em.createNamedQuery("MaterialesEscritorio.findByTipMatAndMarEsc");
            query.setParameter("tipoMat", tipoMaterial);
            query.setParameter("marca", marcaMaterial);
            query.setParameter("descripcion", "%" + descripcion + "%");
            
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
    
    public MaterialesEscritorio findById(Integer id) {
        MaterialesEscritorio parametros = null;
        try {
            Query query = em.createNamedQuery("MaterialesEscritorio.findById");
            query.setParameter("id", id);
            
            parametros = (MaterialesEscritorio) query.getSingleResult();
            
        }catch(Exception e) {
            e.printStackTrace();
            parametros = null;
        }
        return parametros;
    }
}