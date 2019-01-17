/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.DescuentoLiquidacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.DescuentoLiquidacion_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.DescuentoTrabajador;
import com.areatecnica.sigf.entities.LiquidacionSueldo;

/**
 *
 * @author ianfr
 */
@Stateless
public class DescuentoLiquidacionFacade extends AbstractFacade<DescuentoLiquidacion> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DescuentoLiquidacionFacade() {
        super(DescuentoLiquidacion.class);
    }

    public boolean isDescuentoLiquidacionIdDescuentoTrabajadorEmpty(DescuentoLiquidacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<DescuentoLiquidacion> descuentoLiquidacion = cq.from(DescuentoLiquidacion.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(descuentoLiquidacion, entity), cb.isNotNull(descuentoLiquidacion.get(DescuentoLiquidacion_.descuentoLiquidacionIdDescuentoTrabajador)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public DescuentoTrabajador findDescuentoLiquidacionIdDescuentoTrabajador(DescuentoLiquidacion entity) {
        return this.getMergedEntity(entity).getDescuentoLiquidacionIdDescuentoTrabajador();
    }

    public boolean isDescuentoLiquidacionIdLiquidacionSueldoEmpty(DescuentoLiquidacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<DescuentoLiquidacion> descuentoLiquidacion = cq.from(DescuentoLiquidacion.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(descuentoLiquidacion, entity), cb.isNotNull(descuentoLiquidacion.get(DescuentoLiquidacion_.descuentoLiquidacionIdLiquidacionSueldo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public LiquidacionSueldo findDescuentoLiquidacionIdLiquidacionSueldo(DescuentoLiquidacion entity) {
        return this.getMergedEntity(entity).getDescuentoLiquidacionIdLiquidacionSueldo();
    }
    
}
