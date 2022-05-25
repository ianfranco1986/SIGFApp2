/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Egreso;
import com.areatecnica.sigf.entities.EgresoFlota;
import com.areatecnica.sigf.entities.Flota;

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
public class EgresoFlotaFacade extends AbstractFacade<EgresoFlota> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EgresoFlotaFacade() {
        super(EgresoFlota.class);
    }

    public boolean isEgresoFlotaIdEgresoEmpty(EgresoFlota entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<EgresoFlota> egresoFlota = cq.from(EgresoFlota.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(egresoFlota, entity), cb.isNotNull(egresoFlota.get(EgresoFlota_.egresoFlotaIdEgreso)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Egreso findEgresoFlotaIdEgreso(EgresoFlota entity) {
        return this.getMergedEntity(entity).getEgresoFlotaIdEgreso();
    }

    public boolean isEgresoFlotaIdFlotaEmpty(EgresoFlota entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<EgresoFlota> egresoFlota = cq.from(EgresoFlota.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(egresoFlota, entity), cb.isNotNull(egresoFlota.get(EgresoFlota_.egresoFlotaIdFlota)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Flota findEgresoFlotaIdFlota(EgresoFlota entity) {
        return this.getMergedEntity(entity).getEgresoFlotaIdFlota();
    }
    
}
