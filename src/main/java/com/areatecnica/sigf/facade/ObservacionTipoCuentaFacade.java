/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.ObservacionTipoCuenta;
import com.areatecnica.sigf.entities.TipoCuenta;

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
public class ObservacionTipoCuentaFacade extends AbstractFacade<ObservacionTipoCuenta> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ObservacionTipoCuentaFacade() {
        super(ObservacionTipoCuenta.class);
    }

    public boolean isObservacionTipoCuentaIdTipoEmpty(ObservacionTipoCuenta entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ObservacionTipoCuenta> observacionTipoCuenta = cq.from(ObservacionTipoCuenta.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(observacionTipoCuenta, entity), cb.isNotNull(observacionTipoCuenta.get(ObservacionTipoCuenta_.observacionTipoCuentaIdTipo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public TipoCuenta findObservacionTipoCuentaIdTipo(ObservacionTipoCuenta entity) {
        return this.getMergedEntity(entity).getObservacionTipoCuentaIdTipo();
    }
    
}
