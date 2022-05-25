/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.RecaudacionMinuto;
import com.areatecnica.sigf.entities.RegistroMinuto;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class RegistroMinutoFacade extends AbstractFacade<RegistroMinuto> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegistroMinutoFacade() {
        super(RegistroMinuto.class);
    }

    public boolean isRecaudacionMinutoListEmpty(RegistroMinuto entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RegistroMinuto> registroMinuto = cq.from(RegistroMinuto.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(registroMinuto, entity), cb.isNotEmpty(registroMinuto.get(RegistroMinuto_.recaudacionMinutoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<RecaudacionMinuto> findRecaudacionMinutoList(RegistroMinuto entity) {
        RegistroMinuto mergedEntity = this.getMergedEntity(entity);
        List<RecaudacionMinuto> recaudacionMinutoList = mergedEntity.getRecaudacionMinutoList();
        recaudacionMinutoList.size();
        return recaudacionMinutoList;
    }

    public boolean isRegistroMinutoDesdeIdBusEmpty(RegistroMinuto entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RegistroMinuto> registroMinuto = cq.from(RegistroMinuto.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(registroMinuto, entity), cb.isNotNull(registroMinuto.get(RegistroMinuto_.registroMinutoDesdeIdBus)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Bus findRegistroMinutoDesdeIdBus(RegistroMinuto entity) {
        return this.getMergedEntity(entity).getRegistroMinutoDesdeIdBus();
    }

    public boolean isRegistroMinutoHastaIdBusEmpty(RegistroMinuto entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RegistroMinuto> registroMinuto = cq.from(RegistroMinuto.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(registroMinuto, entity), cb.isNotNull(registroMinuto.get(RegistroMinuto_.registroMinutoHastaIdBus)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Bus findRegistroMinutoHastaIdBus(RegistroMinuto entity) {
        return this.getMergedEntity(entity).getRegistroMinutoHastaIdBus();
    }
    
}
