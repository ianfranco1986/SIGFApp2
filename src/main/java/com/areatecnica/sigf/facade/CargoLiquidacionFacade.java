/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.CargoLiquidacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.CargoLiquidacion_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.CargoBus;
import com.areatecnica.sigf.entities.LiquidacionEmpresa;

/**
 *
 * @author ianfr
 */
@Stateless
public class CargoLiquidacionFacade extends AbstractFacade<CargoLiquidacion> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CargoLiquidacionFacade() {
        super(CargoLiquidacion.class);
    }

    public boolean isCargoLiquidacionIdCargoEmpty(CargoLiquidacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CargoLiquidacion> cargoLiquidacion = cq.from(CargoLiquidacion.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cargoLiquidacion, entity), cb.isNotNull(cargoLiquidacion.get(CargoLiquidacion_.cargoLiquidacionIdCargo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public CargoBus findCargoLiquidacionIdCargo(CargoLiquidacion entity) {
        return this.getMergedEntity(entity).getCargoLiquidacionIdCargo();
    }

    public boolean isCargoLiquidacionIdLiquidacionEmpty(CargoLiquidacion entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CargoLiquidacion> cargoLiquidacion = cq.from(CargoLiquidacion.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cargoLiquidacion, entity), cb.isNotNull(cargoLiquidacion.get(CargoLiquidacion_.cargoLiquidacionIdLiquidacion)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public LiquidacionEmpresa findCargoLiquidacionIdLiquidacion(CargoLiquidacion entity) {
        return this.getMergedEntity(entity).getCargoLiquidacionIdLiquidacion();
    }
    
}
