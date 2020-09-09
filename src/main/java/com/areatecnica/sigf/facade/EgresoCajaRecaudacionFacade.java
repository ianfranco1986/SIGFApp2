/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.EgresoCajaRecaudacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.EgresoCajaRecaudacion_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Egreso;
import com.areatecnica.sigf.entities.CajaRecaudacion;

/**
 *
 * @author ianfr
 */
@Stateless
public class EgresoCajaRecaudacionFacade extends AbstractFacade<EgresoCajaRecaudacion> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EgresoCajaRecaudacionFacade() {
        super(EgresoCajaRecaudacion.class);
    }

    public boolean isEgresoCajaRecaudacionIdEgresoEmpty(EgresoCajaRecaudacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<EgresoCajaRecaudacion> egresoCajaRecaudacion = cq.from(EgresoCajaRecaudacion.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(egresoCajaRecaudacion, entity), cb.isNotNull(egresoCajaRecaudacion.get(EgresoCajaRecaudacion_.egresoCajaRecaudacionIdEgreso)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Egreso findEgresoCajaRecaudacionIdEgreso(EgresoCajaRecaudacion entity) {
        return this.getMergedEntity(entity).getEgresoCajaRecaudacionIdEgreso();
    }

    public boolean isEgresoCajaRecaudacionIdCajaEmpty(EgresoCajaRecaudacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<EgresoCajaRecaudacion> egresoCajaRecaudacion = cq.from(EgresoCajaRecaudacion.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(egresoCajaRecaudacion, entity), cb.isNotNull(egresoCajaRecaudacion.get(EgresoCajaRecaudacion_.egresoCajaRecaudacionIdCaja)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public CajaRecaudacion findEgresoCajaRecaudacionIdCaja(EgresoCajaRecaudacion entity) {
        return this.getMergedEntity(entity).getEgresoCajaRecaudacionIdCaja();
    }
    
}
