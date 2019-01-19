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
import com.areatecnica.sigf.entities.RecaudacionExtra_;
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

    public boolean isRecaudacionExtraIdRecaudacionEmpty(RecaudacionExtra entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RecaudacionExtra> recaudacionExtra = cq.from(RecaudacionExtra.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(recaudacionExtra, entity), cb.isNotNull(recaudacionExtra.get(RecaudacionExtra_.recaudacionExtraIdRecaudacion)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Recaudacion findRecaudacionExtraIdRecaudacion(RecaudacionExtra entity) {
        return this.getMergedEntity(entity).getRecaudacionExtraIdRecaudacion();
    }
    
}
