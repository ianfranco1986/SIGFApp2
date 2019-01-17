/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.AbonoBus;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.AbonoBus_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.TipoAbono;
import com.areatecnica.sigf.entities.AbonoLiquidacion;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class AbonoBusFacade extends AbstractFacade<AbonoBus> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AbonoBusFacade() {
        super(AbonoBus.class);
    }

    public boolean isAbonoBusIdBusEmpty(AbonoBus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<AbonoBus> abonoBus = cq.from(AbonoBus.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(abonoBus, entity), cb.isNotNull(abonoBus.get(AbonoBus_.abonoBusIdBus)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Bus findAbonoBusIdBus(AbonoBus entity) {
        return this.getMergedEntity(entity).getAbonoBusIdBus();
    }

    public boolean isAbonoBusIdTipoAbonoEmpty(AbonoBus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<AbonoBus> abonoBus = cq.from(AbonoBus.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(abonoBus, entity), cb.isNotNull(abonoBus.get(AbonoBus_.abonoBusIdTipoAbono)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public TipoAbono findAbonoBusIdTipoAbono(AbonoBus entity) {
        return this.getMergedEntity(entity).getAbonoBusIdTipoAbono();
    }

    public boolean isAbonoLiquidacionListEmpty(AbonoBus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<AbonoBus> abonoBus = cq.from(AbonoBus.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(abonoBus, entity), cb.isNotEmpty(abonoBus.get(AbonoBus_.abonoLiquidacionList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<AbonoLiquidacion> findAbonoLiquidacionList(AbonoBus entity) {
        AbonoBus mergedEntity = this.getMergedEntity(entity);
        List<AbonoLiquidacion> abonoLiquidacionList = mergedEntity.getAbonoLiquidacionList();
        abonoLiquidacionList.size();
        return abonoLiquidacionList;
    }
    
}
