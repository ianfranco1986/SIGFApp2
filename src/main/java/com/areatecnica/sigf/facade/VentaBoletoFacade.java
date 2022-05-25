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
public class VentaBoletoFacade extends AbstractFacade<VentaBoleto> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VentaBoletoFacade() {
        super(VentaBoleto.class);
    }

    public boolean isRecaudacionBoletoListEmpty(VentaBoleto entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<VentaBoleto> ventaBoleto = cq.from(VentaBoleto.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(ventaBoleto, entity), cb.isNotEmpty(ventaBoleto.get(VentaBoleto_.recaudacionBoletoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<RecaudacionBoleto> findRecaudacionBoletoList(VentaBoleto entity) {
        VentaBoleto mergedEntity = this.getMergedEntity(entity);
        List<RecaudacionBoleto> recaudacionBoletoList = mergedEntity.getRecaudacionBoletoList();
        recaudacionBoletoList.size();
        return recaudacionBoletoList;
    }

    public boolean isVentaBoletoIdBusEmpty(VentaBoleto entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<VentaBoleto> ventaBoleto = cq.from(VentaBoleto.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(ventaBoleto, entity), cb.isNotNull(ventaBoleto.get(VentaBoleto_.ventaBoletoIdBus)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Bus findVentaBoletoIdBus(VentaBoleto entity) {
        return this.getMergedEntity(entity).getVentaBoletoIdBus();
    }

    public boolean isVentaBoletoIdInventarioCajaEmpty(VentaBoleto entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<VentaBoleto> ventaBoleto = cq.from(VentaBoleto.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(ventaBoleto, entity), cb.isNotNull(ventaBoleto.get(VentaBoleto_.ventaBoletoIdInventarioCaja)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public InventarioCaja findVentaBoletoIdInventarioCaja(VentaBoleto entity) {
        return this.getMergedEntity(entity).getVentaBoletoIdInventarioCaja();
    }

    public boolean isVentaBoletoIdTrabajadorEmpty(VentaBoleto entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<VentaBoleto> ventaBoleto = cq.from(VentaBoleto.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(ventaBoleto, entity), cb.isNotNull(ventaBoleto.get(VentaBoleto_.ventaBoletoIdTrabajador)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Trabajador findVentaBoletoIdTrabajador(VentaBoleto entity) {
        return this.getMergedEntity(entity).getVentaBoletoIdTrabajador();
    }
    
}
