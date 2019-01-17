/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.AbonoLiquidacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.AbonoLiquidacion_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.AbonoBus;
import com.areatecnica.sigf.entities.LiquidacionEmpresa;

/**
 *
 * @author ianfr
 */
@Stateless
public class AbonoLiquidacionFacade extends AbstractFacade<AbonoLiquidacion> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AbonoLiquidacionFacade() {
        super(AbonoLiquidacion.class);
    }

    public boolean isAbonoLiquidacionIdAbonoEmpty(AbonoLiquidacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<AbonoLiquidacion> abonoLiquidacion = cq.from(AbonoLiquidacion.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(abonoLiquidacion, entity), cb.isNotNull(abonoLiquidacion.get(AbonoLiquidacion_.abonoLiquidacionIdAbono)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public AbonoBus findAbonoLiquidacionIdAbono(AbonoLiquidacion entity) {
        return this.getMergedEntity(entity).getAbonoLiquidacionIdAbono();
    }

    public boolean isAbonoLiquidacionIdLiquidacionEmpresaEmpty(AbonoLiquidacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<AbonoLiquidacion> abonoLiquidacion = cq.from(AbonoLiquidacion.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(abonoLiquidacion, entity), cb.isNotNull(abonoLiquidacion.get(AbonoLiquidacion_.abonoLiquidacionIdLiquidacionEmpresa)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public LiquidacionEmpresa findAbonoLiquidacionIdLiquidacionEmpresa(AbonoLiquidacion entity) {
        return this.getMergedEntity(entity).getAbonoLiquidacionIdLiquidacionEmpresa();
    }
    
}
