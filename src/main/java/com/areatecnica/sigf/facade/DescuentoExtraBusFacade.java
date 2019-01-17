/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.DescuentoExtraBus;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.DescuentoExtraBus_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.RecaudacionDescuentoExtra;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.DescuentoExtra;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class DescuentoExtraBusFacade extends AbstractFacade<DescuentoExtraBus> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DescuentoExtraBusFacade() {
        super(DescuentoExtraBus.class);
    }

    public boolean isRecaudacionDescuentoExtraListEmpty(DescuentoExtraBus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<DescuentoExtraBus> descuentoExtraBus = cq.from(DescuentoExtraBus.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(descuentoExtraBus, entity), cb.isNotEmpty(descuentoExtraBus.get(DescuentoExtraBus_.recaudacionDescuentoExtraList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<RecaudacionDescuentoExtra> findRecaudacionDescuentoExtraList(DescuentoExtraBus entity) {
        DescuentoExtraBus mergedEntity = this.getMergedEntity(entity);
        List<RecaudacionDescuentoExtra> recaudacionDescuentoExtraList = mergedEntity.getRecaudacionDescuentoExtraList();
        recaudacionDescuentoExtraList.size();
        return recaudacionDescuentoExtraList;
    }

    public boolean isDescuentoExtraBusIdBusEmpty(DescuentoExtraBus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<DescuentoExtraBus> descuentoExtraBus = cq.from(DescuentoExtraBus.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(descuentoExtraBus, entity), cb.isNotNull(descuentoExtraBus.get(DescuentoExtraBus_.descuentoExtraBusIdBus)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Bus findDescuentoExtraBusIdBus(DescuentoExtraBus entity) {
        return this.getMergedEntity(entity).getDescuentoExtraBusIdBus();
    }

    public boolean isDescuentoExtraBusIdDescuentoEmpty(DescuentoExtraBus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<DescuentoExtraBus> descuentoExtraBus = cq.from(DescuentoExtraBus.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(descuentoExtraBus, entity), cb.isNotNull(descuentoExtraBus.get(DescuentoExtraBus_.descuentoExtraBusIdDescuento)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public DescuentoExtra findDescuentoExtraBusIdDescuento(DescuentoExtraBus entity) {
        return this.getMergedEntity(entity).getDescuentoExtraBusIdDescuento();
    }
    
}
