/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.Egreso;
import com.areatecnica.sigf.entities.EgresoProcesoRecaudacion;
import com.areatecnica.sigf.entities.ProcesoRecaudacion;

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
public class EgresoProcesoRecaudacionFacade extends AbstractFacade<EgresoProcesoRecaudacion> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EgresoProcesoRecaudacionFacade() {
        super(EgresoProcesoRecaudacion.class);
    }

    public boolean isEgresoProcesoRecaudacionIdEgresoEmpty(EgresoProcesoRecaudacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<EgresoProcesoRecaudacion> egresoProcesoRecaudacion = cq.from(EgresoProcesoRecaudacion.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(egresoProcesoRecaudacion, entity), cb.isNotNull(egresoProcesoRecaudacion.get(EgresoProcesoRecaudacion_.egresoProcesoRecaudacionIdEgreso)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Egreso findEgresoProcesoRecaudacionIdEgreso(EgresoProcesoRecaudacion entity) {
        return this.getMergedEntity(entity).getEgresoProcesoRecaudacionIdEgreso();
    }

    public boolean isEgresoProcesoRecaudacionIdProcesoEmpty(EgresoProcesoRecaudacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<EgresoProcesoRecaudacion> egresoProcesoRecaudacion = cq.from(EgresoProcesoRecaudacion.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(egresoProcesoRecaudacion, entity), cb.isNotNull(egresoProcesoRecaudacion.get(EgresoProcesoRecaudacion_.egresoProcesoRecaudacionIdProceso)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public ProcesoRecaudacion findEgresoProcesoRecaudacionIdProceso(EgresoProcesoRecaudacion entity) {
        return this.getMergedEntity(entity).getEgresoProcesoRecaudacionIdProceso();
    }
    
}
