/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Compra;
import com.areatecnica.sigf.entities.CuentaMayor;
import com.areatecnica.sigf.entities.PlanCuentaSubTipo;

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
public class CuentaMayorFacade extends AbstractFacade<CuentaMayor> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuentaMayorFacade() {
        super(CuentaMayor.class);
    }

    public boolean isCuentaMayorSubTipoIdEmpty(CuentaMayor entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CuentaMayor> cuentaMayor = cq.from(CuentaMayor.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuentaMayor, entity), cb.isNotNull(cuentaMayor.get(CuentaMayor_.cuentaMayorSubTipoId)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

}
