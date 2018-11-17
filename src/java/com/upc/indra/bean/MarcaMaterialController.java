package com.upc.indra.bean;

import com.upc.indra.be.MarcaMaterial;
import com.upc.indra.bean.util.JsfUtil;
import com.upc.indra.bean.util.JsfUtil.PersistAction;
import com.upc.indra.dao.MarcaMaterialFacade;

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

@Named("marcaMaterialController")
@SessionScoped
public class MarcaMaterialController implements Serializable {

    @EJB
    private com.upc.indra.dao.MarcaMaterialFacade ejbFacade;
    private List<MarcaMaterial> items = null;
    private MarcaMaterial selected;

    public MarcaMaterialController() {
    }

    public MarcaMaterial getSelected() {
        return selected;
    }

    public void setSelected(MarcaMaterial selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MarcaMaterialFacade getFacade() {
        return ejbFacade;
    }

    public MarcaMaterial prepareCreate() {
        selected = new MarcaMaterial();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MarcaMaterialCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MarcaMaterialUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MarcaMaterialDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MarcaMaterial> getItems() {
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

    public MarcaMaterial getMarcaMaterial(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<MarcaMaterial> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MarcaMaterial> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = MarcaMaterial.class)
    public static class MarcaMaterialControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MarcaMaterialController controller = (MarcaMaterialController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "marcaMaterialController");
            return controller.getMarcaMaterial(getKey(value));
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
            if (object instanceof MarcaMaterial) {
                MarcaMaterial o = (MarcaMaterial) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MarcaMaterial.class.getName()});
                return null;
            }
        }

    }

}
