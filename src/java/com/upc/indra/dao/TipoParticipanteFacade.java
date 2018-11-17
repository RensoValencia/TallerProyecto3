package com.upc.indra.dao;

import com.upc.indra.be.TipoParticipante;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author RENSO
 * @date 06-nov-2018
 */
@Stateless
public class TipoParticipanteFacade extends AbstractFacade<TipoParticipante> {

    @PersistenceContext(unitName = "TallerProyecto3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoParticipanteFacade() {
        super(TipoParticipante.class);
    }

}