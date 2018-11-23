package com.upc.indra.dao;

import com.upc.indra.be.Area;
import com.upc.indra.be.DetSolParticipante;
import com.upc.indra.be.DetalleSolicitud;
import com.upc.indra.be.Parametros;
import com.upc.indra.be.SolicitudCapacitacion;
import com.upc.indra.bean.util.Constante;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

/**
 * @author RENSO
 * @date 06-nov-2018
 */
@Stateless
public class DetalleSolicitudFacade extends AbstractFacade<DetalleSolicitud> {

    @PersistenceContext(unitName = "TallerProyecto3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleSolicitudFacade() {
        super(DetalleSolicitud.class);
    }
    
    public void actualizar(DetalleSolicitud ds) {
        em.merge(ds);
    }
    
    public List<DetalleSolicitud> findBySolCap(SolicitudCapacitacion solCap) {
        List<DetalleSolicitud> listParametros = null;
        try {
            Query query = em.createNamedQuery("DetalleSolicitud.findBySolCap");
            query.setParameter("solCap", solCap);
            
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
    
    public List<DetalleSolicitud> findBySolExterna(SolicitudCapacitacion solCap, Integer idEsp) {
        List<DetalleSolicitud> listParametros = null;
        try {
            Query query = em.createNamedQuery("DetalleSolicitud.findBySolExterna");
            query.setParameter("solCap", solCap);
            query.setParameter("idArea", idEsp);
            
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
    
    public void guardarDetalleSolicitud(DetalleSolicitud ds) {
        em.persist(ds);
    }
    
    public void borrarDetalleSolicitud(DetalleSolicitud ds, List<DetSolParticipante> part) {
        
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        
        if(!part.isEmpty()) {
            for(DetSolParticipante rrrr: part) {
                CriteriaDelete<DetSolParticipante> delete2 = cb.createCriteriaDelete(DetSolParticipante.class);
                delete2 = cb.createCriteriaDelete(DetSolParticipante.class);
                Root e = delete2.from(DetSolParticipante.class);
                delete2.where(cb.equal(e.get("id"), rrrr.getId()));
                this.em.createQuery(delete2).executeUpdate();
            }
        }
        
        CriteriaDelete<DetalleSolicitud> delete = cb.createCriteriaDelete(DetalleSolicitud.class);
        delete = cb.createCriteriaDelete(DetalleSolicitud.class);
        Root e = delete.from(DetalleSolicitud.class);
        delete.where(cb.equal(e.get("id"), ds.getId()));
        this.em.createQuery(delete).executeUpdate();
    }
    
    public List<DetalleSolicitud> findByEstadoTipoCapaAnioYCurso(Parametros estado, Parametros idTipoCapacitacion, 
            Integer periodo, Parametros idTipForm, Area idArea) {
    
        List<DetalleSolicitud> listParametros = null;
        
        try {
            Query query = em.createNamedQuery("DetalleSolicitud.findByEstadoTipoCapaAnioYCurso");
            query.setParameter("estado", estado);
            query.setParameter("idTipCapa", idTipoCapacitacion);
            query.setParameter("periodo", periodo);
            query.setParameter("idTipForm", idTipForm);
            query.setParameter("idArea", idArea);
            
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;                
    }
    
    public List<DetalleSolicitud> findByEstadoTipoCapaAnioYCurso2(Parametros estado, Parametros idTipoCapacitacion, 
            Integer periodo, Parametros idTipForm, Area idArea) {
    
        List<DetalleSolicitud> listParametros = new ArrayList<>();
        
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT sc.`ID`, LPAD(SC.`ID`, 8, '0'), SC.`FECHA_DOCUMENTO`, SC.`ID_ESTADO`, est.DESCRIPCION, SC.`FECHA_ATENCION`,");
        sql.append("SC.`OBSERVACION`, SC.`ID_AREA`, SC.`PERIODO`, SC.`ID_TIPO_MODALIDAD`, tc.DESCRIPCION  ");
        sql.append("FROM ca_detalle_solicitud DS ");
        sql.append("INNER JOIN ca_solicitud_capacitacion SC ON SC.`ID` = DS.`ID_SOL_CAP` ");
        sql.append("INNER JOIN ca_parametros tc ON tc.`ID` = sc.`ID_TIPO_MODALIDAD` ");
        sql.append("INNER JOIN ca_parametros est ON est.`ID` = sc.`ID_ESTADO` ");
        sql.append("WHERE sc.`ID_AREA` = ").append(idArea.getId()).append(" "); 
        
        if(null != estado) {
            sql.append(" AND EST.`ID` = ").append(estado.getId()).append(" ");
        }
        
        if(null != idTipoCapacitacion) {
            sql.append(" AND TC.`ID` = ").append(idTipoCapacitacion.getId()).append(" ");
        }
        
        if(Constante.SIN_PERIODO.compareTo(periodo) != 0) {
            sql.append(" AND sc.`PERIODO` = ").append(periodo.intValue()).append(" ");
        }
        
        if(null != idTipForm) {
            sql.append(" AND ds.`ID_FORMACION` = ").append(idTipForm.getId()).append(" ");
        }
        
        try {
            Query query = em.createNativeQuery(sql.toString());
            
            List<Object[]> listResult = (List<Object[]>) query.getResultList();
            
            DetalleSolicitud detalleSolicitud;
            for(Object[] result: listResult) {
                detalleSolicitud = new DetalleSolicitud(
                        (Integer) result[0],
                        (String) result[1], 
                        (Date) result[2],
                        (Integer) result[3],
                        (String) result[4],
                        (Date) result[5],
                        (String) result[6],
                        (Integer) result[7],
                        (Integer) result[8],
                        (Integer) result[9],
                        (String) result[10]
                );
                listParametros.add(detalleSolicitud);
            }
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;                
    }

}