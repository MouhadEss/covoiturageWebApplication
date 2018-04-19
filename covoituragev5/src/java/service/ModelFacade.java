/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Model;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mouhad ESSABBANE
 */
@Stateless
public class ModelFacade extends AbstractFacade<Model> {

    @PersistenceContext(unitName = "covoituragev5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModelFacade() {
        super(Model.class);
    }
    
    public List<Model> FindModelByMarque(Long id) {
        return getEntityManager().createQuery("SELECT mo FROM Model mo WHERE mo.marque.id ='" + id + "'").getResultList();
    }
}
