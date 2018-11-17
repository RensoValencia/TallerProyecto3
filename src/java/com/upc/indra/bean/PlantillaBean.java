package com.upc.indra.bean;

import com.upc.indra.be.Usuario;
import com.upc.indra.bean.util.ControladorAbstracto;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author RENSO
 */
@Named(value="plantillaBean")
@ViewScoped
public class PlantillaBean implements Serializable{

    @Getter @Setter private Usuario usuario;
    
    public PlantillaBean() {
    }
    
    @PostConstruct
    public void init() {
        
    }
    
    public void verificarSesion() {
    
        try {
            usuario = (Usuario) ControladorAbstracto.getSessionProperty("user");

            if(null == usuario) {
                ControladorAbstracto.getExternalContext().redirect("../login.xhtml");
            }
        } catch(Exception e) {
            
        }
        
    }
}