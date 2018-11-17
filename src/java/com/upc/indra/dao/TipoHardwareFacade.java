package com.upc.indra.dao;

import com.upc.indra.be.TipoHardware;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author RENSO
 * @date 06-nov-2018
 */
@Stateless
public class TipoHardwareFacade extends AbstractFacade<TipoHardware> {

    @PersistenceContext(unitName = "TallerProyecto3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoHardwareFacade() {
        super(TipoHardware.class);
    }

}