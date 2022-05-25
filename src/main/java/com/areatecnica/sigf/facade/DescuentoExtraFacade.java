/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.DescuentoExtra;
import com.areatecnica.sigf.entities.DescuentoExtraBus;

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
public class DescuentoExtraFacade extends AbstractFacade<DescuentoExtra> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DescuentoExtraFacade() {
        super(DescuentoExtra.class);
    }

    public boolean isDescuentoExtraBusListEmpty(DescuentoExtra entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<DescuentoExtra> descuentoExtra = cq.from(DescuentoExtra.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(descuentoExtra, entity), cb.isNotEmpty(descuentoExtra.get(DescuentoExtra_.descuentoExtraBusList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<DescuentoExtraBus> findDescuentoExtraBusList(DescuentoExtra entity) {
        DescuentoExtra mergedEntity = this.getMergedEntity(entity);
        List<DescuentoExtraBus> descuentoExtraBusList = mergedEntity.getDescuentoExtraBusList();
        descuentoExtraBusList.size();
        return descuentoExtraBusList;
    }

    public boolean isDescuentoExtraIdCuentaEmpty(DescuentoExtra entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<DescuentoExtra> descuentoExtra = cq.from(DescuentoExtra.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(descuentoExtra, entity), cb.isNotNull(descuentoExtra.get(DescuentoExtra_.descuentoExtraIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findDescuentoExtraIdCuenta(DescuentoExtra entity) {
        return this.getMergedEntity(entity).getDescuentoExtraIdCuenta();
    }
    
}
