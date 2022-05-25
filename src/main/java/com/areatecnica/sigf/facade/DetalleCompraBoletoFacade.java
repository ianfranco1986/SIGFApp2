/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.CompraBoleto;
import com.areatecnica.sigf.entities.DetalleCompraBoleto;

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
public class DetalleCompraBoletoFacade extends AbstractFacade<DetalleCompraBoleto> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleCompraBoletoFacade() {
        super(DetalleCompraBoleto.class);
    }

    public boolean isDetalleCompraBoletoIdBoletoEmpty(DetalleCompraBoleto entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<DetalleCompraBoleto> detalleCompraBoleto = cq.from(DetalleCompraBoleto.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(detalleCompraBoleto, entity), cb.isNotNull(detalleCompraBoleto.get(DetalleCompraBoleto_.detalleCompraBoletoIdBoleto)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Boleto findDetalleCompraBoletoIdBoleto(DetalleCompraBoleto entity) {
        return this.getMergedEntity(entity).getDetalleCompraBoletoIdBoleto();
    }

    public boolean isDetalleCompraBoletoIdCompraBoletoEmpty(DetalleCompraBoleto entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<DetalleCompraBoleto> detalleCompraBoleto = cq.from(DetalleCompraBoleto.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(detalleCompraBoleto, entity), cb.isNotNull(detalleCompraBoleto.get(DetalleCompraBoleto_.detalleCompraBoletoIdCompraBoleto)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public CompraBoleto findDetalleCompraBoletoIdCompraBoleto(DetalleCompraBoleto entity) {
        return this.getMergedEntity(entity).getDetalleCompraBoletoIdCompraBoleto();
    }
    
}
