/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.DetalleResumenRecaudacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.DetalleResumenRecaudacion_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.ResumenRecaudacion;

/**
 *
 * @author ianfr
 */
@Stateless
public class DetalleResumenRecaudacionFacade extends AbstractFacade<DetalleResumenRecaudacion> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleResumenRecaudacionFacade() {
        super(DetalleResumenRecaudacion.class);
    }

    public boolean isDetalleResumenRecaudacionIdResumenEmpty(DetalleResumenRecaudacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<DetalleResumenRecaudacion> detalleResumenRecaudacion = cq.from(DetalleResumenRecaudacion.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(detalleResumenRecaudacion, entity), cb.isNotNull(detalleResumenRecaudacion.get(DetalleResumenRecaudacion_.detalleResumenRecaudacionIdResumen)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public ResumenRecaudacion findDetalleResumenRecaudacionIdResumen(DetalleResumenRecaudacion entity) {
        return this.getMergedEntity(entity).getDetalleResumenRecaudacionIdResumen();
    }
    
}
