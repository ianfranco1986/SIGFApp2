/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.RegistroBoleto;

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
public class RegistroBoletoFacade extends AbstractFacade<RegistroBoleto> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegistroBoletoFacade() {
        super(RegistroBoleto.class);
    }

    public boolean isRegistroBoletoIdBoletoEmpty(RegistroBoleto entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RegistroBoleto> registroBoleto = cq.from(RegistroBoleto.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(registroBoleto, entity), cb.isNotNull(registroBoleto.get(RegistroBoleto_.registroBoletoIdBoleto)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Boleto findRegistroBoletoIdBoleto(RegistroBoleto entity) {
        return this.getMergedEntity(entity).getRegistroBoletoIdBoleto();
    }
    
}
