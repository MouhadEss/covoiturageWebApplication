/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Voyage;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mouhad ESSABBANE
 */
@Stateless
public class VoyageFacade extends AbstractFacade<Voyage> {

    @PersistenceContext(unitName = "covoituragev5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @EJB
    PersonneFacade personneFacade;
    
    public VoyageFacade() {
        super(Voyage.class);
    }
    
    @Override
    public void create(Voyage voyage){
        voyage.setId(generateId("Voyage", "id"));
        voyage.setNbrPlaceOuccuper(0);
        voyage.setPersonne(personneFacade.findPersonneById("mouhad"));
        voyage.setDateAnnonce(new Date());
        super.create(voyage);
    }
}
