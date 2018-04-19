/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.BagagePricing;
import bean.BagageVoyage;
import bean.CircuitVoyage;
import bean.Place;
import bean.Voyage;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mouhad ESSABBANE
 */
@Stateless
public class CircuitVoyageFacade extends AbstractFacade<CircuitVoyage> {

    @PersistenceContext(unitName = "covoituragev5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CircuitVoyageFacade() {
        super(CircuitVoyage.class);
    }

    @EJB
    VoyageFacade voyageFacade;
    @EJB
    BagageVoyageFacade bagageVoyageFacade;
    @EJB
    PlaceFacade placeFacade;
    @EJB
    BagagePricingFacade bagagePricingFacade;
    
    public void create(Voyage voyage, List<CircuitVoyage> circuitVoyages,BagageVoyage bagageVoyage,BagagePricing bagagePricing, Place place) {
       voyageFacade.create(voyage);
       
       bagageVoyage.setVoyage(voyage);
       bagageVoyageFacade.create(bagageVoyage);
       
       bagagePricing.setBagageVoyage(bagageVoyage);
       bagagePricingFacade.create(bagagePricing);
       
       
       
        
        for (CircuitVoyage circuitVoyage : circuitVoyages) {
            place.setId(generateId("Place", "id"));
            placeFacade.create(place);
            circuitVoyage.setId(generateId("CircuitVoyage", "id"));
            circuitVoyage.setPlace(place);
            circuitVoyage.setVoyage(voyage);
            create(circuitVoyage);
            
            
        }

        System.out.println(circuitVoyages);
    }

    public List<CircuitVoyage> Ordonner(List<CircuitVoyage> circuits) {
        int i = 1;
        for (CircuitVoyage circuit : circuits) {
            circuit.setNum(i++);
        }
        return circuits;
    }

    public void cloner(CircuitVoyage circuitVoyageDonner, CircuitVoyage circuitVoyageRendue) {
        circuitVoyageRendue.setVilleDep(circuitVoyageDonner.getVilleDep());
        circuitVoyageRendue.setVilleArr(circuitVoyageDonner.getVilleArr());
        circuitVoyageRendue.setPrix(circuitVoyageDonner.getPrix());
        circuitVoyageRendue.setTypeRoute(circuitVoyageDonner.getTypeRoute());

    }

    public CircuitVoyage clonage(CircuitVoyage circuitVoyage) {
        CircuitVoyage clooner = new CircuitVoyage();
        cloner(circuitVoyage, clooner);
        return clooner;
    }

}
