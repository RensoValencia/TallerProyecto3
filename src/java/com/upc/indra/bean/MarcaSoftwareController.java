package com.upc.indra.bean;

import com.upc.indra.be.MarcaSoftware;
import com.upc.indra.bean.util.JsfUtil;
import com.upc.indra.bean.util.JsfUtil.PersistAction;
import com.upc.indra.dao.MarcaSoftwareFacade;

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

@Named("marcaSoftwareController")
@SessionScoped
public class MarcaSoftwareController implements Serializable {

    @EJB
    private com.upc.indra.dao.MarcaSoftwareFacade ejbFacade;
    private List<MarcaSoftware> items = null;
    private MarcaSoftware selected;

    public MarcaSoftwareController() {
    }

    public MarcaSoftware getSelected() {
        return selected;
    }

    public void setSelected(MarcaSoftware selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MarcaSoftwareFacade getFacade() {
        return ejbFacade;
    }

    public MarcaSoftware prepareCreate() {
        selected = new MarcaSoftware();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MarcaSoftwareCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MarcaSoftwareUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MarcaSoftwareDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MarcaSoftware> getItems() {
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

    public MarcaSoftware getMarcaSoftware(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<MarcaSoftware> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MarcaSoftware> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = MarcaSoftware.class)
    public static class MarcaSoftwareControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MarcaSoftwareController controller = (MarcaSoftwareController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "marcaSoftwareController");
            return controller.getMarcaSoftware(getKey(value));
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
            if (object instanceof MarcaSoftware) {
                MarcaSoftware o = (MarcaSoftware) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MarcaSoftware.class.getName()});
                return null;
            }
        }

    }

}
