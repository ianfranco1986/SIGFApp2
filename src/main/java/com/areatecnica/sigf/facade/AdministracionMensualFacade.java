/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.AdministracionMensual;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.AdministracionMensual_;
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
public class AdministracionMensualFacade extends AbstractFacade<AdministracionMensual> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministracionMensualFacade() {
        super(AdministracionMensual.class);
    }

    public boolean isAdministracionMensualIdCuentaEmpty(AdministracionMensual entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<AdministracionMensual> administracionMensual = cq.from(AdministracionMensual.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(administracionMensual, entity), cb.isNotNull(administracionMensual.get(AdministracionMensual_.administracionMensualIdCuenta)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cuenta findAdministracionMensualIdCuenta(AdministracionMensual entity) {
        return this.getMergedEntity(entity).getAdministracionMensualIdCuenta();
    }

    public boolean isDetalleIntervalosMensualListEmpty(AdministracionMensual entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<AdministracionMensual> administracionMensual = cq.from(AdministracionMensual.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(administracionMensual, entity), cb.isNotEmpty(administracionMensual.get(AdministracionMensual_.detalleIntervalosMensualList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<DetalleIntervalosMensual> findDetalleIntervalosMensualList(AdministracionMensual entity) {
        AdministracionMensual mergedEntity = this.getMergedEntity(entity);
        List<DetalleIntervalosMensual> detalleIntervalosMensualList = mergedEntity.getDetalleIntervalosMensualList();
        detalleIntervalosMensualList.size();
        return detalleIntervalosMensualList;
    }
    
}
