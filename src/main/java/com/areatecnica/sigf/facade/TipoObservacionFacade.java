/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.ObservacionTrabajador;
import com.areatecnica.sigf.entities.TipoObservacion;

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
public class TipoObservacionFacade extends AbstractFacade<TipoObservacion> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoObservacionFacade() {
        super(TipoObservacion.class);
    }

    public boolean isTipoObservacionIdCuentaEmpty(TipoObservacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoObservacion> tipoObservacion = cq.from(TipoObservacion.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoObservacion, entity), cb.isNotNull(tipoObservacion.get(TipoObservacion_.tipoObservacionIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findTipoObservacionIdCuenta(TipoObservacion entity) {
        return this.getMergedEntity(entity).getTipoObservacionIdCuenta();
    }

    public boolean isObservacionTrabajadorListEmpty(TipoObservacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoObservacion> tipoObservacion = cq.from(TipoObservacion.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoObservacion, entity), cb.isNotEmpty(tipoObservacion.get(TipoObservacion_.observacionTrabajadorList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<ObservacionTrabajador> findObservacionTrabajadorList(TipoObservacion entity) {
        TipoObservacion mergedEntity = this.getMergedEntity(entity);
        List<ObservacionTrabajador> observacionTrabajadorList = mergedEntity.getObservacionTrabajadorList();
        observacionTrabajadorList.size();
        return observacionTrabajadorList;
    }
    
}
