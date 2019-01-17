/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.RecaudacionBoleto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.RecaudacionBoleto_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Recaudacion;
import com.areatecnica.sigf.entities.VentaBoleto;

/**
 *
 * @author ianfr
 */
@Stateless
public class RecaudacionBoletoFacade extends AbstractFacade<RecaudacionBoleto> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecaudacionBoletoFacade() {
        super(RecaudacionBoleto.class);
    }

    public boolean isRecaudacionBoletoIdRecaudacionEmpty(RecaudacionBoleto entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RecaudacionBoleto> recaudacionBoleto = cq.from(RecaudacionBoleto.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(recaudacionBoleto, entity), cb.isNotNull(recaudacionBoleto.get(RecaudacionBoleto_.recaudacionBoletoIdRecaudacion)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Recaudacion findRecaudacionBoletoIdRecaudacion(RecaudacionBoleto entity) {
        return this.getMergedEntity(entity).getRecaudacionBoletoIdRecaudacion();
    }

    public boolean isRecaudacionBoletoIdVentaBoletoEmpty(RecaudacionBoleto entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RecaudacionBoleto> recaudacionBoleto = cq.from(RecaudacionBoleto.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(recaudacionBoleto, entity), cb.isNotNull(recaudacionBoleto.get(RecaudacionBoleto_.recaudacionBoletoIdVentaBoleto)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public VentaBoleto findRecaudacionBoletoIdVentaBoleto(RecaudacionBoleto entity) {
        return this.getMergedEntity(entity).getRecaudacionBoletoIdVentaBoleto();
    }
    
}
