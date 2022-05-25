/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Recaudacion;
import com.areatecnica.sigf.entities.RecaudacionCombustible;
import com.areatecnica.sigf.entities.VentaCombustible;

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
public class RecaudacionCombustibleFacade extends AbstractFacade<RecaudacionCombustible> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecaudacionCombustibleFacade() {
        super(RecaudacionCombustible.class);
    }

    public boolean isRecaudacionCombustibleIdRecaudacionEmpty(RecaudacionCombustible entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RecaudacionCombustible> recaudacionCombustible = cq.from(RecaudacionCombustible.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(recaudacionCombustible, entity), cb.isNotNull(recaudacionCombustible.get(RecaudacionCombustible_.recaudacionCombustibleIdRecaudacion)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Recaudacion findRecaudacionCombustibleIdRecaudacion(RecaudacionCombustible entity) {
        return this.getMergedEntity(entity).getRecaudacionCombustibleIdRecaudacion();
    }

    public boolean isRecaudacionCombustibleIdVentaCombustibleEmpty(RecaudacionCombustible entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RecaudacionCombustible> recaudacionCombustible = cq.from(RecaudacionCombustible.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(recaudacionCombustible, entity), cb.isNotNull(recaudacionCombustible.get(RecaudacionCombustible_.recaudacionCombustibleIdVentaCombustible)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public VentaCombustible findRecaudacionCombustibleIdVentaCombustible(RecaudacionCombustible entity) {
        return this.getMergedEntity(entity).getRecaudacionCombustibleIdVentaCombustible();
    }
    
}
