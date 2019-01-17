/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.CompraBoleto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.CompraBoleto_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.DetalleCompraBoleto;
import com.areatecnica.sigf.entities.Cuenta;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class CompraBoletoFacade extends AbstractFacade<CompraBoleto> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompraBoletoFacade() {
        super(CompraBoleto.class);
    }

    public boolean isDetalleCompraBoletoListEmpty(CompraBoleto entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CompraBoleto> compraBoleto = cq.from(CompraBoleto.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(compraBoleto, entity), cb.isNotEmpty(compraBoleto.get(CompraBoleto_.detalleCompraBoletoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<DetalleCompraBoleto> findDetalleCompraBoletoList(CompraBoleto entity) {
        CompraBoleto mergedEntity = this.getMergedEntity(entity);
        List<DetalleCompraBoleto> detalleCompraBoletoList = mergedEntity.getDetalleCompraBoletoList();
        detalleCompraBoletoList.size();
        return detalleCompraBoletoList;
    }

    public boolean isCompraBoletoIdCuentaEmpty(CompraBoleto entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CompraBoleto> compraBoleto = cq.from(CompraBoleto.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(compraBoleto, entity), cb.isNotNull(compraBoleto.get(CompraBoleto_.compraBoletoIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findCompraBoletoIdCuenta(CompraBoleto entity) {
        return this.getMergedEntity(entity).getCompraBoletoIdCuenta();
    }
    
}
