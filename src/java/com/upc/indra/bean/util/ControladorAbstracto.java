package com.upc.indra.bean.util;

import org.primefaces.context.RequestContext;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.el.ELResolver;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Christian
 */
public class ControladorAbstracto implements Serializable {

    private static final long serialVersionUID = 1L;

    public ControladorAbstracto() {

    }

    public static Object getBean(String name) {
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        return facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, name);
//        return facesContext.getApplication().createValueBinding("#{" + name +
//            "}").getValue(facesContext);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        //Este es el m�todo correcto seg�n define el api RI de Sun
        javax.el.ELContext elContext = facesContext.getELContext();
        javax.el.ExpressionFactory ef = facesContext.getApplication().getExpressionFactory();
        javax.el.ValueExpression ve = ef.createValueExpression(elContext, "#{" + name + "}", Object.class);
        return ve.getValue(elContext);
    }

    public boolean isUserInRole(String role) {
        return getExternalContext().isUserInRole(role);
    }

    public static Principal getPrincipal() {
        return getExternalContext().getUserPrincipal();
    }

    public static String getUserName() {
        if (getPrincipal() == null) {
            return "";
        }
        return getPrincipal().getName();
    }

    public String getRemoteAddress() {
        return getRequest().getRemoteAddr();
    }

    public static void fatal(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, facesMsg);
    }

    public static void error(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, facesMsg);
    }

    public static void warn(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, facesMsg);
    }

    public static void info(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage("successInfo", facesMsg);
    }

    public static HttpServletResponse getResponse() {
        FacesContext context = FacesContext.getCurrentInstance();
        return (HttpServletResponse) context.getExternalContext().getResponse();
    }

    public static HttpServletRequest getRequest() {
        FacesContext context = FacesContext.getCurrentInstance();
        return (HttpServletRequest) context.getExternalContext().getRequest();
    }

    public static ExternalContext getExternalContext() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getExternalContext();
    }

    public static ServletContext getServletContext() {
        return (ServletContext) getExternalContext().getContext();
    }

    public static HttpSession getSession(boolean create) {
        FacesContext context = FacesContext.getCurrentInstance();
        return (HttpSession) context.getExternalContext().getSession(create);
    }

    public static Object getSessionProperty(String key) {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getExternalContext().getSessionMap().get(key);
    }

    public static Map<String, Object> getObjetosSession() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getExternalContext().getSessionMap();
    }

    public static void setSessionProperty(String key, Object value) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put(key, value);
    }

    public static Object removeSessionProperty(String key) {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getExternalContext().getSessionMap().remove(key);
    }

    public static String getRequestParameter(String param) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(param);
    }

    public static void setRequestParameter(String param, Object value) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getRequestParameterMap().put(param, String.valueOf(value));
    }

    public static String removeRequestParameter(String param) {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getExternalContext().getRequestParameterMap().remove(param);
    }

    public static Object eliminarBean(String bean) {
        return removeRequestParameter(bean);
    }

    public static String obtenerMensajesErrores() {
        Set<String> errores = new HashSet<>();
        FacesContext fc = FacesContext.getCurrentInstance();
        Iterator i = fc.getMessages();
        while (i.hasNext()) {
            FacesMessage facesMsg = (FacesMessage) i.next();
            if (facesMsg.getSeverity().equals(FacesMessage.SEVERITY_ERROR)) {
                errores.add(facesMsg.getSummary());
            }
        }
        StringBuilder mensaje = new StringBuilder("");
        for (String e : errores) {
            mensaje.append(e).append("/");
        }
        return mensaje.toString();
    }

    public static SelectItem[] collectiontoArrayOption(Collection list, String displayMember) {
        SelectItem[] ops = new SelectItem[]{};
        if (list != null) {
            ops = new SelectItem[list.size()];
            int i = 0;
            for (Iterator it = list.iterator(); it.hasNext();) {
                Object entity = it.next();
                ops[i++] = new SelectItem(entity, invocar(entity, displayMember).toString());
            }
        }
        return ops;
    }

    public static SelectItem[] collectiontoArrayOption(Collection list, String valueMember, String displayMember) {
        SelectItem[] ops = new SelectItem[]{};
        if (list != null) {
            ops = new SelectItem[list.size()];
            int i = 0;
            for (Iterator it = list.iterator(); it.hasNext();) {
                Object entity = it.next();
                ops[i++] = new SelectItem(invocar(entity, valueMember), invocar(entity, displayMember).toString());
            }
        }
        return ops;
    }

    public static SelectItem[] collectiontoArrayOption(Collection list, String valueMember, String displayMember, SelectItem defaultOption) {
        SelectItem[] ops = new SelectItem[]{};
        if (list != null) {
            ops = new SelectItem[list.size() + 1];
            ops[0] = defaultOption;
            int i = 1;
            for (Iterator it = list.iterator(); it.hasNext();) {
                Object entity = it.next();
                ops[i++] = new SelectItem(invocar(entity, valueMember), invocar(entity, displayMember).toString());
            }
        }
        return ops;
    }

    private static Object invocar(Object obj, String m) {
        try {
            Class clase = obj.getClass();
            Method metodo = m.equals("toString") ? clase.getMethod(m) : clase.getMethod("get" + m);
            return metodo.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getPageCall() {
        HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String[] uris = origRequest.getRequestURI().split("/");
        String url = uris[uris.length - 1];
        return url;
    }
    
    public static void updateComponent(String... components) {
        RequestContext context = RequestContext.getCurrentInstance();
        context.update(Arrays.asList(components));
    }
    
    public static void executeJavascript(String components) {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute(components);
    }
    
    public static void addCallbackParam(String key, String components) {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.addCallbackParam(key, components);
    }
    
    public static void addCallbackParam(String key, Object components) {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.addCallbackParam(key, components);
    }
    
    public static FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }
    
    public static Object getClazz(String parentName) {
        FacesContext facesContext = getFacesContext();
        ELResolver elr = facesContext.getApplication().getELResolver();
        return elr.getValue(facesContext.getELContext(), null, parentName);
    }
}