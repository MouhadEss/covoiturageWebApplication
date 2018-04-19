/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.BagagePricing;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mouhad ESSABBANE
 */
@Stateless
public class BagagePricingFacade extends AbstractFacade<BagagePricing> {

    @PersistenceContext(unitName = "covoituragev5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BagagePricingFacade() {
        super(BagagePricing.class);
    }
    
    public void create(BagagePricing bagagePricing){
        bagagePricing.setId(generateId("BagagePricing", "id"));
        super.create(bagagePricing);
    }
}
