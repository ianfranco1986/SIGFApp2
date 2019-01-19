/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.CalleServicio;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.CalleServicio_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Calle;
import com.areatecnica.sigf.entities.Servicio;

/**
 *
 * @author ianfr
 */
@Stateless
public class CalleServicioFacade extends AbstractFacade<CalleServicio> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CalleServicioFacade() {
        super(CalleServicio.class);
    }

    public boolean isCalleServicioIdCalleEmpty(CalleServicio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CalleServicio> calleServicio = cq.from(CalleServicio.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(calleServicio, entity), cb.isNotNull(calleServicio.get(CalleServicio_.calleServicioIdCalle)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Calle findCalleServicioIdCalle(CalleServicio entity) {
        return this.getMergedEntity(entity).getCalleServicioIdCalle();
    }

    public boolean isCalleServicioIdServicioEmpty(CalleServicio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CalleServicio> calleServicio = cq.from(CalleServicio.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(calleServicio, entity), cb.isNotNull(calleServicio.get(CalleServicio_.calleServicioIdServicio)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Servicio findCalleServicioIdServicio(CalleServicio entity) {
        return this.getMergedEntity(entity).getCalleServicioIdServicio();
    }
    
}
