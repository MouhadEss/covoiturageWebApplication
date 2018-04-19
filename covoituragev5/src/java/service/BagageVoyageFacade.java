/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.BagageVoyage;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mouhad ESSABBANE
 */
@Stateless
public class BagageVoyageFacade extends AbstractFacade<BagageVoyage> {

    @PersistenceContext(unitName = "covoituragev5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BagageVoyageFacade() {
        super(BagageVoyage.class);
    }
    
    public void create(BagageVoyage bagageVoyage){
        bagageVoyage.setId(generateId("BagageVoyage", "id"));
        super.create(bagageVoyage);
    }
    
}
