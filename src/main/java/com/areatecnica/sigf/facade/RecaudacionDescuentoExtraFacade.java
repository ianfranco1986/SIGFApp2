/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.RecaudacionDescuentoExtra;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.RecaudacionDescuentoExtra_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.DescuentoExtraBus;
import com.areatecnica.sigf.entities.Recaudacion;

/**
 *
 * @author ianfr
 */
@Stateless
public class RecaudacionDescuentoExtraFacade extends AbstractFacade<RecaudacionDescuentoExtra> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecaudacionDescuentoExtraFacade() {
        super(RecaudacionDescuentoExtra.class);
    }

    public boolean isRecaudacionDescuentoExtraIdDctoEmpty(RecaudacionDescuentoExtra entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RecaudacionDescuentoExtra> recaudacionDescuentoExtra = cq.from(RecaudacionDescuentoExtra.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(recaudacionDescuentoExtra, entity), cb.isNotNull(recaudacionDescuentoExtra.get(RecaudacionDescuentoExtra_.recaudacionDescuentoExtraIdDcto)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public DescuentoExtraBus findRecaudacionDescuentoExtraIdDcto(RecaudacionDescuentoExtra entity) {
        return this.getMergedEntity(entity).getRecaudacionDescuentoExtraIdDcto();
    }

    public boolean isRecaudacionDescuentoExtraIdRecaudacionEmpty(RecaudacionDescuentoExtra entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RecaudacionDescuentoExtra> recaudacionDescuentoExtra = cq.from(RecaudacionDescuentoExtra.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(recaudacionDescuentoExtra, entity), cb.isNotNull(recaudacionDescuentoExtra.get(RecaudacionDescuentoExtra_.recaudacionDescuentoExtraIdRecaudacion)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Recaudacion findRecaudacionDescuentoExtraIdRecaudacion(RecaudacionDescuentoExtra entity) {
        return this.getMergedEntity(entity).getRecaudacionDescuentoExtraIdRecaudacion();
    }
    
}
