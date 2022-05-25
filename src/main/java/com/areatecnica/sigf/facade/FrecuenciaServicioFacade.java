/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.*;

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
public class FrecuenciaServicioFacade extends AbstractFacade<FrecuenciaServicio> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FrecuenciaServicioFacade() {
        super(FrecuenciaServicio.class);
    }

    public boolean isFrecuenciaServicioIdPeriodoEmpty(FrecuenciaServicio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<FrecuenciaServicio> frecuenciaServicio = cq.from(FrecuenciaServicio.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(frecuenciaServicio, entity), cb.isNotNull(frecuenciaServicio.get(FrecuenciaServicio_.frecuenciaServicioIdPeriodo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public PeriodoFrecuencia findFrecuenciaServicioIdPeriodo(FrecuenciaServicio entity) {
        return this.getMergedEntity(entity).getFrecuenciaServicioIdPeriodo();
    }

    public boolean isFrecuenciaServicioIdServicioEmpty(FrecuenciaServicio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<FrecuenciaServicio> frecuenciaServicio = cq.from(FrecuenciaServicio.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(frecuenciaServicio, entity), cb.isNotNull(frecuenciaServicio.get(FrecuenciaServicio_.frecuenciaServicioIdServicio)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Servicio findFrecuenciaServicioIdServicio(FrecuenciaServicio entity) {
        return this.getMergedEntity(entity).getFrecuenciaServicioIdServicio();
    }

    public boolean isFrecuenciaServicioIdTipoDemandaEmpty(FrecuenciaServicio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<FrecuenciaServicio> frecuenciaServicio = cq.from(FrecuenciaServicio.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(frecuenciaServicio, entity), cb.isNotNull(frecuenciaServicio.get(FrecuenciaServicio_.frecuenciaServicioIdTipoDemanda)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public TipoDemanda findFrecuenciaServicioIdTipoDemanda(FrecuenciaServicio entity) {
        return this.getMergedEntity(entity).getFrecuenciaServicioIdTipoDemanda();
    }

    public boolean isFrecuenciaServicioIdTipoDiaEmpty(FrecuenciaServicio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<FrecuenciaServicio> frecuenciaServicio = cq.from(FrecuenciaServicio.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(frecuenciaServicio, entity), cb.isNotNull(frecuenciaServicio.get(FrecuenciaServicio_.frecuenciaServicioIdTipoDia)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public TipoDiaFrecuencia findFrecuenciaServicioIdTipoDia(FrecuenciaServicio entity) {
        return this.getMergedEntity(entity).getFrecuenciaServicioIdTipoDia();
    }
    
}
