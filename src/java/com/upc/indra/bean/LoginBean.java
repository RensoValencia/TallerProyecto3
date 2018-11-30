package com.upc.indra.bean;

import com.upc.indra.be.Usuario;
import com.upc.indra.bean.util.ControladorAbstracto;
import com.upc.indra.bean.util.JsfUtil;
import com.upc.indra.bean.util.UtilSeguridad;
import com.upc.indra.dao.UsuarioFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author RENSO
 */
@Named(value="loginBean")
@ViewScoped
public class LoginBean implements Serializable{

    @Getter @Setter private Usuario usuario;
    @Inject private UsuarioFacade usuarioFacade;
    
    public LoginBean() {
    }
    
    @PostConstruct
    public void initLoginBean() {
        usuario = new Usuario();
    }
    
    public void iniciarSession() {
    
        String redireccion = "";
        
        try {
            if(usuario.getNombre() == null || usuario.getNombre().equals("")) {
                JsfUtil.addErrorMessage("Por favor ingrese su usuario.");
            } else if(usuario.getClave() == null && usuario.getClave().equals("")) {
                JsfUtil.addErrorMessage("Por favor ingrese su clave.");
            } else {
                Usuario user = usuarioFacade.findByUserAndPass(usuario.getNombre(), 
                        UtilSeguridad.encriptar(usuario.getClave()));

                if(null != user) {
                    ControladorAbstracto.setSessionProperty("user", user);
                    if(user.getIdEmpleado().getIdRol().getNivel().compareTo(new Integer("3")) == 0) {//RR.HH
                        ControladorAbstracto.getExternalContext().redirect("/TallerProyecto3/faces/usuario/List.xhtml");
                    } else if(user.getIdEmpleado().getIdRol().getNivel().compareTo(new Integer("1")) == 0) {//SISTEMAS
                        ControladorAbstracto.getExternalContext().redirect("/TallerProyecto3/faces/actualizarSolicitudCapacitacion/frmListadoSolicitudCapacitacion.xhtml");
                    } else if(user.getIdEmpleado().getIdRol().getNivel().compareTo(new Integer("5")) == 0){//otras areas
                        ControladorAbstracto.getExternalContext().redirect("/TallerProyecto3/faces/materialesEscritorio/List.xhtml");
                    } else if(user.getIdEmpleado().getIdRol().getNivel().compareTo(new Integer("6")) == 0){//otras areas
                        ControladorAbstracto.getExternalContext().redirect("/TallerProyecto3/faces/griRecurso/List.xhtml");
                    } else if(user.getIdEmpleado().getIdRol().getNivel().compareTo(new Integer("7")) == 0){
                        ControladorAbstracto.getExternalContext().redirect("/TallerProyecto3/faces/formacion/List.xhtml");
                    } else {
                        ControladorAbstracto.getExternalContext().redirect("/TallerProyecto3/faces/actualizarPlanPlanificacion/frmListadoPlanCapacitacion.xhtml");
                    }

                } else {
                    JsfUtil.addErrorMessage("Sus credenciales son incorrectas.");
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void cerrarSesion() {
        ControladorAbstracto.getExternalContext().invalidateSession();
    }
}