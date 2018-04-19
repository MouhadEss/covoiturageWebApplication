package controller;

import bean.BagagePricing;
import bean.BagageVoyage;
import bean.CircuitVoyage;
import bean.Marque;
import bean.Model;
import bean.Place;
import bean.Voyage;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.CircuitVoyageFacade;

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
import service.ModelFacade;

@Named("circuitVoyageController")
@SessionScoped
public class CircuitVoyageController implements Serializable {

    @EJB
    private service.CircuitVoyageFacade ejbFacade;
    @EJB
    private service.ModelFacade modelFacade;
    private List<Model> models = new ArrayList();
    private List<CircuitVoyage> items = new ArrayList();
    private CircuitVoyage selected;
    private Voyage voyage;
    private Marque marque;
    private BagageVoyage bagageVoyage;
    private BagagePricing bagagePricing;
    private Place place;

    public CircuitVoyageController() {
    }

    public void addListSelect() {
        items.add(ejbFacade.clonage(selected));
        ejbFacade.Ordonner(items);
    }

    public void removeListSelect(CircuitVoyage circuitVoyage) {
        items.remove(circuitVoyage);
        ejbFacade.Ordonner(items);
    }

    public List<Model> findModel() {
        return models = modelFacade.FindModelByMarque(getMarque().getId());
    }

    public void createT() {
        ejbFacade.create(voyage, items, bagageVoyage, bagagePricing, place);
    }

    public BagageVoyage getBagageVoyage() {
        if (bagageVoyage == null) {
            bagageVoyage = new BagageVoyage();
        }
        return bagageVoyage;
    }

    public void setBagageVoyage(BagageVoyage bagageVoyage) {
        this.bagageVoyage = bagageVoyage;
    }

    public BagagePricing getBagagePricing() {
        if (bagagePricing == null) {
            bagagePricing = new BagagePricing();
        }
        return bagagePricing;
    }

    public void setBagagePricing(BagagePricing bagagePricing) {
        this.bagagePricing = bagagePricing;
    }

    public Place getPlace() {
        if (place == null) {
            place = new Place();
        }
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Marque getMarque() {
        if (marque == null) {
            marque = new Marque();
        }
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public ModelFacade getModelFacade() {
        return modelFacade;
    }

    public void setModelFacade(ModelFacade modelFacade) {
        this.modelFacade = modelFacade;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    public CircuitVoyageFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(CircuitVoyageFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public Voyage getVoyage() {
        if (voyage == null) {
            voyage = new Voyage();
        }
        return voyage;
    }

    public void setVoyage(Voyage voyage) {
        this.voyage = voyage;
    }

    public CircuitVoyage getSelected() {
        if (selected == null) {
            selected = new CircuitVoyage();
        }
        return selected;
    }

    public void setSelected(CircuitVoyage selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CircuitVoyageFacade getFacade() {
        return ejbFacade;
    }

    public CircuitVoyage prepareCreate() {
        selected = new CircuitVoyage();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CircuitVoyageCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CircuitVoyageUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CircuitVoyageDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<CircuitVoyage> getItems() {
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

    public CircuitVoyage getCircuitVoyage(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<CircuitVoyage> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<CircuitVoyage> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = CircuitVoyage.class)
    public static class CircuitVoyageControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CircuitVoyageController controller = (CircuitVoyageController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "circuitVoyageController");
            return controller.getCircuitVoyage(getKey(value));
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
            if (object instanceof CircuitVoyage) {
                CircuitVoyage o = (CircuitVoyage) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CircuitVoyage.class.getName()});
                return null;
            }
        }

    }

}
