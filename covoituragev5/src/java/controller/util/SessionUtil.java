package controller.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class SessionUtil {

    private static final SessionUtil instance = new SessionUtil();
    private static List<Object> list=new ArrayList();

    private SessionUtil() {
    }

    public static void remove(String cle) {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (fc != null && fc.getExternalContext() != null) {
            getSession(fc).removeAttribute(cle);
        }
    }
    
    
    public static void registerObject(Object user) {
        //clone user before
        setAttribute("user", user);
        if(!isConnected(user)){
            list.add(user);
        }
    }

    public static Object getConnectedObject() {
        return (Object) getAttribute("user");
    }
    private static boolean isConnected(Object user){
        if (list.stream().anyMatch((c) -> (c.getClass().equals(user.getClass())))) {
            return true;
        }
    return false;
    }
    public static void setAttribute(String cle, Object valeur) {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (fc != null && fc.getExternalContext() != null) {
            getSession(fc).setAttribute(cle, valeur);
        }
    }

    private static HttpSession getSession(FacesContext fc) {
        return (HttpSession) fc.getExternalContext().getSession(false);
    }

    public static Object getAttribute(String cle) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Object res = null;
        if (isContextOk(fc)) {
            res = getSession(fc).getAttribute(cle);
        }
        return res;
    }

    private static boolean isContextOk(FacesContext fc) {
        boolean res = (fc != null
                && fc.getExternalContext() != null
                && fc.getExternalContext().getSession(false) != null);
        return res;
    }

    public static void redirect(String pagePath) throws IOException {
//        if (!pagePath.endsWith(".xhtml")) {
//            pagePath += ".xhtml";
//        }
        FacesContext.getCurrentInstance().getExternalContext().redirect(pagePath);

    }

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

}
