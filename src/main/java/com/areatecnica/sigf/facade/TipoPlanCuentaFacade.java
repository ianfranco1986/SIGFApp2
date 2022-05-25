/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.TipoPlanCuenta;

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
public class TipoPlanCuentaFacade extends AbstractFacade<TipoPlanCuenta> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoPlanCuentaFacade() {
        super(TipoPlanCuenta.class);
    }

    public boolean isPlanCuentaListEmpty(TipoPlanCuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoPlanCuenta> tipoPlanCuenta = cq.from(TipoPlanCuenta.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoPlanCuenta, entity), cb.isNotEmpty(tipoPlanCuenta.get(TipoPlanCuenta_.planCuentaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

}
