/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Despacho;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.Despacho_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.Trabajador;
import com.areatecnica.sigf.entities.Usuario;
import com.areatecnica.sigf.entities.Servicio;

/**
 *
 * @author ianfr
 */
@Stateless
public class DespachoFacade extends AbstractFacade<Despacho> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DespachoFacade() {
        super(Despacho.class);
    }

    public boolean isDespachoIdBusEmpty(Despacho entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Despacho> despacho = cq.from(Despacho.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(despacho, entity), cb.isNotNull(despacho.get(Despacho_.despachoIdBus)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Bus findDespachoIdBus(Despacho entity) {
        return this.getMergedEntity(entity).getDespachoIdBus();
    }

    public boolean isDespachoIdTrabajadorEmpty(Despacho entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Despacho> despacho = cq.from(Despacho.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(despacho, entity), cb.isNotNull(despacho.get(Despacho_.despachoIdTrabajador)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Trabajador findDespachoIdTrabajador(Despacho entity) {
        return this.getMergedEntity(entity).getDespachoIdTrabajador();
    }

    public boolean isDespachoIdInspectorEmpty(Despacho entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Despacho> despacho = cq.from(Despacho.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(despacho, entity), cb.isNotNull(despacho.get(Despacho_.despachoIdInspector)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Usuario findDespachoIdInspector(Despacho entity) {
        return this.getMergedEntity(entity).getDespachoIdInspector();
    }

    public boolean isDespachoIdServicioEmpty(Despacho entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Despacho> despacho = cq.from(Despacho.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(despacho, entity), cb.isNotNull(despacho.get(Despacho_.despachoIdServicio)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Servicio findDespachoIdServicio(Despacho entity) {
        return this.getMergedEntity(entity).getDespachoIdServicio();
    }
    
}
