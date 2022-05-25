/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Control;
import com.areatecnica.sigf.entities.ControlHorarioServicio;
import com.areatecnica.sigf.entities.HorarioServicio;

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
public class ControlHorarioServicioFacade extends AbstractFacade<ControlHorarioServicio> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ControlHorarioServicioFacade() {
        super(ControlHorarioServicio.class);
    }

    public boolean isControlHorarioServicioIdControlEmpty(ControlHorarioServicio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ControlHorarioServicio> controlHorarioServicio = cq.from(ControlHorarioServicio.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(controlHorarioServicio, entity), cb.isNotNull(controlHorarioServicio.get(ControlHorarioServicio_.controlHorarioServicioIdControl)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Control findControlHorarioServicioIdControl(ControlHorarioServicio entity) {
        return this.getMergedEntity(entity).getControlHorarioServicioIdControl();
    }

    public boolean isControlHorarioServicioIdHorarioEmpty(ControlHorarioServicio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ControlHorarioServicio> controlHorarioServicio = cq.from(ControlHorarioServicio.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(controlHorarioServicio, entity), cb.isNotNull(controlHorarioServicio.get(ControlHorarioServicio_.controlHorarioServicioIdHorario)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public HorarioServicio findControlHorarioServicioIdHorario(ControlHorarioServicio entity) {
        return this.getMergedEntity(entity).getControlHorarioServicioIdHorario();
    }
    
}
