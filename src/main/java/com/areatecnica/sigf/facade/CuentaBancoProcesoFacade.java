/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.CuentaBancoProceso;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.CuentaBancoProceso_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.CuentaBancaria;
import com.areatecnica.sigf.entities.ProcesoRecaudacion;

/**
 *
 * @author ianfr
 */
@Stateless
public class CuentaBancoProcesoFacade extends AbstractFacade<CuentaBancoProceso> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuentaBancoProcesoFacade() {
        super(CuentaBancoProceso.class);
    }

    public boolean isCuentaBancoProcesoIdBancoEmpty(CuentaBancoProceso entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CuentaBancoProceso> cuentaBancoProceso = cq.from(CuentaBancoProceso.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuentaBancoProceso, entity), cb.isNotNull(cuentaBancoProceso.get(CuentaBancoProceso_.cuentaBancoProcesoIdBanco)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public CuentaBancaria findCuentaBancoProcesoIdBanco(CuentaBancoProceso entity) {
        return this.getMergedEntity(entity).getCuentaBancoProcesoIdBanco();
    }

    public boolean isCuentaBancoProcesoIdProcesoEmpty(CuentaBancoProceso entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CuentaBancoProceso> cuentaBancoProceso = cq.from(CuentaBancoProceso.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuentaBancoProceso, entity), cb.isNotNull(cuentaBancoProceso.get(CuentaBancoProceso_.cuentaBancoProcesoIdProceso)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public ProcesoRecaudacion findCuentaBancoProcesoIdProceso(CuentaBancoProceso entity) {
        return this.getMergedEntity(entity).getCuentaBancoProcesoIdProceso();
    }
    
}
