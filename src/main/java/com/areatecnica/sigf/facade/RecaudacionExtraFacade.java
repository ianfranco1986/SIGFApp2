/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.RecaudacionExtra;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Recaudacion;

/**
 *
 * @author ianfr
 */
@Stateless
public class RecaudacionExtraFacade extends AbstractFacade<RecaudacionExtra> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecaudacionExtraFacade() {
        super(RecaudacionExtra.class);
    }

    public Recaudacion findRecaudacionExtraIdRecaudacion(RecaudacionExtra entity) {
        return this.getMergedEntity(entity).getRecaudacionExtraIdRecaudacion();
    }
    
}
