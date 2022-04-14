/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.ObservacionTrabajador;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.ObservacionTrabajador_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.TipoObservacion;
import com.areatecnica.sigf.entities.Trabajador;

/**
 *
 * @author ianfr
 */
@Stateless
public class ObservacionTrabajadorFacade extends AbstractFacade<ObservacionTrabajador> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ObservacionTrabajadorFacade() {
        super(ObservacionTrabajador.class);
    }

    public boolean isObservacionTrabajadorIdTipoObservacionEmpty(ObservacionTrabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ObservacionTrabajador> observacionTrabajador = cq.from(ObservacionTrabajador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(observacionTrabajador, entity), cb.isNotNull(observacionTrabajador.get(ObservacionTrabajador_.observacionTrabajadorIdTipoObservacion)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public TipoObservacion findObservacionTrabajadorIdTipoObservacion(ObservacionTrabajador entity) {
        return this.getMergedEntity(entity).getObservacionTrabajadorIdTipoObservacion();
    }

    public boolean isObservacionTrabajadorIdTrabajadorEmpty(ObservacionTrabajador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ObservacionTrabajador> observacionTrabajador = cq.from(ObservacionTrabajador.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(observacionTrabajador, entity), cb.isNotNull(observacionTrabajador.get(ObservacionTrabajador_.observacionTrabajadorIdTrabajador)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Trabajador findObservacionTrabajadorIdTrabajador(ObservacionTrabajador entity) {
        return this.getMergedEntity(entity).getObservacionTrabajadorIdTrabajador();
    }
    
}
