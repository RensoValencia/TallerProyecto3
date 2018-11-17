package com.upc.indra.dao;

import com.upc.indra.be.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author RENSO
 * @date 06-nov-2018
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "TallerProyecto3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public Usuario findByUserAndPass(String usuario, String clave) {
        Usuario parametros = null;
        try {
            Query query = em.createNamedQuery("Usuario.findByUserAndPass");
            query.setParameter("usuario", usuario);
            query.setParameter("clave", clave);
            
            parametros = (Usuario) query.getSingleResult();
            
        }catch(Exception e) {
            e.printStackTrace();
            parametros = null;
        }
        return parametros;
    }

}