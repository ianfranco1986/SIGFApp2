/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.CuentaBancoTrabajador;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.CuentaBancoTrabajador_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.CuentaBancaria;
import com.areatecnica.sigf.entities.Trabajador;

/**
 *
 * @author ianfr
 */
@Stateless
public class CuentaBancoTrabajadorFacade extends AbstractFacade<CuentaBancoTrabajador> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuentaBancoTrabajadorFacade() {
        super(CuentaBancoTrabajador.class);
    }

    public boolean isCuentaBancoTrabajadorCuentaEmpty(CuentaBancoTrabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CuentaBancoTrabajador> cuentaBancoTrabajador = cq.from(CuentaBancoTrabajador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuentaBancoTrabajador, entity), cb.isNotNull(cuentaBancoTrabajador.get(CuentaBancoTrabajador_.cuentaBancoTrabajadorCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public CuentaBancaria findCuentaBancoTrabajadorCuenta(CuentaBancoTrabajador entity) {
        return this.getMergedEntity(entity).getCuentaBancoTrabajadorCuenta();
    }

    public boolean isCuentaBancoTrabajadorTrabajadorEmpty(CuentaBancoTrabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CuentaBancoTrabajador> cuentaBancoTrabajador = cq.from(CuentaBancoTrabajador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cuentaBancoTrabajador, entity), cb.isNotNull(cuentaBancoTrabajador.get(CuentaBancoTrabajador_.cuentaBancoTrabajadorTrabajador)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Trabajador findCuentaBancoTrabajadorTrabajador(CuentaBancoTrabajador entity) {
        return this.getMergedEntity(entity).getCuentaBancoTrabajadorTrabajador();
    }
    
}
