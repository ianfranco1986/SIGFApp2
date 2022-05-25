/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.ObservacionTipoCuenta;
import com.areatecnica.sigf.entities.TipoCuenta;

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
public class TipoCuentaFacade extends AbstractFacade<TipoCuenta> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoCuentaFacade() {
        super(TipoCuenta.class);
    }

    public boolean isObservacionTipoCuentaListEmpty(TipoCuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoCuenta> tipoCuenta = cq.from(TipoCuenta.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoCuenta, entity), cb.isNotEmpty(tipoCuenta.get(TipoCuenta_.observacionTipoCuentaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<ObservacionTipoCuenta> findObservacionTipoCuentaList(TipoCuenta entity) {
        TipoCuenta mergedEntity = this.getMergedEntity(entity);
        List<ObservacionTipoCuenta> observacionTipoCuentaList = mergedEntity.getObservacionTipoCuentaList();
        observacionTipoCuentaList.size();
        return observacionTipoCuentaList;
    }

    public boolean isCuentaListEmpty(TipoCuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoCuenta> tipoCuenta = cq.from(TipoCuenta.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoCuenta, entity), cb.isNotEmpty(tipoCuenta.get(TipoCuenta_.cuentaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Cuenta> findCuentaList(TipoCuenta entity) {
        TipoCuenta mergedEntity = this.getMergedEntity(entity);
        List<Cuenta> cuentaList = mergedEntity.getCuentaList();
        cuentaList.size();
        return cuentaList;
    }
    
}
