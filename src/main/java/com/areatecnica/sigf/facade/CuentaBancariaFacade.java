/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.CuentaBancaria;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ianfr
 */
@Stateless
public class CuentaBancariaFacade extends AbstractFacade<CuentaBancaria> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuentaBancariaFacade() {
        super(CuentaBancaria.class);
    }

//    public boolean isCuentaBancoProcesoListEmpty(CuentaBancaria entity) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
//        Root<CuentaBancaria> cuentaBancaria = cq.from(CuentaBancaria.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuentaBancaria, entity), cb.isNotEmpty(cuentaBancaria.get(CuentaBancaria_.cuentaBancoProcesoList)));
//        return em.createQuery(cq).getResultList().isEmpty();
//    }
//
//    public List<CuentaBancoProceso> findCuentaBancoProcesoList(CuentaBancaria entity) {
//        CuentaBancaria mergedEntity = this.getMergedEntity(entity);
//        List<CuentaBancoProceso> cuentaBancoProcesoList = mergedEntity.getCuentaBancoProcesoList();
//        cuentaBancoProcesoList.size();
//        return cuentaBancoProcesoList;
//    }
//
//    public boolean isCuentaBancoEmpresaListEmpty(CuentaBancaria entity) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
//        Root<CuentaBancaria> cuentaBancaria = cq.from(CuentaBancaria.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuentaBancaria, entity), cb.isNotEmpty(cuentaBancaria.get(CuentaBancaria_.cuentaBancoEmpresaList)));
//        return em.createQuery(cq).getResultList().isEmpty();
//    }
//
//
//    public boolean isCuentaBancariaIdBancoEmpty(CuentaBancaria entity) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
//        Root<CuentaBancaria> cuentaBancaria = cq.from(CuentaBancaria.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuentaBancaria, entity), cb.isNotNull(cuentaBancaria.get(CuentaBancaria_.cuentaBancariaIdBanco)));
//        return em.createQuery(cq).getResultList().isEmpty();
//    }
//
//    public Banco findCuentaBancariaIdBanco(CuentaBancaria entity) {
//        return this.getMergedEntity(entity).getCuentaBancariaIdBanco();
//    }
//
//    public boolean isCuentaBancariaIdTipoCuentaEmpty(CuentaBancaria entity) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
//        Root<CuentaBancaria> cuentaBancaria = cq.from(CuentaBancaria.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuentaBancaria, entity), cb.isNotNull(cuentaBancaria.get(CuentaBancaria_.cuentaBancariaIdTipoCuenta)));
//        return em.createQuery(cq).getResultList().isEmpty();
//    }
//
//    public TipoCuentaBanco findCuentaBancariaIdTipoCuenta(CuentaBancaria entity) {
//        return this.getMergedEntity(entity).getCuentaBancariaIdTipoCuenta();
//    }
//
//    public boolean isCuentaBancoTrabajadorListEmpty(CuentaBancaria entity) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
//        Root<CuentaBancaria> cuentaBancaria = cq.from(CuentaBancaria.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuentaBancaria, entity), cb.isNotEmpty(cuentaBancaria.get(CuentaBancaria_.cuentaBancoTrabajadorList)));
//        return em.createQuery(cq).getResultList().isEmpty();
//    }
//
//    public List<CuentaBancoTrabajador> findCuentaBancoTrabajadorList(CuentaBancaria entity) {
//        CuentaBancaria mergedEntity = this.getMergedEntity(entity);
//        List<CuentaBancoTrabajador> cuentaBancoTrabajadorList = mergedEntity.getCuentaBancoTrabajadorList();
//        cuentaBancoTrabajadorList.size();
//        return cuentaBancoTrabajadorList;
//    }
    
}
