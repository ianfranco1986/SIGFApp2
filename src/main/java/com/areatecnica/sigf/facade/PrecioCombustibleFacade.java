/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.PrecioCombustible;
import com.areatecnica.sigf.entities.TipoCombustible;

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
public class PrecioCombustibleFacade extends AbstractFacade<PrecioCombustible> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrecioCombustibleFacade() {
        super(PrecioCombustible.class);
    }

    public boolean isPrecioCombustibleIdCuentaEmpty(PrecioCombustible entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<PrecioCombustible> precioCombustible = cq.from(PrecioCombustible.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(precioCombustible, entity), cb.isNotNull(precioCombustible.get(PrecioCombustible_.precioCombustibleIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findPrecioCombustibleIdCuenta(PrecioCombustible entity) {
        return this.getMergedEntity(entity).getPrecioCombustibleIdCuenta();
    }

    public boolean isPrecioCombustibleIdTipoCombustibleEmpty(PrecioCombustible entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<PrecioCombustible> precioCombustible = cq.from(PrecioCombustible.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(precioCombustible, entity), cb.isNotNull(precioCombustible.get(PrecioCombustible_.precioCombustibleIdTipoCombustible)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public TipoCombustible findPrecioCombustibleIdTipoCombustible(PrecioCombustible entity) {
        return this.getMergedEntity(entity).getPrecioCombustibleIdTipoCombustible();
    }
    
}
