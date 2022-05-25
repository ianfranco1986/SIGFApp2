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
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class LiquidacionSueldoFacade extends AbstractFacade<LiquidacionSueldo> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LiquidacionSueldoFacade() {
        super(LiquidacionSueldo.class);
    }

    public boolean isLiquidacionSueldoIdEmpresaEmpty(LiquidacionSueldo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<LiquidacionSueldo> liquidacionSueldo = cq.from(LiquidacionSueldo.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(liquidacionSueldo, entity), cb.isNotNull(liquidacionSueldo.get(LiquidacionSueldo_.liquidacionSueldoIdEmpresa)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Empresa findLiquidacionSueldoIdEmpresa(LiquidacionSueldo entity) {
        return this.getMergedEntity(entity).getLiquidacionSueldoIdEmpresa();
    }

    public boolean isLiquidacionSueldoIdMovimientoPersonalEmpty(LiquidacionSueldo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<LiquidacionSueldo> liquidacionSueldo = cq.from(LiquidacionSueldo.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(liquidacionSueldo, entity), cb.isNotNull(liquidacionSueldo.get(LiquidacionSueldo_.liquidacionSueldoIdMovimientoPersonal)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public TipoMovimientoPersonal findLiquidacionSueldoIdMovimientoPersonal(LiquidacionSueldo entity) {
        return this.getMergedEntity(entity).getLiquidacionSueldoIdMovimientoPersonal();
    }

    public boolean isLiquidacionSueldoIdTerminalEmpty(LiquidacionSueldo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<LiquidacionSueldo> liquidacionSueldo = cq.from(LiquidacionSueldo.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(liquidacionSueldo, entity), cb.isNotNull(liquidacionSueldo.get(LiquidacionSueldo_.liquidacionSueldoIdTerminal)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Terminal findLiquidacionSueldoIdTerminal(LiquidacionSueldo entity) {
        return this.getMergedEntity(entity).getLiquidacionSueldoIdTerminal();
    }

    public boolean isLiquidacionSueldoIdTipoContratoEmpty(LiquidacionSueldo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<LiquidacionSueldo> liquidacionSueldo = cq.from(LiquidacionSueldo.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(liquidacionSueldo, entity), cb.isNotNull(liquidacionSueldo.get(LiquidacionSueldo_.liquidacionSueldoIdTipoContrato)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public TipoContrato findLiquidacionSueldoIdTipoContrato(LiquidacionSueldo entity) {
        return this.getMergedEntity(entity).getLiquidacionSueldoIdTipoContrato();
    }

    public boolean isLiquidacionSueldoIdTrabajadorEmpty(LiquidacionSueldo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<LiquidacionSueldo> liquidacionSueldo = cq.from(LiquidacionSueldo.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(liquidacionSueldo, entity), cb.isNotNull(liquidacionSueldo.get(LiquidacionSueldo_.liquidacionSueldoIdTrabajador)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Trabajador findLiquidacionSueldoIdTrabajador(LiquidacionSueldo entity) {
        return this.getMergedEntity(entity).getLiquidacionSueldoIdTrabajador();
    }

    public boolean isHaberLiquidacionListEmpty(LiquidacionSueldo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<LiquidacionSueldo> liquidacionSueldo = cq.from(LiquidacionSueldo.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(liquidacionSueldo, entity), cb.isNotEmpty(liquidacionSueldo.get(LiquidacionSueldo_.haberLiquidacionList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<HaberLiquidacion> findHaberLiquidacionList(LiquidacionSueldo entity) {
        LiquidacionSueldo mergedEntity = this.getMergedEntity(entity);
        List<HaberLiquidacion> haberLiquidacionList = mergedEntity.getHaberLiquidacionList();
        haberLiquidacionList.size();
        return haberLiquidacionList;
    }

    public boolean isDescuentoLiquidacionListEmpty(LiquidacionSueldo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<LiquidacionSueldo> liquidacionSueldo = cq.from(LiquidacionSueldo.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(liquidacionSueldo, entity), cb.isNotEmpty(liquidacionSueldo.get(LiquidacionSueldo_.descuentoLiquidacionList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<DescuentoLiquidacion> findDescuentoLiquidacionList(LiquidacionSueldo entity) {
        LiquidacionSueldo mergedEntity = this.getMergedEntity(entity);
        List<DescuentoLiquidacion> descuentoLiquidacionList = mergedEntity.getDescuentoLiquidacionList();
        descuentoLiquidacionList.size();
        return descuentoLiquidacionList;
    }
    
}
