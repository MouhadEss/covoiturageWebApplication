/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Personne;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mouhad ESSABBANE
 */
@Stateless
public class PersonneFacade extends AbstractFacade<Personne> {

    @PersistenceContext(unitName = "covoituragev5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonneFacade() {
        super(Personne.class);
    }
    
     public Personne findPersonneById(String email) {
        return (Personne) getEntityManager().createQuery("SELECT p FROM Personne p WHERE p.email='" + email + "'").getSingleResult();
    }
    
}
