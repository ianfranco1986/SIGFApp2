/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.RecaudacionMinuto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.RecaudacionMinuto_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Recaudacion;
import com.areatecnica.sigf.entities.RegistroMinuto;

/**
 *
 * @author ianfr
 */
@Stateless
public class RecaudacionMinutoFacade extends AbstractFacade<RecaudacionMinuto> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecaudacionMinutoFacade() {
        super(RecaudacionMinuto.class);
    }

    public boolean isRecaudacionMinutoIdRecaudacionEmpty(RecaudacionMinuto entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RecaudacionMinuto> recaudacionMinuto = cq.from(RecaudacionMinuto.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(recaudacionMinuto, entity), cb.isNotNull(recaudacionMinuto.get(RecaudacionMinuto_.recaudacionMinutoIdRecaudacion)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Recaudacion findRecaudacionMinutoIdRecaudacion(RecaudacionMinuto entity) {
        return this.getMergedEntity(entity).getRecaudacionMinutoIdRecaudacion();
    }

    public boolean isRecaudacionMinutoIdRegistroMinutoEmpty(RecaudacionMinuto entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RecaudacionMinuto> recaudacionMinuto = cq.from(RecaudacionMinuto.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(recaudacionMinuto, entity), cb.isNotNull(recaudacionMinuto.get(RecaudacionMinuto_.recaudacionMinutoIdRegistroMinuto)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public RegistroMinuto findRecaudacionMinutoIdRegistroMinuto(RecaudacionMinuto entity) {
        return this.getMergedEntity(entity).getRecaudacionMinutoIdRegistroMinuto();
    }
    
}
