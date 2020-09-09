/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Banco;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.Banco_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.CuentaBancaria;
import com.areatecnica.sigf.entities.DetalleResumenCheque;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class BancoFacade extends AbstractFacade<Banco> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BancoFacade() {
        super(Banco.class);
    }

    public boolean isBancoIdCuentaEmpty(Banco entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Banco> banco = cq.from(Banco.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(banco, entity), cb.isNotNull(banco.get(Banco_.bancoIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findBancoIdCuenta(Banco entity) {
        return this.getMergedEntity(entity).getBancoIdCuenta();
    }

    public boolean isCuentaBancariaListEmpty(Banco entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Banco> banco = cq.from(Banco.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(banco, entity), cb.isNotEmpty(banco.get(Banco_.cuentaBancariaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<CuentaBancaria> findCuentaBancariaList(Banco entity) {
        Banco mergedEntity = this.getMergedEntity(entity);
        List<CuentaBancaria> cuentaBancariaList = mergedEntity.getCuentaBancariaList();
        cuentaBancariaList.size();
        return cuentaBancariaList;
    }

    public boolean isDetalleResumenChequeListEmpty(Banco entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Banco> banco = cq.from(Banco.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(banco, entity), cb.isNotEmpty(banco.get(Banco_.detalleResumenChequeList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<DetalleResumenCheque> findDetalleResumenChequeList(Banco entity) {
        Banco mergedEntity = this.getMergedEntity(entity);
        List<DetalleResumenCheque> detalleResumenChequeList = mergedEntity.getDetalleResumenChequeList();
        detalleResumenChequeList.size();
        return detalleResumenChequeList;
    }
    
}
