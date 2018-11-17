package com.upc.indra.bean;

import com.upc.indra.be.Parametros;
import com.upc.indra.bean.util.JsfUtil;
import com.upc.indra.bean.util.JsfUtil.PersistAction;
import com.upc.indra.dao.ParametrosFacade;
import com.upc.indra.enu.GrupoParametrosEnum;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("parametrosController")
@SessionScoped
public class ParametrosController implements Serializable {

    @EJB
    private com.upc.indra.dao.ParametrosFacade ejbFacade;
    private List<Parametros> items = null;
    private Parametros selected;

    public ParametrosController() {
    }

    public Parametros getSelected() {
        return selected;
    }

    public void setSelected(Parametros selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ParametrosFacade getFacade() {
        return ejbFacade;
    }

    public Parametros prepareCreate() {
        selected = new Parametros();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ParametrosCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ParametrosUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ParametrosDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Parametros> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Parametros getParametros(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Parametros> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Parametros> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    public List<Parametros> getListEstadoSolicitud() {
        return getFacade().findByGrupo(GrupoParametrosEnum.ESTADO_SOLICITUD_CAPACITACION.getValue());
    }
    
    public List<Parametros> getListTipoModalidad() {
        return getFacade().findByGrupo(GrupoParametrosEnum.TIPO_MODALIDAD.getValue());
    }
    
    public List<Parametros> getListTipoFormacion() {
        return getFacade().findByGrupo(GrupoParametrosEnum.TIPO_FORMACION.getValue());
    }
    
    public List<Parametros> getListTipoSala() {
        return getFacade().findByGrupo(GrupoParametrosEnum.TIPO_SALA.getValue());
    }
    
    public List<Parametros> getListTipoCapacitacion() {
        return getFacade().findByGrupo(GrupoParametrosEnum.TIPO_PLAN_PLANIFICACION.getValue());
    }
    
    public List<Parametros> getListEstadoTipoCapacitacion() {
        return getFacade().findByGrupo(GrupoParametrosEnum.ESTADO_PLAN_CAPACITACION.getValue());
    }
    
    public List<Parametros> getListFormacionAcademica() {
        return getFacade().findByGrupo(GrupoParametrosEnum.FORMACION_ACADEMICA.getValue());
    }
    
    public List<Parametros> getListGradoAcademico() {
        return getFacade().findByGrupo(GrupoParametrosEnum.GRADO_ACADEMICO.getValue());
    }

    @FacesConverter(forClass = Parametros.class)
    public static class ParametrosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ParametrosController controller = (ParametrosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "parametrosController");
            return controller.getParametros(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Parametros) {
                Parametros o = (Parametros) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Parametros.class.getName()});
                return null;
            }
        }

    }

}
