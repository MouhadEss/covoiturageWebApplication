/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.TypeRoute;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mouhad ESSABBANE
 */
@Stateless
public class TypeRouteFacade extends AbstractFacade<TypeRoute> {

    @PersistenceContext(unitName = "covoituragev5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypeRouteFacade() {
        super(TypeRoute.class);
    }
    
    public void create(TypeRoute typeRoute){
        typeRoute.setId(generateId("TypeRoute", "id"));
        super.create(typeRoute);
    }
}
