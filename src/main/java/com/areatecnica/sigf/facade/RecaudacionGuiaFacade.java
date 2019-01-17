/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.RecaudacionGuia;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.RecaudacionGuia_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Egreso;
import com.areatecnica.sigf.entities.Guia;
import com.areatecnica.sigf.entities.Recaudacion;

/**
 *
 * @author ianfr
 */
@Stateless
public class RecaudacionGuiaFacade extends AbstractFacade<RecaudacionGuia> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecaudacionGuiaFacade() {
        super(RecaudacionGuia.class);
    }

    public boolean isRecaudacionGuiaIdEgresoEmpty(RecaudacionGuia entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RecaudacionGuia> recaudacionGuia = cq.from(RecaudacionGuia.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(recaudacionGuia, entity), cb.isNotNull(recaudacionGuia.get(RecaudacionGuia_.recaudacionGuiaIdEgreso)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Egreso findRecaudacionGuiaIdEgreso(RecaudacionGuia entity) {
        return this.getMergedEntity(entity).getRecaudacionGuiaIdEgreso();
    }

    public boolean isRecaudacionGuiaIdGuiaEmpty(RecaudacionGuia entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RecaudacionGuia> recaudacionGuia = cq.from(RecaudacionGuia.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(recaudacionGuia, entity), cb.isNotNull(recaudacionGuia.get(RecaudacionGuia_.recaudacionGuiaIdGuia)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Guia findRecaudacionGuiaIdGuia(RecaudacionGuia entity) {
        return this.getMergedEntity(entity).getRecaudacionGuiaIdGuia();
    }

    public boolean isRecaudacionGuiaIdRecaudacionEmpty(RecaudacionGuia entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<RecaudacionGuia> recaudacionGuia = cq.from(RecaudacionGuia.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(recaudacionGuia, entity), cb.isNotNull(recaudacionGuia.get(RecaudacionGuia_.recaudacionGuiaIdRecaudacion)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Recaudacion findRecaudacionGuiaIdRecaudacion(RecaudacionGuia entity) {
        return this.getMergedEntity(entity).getRecaudacionGuiaIdRecaudacion();
    }
    
}
