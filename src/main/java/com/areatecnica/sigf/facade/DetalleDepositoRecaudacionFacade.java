/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.DetalleDepositoRecaudacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.DetalleDepositoRecaudacion_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.CuentaBancaria;
import com.areatecnica.sigf.entities.ResumenRecaudacion;

/**
 *
 * @author ianfr
 */
@Stateless
public class DetalleDepositoRecaudacionFacade extends AbstractFacade<DetalleDepositoRecaudacion> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleDepositoRecaudacionFacade() {
        super(DetalleDepositoRecaudacion.class);
    }

    public boolean isDetalleDepositoRecaudacionIdCuentaEmpty(DetalleDepositoRecaudacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<DetalleDepositoRecaudacion> detalleDepositoRecaudacion = cq.from(DetalleDepositoRecaudacion.class);
        //cq.select(cb.literal(1L)).distinct(true).where(cb.equal(detalleDepositoRecaudacion, entity), cb.isNotNull(detalleDepositoRecaudacion.get(DetalleDepositoRecaudacion_.detalleDepositoRecaudacionIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public CuentaBancaria findDetalleDepositoRecaudacionIdCuenta(DetalleDepositoRecaudacion entity) {
        return this.getMergedEntity(entity).getDetalleDepositoRecaudacionIdCuenta();
    }

    public boolean isDetalleDepositoRecaudacionIdResumenEmpty(DetalleDepositoRecaudacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<DetalleDepositoRecaudacion> detalleDepositoRecaudacion = cq.from(DetalleDepositoRecaudacion.class);
        //cq.select(cb.literal(1L)).distinct(true).where(cb.equal(detalleDepositoRecaudacion, entity), cb.isNotNull(detalleDepositoRecaudacion.get(DetalleDepositoRecaudacion_.detalleDepositoRecaudacionIdResumen)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public ResumenRecaudacion findDetalleDepositoRecaudacionIdResumen(DetalleDepositoRecaudacion entity) {
        return this.getMergedEntity(entity).getDetalleDepositoRecaudacionIdResumen();
    }
    
    
}
