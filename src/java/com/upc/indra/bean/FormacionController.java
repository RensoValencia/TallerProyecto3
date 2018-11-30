package com.upc.indra.bean;

import static com.upc.indra.bean.util.Constante.STR_VACIO;
import com.upc.indra.bean.util.ConstanteMensaje;
import com.upc.indra.be.Formacion;
import com.upc.indra.be.Parametros;
import com.upc.indra.be.Usuario;
import com.upc.indra.bean.util.Constante;
import static com.upc.indra.bean.util.ConstanteMensaje.MESSAGE_ERROR_INESPERADO;
import com.upc.indra.bean.util.ControladorAbstracto;
import com.upc.indra.bean.util.JsfUtil;
import com.upc.indra.bean.util.JsfUtil.PersistAction;
import com.upc.indra.bean.util.UtilList;
import com.upc.indra.dao.FormacionFacade;
import java.io.Serializable;
import java.util.Date;
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
import javax.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
/**
 * @author Gabriel
 * @date 14/11/2018
 * @version 1.0
 * @description Permite registrar todas las planificaciones para las solicitudes
 */
@Named("formacionController")
@SessionScoped
public class FormacionController implements Serializable {

    private Usuario usuarioLogueado;
    
    @EJB private com.upc.indra.dao.FormacionFacade ejbFacade;
    private List<Formacion> items = null;
    @Getter @Setter private Formacion selected;
    
    @Getter @Setter private Formacion formacionBuscar;
    
    @Getter @Setter private String nombre;
    @Getter @Setter private Parametros idTipoFormacion;
    
    @PostConstruct
    public void init() {
        usuarioLogueado = (Usuario) ControladorAbstracto.getSessionProperty(Constante.USER);
        formacionBuscar = new Formacion();
    }

    public void buscarFormacion() {
        
        try {
            if((!nombre.equals(STR_VACIO)) && idTipoFormacion != null) {
                items = ejbFacade.findByNombreAndIdTipoFormacion(nombre, idTipoFormacion);
            } else if((!nombre.equals(STR_VACIO)) && idTipoFormacion == null) {
                items = ejbFacade.findByNombre(nombre);
            } else if((nombre.equals(STR_VACIO)) && idTipoFormacion != null) {
                items = ejbFacade.findByIdTipoFormacion(idTipoFormacion);
            } else {
                items = ejbFacade.findAll();
            }

            if(UtilList.isEmpty(items)) {
                JsfUtil.addErrorMessage(ConstanteMensaje.ERR_LIST_VACIO);
                return;
            }
            ControladorAbstracto.updateComponent("FormacionListForm:datalist");
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(e.getMessage() + Constante.STR_GUION + MESSAGE_ERROR_INESPERADO);     
        }
    }
    
    public FormacionController() {
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private FormacionFacade getFacade() {
        return ejbFacade;
    }

    public Formacion prepareCreate() {
        selected = new Formacion();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        
        if(UtilList.isNotEmpty(ejbFacade.findByNombre(selected.getNombre()))) {
            JsfUtil.addErrorMessage("Por favor, Ud. debe ingresar otro curso, el que intenta registrar ya existe"); 
            return;
        }
        ControladorAbstracto.executeJavascript("PF('FormacionCreateDialog').hide();");
        
        selected.setIdUsuarioCreacion(usuarioLogueado);
        selected.setPcCreacion(Constante.HOST_CREACION);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("FormacionCreated"));
        
        if (!JsfUtil.isValidationFailed()) {
            items = null; 
        }
    }

    public void update() {
        selected.setIdUsuarioModificacion(usuarioLogueado);
        selected.setPcModificacion(Constante.HOST_MODIFACION);
        selected.setFechaModificacion(new Date());
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("FormacionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("FormacionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; 
            items = null;
        }
    }

    public List<Formacion> getItems() {
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
                String msg = STR_VACIO;
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

    public Formacion getFormacion(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Formacion> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Formacion> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Formacion.class)
    public static class FormacionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FormacionController controller = (FormacionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "formacionController");
            return controller.getFormacion(getKey(value));
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
            if (object instanceof Formacion) {
                Formacion o = (Formacion) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Formacion.class.getName()});
                return null;
            }
        }
    }
}