/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.ControlServicio;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.ControlServicio_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Control;
import com.areatecnica.sigf.entities.Servicio;

/**
 *
 * @author ianfr
 */
@Stateless
public class ControlServicioFacade extends AbstractFacade<ControlServicio> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ControlServicioFacade() {
        super(ControlServicio.class);
    }

    public boolean isControlServicioIdControlEmpty(ControlServicio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ControlServicio> controlServicio = cq.from(ControlServicio.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(controlServicio, entity), cb.isNotNull(controlServicio.get(ControlServicio_.controlServicioIdControl)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Control findControlServicioIdControl(ControlServicio entity) {
        return this.getMergedEntity(entity).getControlServicioIdControl();
    }

    public boolean isControlServicioIdServicioEmpty(ControlServicio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ControlServicio> controlServicio = cq.from(ControlServicio.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(controlServicio, entity), cb.isNotNull(controlServicio.get(ControlServicio_.controlServicioIdServicio)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Servicio findControlServicioIdServicio(ControlServicio entity) {
        return this.getMergedEntity(entity).getControlServicioIdServicio();
    }
    
}
