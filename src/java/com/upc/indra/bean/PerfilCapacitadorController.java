package com.upc.indra.bean;

import static com.upc.indra.bean.util.Constante.STR_VACIO;
import com.upc.indra.be.PerfilCapacitador;
import com.upc.indra.bean.util.ControladorAbstracto;
import com.upc.indra.bean.util.JsfUtil;
import com.upc.indra.bean.util.JsfUtil.PersistAction;
import com.upc.indra.dao.PerfilCapacitadorFacade;

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
import lombok.Getter;
import lombok.Setter;
/**
 * @author Ivonne Porras
 * @date 14/11/2018
 * @version 1.0
 * @description Permite registrar todas las planificaciones para las solicitudes
 */
@Named("perfilCapacitadorController")
@SessionScoped
public class PerfilCapacitadorController implements Serializable {

    @EJB
    private com.upc.indra.dao.PerfilCapacitadorFacade ejbFacade;
    private List<PerfilCapacitador> items = null;
    private PerfilCapacitador selected;

    @Getter @Setter private String nombre;
    @Getter @Setter private String descripcion;
    
    public PerfilCapacitadorController() {
    }

    public PerfilCapacitador getSelected() {
        return selected;
    }

    public void setSelected(PerfilCapacitador selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PerfilCapacitadorFacade getFacade() {
        return ejbFacade;
    }
    
    public void buscarPerfilCapacitador() {
    
        if(!nombre.equals(STR_VACIO) && !descripcion.equals(STR_VACIO)) {
            items = ejbFacade.findByNombreAndDescripcion(nombre, descripcion);
        } else if(!nombre.equals(STR_VACIO) && descripcion.equals(STR_VACIO)) {
            items = ejbFacade.findByNombre(nombre);
        } else if(nombre.equals(STR_VACIO) && !descripcion.equals(STR_VACIO)) {
            items = ejbFacade.findByDescripcion(descripcion);
        } else {
            items = ejbFacade.findAll();
        }
        
        if(items == null || items.isEmpty()) {
            JsfUtil.addErrorMessage("No es encontraron resultados con los criterios ingresados");
            return;
        }
        
        ControladorAbstracto.updateComponent("PerfilCapacitadorListForm:datalist");
        
    }

    public PerfilCapacitador prepareCreate() {
        selected = new PerfilCapacitador();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PerfilCapacitadorCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PerfilCapacitadorUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PerfilCapacitadorDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<PerfilCapacitador> getItems() {
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

    public PerfilCapacitador getPerfilCapacitador(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<PerfilCapacitador> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PerfilCapacitador> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = PerfilCapacitador.class)
    public static class PerfilCapacitadorControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PerfilCapacitadorController controller = (PerfilCapacitadorController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "perfilCapacitadorController");
            return controller.getPerfilCapacitador(getKey(value));
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
            if (object instanceof PerfilCapacitador) {
                PerfilCapacitador o = (PerfilCapacitador) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PerfilCapacitador.class.getName()});
                return null;
            }
        }

    }

}
