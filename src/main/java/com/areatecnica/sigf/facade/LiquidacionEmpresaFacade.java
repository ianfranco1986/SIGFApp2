/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.facade;

import com.areatecnica.sigf.entities.LiquidacionEmpresa;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import com.areatecnica.sigf.entities.LiquidacionEmpresa_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.areatecnica.sigf.entities.CargoLiquidacion;
import com.areatecnica.sigf.entities.Empresa;
import com.areatecnica.sigf.entities.AbonoLiquidacion;
import java.util.List;

/**
 *
 * @author ianfr
 */
@Stateless
public class LiquidacionEmpresaFacade extends AbstractFacade<LiquidacionEmpresa> {

    @PersistenceContext(unitName = "com.areatecnica_SIGFapp_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LiquidacionEmpresaFacade() {
        super(LiquidacionEmpresa.class);
    }

    public boolean isCargoLiquidacionListEmpty(LiquidacionEmpresa entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<LiquidacionEmpresa> liquidacionEmpresa = cq.from(LiquidacionEmpresa.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(liquidacionEmpresa, entity), cb.isNotEmpty(liquidacionEmpresa.get(LiquidacionEmpresa_.cargoLiquidacionList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<CargoLiquidacion> findCargoLiquidacionList(LiquidacionEmpresa entity) {
        LiquidacionEmpresa mergedEntity = this.getMergedEntity(entity);
        List<CargoLiquidacion> cargoLiquidacionList = mergedEntity.getCargoLiquidacionList();
        cargoLiquidacionList.size();
        return cargoLiquidacionList;
    }

    public boolean isLiquidacionEmpresaIdEmpresaEmpty(LiquidacionEmpresa entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<LiquidacionEmpresa> liquidacionEmpresa = cq.from(LiquidacionEmpresa.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(liquidacionEmpresa, entity), cb.isNotNull(liquidacionEmpresa.get(LiquidacionEmpresa_.liquidacionEmpresaIdEmpresa)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Empresa findLiquidacionEmpresaIdEmpresa(LiquidacionEmpresa entity) {
        return this.getMergedEntity(entity).getLiquidacionEmpresaIdEmpresa();
    }

    public boolean isAbonoLiquidacionListEmpty(LiquidacionEmpresa entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<LiquidacionEmpresa> liquidacionEmpresa = cq.from(LiquidacionEmpresa.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(liquidacionEmpresa, entity), cb.isNotEmpty(liquidacionEmpresa.get(LiquidacionEmpresa_.abonoLiquidacionList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<AbonoLiquidacion> findAbonoLiquidacionList(LiquidacionEmpresa entity) {
        LiquidacionEmpresa mergedEntity = this.getMergedEntity(entity);
        List<AbonoLiquidacion> abonoLiquidacionList = mergedEntity.getAbonoLiquidacionList();
        abonoLiquidacionList.size();
        return abonoLiquidacionList;
    }
    
}
