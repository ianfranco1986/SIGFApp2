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
public class BoletoFacade extends AbstractFacade<Boleto> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BoletoFacade() {
        super(Boleto.class);
    }

    public boolean isBoletoIdCuentaEmpty(Boleto entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Boleto> boleto = cq.from(Boleto.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(boleto, entity), cb.isNotNull(boleto.get(Boleto_.boletoIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findBoletoIdCuenta(Boleto entity) {
        return this.getMergedEntity(entity).getBoletoIdCuenta();
    }

    public boolean isInventarioInternoListEmpty(Boleto entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Boleto> boleto = cq.from(Boleto.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(boleto, entity), cb.isNotEmpty(boleto.get(Boleto_.inventarioInternoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<InventarioInterno> findInventarioInternoList(Boleto entity) {
        Boleto mergedEntity = this.getMergedEntity(entity);
        List<InventarioInterno> inventarioInternoList = mergedEntity.getInventarioInternoList();
        inventarioInternoList.size();
        return inventarioInternoList;
    }

    public boolean isDetalleCompraBoletoListEmpty(Boleto entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Boleto> boleto = cq.from(Boleto.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(boleto, entity), cb.isNotEmpty(boleto.get(Boleto_.detalleCompraBoletoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<DetalleCompraBoleto> findDetalleCompraBoletoList(Boleto entity) {
        Boleto mergedEntity = this.getMergedEntity(entity);
        List<DetalleCompraBoleto> detalleCompraBoletoList = mergedEntity.getDetalleCompraBoletoList();
        detalleCompraBoletoList.size();
        return detalleCompraBoletoList;
    }

    public boolean isRegistroBoletoListEmpty(Boleto entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Boleto> boleto = cq.from(Boleto.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(boleto, entity), cb.isNotEmpty(boleto.get(Boleto_.registroBoletoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<RegistroBoleto> findRegistroBoletoList(Boleto entity) {
        Boleto mergedEntity = this.getMergedEntity(entity);
        List<RegistroBoleto> registroBoletoList = mergedEntity.getRegistroBoletoList();
        registroBoletoList.size();
        return registroBoletoList;
    }

    public boolean isTarifaGrupoServicioListEmpty(Boleto entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Boleto> boleto = cq.from(Boleto.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(boleto, entity), cb.isNotEmpty(boleto.get(Boleto_.tarifaGrupoServicioList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<TarifaGrupoServicio> findTarifaGrupoServicioList(Boleto entity) {
        Boleto mergedEntity = this.getMergedEntity(entity);
        List<TarifaGrupoServicio> tarifaGrupoServicioList = mergedEntity.getTarifaGrupoServicioList();
        tarifaGrupoServicioList.size();
        return tarifaGrupoServicioList;
    }
    
}
