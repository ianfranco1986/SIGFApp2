/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.DetalleIntervalosMensual;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.DetalleIntervalosMensual_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.AdministracionMensual;
import com.areatecnica.sigf.entities.IntervalosAdministracion;

/**
 *
 * @author ianfr
 */
@Stateless
public class DetalleIntervalosMensualFacade extends AbstractFacade<DetalleIntervalosMensual> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleIntervalosMensualFacade() {
        super(DetalleIntervalosMensual.class);
    }

    public boolean isDetalleIntervalosMensualIdAdministracionEmpty(DetalleIntervalosMensual entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<DetalleIntervalosMensual> detalleIntervalosMensual = cq.from(DetalleIntervalosMensual.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(detalleIntervalosMensual, entity), cb.isNotNull(detalleIntervalosMensual.get(DetalleIntervalosMensual_.detalleIntervalosMensualIdAdministracion)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public AdministracionMensual findDetalleIntervalosMensualIdAdministracion(DetalleIntervalosMensual entity) {
        return this.getMergedEntity(entity).getDetalleIntervalosMensualIdAdministracion();
    }

    public boolean isDetalleIntervalosMensualIdIntervaloAdministracionEmpty(DetalleIntervalosMensual entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<DetalleIntervalosMensual> detalleIntervalosMensual = cq.from(DetalleIntervalosMensual.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(detalleIntervalosMensual, entity), cb.isNotNull(detalleIntervalosMensual.get(DetalleIntervalosMensual_.detalleIntervalosMensualIdIntervaloAdministracion)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public IntervalosAdministracion findDetalleIntervalosMensualIdIntervaloAdministracion(DetalleIntervalosMensual entity) {
        return this.getMergedEntity(entity).getDetalleIntervalosMensualIdIntervaloAdministracion();
    }
    
}
