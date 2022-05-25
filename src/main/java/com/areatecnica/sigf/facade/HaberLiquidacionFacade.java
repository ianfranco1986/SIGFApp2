/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.HaberLiquidacion;
import com.areatecnica.sigf.entities.HaberTrabajador;
import com.areatecnica.sigf.entities.LiquidacionSueldo;

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
public class HaberLiquidacionFacade extends AbstractFacade<HaberLiquidacion> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HaberLiquidacionFacade() {
        super(HaberLiquidacion.class);
    }

    public boolean isHaberLiquidacionIdHaberEmpty(HaberLiquidacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<HaberLiquidacion> haberLiquidacion = cq.from(HaberLiquidacion.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(haberLiquidacion, entity), cb.isNotNull(haberLiquidacion.get(HaberLiquidacion_.haberLiquidacionIdHaber)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public HaberTrabajador findHaberLiquidacionIdHaber(HaberLiquidacion entity) {
        return this.getMergedEntity(entity).getHaberLiquidacionIdHaber();
    }

    public boolean isHaberLiquidacionIdLiquidacionSueldoEmpty(HaberLiquidacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<HaberLiquidacion> haberLiquidacion = cq.from(HaberLiquidacion.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(haberLiquidacion, entity), cb.isNotNull(haberLiquidacion.get(HaberLiquidacion_.haberLiquidacionIdLiquidacionSueldo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public LiquidacionSueldo findHaberLiquidacionIdLiquidacionSueldo(HaberLiquidacion entity) {
        return this.getMergedEntity(entity).getHaberLiquidacionIdLiquidacionSueldo();
    }
    
}
