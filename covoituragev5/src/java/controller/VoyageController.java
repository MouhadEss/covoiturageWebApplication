package controller;

import bean.CircuitVoyage;
import bean.Voyage;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.VoyageFacade;

import java.io.Serializable;
import java.util.ArrayList;
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
import service.CircuitVoyageFacade;

@Named("voyageController")
@SessionScoped
public class VoyageController implements Serializable {

    @EJB
    private service.VoyageFacade ejbFacade;
    @EJB
    private service.CircuitVoyageFacade circuitFacade;
    private List<Voyage> items = null;
    private Voyage selected;
    private CircuitVoyage circuitVoyage;
    private List<CircuitVoyage> circuits= new ArrayList();

    public VoyageController() {
    }

    public List<CircuitVoyage> listConstract() {
        circuits.add(circuitFacade.clonage(circuitVoyage));
        circuitFacade.Ordonner(circuits);
        
        return circuits;
    }
    
    public VoyageFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(VoyageFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public CircuitVoyageFacade getCircuitFacade() {
        return circuitFacade;
    }

    public void setCircuitFacade(CircuitVoyageFacade circuitFacade) {
        this.circuitFacade = circuitFacade;
    }

    public CircuitVoyage getCircuitVoyage() {
        if(circuitVoyage==null){
            circuitVoyage= new CircuitVoyage();
        }
        return circuitVoyage;
    }

    public void setCircuitVoyage(CircuitVoyage circuitVoyage) {
        this.circuitVoyage = circuitVoyage;
    }

    public List<CircuitVoyage> getCircuits() {
        return circuits;
    }

    public void setCircuits(List<CircuitVoyage> circuits) {
        this.circuits = circuits;
    }
    
    

    public Voyage getSelected() {
        if(selected==null)
            selected = new Voyage();
        return selected;
    }

    public void setSelected(Voyage selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private VoyageFacade getFacade() {
        return ejbFacade;
    }

    public Voyage prepareCreate() {
        selected = new Voyage();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("VoyageCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("VoyageUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("VoyageDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Voyage> getItems() {
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

    public Voyage getVoyage(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Voyage> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Voyage> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Voyage.class)
    public static class VoyageControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VoyageController controller = (VoyageController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "voyageController");
            return controller.getVoyage(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Voyage) {
                Voyage o = (Voyage) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Voyage.class.getName()});
                return null;
            }
        }

    }

}
