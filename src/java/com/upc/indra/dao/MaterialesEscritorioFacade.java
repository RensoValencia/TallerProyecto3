package com.upc.indra.dao;

import com.upc.indra.be.MarcaMaterial;
import com.upc.indra.be.MaterialesEscritorio;
import com.upc.indra.be.TipoMaterial;
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
    
    public List<MaterialesEscritorio> findByTipMatAndMarEsc2(TipoMaterial tipoMaterial, MarcaMaterial marcaMaterial, String descripcion) {
        List<MaterialesEscritorio> listParametros = new ArrayList<>();
        
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT me.`ID`, me.`CODIGO`, me.`DESCRIPCION`, me.`ID_TIPO_MAT`, tm.`NOMBRE`, me.`ID_MARCA`, mm.`NOMBRE` ");
        sql.append("FROM ca_materiales_escritorio me ");
        sql.append("INNER JOIN ca_tipo_material tm ON tm.`ID` = me.`ID_TIPO_MAT` ");
        sql.append("INNER JOIN ca_marca_material mm ON mm.`ID` = me.`ID_MARCA` ");
        sql.append("WHERE 1 = 1 ");
        
        if(null != tipoMaterial) {
            sql.append(" AND me.`ID_TIPO_MAT` = ").append(tipoMaterial.getId()).append(" ");
        } 
        
        if(null != marcaMaterial) {
            sql.append(" AND me.`ID_MARCA` = ").append(marcaMaterial.getId()).append(" ");
        }
        
        if(!Constante.STR_VACIO.equals(descripcion)) {
            sql.append(" AND me.`DESCRIPCION` LIKE '%").append(descripcion).append("%' ");
        }
        
        try {
            Query query = em.createNativeQuery(sql.toString());
            List<Object[]> listResult = (List<Object[]>) query.getResultList();
            
            MaterialesEscritorio matEsc;
            for(Object[] result: listResult) {
                matEsc = new MaterialesEscritorio(
                        (Integer) result[0],
                        (String) result[1],
                        (String) result[2],
                        (Integer) result[3],
                        (String) result[4],
                        (Integer) result[5],
                        (String) result[6]
                );
                listParametros.add(matEsc);
            }
            
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