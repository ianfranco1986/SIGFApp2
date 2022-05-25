/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.CompraPetroleo;

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
public class CompraPetroleoFacade extends AbstractFacade<CompraPetroleo> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompraPetroleoFacade() {
        super(CompraPetroleo.class);
    }

    public boolean isCompraPetroleoIdCuentaEmpty(CompraPetroleo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CompraPetroleo> compraCombustible = cq.from(CompraPetroleo.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(compraCombustible, entity), cb.isNotNull(compraCombustible.get(CompraPetroleo_.compraCombustibleIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public boolean isCompraPetroleoIdTipoEmpty(CompraPetroleo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CompraPetroleo> compraCombustible = cq.from(CompraPetroleo.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(compraCombustible, entity), cb.isNotNull(compraCombustible.get(CompraPetroleo_.compraCombustibleIdTipo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

}
