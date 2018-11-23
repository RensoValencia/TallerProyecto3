package com.upc.indra.dao;

import com.upc.indra.be.GriMarca;
import com.upc.indra.be.GriRecurso;
import com.upc.indra.be.GriTipoRecurso;
import com.upc.indra.bean.util.Constante;
import java.util.ArrayList;
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
    
    public List<GriRecurso> findByTipoRecursoMarcaAndNombre2(GriTipoRecurso griTipoRecurso, GriMarca griMarca, String nombre) {
                List<GriRecurso> listParametros = new ArrayList<>();
    
         
        StringBuilder sql = new StringBuilder();
       
        sql.append("SELECT gr.`ID`, gr.`DESCRIPCION`, gr.`PROCESADOR`, gr.`MEMORIA_RAM`, gm.id, gm.nombre, tr.id, tr.nombre ");
        sql.append("FROM gri_recurso gr ");
        sql.append("INNER JOIN gri_marca gm ON gm.`ID` = gr.`ID_GRI_MARCA` ");
        sql.append("INNER JOIN gri_tipo_recurso tr ON tr.`ID` = gr.`ID_GRI_TIPO_RECURSO` ");
        sql.append("WHERE 1 = 1 ");
        
        if(null != griTipoRecurso) {
            sql.append(" AND tr.ID = ").append(griTipoRecurso.getId()).append(" ");
        }
        
        if(null != griMarca) {
            sql.append(" AND gm.ID = ").append(griMarca.getId()).append(" ");
        }
        
        if(!Constante.STR_VACIO.equals(nombre)) {
            sql.append(" AND gr.`DESCRIPCION` LIKE '%").append(nombre).append("%' ");
        }
                
        try {
            Query query = em.createNativeQuery(sql.toString());
            List<Object[]> listResult = (List<Object[]>) query.getResultList();
            
            GriRecurso griRecurso;
            for(Object[] result: listResult) {
                griRecurso = new GriRecurso(
                        (Integer) result[0],
                        (String) result[1],
                        (String) result[2],
                        (String) result[3],
                        (Integer) result[4],
                        (String) result[5],
                        (Integer) result[6],
                        (String) result[7]
                );
                listParametros.add(griRecurso);
            }
            
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