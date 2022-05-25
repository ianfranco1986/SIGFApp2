/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.CtvResumen;
import com.areatecnica.sigf.entities.DetalleDepositoRecaudacion;
import com.areatecnica.sigf.entities.ResumenRecaudacion;

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
public class ResumenRecaudacionFacade extends AbstractFacade<ResumenRecaudacion> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;
@Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ResumenRecaudacionFacade() {
        super(ResumenRecaudacion.class);
    }

    public boolean isResumenRecaudacionIdCajaEmpty(ResumenRecaudacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ResumenRecaudacion> resumenRecaudacion = cq.from(ResumenRecaudacion.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(resumenRecaudacion, entity), cb.isNotNull(resumenRecaudacion.get(ResumenRecaudacion_.resumenRecaudacionIdCaja)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public CajaRecaudacion findResumenRecaudacionIdCaja(ResumenRecaudacion entity) {
        return this.getMergedEntity(entity).getResumenRecaudacionIdCaja();
    }

    public boolean isDetalleDepositoRecaudacionListEmpty(ResumenRecaudacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ResumenRecaudacion> resumenRecaudacion = cq.from(ResumenRecaudacion.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(resumenRecaudacion, entity), cb.isNotEmpty(resumenRecaudacion.get(ResumenRecaudacion_.detalleDepositoRecaudacionList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<DetalleDepositoRecaudacion> findDetalleDepositoRecaudacionList(ResumenRecaudacion entity) {
        ResumenRecaudacion mergedEntity = this.getMergedEntity(entity);
        List<DetalleDepositoRecaudacion> detalleDepositoRecaudacionList = mergedEntity.getDetalleDepositoRecaudacionList();
        detalleDepositoRecaudacionList.size();
        return detalleDepositoRecaudacionList;
    }

    public boolean isCtvResumenListEmpty(ResumenRecaudacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ResumenRecaudacion> resumenRecaudacion = cq.from(ResumenRecaudacion.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(resumenRecaudacion, entity), cb.isNotEmpty(resumenRecaudacion.get(ResumenRecaudacion_.ctvResumenList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<CtvResumen> findCtvResumenList(ResumenRecaudacion entity) {
        ResumenRecaudacion mergedEntity = this.getMergedEntity(entity);
        List<CtvResumen> ctvResumenList = mergedEntity.getCtvResumenList();
        ctvResumenList.size();
        return ctvResumenList;
    }
    
}
