/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.Egreso;
import com.areatecnica.sigf.entities.EgresoBus;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ianfr
 */
@Stateless
public class EgresoBusFacade extends AbstractFacade<EgresoBus> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EgresoBusFacade() {
        super(EgresoBus.class);
    }

    public boolean isEgresoBusIdEgresoEmpty(EgresoBus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<EgresoBus> egresoBus = cq.from(EgresoBus.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(egresoBus, entity), cb.isNotNull(egresoBus.get(EgresoBus_.egresoBusIdEgreso)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Egreso findEgresoBusIdEgreso(EgresoBus entity) {
        return this.getMergedEntity(entity).getEgresoBusIdEgreso();
    }

    public boolean isEgresoBusIdBusEmpty(EgresoBus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<EgresoBus> egresoBus = cq.from(EgresoBus.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(egresoBus, entity), cb.isNotNull(egresoBus.get(EgresoBus_.egresoBusIdBus)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Bus findEgresoBusIdBus(EgresoBus entity) {
        return this.getMergedEntity(entity).getEgresoBusIdBus();
    }
    
}
