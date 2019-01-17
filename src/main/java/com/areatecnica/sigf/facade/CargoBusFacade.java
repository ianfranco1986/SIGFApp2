/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.CargoBus;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.areatecnica.sigf.entities.CargoBus;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.CargoLiquidacion;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.TipoCargo;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class CargoBusFacade extends AbstractFacade<CargoBus> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CargoBusFacade() {
        super(CargoBus.class);
    }

//    public boolean isCargoLiquidacionListEmpty(CargoBus entity) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
//        Root<CargoBus> cargoBus = cq.from(CargoBus.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cargoBus, entity), cb.isNotEmpty(cargoBus.get(CargoBus_.cargoLiquidacionList)));
//        return em.createQuery(cq).getResultList().isEmpty();
//    }
//
//    public List<CargoLiquidacion> findCargoLiquidacionList(CargoBus entity) {
//        CargoBus mergedEntity = this.getMergedEntity(entity);
//        List<CargoLiquidacion> cargoLiquidacionList = mergedEntity.getCargoLiquidacionList();
//        cargoLiquidacionList.size();
//        return cargoLiquidacionList;
//    }
//
//    public boolean isCargoBusIdBusEmpty(CargoBus entity) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
//        Root<CargoBus> cargoBus = cq.from(CargoBus.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cargoBus, entity), cb.isNotNull(cargoBus.get(CargoBus_.cargoBusIdBus)));
//        return em.createQuery(cq).getResultList().isEmpty();
//    }
//
//    public Bus findCargoBusIdBus(CargoBus entity) {
//        return this.getMergedEntity(entity).getCargoBusIdBus();
//    }
//
//    public boolean isCargoBusIdTipoEmpty(CargoBus entity) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
//        Root<CargoBus> cargoBus = cq.from(CargoBus.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cargoBus, entity), cb.isNotNull(cargoBus.get(CargoBus_.cargoBusIdTipo)));
//        return em.createQuery(cq).getResultList().isEmpty();
//    }
//
//    public TipoCargo findCargoBusIdTipo(CargoBus entity) {
//        return this.getMergedEntity(entity).getCargoBusIdTipo();
//    }
    
}
