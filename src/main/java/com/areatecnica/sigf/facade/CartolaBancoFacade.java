/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.CartolaBanco;
import com.areatecnica.sigf.entities.CuentaBancaria;
import com.areatecnica.sigf.entities.DetalleCartola;

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
public class CartolaBancoFacade extends AbstractFacade<CartolaBanco> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CartolaBancoFacade() {
        super(CartolaBanco.class);
    }

    public boolean isCartolaBancoIdCuentaBancariaEmpty(CartolaBanco entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CartolaBanco> cartolaBanco = cq.from(CartolaBanco.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cartolaBanco, entity), cb.isNotNull(cartolaBanco.get(CartolaBanco_.cartolaBancoIdCuentaBancaria)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public CuentaBancaria findCartolaBancoIdCuentaBancaria(CartolaBanco entity) {
        return this.getMergedEntity(entity).getCartolaBancoIdCuentaBancaria();
    }

    public boolean isDetalleCartolaListEmpty(CartolaBanco entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CartolaBanco> cartolaBanco = cq.from(CartolaBanco.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cartolaBanco, entity), cb.isNotEmpty(cartolaBanco.get(CartolaBanco_.detalleCartolaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<DetalleCartola> findDetalleCartolaList(CartolaBanco entity) {
        CartolaBanco mergedEntity = this.getMergedEntity(entity);
        List<DetalleCartola> detalleCartolaList = mergedEntity.getDetalleCartolaList();
        detalleCartolaList.size();
        return detalleCartolaList;
    }
    
}
