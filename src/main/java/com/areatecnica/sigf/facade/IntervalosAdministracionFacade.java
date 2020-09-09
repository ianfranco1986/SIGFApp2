/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.IntervalosAdministracion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.IntervalosAdministracion_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.DetalleIntervalosMensual;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class IntervalosAdministracionFacade extends AbstractFacade<IntervalosAdministracion> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IntervalosAdministracionFacade() {
        super(IntervalosAdministracion.class);
    }

    public boolean isIntervalosAdministracionIdCuentaEmpty(IntervalosAdministracion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<IntervalosAdministracion> intervalosAdministracion = cq.from(IntervalosAdministracion.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(intervalosAdministracion, entity), cb.isNotNull(intervalosAdministracion.get(IntervalosAdministracion_.intervalosAdministracionIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findIntervalosAdministracionIdCuenta(IntervalosAdministracion entity) {
        return this.getMergedEntity(entity).getIntervalosAdministracionIdCuenta();
    }

    public boolean isDetalleIntervalosMensualListEmpty(IntervalosAdministracion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<IntervalosAdministracion> intervalosAdministracion = cq.from(IntervalosAdministracion.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(intervalosAdministracion, entity), cb.isNotEmpty(intervalosAdministracion.get(IntervalosAdministracion_.detalleIntervalosMensualList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<DetalleIntervalosMensual> findDetalleIntervalosMensualList(IntervalosAdministracion entity) {
        IntervalosAdministracion mergedEntity = this.getMergedEntity(entity);
        List<DetalleIntervalosMensual> detalleIntervalosMensualList = mergedEntity.getDetalleIntervalosMensualList();
        detalleIntervalosMensualList.size();
        return detalleIntervalosMensualList;
    }
    
}
