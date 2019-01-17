/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.CompraCombustible;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.CompraCombustible_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.TipoCombustible;

/**
 *
 * @author ianfr
 */
@Stateless
public class CompraCombustibleFacade extends AbstractFacade<CompraCombustible> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompraCombustibleFacade() {
        super(CompraCombustible.class);
    }

    public boolean isCompraCombustibleIdCuentaEmpty(CompraCombustible entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CompraCombustible> compraCombustible = cq.from(CompraCombustible.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(compraCombustible, entity), cb.isNotNull(compraCombustible.get(CompraCombustible_.compraCombustibleIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findCompraCombustibleIdCuenta(CompraCombustible entity) {
        return this.getMergedEntity(entity).getCompraCombustibleIdCuenta();
    }

    public boolean isCompraCombustibleIdTipoEmpty(CompraCombustible entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CompraCombustible> compraCombustible = cq.from(CompraCombustible.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(compraCombustible, entity), cb.isNotNull(compraCombustible.get(CompraCombustible_.compraCombustibleIdTipo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public TipoCombustible findCompraCombustibleIdTipo(CompraCombustible entity) {
        return this.getMergedEntity(entity).getCompraCombustibleIdTipo();
    }
    
}
