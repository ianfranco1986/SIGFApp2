/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class ControlFacade extends AbstractFacade<Control> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ControlFacade() {
        super(Control.class);
    }

    public boolean isControlServicioListEmpty(Control entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Control> control = cq.from(Control.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(control, entity), cb.isNotEmpty(control.get(Control_.controlServicioList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<ControlServicio> findControlServicioList(Control entity) {
        Control mergedEntity = this.getMergedEntity(entity);
        List<ControlServicio> controlServicioList = mergedEntity.getControlServicioList();
        controlServicioList.size();
        return controlServicioList;
    }

    public boolean isControlHorarioServicioListEmpty(Control entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Control> control = cq.from(Control.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(control, entity), cb.isNotEmpty(control.get(Control_.controlHorarioServicioList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<ControlHorarioServicio> findControlHorarioServicioList(Control entity) {
        Control mergedEntity = this.getMergedEntity(entity);
        List<ControlHorarioServicio> controlHorarioServicioList = mergedEntity.getControlHorarioServicioList();
        controlHorarioServicioList.size();
        return controlHorarioServicioList;
    }

    public boolean isControlIdCuentaEmpty(Control entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Control> control = cq.from(Control.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(control, entity), cb.isNotNull(control.get(Control_.controlIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findControlIdCuenta(Control entity) {
        return this.getMergedEntity(entity).getControlIdCuenta();
    }

    public boolean isControlIdTipoEmpty(Control entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Control> control = cq.from(Control.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(control, entity), cb.isNotNull(control.get(Control_.controlIdTipo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public TipoControl findControlIdTipo(Control entity) {
        return this.getMergedEntity(entity).getControlIdTipo();
    }
    
}
